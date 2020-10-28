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

		System.out.println("******DOG OBJECT*****");
		Dog dog = new Animal();
		dog.bark();

		System.out.println("******LION OBJECT*****");
		Lion lion = new Animal();
		lion.roar();

		System.out.println("**********");

	}

}

interface Dog {
	void bark();
}

interface Lion {
	void roar();
}
