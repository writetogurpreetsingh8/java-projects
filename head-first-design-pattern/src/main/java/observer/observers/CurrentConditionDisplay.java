package observer.observers;

import observer.DisplayElement;
import observer.subjects.WeatherData;

public class CurrentConditionDisplay implements Observer,DisplayElement {

	private WeatherData watherData;
	private float temperature;
	
	public CurrentConditionDisplay(WeatherData watherData) {
		this.watherData = watherData;
		this.watherData.registerObserver(this);
	}
	
	@Override
	public void update() {
		this.temperature = this.watherData.getTempearature();
		this.display();
	}

	@Override
	public void display() {
		System.out.println("current condition of temperature is "+this.temperature);
	}

}
