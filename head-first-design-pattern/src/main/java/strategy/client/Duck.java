package strategy.client;

import java.util.Objects;

import strategy.behaviors.fly.FlyBehavior;
import strategy.behaviors.quack.QuackBehavior;

public abstract class Duck {
	
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
	
	
	public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		super();
		this.flyBehavior = flyBehavior;
		this.quackBehavior = quackBehavior;
	}

	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

	public void swim() {
		System.out.println("I can swim() ");
	}
	
	public void performQuack() {
		if(Objects.nonNull(this.quackBehavior)) {
			this.quackBehavior.quack();
		}else {
			System.out.println("QuackBehavior isn't set ");
		}
	}
	
	public void performFly() {
		if(Objects.nonNull(this.flyBehavior)) {
			this.flyBehavior.fly();
		}else {
			System.out.println("FlyBehavior isn't set ");
		}
	}
	
	public abstract void display();

}
