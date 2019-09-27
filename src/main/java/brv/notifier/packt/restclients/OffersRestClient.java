package brv.notifier.packt.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import brv.notifier.packt.restclients.model.JsonOffers;

@FeignClient(name = "${api.offers.name}", url = "${api.offers.url}")
public interface OffersRestClient {

	@GetMapping
	JsonOffers getOffers(@RequestParam String dateFrom, @RequestParam String dateTo);
}
