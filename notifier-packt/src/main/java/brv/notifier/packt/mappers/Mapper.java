package brv.notifier.packt.mappers;

import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.PacktFreeOfferBuilder;
import brv.notifier.packt.model.enums.Url;
import brv.notifier.packt.model.enums.WebPath;
import brv.notifier.packt.model.json.JsonSummary;

public final class Mapper {

	private Mapper() {}
	
	/**
	 * Maps the input JsonSummary into a PacktFreeOffer object.
	 * @param offerSummary the json object to map.
	 * @return A PacktFreeOffer object.
	 */
	public static PacktFreeOffer jsonToModel(JsonSummary offerSummary) {
		
		PacktFreeOfferBuilder builder = new PacktFreeOfferBuilder(offerSummary.getTitle())
				// Adds the retrieved image and encodes it to prevent special characters..
				.withCoverImage(UriComponentsBuilder.fromHttpUrl(offerSummary.getCoverImage())
						.encode()
						.build()
						.toString())
				.withOfferUrl(Url.SHOP.path(WebPath.FREE_OFFER.getPath()))
				.withShopUrl(Url.SHOP.path(offerSummary.getShopUrl()))
				.withReadUrl(Url.SUBSCRIBE.path(offerSummary.getReadUrl()))
				.withOneLiner(offerSummary.getOneLiner())
				.withAbout(offerSummary.getAbout())
				.withLearn(offerSummary.getLearn())
				.withFeatures(offerSummary.getFeatures());
		
		return builder.build();
	}
}
