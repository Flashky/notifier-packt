package brv.notifier.packt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.dto.PacktFreeOffer;
import brv.notifier.packt.enums.Url;
import brv.notifier.packt.model.json.JsonSummary;

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
		return Url.SHOP.path(shopUrl);
	}
	
	@Named("readUrl")
	protected String mapReadUrl(String path) {
		return Url.SUBSCRIBE.path(path);
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
