
public class ArrayToolsTest 
{
	public static void main(String[]args)
	{
		char [] charArray = {'a','*','2','G'};
		System.out.println("Maximum value of charArray is " + ArrayTools.maximum(charArray));
		System.out.println("Minimum value of charArray is " + ArrayTools.minimum(charArray));
		System.out.println("Maximum location of charArray is " + ArrayTools.maximumAt(charArray));
		System.out.println("Minimum location of charArray is " + ArrayTools.minimumAt(charArray));
		int [] intArray = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Maximum value of intArray is " + ArrayTools.maximum(intArray));
		System.out.println("Minimum value of intArray is " + ArrayTools.minimum(intArray));
		System.out.println("Maximum location of intArray is " + ArrayTools.maximumAt(intArray));
		System.out.println("Minimum location of intArray is " + ArrayTools.minimumAt(intArray));
		System.out.println("Average value of intArray is " + ArrayTools.average(intArray));
		double [] doubleArray = {1.2,2.3,3.4,4.5,5.6,6.7,7.8,8.9,9.0,10.1};
		System.out.println("Maximum value of doubleArray is " + ArrayTools.maximum(doubleArray));
		System.out.println("Minimum value of doubleArray is " + ArrayTools.minimum(doubleArray));
		System.out.println("Maximum location of doubleArray is " + ArrayTools.maximumAt(doubleArray));
		System.out.println("Minimum location of doubleArray is " + ArrayTools.minimumAt(doubleArray));
		System.out.println("Average value of doubleArray is " + ArrayTools.average(doubleArray));
	}
}