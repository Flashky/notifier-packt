package brv.notifier.packt.services.offers.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import brv.notifier.packt.model.offers.JsonSummary;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapper;

public class PacktFreeOfferMapperTest {

	private PacktFreeOfferMapper mapper = Mappers.getMapper( PacktFreeOfferMapper.class );
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


		PacktFreeOffer offerMapStruct = mapper.jsonToModel(jsonSummary);
		
		assertEquals("https://my-image-url", offerMapStruct.getCoverImage());
		assertEquals("https://www.packtpub.com/my-shop-url", offerMapStruct.getShopUrl());
		assertEquals("https://subscription.packtpub.com/my-read-url", offerMapStruct.getReadUrl());
		assertEquals("oneliner", offerMapStruct.getOneLiner());
		assertEquals("about", offerMapStruct.getAbout());
		assertEquals("learn", offerMapStruct.getLearn());
		assertEquals("features", offerMapStruct.getFeatures());
		
		
	}
	
	@Test
	public void testNull() {
		
		assertNull(mapper.jsonToModel(null));
	}

}
