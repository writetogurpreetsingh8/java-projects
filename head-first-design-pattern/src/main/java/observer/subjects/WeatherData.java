package observer.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import observer.observers.Observer;

public class WeatherData implements Subject {

	private final List<Observer> observers;
	private float tempearature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		this.observers = new ArrayList<Observer>();
	}
	
	@Override
	public void registerObserver(Observer observer) {
		Objects.requireNonNull(observer);
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		Objects.requireNonNull(observer);
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : this.observers) {
			observer.update();
		}
	}

	public float getTempearature() {
		return tempearature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}
	
	public void setMeasurement(float tempearature,float humidity,float pressure) {
		this.humidity = humidity;
		this.pressure = pressure;
		this.tempearature = tempearature;
		this.measurementChanged();
	}
	
	private void measurementChanged() {
		this.notifyObservers();
	}
}
