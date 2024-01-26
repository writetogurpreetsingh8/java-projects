package strategy.client;

import strategy.behaviors.fly.FlyBehavior;
import strategy.behaviors.quack.QuackBehavior;

public class MalleredDuck extends Duck {

	public MalleredDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		super(flyBehavior, quackBehavior);
	}
	
	@Override
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		super.setFlyBehavior(flyBehavior);
	}
	
	@Override
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		super.setQuackBehavior(quackBehavior);
	}

	@Override
	public void display() {
		System.out.println("I am Mallered Duck!!");
		
	}
	
	

}
