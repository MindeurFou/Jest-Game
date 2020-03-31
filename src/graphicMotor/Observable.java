package graphicMotor;

import java.util.HashSet;
import java.util.Iterator;

public class Observable {

	
	private HashSet<Observer> observers;
	private boolean hasChanged;
	
	public Observable() {
		this.observers = new HashSet<Observer>();
		this.hasChanged = false;
	}
	
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void deleteObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public void notifyObervers() {
		if (this.hasChanged) {
			Iterator<Observer> it = this.observers.iterator();

			while (it.hasNext()) {
				it.next().update(this);
			}
		}
	}
	
	public void setChanged() {
		this.hasChanged = true;
	}
	
	public void clearChanged() {
		this.hasChanged = false;
	}
}
