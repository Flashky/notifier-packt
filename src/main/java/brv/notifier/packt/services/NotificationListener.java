package brv.notifier.packt.services;

public interface NotificationListener<T> {

	void notify(T data);
}
