package projects.magicWand;
/*
 * 
 * Directions for students:
 * 
 * If a method contains "*** complete this method ***" on the first line, complete
 * it where the comments direct you. Otherwise, the methods are already complete.
 */

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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.awt.Color;
import java.util.Stack;
/**
 * The <code>MagicWandWorld</code> is the main application. 
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 * <p> 
 * copyright&copy; 2007 Dave Wittry (http://apcomputerscience.com)
 * @author Dave Wittry
 */
public class MagicWandWorld extends World<Tile>
{
   private Location startingLoc = null;
   private Color startingColor = null;

   /**
     * Constructs a MagicWand World
     */      
   public MagicWandWorld()
   {
     makeWorld();
     setMessage("Your Magic Wand is now the eye dropper - \"Pick Up\" a color by clicking a cell");
   }

   /**
     * Ramdomly place Tiles in the world
     */      
   private void makeWorld()
   { 
     Grid<Tile> gr = getGrid();
     Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
 
     for(int i = 0; i < gr.getNumRows(); i ++)
     {
    	 for(int j = 0; j < gr.getNumCols(); j ++)
    	 {
    		 gr.put(new Location(i, j), new Tile( "Tile" , colors[(int)(4*Math.random())]));
    	 }
     }
   }
   
   /**
    * This is a recursive method which does the changing of the colors. It is called from locationClicked(). A recursive method is a method that calls itself. You will learn about recursive methods in another course unit...but for this assignment all that is important is to know that a method can call itself.
    * @param loc location of the candidate cell for a color change
    * @param col color of the cell at location <code>loc</code>
    */
   private void changeColors(Location loc, Color col)
   {
//	   if(!getGrid().get(loc).getColor().equals(col))
//	   {
//		   return;
//	   }
//	   getGrid().put(loc, new Tile("Tile", startingColor));
//	   for(int i = Math.max(loc.getRow() - 1, 0); i <= Math.min(loc.getRow() + 1, getGrid().getNumRows() - 1); i ++)
//	   {
//		   for(int j = Math.max(loc.getCol() - 1, 0); j <= Math.min(loc.getCol() + 1, getGrid().getNumCols() - 1); j ++)
//		   {
//			   if(!(i == loc.getRow() && j == loc.getCol()))
//			   		changeColors(new Location(i , j), col);
//		   }
//	   }
	   
     // Recursive Version
	 /*
	  * Students, do the following:
	  * 1) see if the color of the object at location loc in the grid matches col - if not, return
	  * 2) put a new Tile into the grid at location loc which has the startingColor as its color
	  * 3) get all the occupied locations around loc within the grid and, for each one, call changeColors
	  */   
     // End of Recursive Version
        
	   Stack<Location> locs = new Stack<Location>();
	   locs.push(loc);
	   while(!locs.isEmpty())
	   {
		   Location current = locs.pop();
		   getGrid().put(current, new Tile("Tile", startingColor));
		   for(Location l : getGrid().getOccupiedAdjacentLocations(current))
		   {
			   if(getGrid().get(l).getColor().equals(col))
			   {
				   locs.push(l);
			   }
		   }
	   }
     // Stack version 
	 /*
	  * Students, if you haven't written the recursive version yet, take a few minutes to
	  * do so - this should be relatively quick. Then, once that works,
	  * comment it out and write a new version which uses a Stack instead. 
	  * You were introduced to the Stack class in an earlier course unit that covered Collections and Generics.
	  */
     // End of Stack Version
	   
   }
   
   /**
    * This method is auto-magically called by the GUI and passed the 
    * location that the user clicked.
    * @param loc location on the Grid where the mouse was clicked
    * @return <code>true</code> if the world consumes the key press, <code>false</code>
    * @see <code>World</code> class
    */
   public boolean locationClicked(Location loc)
   {
       Grid<Tile> gr = getGrid();
       if ( startingLoc == null )
       {
         startingLoc = loc;
         startingColor = gr.get(loc).getColor();
         setMessage("You now have a magic wand, try it!\nYour magic color is " + startingColor);
       }
       else 
         if ( ! gr.get(loc).getColor().equals(startingColor) ) 
           changeColors(loc, gr.get(loc).getColor());
       return true;      
   }
    
   public static void main(String[] args)
   {
     World mw = new MagicWandWorld();
     System.setProperty("info.gridworld.gui.selection", "hide");
     mw.show();
   }
}