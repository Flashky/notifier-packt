package brv.notifier.packt.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.PacktOffer;
import brv.notifier.packt.properties.PacktProperties;
import brv.notifier.packt.util.MessageHelper;

@Service
public class PacktCheckTask {

	@Autowired
	private PacktProperties packtProperties;

	@Autowired
	@Qualifier("messages-app")
	private MessageHelper  messageHelper;

	private static final Logger LOGGER = LogManager.getLogger(PacktCheckTask.class.getName());
	
	/** 
	 * Previous found offer.
	 * <p>
	 * Used to prevent duplicated notifications, any offer should be notified just once.
	 * </p>
	 */
	private PacktOffer previousOffer;
	
	private List<NotificationListener> listeners = new LinkedList<>();
	
	//@Scheduled(cron = "${service.cron}")
	@Scheduled(fixedRate = 5000)
	public void checkPacktDailyOffer() {

		Document htmlPage;
		
		try {
			
			htmlPage = Jsoup.connect(packtProperties.getOfferUrl()).get();
			PacktOffer offer = createPacktOffer(htmlPage);
			
			if(!offer.equals(previousOffer)) {
				notifyListeners(offer);
				previousOffer = offer;
			}
			
			
		} catch (IOException e) {
			// TODO in a future, add three retries.
			// TODO at the third error, print the cause of the error.
			LOGGER.warn(messageHelper.getMessage("warn.could-not-retrieve"));
		}
	}
	
	/**
	 * Creates a PacktOffer object using the retrieved html data.
	 * @param htmlPage - a Document object containing the webpage to retrieve the data from.
	 * @return <code>PacktOffer</code> - the offer details.
	 */
	private PacktOffer createPacktOffer(Document htmlPage) {
		
		PacktOffer offer = new PacktOffer();
		
		// Book title
		Element htmlTitle = htmlPage.selectFirst(packtProperties.getCssSelector().getTitle());
		
		if(htmlTitle != null) {
			offer.setTitle(htmlTitle.text());
		}
		
		// Book details page
		Element htmlBookUrl = htmlPage.selectFirst(packtProperties.getCssSelector().getBookUrl());
		
		if(htmlBookUrl != null) {
			offer.setBookUrl(htmlBookUrl.absUrl("href"));
		}
		
		// Book cover image
		Element htmlImage = htmlPage.selectFirst(packtProperties.getCssSelector().getImageUrl());
		
		if(htmlImage != null) {
			offer.setImageUrl(htmlImage.absUrl("src"));
		}
		
		// Offer url
		offer.setOfferUrl(packtProperties.getOfferUrl());
		
		return offer;
	}
	
	/**
	 * Notifies every listener that a new Packt offer has been detected.
	 * @param offer - the data to be notified.
	 */
	private void notifyListeners(PacktOffer offer) {
		
		Object[] messageParameters = new Object[] { offer.getTitle() };	
		LOGGER.info(messageHelper.getMessage("info.offer-detected", messageParameters));
		for(NotificationListener listener : listeners) {
			listener.notify(offer);
		}
	}
	
	/**
	 * Adds a listener to be notified when the task detects an offer.
	 * <p>
	 * A <code>null</code> listener will be ignored if added.
	 * </p>
	 * @param listener - the listener to register.
	 */
	public void addNotificationListener(NotificationListener listener) {
		if(listener != null)
			this.listeners.add(listener);
	}

	/**
	 * Removes a listener from the registered listeners.
	 * @param listener - the listener to remove.
	 */
	public void removeNotificationListener(NotificationListener listener) {

			listeners.remove(listener);
	}
} 
