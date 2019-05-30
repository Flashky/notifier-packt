package brv.notifier.packt.repositories;

import java.util.Optional;

import brv.notifier.packt.model.json.JsonSummary;

public interface SummaryRepository {

	/**
	 * Obtains the summary of a product.
	 * @param productId The product to find.
	 * @return An object containing the summary of the product.
	 */
	Optional<JsonSummary> findById(Long productId);
}
