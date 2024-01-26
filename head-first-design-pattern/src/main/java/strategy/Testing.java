package strategy;

import strategy.behaviors.fly.FlyBehavior;
import strategy.behaviors.fly.FlyDucks;
import strategy.behaviors.fly.FlyRocketPower;
import strategy.behaviors.quack.Quack;
import strategy.behaviors.quack.QuackBehavior;
import strategy.client.Duck;
import strategy.client.MalleredDuck;

public class Testing {

	public static void main(String[] args) {
		
		FlyBehavior flybeh = new FlyDucks();
		QuackBehavior quackBeh = new Quack();
		Duck mallered = new MalleredDuck(flybeh, quackBeh);
		
		mallered.display();
		mallered.performFly();
		mallered.performQuack();
		
		flybeh = new FlyRocketPower();
		mallered.setFlyBehavior(flybeh);
		mallered.performFly();
		
		
	}

}
