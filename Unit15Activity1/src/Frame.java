
//Frame for the program to be displayed in
import javax.swing.*;
public class Frame extends JFrame
{
	
	//Frame constructor
	public Frame()
	{
		//Frame attributes set
		setTitle("Pizza Selection");
		setSize( 400 , 200 );
		setResizable(false);
		setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JPanel created and added to frame
		JPanel panel = new SelectionPanel();
		add(panel);
	}
	
	//main method simply creating and displaying the frame
	public static void main(String[]args)
	{
		JFrame frame = new Frame();
		frame.setVisible(true);
	}	
}
