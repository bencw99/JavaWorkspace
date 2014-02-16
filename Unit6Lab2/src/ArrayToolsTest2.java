
public class ArrayToolsTest2 
{
	public static void main(String[]args)
	{
		char [] charArray = {'a','*','2','G'};
		char [] charArray2 = {'a','*','2','G'};
		if(ArrayTools.equals(charArray,charArray2))
		{
			System.out.println("The two char arrays are equal");
		}
		else
		{
			System.out.println("The two char arrays are not equal");
		}
		System.out.println("The location of * in the char array is " + ArrayTools.find(charArray, '*'));
		ArrayTools.sort(charArray);
		System.out.println("A sorted version of the char array is ");
		for(int i = 0; i < charArray.length; i++)
		{
			System.out.print(charArray[i] + ", ");
		}
		System.out.println();
		int [] intArray = {56,79,31,84,57,96,17,42,12,101};
		int [] intArray2 =  {56,78,31,84,57,96,17,42,12,101};
		if(ArrayTools.equals(intArray, intArray2))
		{
			System.out.println("The two int arrays are equal");
		}
		else
		{
			System.out.println("The two int arrays are not equal");
		}
		System.out.println("The location of 31 in the int array is " + ArrayTools.find(intArray, 31));
		ArrayTools.sort(intArray);
		System.out.println("A sorted version of the int array is ");
		for(int i = 0; i < intArray.length; i++)
		{
			System.out.print(intArray[i] + ", ");
		}
		System.out.println();
		double [] doubleArray = {72.4,235.4,853.35,365.6,456.3,253.7,674.2};
		double [] doubleArray2 = {72.4,235.4,853.35,365.6,456.3,253.7,674.2};
		if(ArrayTools.equals(doubleArray, doubleArray2))
		{
			System.out.println("The two double arrays are equal");
		}
		else
		{
			System.out.println("The two double arrays are not equal");
		}
		System.out.println("The location of 456.3 in the double array is " + ArrayTools.find(doubleArray, 456.3));
		ArrayTools.sort(doubleArray);
		System.out.println("A sorted version of the double array is ");
		for(int i = 0; i < doubleArray.length; i++)
		{
			System.out.print(doubleArray[i] + ", ");
		}
		System.out.println();
	}
}
