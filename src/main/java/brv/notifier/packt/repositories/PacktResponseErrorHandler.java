package brv.notifier.packt.repositories;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class PacktResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger LOGGER = LogManager.getLogger(PacktResponseErrorHandler.class.getName());
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().isError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		switch(response.getStatusCode().series()) {
		
			case SERVER_ERROR:
				LOGGER.error("Server side error when calling to the API "+ getReason(response.getStatusCode()));
				break;
				
			case CLIENT_ERROR:	
				LOGGER.error("Client side error when calling to the API "+getReason(response.getStatusCode()));
				break;
				
			default:
				break;
		}
		
	}
	
	private String getReason(HttpStatus httpStatus) {
		return new StringBuilder("(")
				.append(httpStatus.value())
				.append(" - ")
				.append(httpStatus.getReasonPhrase())
				.append(")")
				.toString();
	}

}
