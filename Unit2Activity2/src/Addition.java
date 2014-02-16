import java.util.Scanner;
import javax.swing.JOptionPane;

public class Addition 
{
	public static void main(String[]args)
	{
		Scanner input = new Scanner( System.in );
		int number1;
		int number2;
		int sum;
		number1 = Integer.parseInt( JOptionPane.showInputDialog("Enter the first integer:" ) ); 
		number2 = Integer.parseInt( JOptionPane.showInputDialog("Enter the second integer:" ) );
		sum = number1 + number2;
		System.out.printf( "Sum is %d\n" , sum);
	}
}
