package brv.test.concepts;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentsBuilderTest {

	private static final Long PRODUCT_ID = 5L;
	@Test
	public void testPathSegments() {
		
		String url = UriComponentsBuilder.fromHttpUrl("http://base.api.com")
		.pathSegment("{productId}")
		.pathSegment("cover")
		.pathSegment("smaller").buildAndExpand(PRODUCT_ID).toString();
		
		assertEquals("http://base.api.com/5/cover/smaller", url);
	}

}
