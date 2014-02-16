
public class CatTester 
{
	public static void main(String[]args)
	{
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		cat1.setAge(5);
		cat1.setBreed("Bengal");
		cat1.setSex('m');
		cat1.setWeight(40);
		cat1.setName("Bob");
		cat2.setAge(5);
		cat2.setBreed("Bengal");
		cat2.setSex('m');
		cat2.setWeight(40);
		cat2.setName("Bob");
		System.out.println("Name of cat 1 is " + cat1.getName());
		System.out.println("Weight of cat 1 is " + cat1.getWeight());
		System.out.println("Age of cat 1 is " + cat1.getAge());
		System.out.println("Sex of cat 1 is " + cat1.getSex());
		System.out.println("Breed of cat 1 is " + cat1.getBreed());
		System.out.println("Name of cat 2 is " + cat2.getName());
		System.out.println("Weight of cat 2 is " + cat2.getWeight());
		System.out.println("Age of cat 2 is " + cat2.getAge());
		System.out.println("Sex of cat 2 is " + cat2.getSex());
		System.out.println("Breed of cat 2 is " + cat2.getBreed());
		cat1.setName("Fred");
		cat1.setAge(6);
		cat2.setAge(4);
		cat2.setWeight(30);
		System.out.println();
		System.out.println("Name of cat 1 is " + cat1.getName());
		System.out.println("Weight of cat 1 is " + cat1.getWeight());
		System.out.println("Age of cat 1 is " + cat1.getAge());
		System.out.println("Sex of cat 1 is " + cat1.getSex());
		System.out.println("Breed of cat 1 is " + cat1.getBreed());
		System.out.println("Name of cat 2 is " + cat2.getName());
		System.out.println("Weight of cat 2 is " + cat2.getWeight());
		System.out.println("Age of cat 2 is " + cat2.getAge());
		System.out.println("Sex of cat 2 is " + cat2.getSex());
		System.out.println("Breed of cat 2 is " + cat2.getBreed());
		System.out.println(cat2.meow());
	}
}
