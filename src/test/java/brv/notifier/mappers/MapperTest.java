package brv.notifier.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import brv.notifier.packt.mappers.Mapper;
import brv.notifier.packt.mappers.PacktFreeOfferMapper;
import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.json.JsonSummary;

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
		
		PacktFreeOffer offer = Mapper.jsonToModel(jsonSummary);
		
		// Test
		assertEquals("https://my-image-url", offer.getCoverImage());
		assertEquals("https://www.packtpub.com/packt/offers/free-learning", offer.getOfferUrl());
		assertEquals("https://www.packtpub.com/my-shop-url", offer.getShopUrl());
		assertEquals("https://subscription.packtpub.com/my-read-url", offer.getReadUrl());
		assertEquals("oneliner", offer.getOneLiner());
		assertEquals("about", offer.getAbout());
		assertEquals("learn", offer.getLearn());
		assertEquals("features", offer.getFeatures());
		

		PacktFreeOfferMapper mapper = Mappers.getMapper( PacktFreeOfferMapper.class );

		PacktFreeOffer offerMapStruct = mapper.jsonToModel(jsonSummary);
		
		assertEquals("https://my-image-url", offerMapStruct.getCoverImage());
		assertEquals("https://www.packtpub.com/packt/offers/free-learning", offerMapStruct.getOfferUrl());
		assertEquals("https://www.packtpub.com/my-shop-url", offerMapStruct.getShopUrl());
		assertEquals("https://subscription.packtpub.com/my-read-url", offerMapStruct.getReadUrl());
		assertEquals("oneliner", offerMapStruct.getOneLiner());
		assertEquals("about", offerMapStruct.getAbout());
		assertEquals("learn", offerMapStruct.getLearn());
		assertEquals("features", offerMapStruct.getFeatures());
		
		
	}

}
