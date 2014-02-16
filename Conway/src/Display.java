import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
// Note that the JComponent is set up to listen for mouse clicks
// and mouse movement.  To achieve this, the MouseListener and
// MousMotionListener interfaces are implemented and there is additional
// code in init() to attach those interfaces to the JComponent.
public class Display extends JComponent implements MouseListener, MouseMotionListener {
    public static final int Y = 80;
    public static final int X = 100;
    public static boolean erase = false;
    public static Cell[][] cell = new Cell[X][Y];
    private final int X_GRID_OFFSET = 25; // 25 pixels from left
    private final int Y_GRID_OFFSET = 40; // 40 pixels from top
    private final int CELL_WIDTH = 5;
    private final int CELL_HEIGHT = 5;
    // Note that a final field can be initialized in constructor
    private final int DISPLAY_WIDTH;   
    private final int DISPLAY_HEIGHT;
    private StartButton startStop;
    private PauseButton pause;
    private ClearButton clear;
    private QuitButton quit;
    private StepButton step;
    private boolean paintloop = false;
    public Display(int width, int height) 
    {
        DISPLAY_WIDTH = width;
        DISPLAY_HEIGHT = height;
        init();
    }
    public void init() 
    {
        setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        initCells();
        addMouseListener(this);
        addMouseMotionListener(this);
        // Example of setting up a button.
        // See the StartButton class nested below.
        startStop = new StartButton();
        startStop.setBounds(100, 550, 100, 36);
        add(startStop);
        startStop.setVisible(true);
        //Pause button
        pause = new PauseButton();
        pause.setBounds(300, 550, 100, 36);
        add(pause);
        pause.setVisible(true);
        clear = new ClearButton();
        clear.setBounds(200, 550, 100, 36);
        add(clear);
        clear.setVisible(true);
        quit = new QuitButton();
        quit.setBounds(400, 550, 100, 36);
        add(quit);
        quit.setVisible(true);
        step = new StepButton();
        step.setBounds(500, 550, 100, 36);
        add(step);
        step.setVisible(true);
        repaint();
    }
    public void repain() 
    {
    	repaint();
    }
    public void paintComponent(Graphics g) 
    {
        final int TIME_BETWEEN_REPLOTS = 100; // change to your liking
        g.setColor(Color.BLACK);
        drawGrid(g);
        drawCells(g);
        drawButtons();
        if (paintloop) 
        {
            try 
            {
                Thread.sleep(TIME_BETWEEN_REPLOTS);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            nextGeneration();
            repaint();
        }
    }
    public void initCells() 
    {
        for (int y = 0; y < Y; y++) 
        {
            for (int x = 0; x < X; x++) 
            {
                cell[x][y] = new Cell(x, y);
            }
        }
        cell[36][22].setAlive(true); // sample use of cell mutator method
        cell[36][23].setAlive(true); // sample use of cell mutator method
        cell[36][24].setAlive(true); // sample use of cell mutator method
    }
    public void togglePaintLoop() 
    {
        paintloop = !paintloop;
        repaint();
    }
    public void setPaintLoop(boolean value) 
    {
        paintloop = value;
    }
    void drawGrid(Graphics g) 
    {
        for (int y = 0; y <= Y; y++) 
        {
            g.drawLine(X_GRID_OFFSET, Y_GRID_OFFSET + (y * (CELL_HEIGHT + 1)), X_GRID_OFFSET + X * (CELL_WIDTH + 1), Y_GRID_OFFSET + (y * (CELL_HEIGHT + 1)));
        }
        for (int x = 0; x <= X; x++) 
        {
            g.drawLine(X_GRID_OFFSET + (x * (CELL_WIDTH + 1)), Y_GRID_OFFSET, X_GRID_OFFSET + (x * (CELL_WIDTH + 1)), Y_GRID_OFFSET + Y * (CELL_HEIGHT + 1));
        }
    }
    void drawCells(Graphics g) 
    {
        // Have each cell draw itself
        for (int y = 0; y < Y; y++) 
        {
            for (int x = 0; x < X; x++) 
            {
                // The cell cannot know for certain the offsets nor the height
                // and width; it has been set up to know its own position, so
                // that need not be passed as an argument to the draw method
                cell[x][y].draw(X_GRID_OFFSET, Y_GRID_OFFSET, CELL_WIDTH, CELL_HEIGHT, g);
            }
        }
    }
    private void drawButtons() 
    {
        startStop.repaint();
        pause.repaint();
        clear.repaint();
        quit.repaint();
        step.repaint();
    }
    private void nextGeneration() 
    {
        for (int y = 0; y < Y; y++)
        {
            for (int x = 0; x < X; x++) 
            {
            	cell[x][y].calcNeighbors(cell);
            }
        }
        repaint();
        for (int y = 0; y < Y; y++)
        {
            for (int x = 0; x < X; x++) 
            {
                if(cell[x][y].getAlive() == true)
                {
                    if(cell[x][y].getNeighbors() == 2 || cell[x][y].getNeighbors() == 3)
                    {
                    	cell[x][y].setAlive(true);
                    }
                    else
                    {
                    	cell[x][y].setAlive(false);
                    }
                }
                if(cell[x][y].getAlive() == false)
                {
                    if(cell[x][y].getNeighbors() == 3)
                    {
                    	cell[x][y].setAlive(true);
                    }
                    else
                    {
                    	cell[x][y].setAlive(false);
                    }
                }
            }

        }
    }
    public void mouseClicked(MouseEvent e) 
    {

    }
    public void mouseEntered(MouseEvent arg0) 
    {

    }
    public void mouseExited(MouseEvent arg0) 
    {
    	
    }
    public void mousePressed(MouseEvent e) 
    {
		if(e.getX() <= X_GRID_OFFSET + (CELL_WIDTH + 1)*X && e.getX() >= X_GRID_OFFSET && e.getY() >= Y_GRID_OFFSET && e.getY() <= (CELL_HEIGHT + 1)*Y + Y_GRID_OFFSET)
		{
	    	if(cell[(e.getX() - X_GRID_OFFSET)/6][(e.getY() - Y_GRID_OFFSET)/6].getAlive())
	    	{
	    		erase = true;
	    	}
	    	else
	    	{
	    		erase = false;
	    	}
			if(erase)
			{
				cell[(e.getX() - X_GRID_OFFSET)/6][(e.getY() - Y_GRID_OFFSET)/6].setAlive(false);	
			}
			else
			{
				cell[(e.getX() - X_GRID_OFFSET)/6][(e.getY() - Y_GRID_OFFSET)/6].setAlive(true);	
			}
			repaint();
		}
    }
    public void mouseReleased(MouseEvent arg0) 
    {

    }
    public void mouseDragged(MouseEvent e) 
    {
		if(e.getX() <= X_GRID_OFFSET + (CELL_WIDTH + 1)*X && e.getX() >= X_GRID_OFFSET && e.getY() >= Y_GRID_OFFSET && e.getY() <= (CELL_HEIGHT + 1)*Y + Y_GRID_OFFSET)
		{
			if(erase)
			{
				cell[(e.getX() - X_GRID_OFFSET)/6][(e.getY() - Y_GRID_OFFSET)/6].setAlive(false);	
			}
			else
			{
				cell[(e.getX() - X_GRID_OFFSET)/6][(e.getY() - Y_GRID_OFFSET)/6].setAlive(true);	
			}
			repaint();
		}
    }
    public void mouseMoved(MouseEvent arg0) 
    {

    }
    private class PauseButton extends JButton implements ActionListener 
    {
        PauseButton() 
        {
            super("Pause");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
            // nextGeneration(); // test the start button
            if (this.getText().equals("Pause")) 
            {
                togglePaintLoop();
                setText("Resume");
            } 
            else 
            {
                togglePaintLoop();
                setText("Pause");
            }
            repaint();
        }
    }
    private class ClearButton extends JButton implements ActionListener 
    {
        ClearButton() 
        {
            super("Clear");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
            setText("Clear");
            for (int y = 0; y < Y; y++) 
            {
                for (int x = 0; x < X; x++) 
                {
                    cell[x][y] = new Cell(x,y);
                }
            }
            repain();
        }
    }
    private class StartButton extends JButton implements ActionListener 
    {
        StartButton() 
        {
            super("Start");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
            // nextGeneration(); // test the start button
            if (this.getText().equals("Start")) 
            {
                togglePaintLoop();
                setText("Stop");
            } 
            else 
            {
                togglePaintLoop();
                setText("Start");
            }
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
            setText("Quit");
            System.exit(0);
        }
    }
    private class StepButton extends JButton implements ActionListener 
    {
        StepButton() 
        {
            super("Step");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent arg0) 
        {
            setText("Step");
            nextGeneration();
            repaint();
        }
    }
}