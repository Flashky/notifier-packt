package brv.notifier.packt.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import brv.notifier.packt.model.offers.JsonSummary;

@FeignClient(name = "${api.products.name}", url = "${api.products.url}")
public interface ProductsRestClient {

	@GetMapping("/{productId}/summary")
	JsonSummary getProductSummary(@PathVariable Long productId);
	
	@GetMapping("/{productId}/cover/smaller")
	byte[] getProductImage(@PathVariable Long productId);
}
