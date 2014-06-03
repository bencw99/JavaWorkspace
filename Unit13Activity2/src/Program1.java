//This program demonstrates the five JOptionPane Message Dialog window types
import javax.swing.JOptionPane;
public class Program1 
{
	public static void main(String[]args)
	{
		JOptionPane.showMessageDialog(null, new String("This is an information message"), "Information", JOptionPane.INFORMATION_MESSAGE);//Information message
		JOptionPane.showMessageDialog(null, new String("This is an error message"), "Error", JOptionPane.ERROR_MESSAGE);//Error message
		JOptionPane.showMessageDialog(null, new String("This is a question message"), "Question", JOptionPane.QUESTION_MESSAGE);//Question message
		JOptionPane.showMessageDialog(null, new String("This is a plain message"), "Plain", JOptionPane.PLAIN_MESSAGE);//Plain message
		JOptionPane.showMessageDialog(null, new String("This is a warning message"), "Warning", JOptionPane.WARNING_MESSAGE);//Warning message
	}
}
