package appl.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape2D {

    public Rectangle() {
       myCoordX = 5;
       myCoordY = 5;
       mySizeX = 100;
       mySizeY = 100;
       myColor = Color.black;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(myColor);
        g.fillRect(myCoordX - (mySizeX / 2), myCoordY - (mySizeY / 2), mySizeX, mySizeY);        
    }
}
