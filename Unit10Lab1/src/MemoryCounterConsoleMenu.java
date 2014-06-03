import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class MemoryCounterConsoleMenu extends Applet
{
	MemoryCounter counter;
	IncrementButton incrementor = new IncrementButton();
	DecrementButton decrementor = new DecrementButton();
	QuitButton quit = new QuitButton();
	ResetButton reset = new ResetButton();
	ResetMemButton resetMem = new ResetMemButton();
	AddMemButton addMem = new AddMemButton();
	public MemoryCounterConsoleMenu(int c)
	{
		counter = new MemoryCounter(c); //counter initialized
		init();
	}
	public void init() //buttons added
	{	
        add(incrementor);
        incrementor.setVisible(true);
        add(decrementor);
        decrementor.setVisible(true);
        add(quit);
        quit.setVisible(true);
        add(reset);
        reset.setVisible(true);
        add(resetMem);
        resetMem.setVisible(true);
        add(addMem);
        addMem.setVisible(true);
		repaint();
	}
	public void draw(Graphics screen)
	{
		//draws buttons and count
		Font font = new Font("Arial", Font.BOLD, 40);
		screen.setFont(font);
        quit.repaint();
        reset.repaint();
        incrementor.repaint();
        decrementor.repaint();
        resetMem.repaint();
        addMem.repaint();
		screen.drawString("Count: " + counter.getCount(),10,50);
		screen.drawString("Memory: " + counter.getMem(),10,110);
	}
    private class IncrementButton extends JButton implements ActionListener 
    {
        IncrementButton() 
        {
            super("Increment");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	counter.increment();
            repaint();
        }
    }
    private class DecrementButton extends JButton implements ActionListener 
    {
        DecrementButton() 
        {
            super("Decrement");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	counter.decrement();
            repaint();
        }
    }
    private class ResetButton extends JButton implements ActionListener 
    {
        ResetButton() 
        {
            super("Reset");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	counter.reset();
            repaint();
        }
    }
    private class QuitButton extends JButton implements ActionListener 
    {
        QuitButton() 
        {
            super("Quit");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	System.exit(0);
        }
    }
    private class ResetMemButton extends JButton implements ActionListener 
    {
        ResetMemButton() 
        {
            super("Reset Mem");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	counter.resetMem();
        }
    }
    private class AddMemButton extends JButton implements ActionListener 
    {
        AddMemButton() 
        {
            super("Add Mem");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
        	counter.addMem();
        }
    }
}
