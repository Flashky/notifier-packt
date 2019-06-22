package brv.notifier.packt.constants;

public final class PlaceholderValue {

	public static final String CRON_EXPRESSION	= "${service.cron}";
	public static final String LANG 	= "${app.lang:en}";
	
	private PlaceholderValue() {}

}
