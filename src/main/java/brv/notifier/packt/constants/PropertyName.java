package brv.notifier.packt.constants;

public final class PropertyName {

	private PropertyName() {}
	
	private static final String ENABLED = ".enabled";
	
	public static final String EMAIL = "app.notifications.email";
	public static final String TWITTER = "app.notifications.twitter";
	public static final String EMAIL_ENABLED = EMAIL + ENABLED;
	public static final String TWITTER_ENABLED = TWITTER + ENABLED;
	
}
