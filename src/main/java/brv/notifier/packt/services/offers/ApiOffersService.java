package brv.notifier.packt.services.offers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.offers.JsonOffer;
import brv.notifier.packt.model.offers.JsonSummary;
import brv.notifier.packt.repositories.ImageRepository;
import brv.notifier.packt.repositories.OffersRepository;
import brv.notifier.packt.repositories.SummaryRepository;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapper;

/**
 * Implementation of CheckoutService which obtains the Packt offer data through an API.
 * @author Flashky
 *
 */
@Service
public class ApiOffersService implements OffersService {
	
	/* README */
	
	/* 
	 * Idea para migrar esto a un microservicio aparte.
	 * 
	 * Habría dos mappings GET:
	 * 
	 * MAPPING 1: 
	 * GET /offers?date=yyyy-mm-dd
	 * Devuelve la oferta de la fecha especificada.
	 * 
	 * date: mandatory
	 * 
	 * MAPPING 2:
	 * GET /offers?startDate=yyyy-mm-dd
	 * GET /offers?startDate=yyyy-mm-dd&endDate=yyyy-mm-dd
	 * Devuelve las ofertas desde la fecha de inicio en adelante sin límite o hasta la fecha final:
	 * 
	 * startDate: mandatory
	 * endDate: optional
	 * 
	 * --- 
	 * 
	 * El diseño del servicio iría nuevamente en tres capas:
	 * - Capa controladora, para validar los campos de entrada y mapear los resultados finales.
	 * - Capa servicio: contendrá el servicio principal para orquestar las llamadas a las dos APIs de Packt y agregar los datos en el DTO de salida.
	 * - Capa repositorio: contendrá las mismas clases de repositorio que se han generado en este proyecto.
	 * 
	 * Paquetes a migrar y valor renombrado:
	 * (ninguno, paquete base)						-> brv.api.offers
	 * (ninguno, nuevo paquete controlador)			-> brv.api.offers.controllers
	 * brv.notifier.packt.services.offers.dto		-> brv.api.offers.controllers.dto
	 * brv.notifier.packt.services.offers			-> brv.api.offers.services
	 * brv.notifier.packt.services.offers.mappers 	-> brv.api.offers.services.mappers
	 * brv.notifier.packt.repositories 				-> brv.api.offers.repositories
	 * brv.notifier.packt.model.offers				-> brv.api.offers.repositories.models
	 */
	@Autowired
	private OffersRepository offersDao;
	
	@Autowired
	private SummaryRepository summaryDao;
	
	@Autowired
	private ImageRepository imageDao;
	
	@Autowired
	private PacktFreeOfferMapper mapper;
	
	@Override
	public PacktFreeOffer getPacktOffer(LocalDate date) {

		PacktFreeOffer result = null;
		
		// Obtain the productId of the deal of the day
		Optional<JsonOffer> offer = offersDao.getOffer(date);
		
		if(offer.isPresent()) {			
			result = getSummary(offer.get());
		}

		return result;
	}
	
	private PacktFreeOffer getSummary(JsonOffer offer) {
		
		PacktFreeOffer result = null;
		
		// Retrieve the data for the ebook offer
		Optional<JsonSummary> summary = summaryDao.findById(offer.getProductId());
		
		if(summary.isPresent()) {
			JsonSummary jsonSummary = summary.get();
			result = mapper.jsonToModel(jsonSummary);

			// Retrieve cover image as bytes if possible.
			Optional<byte[]> image = imageDao.getFromUrl(result.getCoverImage());
			
			if(image.isPresent()) {
				result.setCoverImageBytes(image.get());
			}
		}
		
		return result;
				
	}



}
