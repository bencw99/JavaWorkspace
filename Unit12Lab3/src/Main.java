import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * In this program, a GUI is used to allow a user to add and remove Contacts from a contact list, and to display the contact list
 * The contacts are instances of the Contact class and are stores in a tree map, which stores them in lexicographical order
 */
public class Main extends JPanel
{
	//Class variables for GUI
    private static int screenWidth = 400;
    private static int screenHeight = 200;
    private static AddButton add = new AddButton(50, 50);
    private static RemoveButton remove = new RemoveButton(200, 50);
    private static DisplayButton display = new DisplayButton();
    private static ContactList contacts = new ContactList();
    private static String fileName;
    private static FileProcessor processor;
	public static void main(String[]args) throws FileNotFoundException
	{
		//GUI components
		fileName = JOptionPane.showInputDialog("Enter the file name where the contact list is stored");
		processor = new FileProcessor(fileName, fileName);
		contacts = processor.read();
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(screenWidth,screenHeight);
        frame.setTitle("Contact List");
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main drawer = new Main();
        frame.add(drawer);
        frame.add(add);
        frame.add(remove);
        frame.add(display);
        frame.setVisible(true);
        remove.setVisible(true);
        add.setVisible(true);
        display.setVisible(true);
        //Loop that allows the user to use the program continuously until user exits
        while(true)
        {
        	frame.repaint(); //repainting of screen
        	processor.write(contacts); //writing contacts back to file
        	//Sleeping thread to use less processing power
        	try
        	{
        		Thread.sleep(50);
        	}
        	catch(InterruptedException e)
        	{
        		e.printStackTrace();
        	}
        }
	}
	//Paint method that draws on screen
	public void paint(Graphics screen)
	{
		add.repaint();
		remove.repaint();
		display.repaint();
		screen.setFont(new Font("Arial", Font.PLAIN, 24));
		screen.drawString(contacts.toString(), 50, 100);
	}
	//JButton inheritors for remove, add, and display functions
    private static class RemoveButton extends JButton implements ActionListener 
    {
        RemoveButton(int x, int y) 
        {
            super("Remove");
            addActionListener(this);
            setLocation(x,y);
            setSize(100, 50);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	contacts.remove(new Contact(JOptionPane.showInputDialog("Enter the contact you want to remove in the form FIRST LAST PHONE_NUMBER EMAIL")));
        }
    }
    private static class AddButton extends JButton implements ActionListener 
    {
        AddButton(int x, int y) 
        {
            super("Add");
            addActionListener(this);
            setLocation(x,y);
            setSize(100, 50);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	contacts.add(new Contact(JOptionPane.showInputDialog("Enter the contact you want to add in the form FIRST LAST PHONE_NUMBER EMAIL")));
        }
    }
    private static class DisplayButton extends JButton implements ActionListener 
    {
        DisplayButton() 
        {
            super("Display");
            addActionListener(this);
            setSize(100, 50);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	System.out.println("Contacts: ");
        	System.out.println(contacts);
        }
    }
}
