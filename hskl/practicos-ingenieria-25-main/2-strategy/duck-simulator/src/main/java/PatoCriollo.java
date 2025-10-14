public class PatoCriollo extends Duck {

	public PatoCriollo() {
		quackBehavior = new Quack();
		flyBehavior = new FlyWithWings();
	}

	public void display() {
		System.out.println("I'm a pato criollo");
	}

}
