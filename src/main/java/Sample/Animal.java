package Sample;

public class Animal implements Dog, Lion {

	public void roar() {
		System.out.println("Lion can roar");

	}

	public void bark() {
		System.out.println("Dog can bark");

	}

	public static void main(String[] args) {
		Animal obj = new Animal();
		obj.bark();
		obj.roar();

		Dog dog = new Animal();
		dog.bark();

		Lion lion = new Animal();
		lion.roar();

	}

}

interface Dog {
	void bark();
}

interface Lion {
	void roar();
}
