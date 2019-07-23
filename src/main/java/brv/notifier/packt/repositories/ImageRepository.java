package brv.notifier.packt.repositories;

import java.util.Optional;

public interface ImageRepository {

	Optional<byte[]> getFromUrl(String imageUrl);
}
