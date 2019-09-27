package brv.test.util.dummies;

import brv.notifier.packt.restclients.model.JsonSummary;

public class DummyJsonSummary {

	private DummyJsonSummary() {}
	
	public static JsonSummary get() {
	
		JsonSummary summary = new JsonSummary();
		
		summary.setAbout("about");
		summary.setCoverImage("http://image.png");
		summary.setFeatures("features");
		summary.setLearn("learn");
		summary.setOneLiner("oneLiner");
		summary.setProductId(5L);
		summary.setReadUrl("http://read-url");
		summary.setShopUrl("http://shop-url");
		summary.setTitle("title");
		summary.setType("type");
		
		return summary;
	}
}
