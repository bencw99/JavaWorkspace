package projects.magicWand;
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * 
 * minor modifications, javadoc, and comments by Dave Wittry
 */

/* note on images/graphics/text: 
 * 
 * GridWorld works very simply. If you have a .gif file with the 
 * same name as this class (Tile.gif), then that graphic
 * will get displayed(that's not what I'm doing here). 
 * If there is no .gif, then the gui will look
 * for a getColor() and getText() method in this class(that is what I'm
 * doing here). If it finds one or the other, the cell will be 
 * displayed in that color with that text.
 * 
 * Swapping images, which I'm NOT doing here below, is also easily accomplished.
 * Assume for a minute I need a black game piece, a white game piece, and a gray 
 * tile(so that my board looks beautiful and each cell blends together(my "empty" cells
 * will contain a gray tile). I have done exactly this in another project in this series
 * called WuZiQi, by the way. So anyway, I would then have 3 graphic files, 
 * GamePiece.gif(gray tile), GamePiece_black.gif, and GamePiece_white.gif.
 * If I want the GUI to swap a different image but keep this same class, just
 * provide a getImageSuffix() method which returns the suffix ("", "_black",
 * or "_white", appropriately). It'll then find the correct graphic in your project. Cool!
 * 
 * Lastly, if a class has no graphic, no getColor() and no getText() method,
 * then the GUI will simply call the toString() method. That's awesome. That 
 * means that putting Integer, String, Boolean, etc. objects into the World 
 * will have their toString() values automagically displayed with no extra effort.
*/
import java.awt.Color;

/**
 * 
 * A <code>Tile</code> is the type of piece that is placed/used in the Grid.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class Tile
{
   private Color color;
   private String text;

   /**
     * Constructs a Tile object with String/Object.
     * @param text the text to display for this Tile
     * @param color the color to display for this Tile
     */
   public Tile(String text, Color color)
   {
     this.text = text;
     this.color = color;
   }

   /**
     * Returns the color for this Tile.
     * @return color of this Tile 
     */   
   public Color getColor()
   {
     return color;
   }
   
   /**
     * Sets the color of this Tile. 
     * @param color the color for this Tile
     */
   public void setColor(Color color)
   {
     this.color = color;
   }
   
   /**
     * Returns the text for this Tile.
     * @return the text String for this Tile
     */
   public String getText()
   {
     return text;
   } 
 
   public String toString()
   {
     return color.toString();
   }
}
