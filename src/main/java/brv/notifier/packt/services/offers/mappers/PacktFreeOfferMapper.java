package brv.notifier.packt.services.offers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.enums.Host;
import brv.notifier.packt.model.offers.JsonSummary;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;

@Mapper(componentModel = "spring")
public abstract class PacktFreeOfferMapper {

	/**
	 * Maps the input JsonSummary into a PacktFreeOffer object.
	 * @param offerSummary the json object to map.
	 * @return A PacktFreeOffer object.
	 */
	@Mapping(target = "shopUrl", 	qualifiedByName = "shopUrl")
	@Mapping(target = "readUrl", 	qualifiedByName = "readUrl")
	@Mapping(target = "coverImage", qualifiedByName = "coverImage")
	public abstract PacktFreeOffer jsonToModel(JsonSummary offerSummary);
	
	@Named("shopUrl")
	protected String mapShopUrl(String shopUrl) {
		return Host.SHOP.path(shopUrl);
	}
	
	@Named("readUrl")
	protected String mapReadUrl(String path) {
		return Host.SUBSCRIBE.path(path);
	}
	
	
	@Named("coverImage")
	protected String mapCoverImage(String coverImage) {
	
		// Cover image URI is encoded to protect against whitespaces and other special characters
		return UriComponentsBuilder.fromHttpUrl(coverImage)
				.build()
				.encode()
				.toString();
	
	}
	

	
}
