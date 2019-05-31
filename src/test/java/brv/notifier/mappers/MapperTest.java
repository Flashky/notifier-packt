package brv.notifier.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import brv.notifier.packt.model.offers.JsonSummary;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapper;

public class MapperTest {

	
	@Test
	public void test() {

		JsonSummary jsonSummary = new JsonSummary();
		
		jsonSummary.setCoverImage("https://my-image-url");
		jsonSummary.setShopUrl("my-shop-url");
		jsonSummary.setReadUrl("my-read-url");
		jsonSummary.setOneLiner("oneliner");
		jsonSummary.setAbout("about");
		jsonSummary.setLearn("learn");
		jsonSummary.setFeatures("features");

		PacktFreeOfferMapper mapper = Mappers.getMapper( PacktFreeOfferMapper.class );

		PacktFreeOffer offerMapStruct = mapper.jsonToModel(jsonSummary);
		
		assertEquals("https://my-image-url", offerMapStruct.getCoverImage());
		//assertEquals("https://www.packtpub.com/packt/offers/free-learning", offerMapStruct.getOfferUrl());
		assertEquals("https://www.packtpub.com/my-shop-url", offerMapStruct.getShopUrl());
		assertEquals("https://subscription.packtpub.com/my-read-url", offerMapStruct.getReadUrl());
		assertEquals("oneliner", offerMapStruct.getOneLiner());
		assertEquals("about", offerMapStruct.getAbout());
		assertEquals("learn", offerMapStruct.getLearn());
		assertEquals("features", offerMapStruct.getFeatures());
		
		
	}

}
