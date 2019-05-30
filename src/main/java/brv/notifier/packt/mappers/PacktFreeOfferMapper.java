package brv.notifier.packt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.json.JsonSummary;

@Mapper
public abstract class PacktFreeOfferMapper {

	private static final String ENUM_PACKAGE = "brv.notifier.packt.model.enums.";
	
	@Mapping(target = "offerUrl", expression = "java( "+ ENUM_PACKAGE + "Url.SHOP.path("+ ENUM_PACKAGE + "WebPath.FREE_OFFER.getPath()) )")
	@Mapping(target = "shopUrl", expression = "java( "+ ENUM_PACKAGE + "Url.SHOP.path(offerSummary.getShopUrl()))")
	@Mapping(target = "readUrl", expression = "java( "+ ENUM_PACKAGE + "Url.SUBSCRIBE.path(offerSummary.getReadUrl()) )")
	public abstract PacktFreeOffer jsonToModel(JsonSummary offerSummary);
	
}
