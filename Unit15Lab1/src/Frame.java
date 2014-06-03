import java.awt.*;
import javax.swing.*;
//This program is a GUI for a song purchase checkout menu
public class Frame extends JFrame
{
	//Constructor for frame to make a panel and set attributes of frame
	public Frame()
	{
		setTitle("Song Selection Checkout");
		setSize(410, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new DisplayPanel(this);
		add(panel);
	}
	
	//Main method where the frame is declared and initialized
	public static void main(String[]args)
	{
		JFrame frame =  new Frame();
		frame.setVisible(true);
	}
}
