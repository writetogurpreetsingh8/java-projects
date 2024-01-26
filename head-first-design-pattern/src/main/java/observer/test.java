package observer;

import observer.observers.CurrentConditionDisplay;
import observer.observers.ForecastDisplay;
import observer.observers.Observer;
import observer.subjects.WeatherData;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherData wd = new WeatherData();
		CurrentConditionDisplay currentConditionObserver = new CurrentConditionDisplay(wd);
		ForecastDisplay forecastDisplayObserver = new ForecastDisplay(wd);
		
		wd.setMeasurement(12.0f, 30.0f, 5.0f);
		wd.removeObserver(currentConditionObserver);
		wd.setMeasurement(12.0f, 30.0f, 5.0f);
		
	}

}
