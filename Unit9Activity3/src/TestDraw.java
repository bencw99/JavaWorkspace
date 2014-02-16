import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class TestDraw 
{
	public static void main(String[]args)
	{
		DrawPanel panel = new DrawPanel(); //declares and creates DrawPanel
		JFrame application = new JFrame(); //declares and creates JFrame
		JLabel label = new JLabel(panel.getStatus()); //declares and creates label
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel); //adds DrawPanel
		application.add(label, BorderLayout.SOUTH);
		application.setSize(300,300); //sets size
		application.setVisible(true);
	}
}
