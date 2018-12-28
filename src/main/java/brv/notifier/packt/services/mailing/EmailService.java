package brv.notifier.packt.services.mailing;

public interface EmailService {

	/** 
	 * Sends an email using the input data.
	 * @param emailData
	 */
	void send(EmailData emailData);
}
