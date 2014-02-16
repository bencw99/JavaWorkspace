
public class ArrayTools 
{
	public static char minimum(char[]array)
	{
		char minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) < (int)(minimum))
			{
				minimum = array[i];
			}
		}
		return minimum;
	}
	public static int minimum(int[]array)
	{
		int minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((array[i]) < (minimum))
			{
				minimum = array[i];
			}
		}
		return minimum;
	}
	public static double minimum(double[]array)
	{
		double minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((array[i]) < (minimum))
			{
				minimum = array[i];
			}
		}
		return minimum;
	}
	public static char maximum(char[]array)
	{
		char maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) > (int)(maximum))
			{
				maximum = array[i];
			}
		}
		return maximum;
	}
	public static int maximum(int[]array)
	{
		int maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((array[i]) > (maximum))
			{
				maximum = array[i];
			}
		}
		return maximum;
	}
	public static double maximum(double[]array)
	{
		double maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((array[i]) > (maximum))
			{
				maximum = array[i];
			}
		}
		return maximum;
	}
	public static int maximumAt(char[]array)
	{
		int maxVal = 0;
		char maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) > (int)(maximum))
			{
				maximum = array[i];
				maxVal = i;
			}
		}
		return maxVal;
	}
	public static int minimumAt(char[]array)
	{
		int minVal = 0;
		char minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) < (int)(minimum))
			{
				minimum = array[i];
				minVal = i;
			}
		}
		return minVal;
	}
	public static int maximumAt(int[]array)
	{
		int maxVal = 0;
		int maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) > (int)(maximum))
			{
				maximum = array[i];
				maxVal = i;
			}
		}
		return maxVal;
	}
	public static int minimumAt(int[]array)
	{
		int minVal = 0;
		int minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) < (int)(minimum))
			{
				minimum = array[i];
				minVal = i;
			}
		}
		return minVal;
	}
	public static int maximumAt(double[]array)
	{
		int maxVal = 0;
		double maximum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) > (int)(maximum))
			{
				maximum = array[i];
				maxVal = i;
			}
		}
		return maxVal;
	}
	public static int minimumAt(double[]array)
	{
		int minVal = 0;
		double minimum = array[0];
		for(int i = 0; i < array.length; i++)
		{
			if((int)(array[i]) < (int)(minimum))
			{
				minimum = array[i];
				minVal = i;
			}
		}
		return minVal;
	}
	public static double average( int array[])
	{
		double sum = 0;
		for(int i = 0; i < array.length; i++)
		{
			sum += array[i];
		}
		return sum/array.length;
	}
	public static double average( double array[])
	{
		double sum = 0;
		for(int i = 0; i < array.length; i++)
		{
			sum += array[i];
		}
		return sum/array.length;
	}
	public static boolean equals(char a1[], char a2[])
	{
		boolean a = true;
		if(a1.length == a2.length)
		{
			for(int i = 0; i < a1.length; i++)
			{
				if(a1[i] != a2[i])
				{
					a = false;
				}
			}
		}
		else
		{
			a = false;
		}
		return a;
	}
	public static boolean equals(int a1[], int a2[])
	{
		boolean a = true;
		if(a1.length == a2.length)
		{
			for(int i = 0; i < a1.length; i++)
			{
				if(a1[i] != a2[i])
				{
					a = false;
				}
			}
		}
		else
		{
			a = false;
		}
		return a;
	}
	public static boolean equals(double a1[], double a2[])
	{
		boolean a = true;
		if(a1.length == a2.length)
		{
			for(int i = 0; i < a1.length; i++)
			{
				if(a1[i] != a2[i])
				{
					a = false;
				}
			}
		}
		else
		{
			a = false;
		}
		return a;
	}
	public static int find(char array[], char key)
	{
		int a = -1;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == key)
			{
				a = i;
				break;
			}
		}
		return a;
	}
	public static int find(int array[], int key)
	{
		int a = -1;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == key)
			{
				a = i;
				break;
			}
		}
		return a;
	}
	public static int find(double array[], double key)
	{
		int a = -1;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == key)
			{
				a = i;
				break;
			}
		}
		return a;
	}
	public static void sort(char array[])
	{
		for(int i = array.length; i > 0; i--)
		{
			for(int j = 1; j < i; j++)
			{
				if((int)array[j - 1] > (int)array[j])
				{
					char c = array[j];
					array[j] = array[j - 1];
					array[j - 1] = c;
				}
			}
		}
	}
	public static void sort(int array[])
	{
		for(int i = array.length; i > 0; i--)
		{
			for(int j = 1; j < i; j++)
			{
				if(array[j - 1] > array[j])
				{
					int c = array[j];
					array[j] = array[j - 1];
					array[j - 1] = c;
				}
			}
		}
	}
	public static void sort(double array[])
	{
		for(int i = array.length; i > 0; i--)
		{
			for(int j = 1; j < i; j++)
			{
				if(array[j - 1] > array[j])
				{
					double c = array[j];
					array[j] = array[j - 1];
					array[j - 1] = c;
				}
			}
		}
	}
}
