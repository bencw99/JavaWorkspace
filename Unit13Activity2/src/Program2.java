//This program demonstrates the three JOptionPane confirmation Dialog window types
import javax.swing.JOptionPane;
public class Program2 
{
	public static void main(String[] args) 
	{
		int rain = JOptionPane.showConfirmDialog(null, new String("Is it raining?"), "Yes No Option", JOptionPane.YES_NO_OPTION);//Yes/No message
		int quit = JOptionPane.showConfirmDialog(null, new String("Are you sure you want to quit?"), "Ok Cancel Option", JOptionPane.OK_CANCEL_OPTION);//ok/cancel message
		int hungry = JOptionPane.showConfirmDialog(null, new String("Are you hungry"), "Yes No Cancel Option", JOptionPane.YES_NO_CANCEL_OPTION);//Yes/No/Cancel message
		if(rain == JOptionPane.YES_OPTION)
		{
			System.out.println("it is raining");
		}
		else
		{
			System.out.println("it is not raining");
		}
		if(quit == JOptionPane.OK_OPTION)
		{
			System.out.println("The user quit");
		}
		else
		{
			System.out.println("The user canceled");
		}
		if(hungry == JOptionPane.YES_OPTION)
		{
			System.out.println("The user is hungry");
		}
		if(hungry == JOptionPane.CANCEL_OPTION)
		{
			System.out.println("The user didn't want to respond");
		}
		if(hungry == JOptionPane.NO_OPTION)
		{
			System.out.println("the user is not hungry");
		}
	}
}
