package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape2D {

    protected int myCoordX, myCoordY;
    protected int mySizeX, mySizeY;
    protected Color myColor;

    public void move(int deltaX, int deltaY) {
        myCoordX += deltaX;
        myCoordY += deltaY;
    }
    
    public void resize(int deltaSizeX, int deltaSizeY) {
        mySizeX += deltaSizeX;
        mySizeY += deltaSizeY;        
    }
    
    public void setColor(Color color) {
        myColor = color;
    }
    
    public abstract void draw(Graphics g);
    
    

}
