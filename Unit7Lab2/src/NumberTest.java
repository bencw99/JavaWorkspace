
public class NumberTest 
{
	public static void main(String[]args)
	{
		String binaryString = "101011";
		System.out.println("The binary number " + binaryString + " is " + Number.binaryToDecimal(binaryString) + " in decimal");
		System.out.println("The binary number " + binaryString + " is " + Number.binaryToHex(binaryString) + " in hexadecimal");
		int decimal = 2941;
		System.out.println("The decimal number " + decimal + " is " + Number.decimalToHex(decimal) + " in hexadecimal");
		System.out.println("The decimal number " + decimal + " is " + Number.decimalToBinary(decimal) + " in binary");
	}
}
