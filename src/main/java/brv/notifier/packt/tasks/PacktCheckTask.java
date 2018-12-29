package brv.notifier.packt.tasks;

import java.util.LinkedList;
import java.util.List;

import brv.notifier.packt.services.NotificationListener;

/**
 * Abstract templated class which defines the contract to execute by tasks.
 * <p>Uses observer pattern, providing the ability 
 * to {@link #addNotificationListener(NotificationListener) add} and {@link #removeNotificationListener(NotificationListener) remove} 
 * listeners which can be later notified by {@link #notifyListeners(Object)} method.</p>
 * <p>
 * It is the subclass responsability to call to {@link #notifyListeners(Object)} method.
 * </p>
 * @author Flashk
 *
 * @param <T>
 */
public abstract class PacktCheckTask<T> {

	private List<NotificationListener<T>> listeners = new LinkedList<>();
	
	public abstract void check();
	
	/**
	 * Notifies every listener.
	 * @param offer - the data to be notified.
	 */

	protected void notifyListeners(T offer) {
		
		for(NotificationListener<T> listener : listeners) {
			listener.notify(offer);
		}
	}
	
	/**
	 * Adds a listener to be notified when the task detects an offer.
	 * <p>
	 * A <code>null</code> listener will be ignored if added.
	 * </p>
	 * @param listener - the listener to register.
	 */
	public void addNotificationListener(NotificationListener<T> listener) {
		if(listener != null)
			this.listeners.add(listener);
	}

	/**
	 * Removes a listener from the registered listeners.
	 * @param listener - the listener to remove.
	 */
	public void removeNotificationListener(NotificationListener<T> listener) {

		listeners.remove(listener);
	}
	
}
