/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package projects.fifteen;
import java.awt.Color;

/**
 * A <code>ColorTextPiece</code> is the game piece placed in the Grid.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 * <p>
 * copyright&copy; 2007 Dave Wittry (http://apcomputerscience.com)
 * @author Dave Wittry
 */
public class ColorTextPiece
{
    private String str;
    private Color col;

   /**
     * Construct a game piece with a String
     * @param str a String to be displayed for this piece
     */    
    public ColorTextPiece(String str) 
    { 
      this.str = str; 
      this.col = Color.GREEN;
    }
    
   /**
     * Returns the text for this piece
     * @return text String for this piece
     */    
    public String getText() 
    { 
      return str;
    }
    
   /**
     * Returns the color of this piece
     * @return the color of this piece
     */    
    public Color getColor()
    {
      return col;
    }
    
    public String toString() 
    {
      return str;
    }
}
