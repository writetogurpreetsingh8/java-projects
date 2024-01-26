package observer.observers;

import observer.DisplayElement;
import observer.subjects.WeatherData;

public class ForecastDisplay implements Observer,DisplayElement {

	private WeatherData watherData;
	private float temperature;
	private float pressure;
	private float humidity;
	
	public ForecastDisplay(WeatherData watherData) {
		this.watherData = watherData;
		this.watherData.registerObserver(this);
	}
	
	@Override
	public void update() {
		this.temperature = this.watherData.getTempearature();
		this.pressure = this.watherData.getPressure();
		this.humidity = this.watherData.getHumidity();
		this.display();
	}

	@Override
	public void display() {
		System.out.println("Forcasting Display is  Tempareture:- "+this.temperature+" Pressure:- "+this.pressure+" and Humidity:- "+this.humidity);
	}

}
