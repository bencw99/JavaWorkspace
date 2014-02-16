
public class TempConversion {
	public static void main(String [] args)
	{
		String Type = "F"; 
		Type = Input.getString ("Please enter F if you would like to convert from Fahrenheit to Celsius or C if you would like to convert from Celsius to Fahrenheit.");
		if (Type.equals("F"))
		{
			double F = Input.getDouble (" Please enter the temperature in degrees Fahrenheit ");
			double C = FahrenheitToCelsius(F);
			System.out.println ( " The temperature is " + C + " degrees Celsius" );
		}
		if (Type.equals("C"))
		{
			double C = Input.getDouble (" Please enter the temperature in degrees Celsius ");
			double F;
			F = CelsiusToFahrenheit(C);
			System.out.println ( " The temperature is " + F + " degrees Fahrenheit" );
		}
		if ((Type.equals("C") || Type.equals("F")) == false)
		{
			System.out.println("Invalid Input");
		}
	}
	public static double FahrenheitToCelsius(double F)
	{
		double C = 5./9. * ( F - 32 );
		return C;
	}
	public static double CelsiusToFahrenheit(double C)
	{
		double F =  9./5. * C + 32;
		return F;
	}
}
