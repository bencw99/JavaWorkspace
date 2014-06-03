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
package projects.fifteen;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.util.ArrayList;

/**
 * The <code>GameOfFifteen</code> class is the main application.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 * <p> 
 * copyright&copy; 2007 Dave Wittry (http://apcomputerscience.com)
 * @author Dave Wittry
 */
public class GameOfFifteen extends World<ColorTextPiece>
{   
   private boolean winner;
   
   /**
     * Constructs a bounded 4-by-4 grid
     */   
   public GameOfFifteen()
   {
     setGrid( new BoundedGrid<ColorTextPiece>(4, 4) );
     loadBoard();
     winner = false;
     setMessage("Rearrange the tiles in alphabetical row-major order.\n" +
                "Type the letter you want to move.");
   }
   
   /**
     * Loads the board/grid with the first 15 letters of the alphabet scrambled
     */   
   private void loadBoard()
   {
//     *** complete this method first ***
       
     String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
     ArrayList<String> list = new ArrayList<String>();
     
     /*
      * Students, write a for-each loop here which traverses through the letters
      * array and places each one into the ArrayList list.
      */
     for(String letter : letters)
     {
    	 list.add(letter);
     }
     
     /*
      * Students, write a loop which takes one letter at a time from list and 
      * adds it to the World/Grid in row-major order. Do this with one for-each
      * loop that goes through the 'list' ArrayList. You should not need any other loops.
      */
     for(String letter: list)
     {
    	 int code = (int)(letter.charAt(0)) - 65;
    	 add(new Location(code/4 , code % 4), new ColorTextPiece(letter));
     }
     
     // Before you go on, make sure you run it and see that the GUI displays
     // the letters in order.
     
     /* For shuffling, we have to be careful. We can't just
      * scramble any way we wish because there is the "15-14"
      * problem - read a website for explanation. In short, not all
      * random chosen starting situations are solvable. So, my scramble
      * scrambles the way someone would scramble the board that
      * you would buy in a store - since the pieces can't be removed,
      * if we start with a solved puzzle and make a whole bunch of
      * random legal moves (don't remove pieces and snap them back in),
      * then we'll still have a solvable puzzle when we're done.
      * So, uncomment this for loop and go write the makeALegalMove() method.
      */ 
     for (int num=1; num<=200; num++) 
     {
    	 makeALegalMove(getGrid()); // you need to go write this method
     }
   }
   
   public static void makeALegalMove(Grid<ColorTextPiece> grid)
   {
	   ArrayList<Location> possibles = new ArrayList<Location>();
	   Location empty = null;
	   outer: for(int i = 0; i < grid.getNumRows(); i ++)
	   {
		   for(int j = 0; j < grid.getNumCols(); j ++)
		   {
			   if(grid.get(new Location(i, j)) == null)
			   {
				   empty = new Location(i , j);
				   break outer;
			   }
		   }
	   }
	   for(int i = 0; i < grid.getNumRows(); i ++)
	   {
		   for(int j = 0; j < grid.getNumCols(); j ++)
		   {
			   if(grid.getEmptyAdjacentLocations(new Location(i, j)).size() != 0 && (grid.get(new Location(i,j)) != null))
			   {
				   possibles.add(new Location(i , j));
			   }
		   }
	   }
	   for(Location possible: possibles)
	   {
		   System.out.println(possible);
	   }
	   int random = (int)(possibles.size()*Math.random());
	   System.out.println(" " + possibles.get(random));
	   ColorTextPiece piece = (ColorTextPiece)(grid.remove(possibles.get(random)));
	   grid.put(empty, piece);
   }
   
   /**
     * The method is called by the GUI when a keyboard event takes place
     * @param desc the String describing the key
     * @param loc the selected location in the grid at the time the key was pressed 
     * @return <code>true</code> if the world consumes the key press, <code>false</code
     * if the GUI should consume it<br>
     * @see <code>World</code> class
     */   
   public boolean keyPressed(String desc, Location loc) 
   {   
      //*** complete this method ***
      //*** where you see the "..." and a comment afterward, complete the stmt.
       if ( winner ) 
    	   return true; // game over; no more play
       if ( !("A".compareTo(desc)<=0 && "Z".compareTo(desc)>=0) ) // not a legal keyboard entry
    	   return true;
       Grid<ColorTextPiece> grid = getGrid();     
       ArrayList<Location> nbrs = grid.getOccupiedLocations();
	     	for (Location possLoc: nbrs)
	     		if ( grid.get(possLoc).getText().equals(desc) ) 
	     		{
	     			ArrayList<Location> emptyCells = grid.getEmptyAdjacentLocations(possLoc);
	     			if ( emptyCells.size() == 0 ) // no open spot next to that letter
	     			{
	     				return true;
	     			}
	     			// at this point, there is exactly one open spot
	     			int dirToward = possLoc.getDirectionToward(emptyCells.get(0));
	     			if ( dirToward % 90 != 0)
	     			{
	     				return true;    // can only move horizontally/vertically
	     			}
	     			// we've found somewhere to move to
	     			grid.put(possLoc.getAdjacentLocation(dirToward), grid.remove(possLoc));
	         
	         	winner = determineWinner();
	         	if ( winner )
	        	 	setMessage("You WIN");
	         	else
	        	 	setMessage("You moved letter " + desc + " from position " + possLoc);
	         	return true;
	        }
	     	return true;
   }

   /**
     * The method determines if there is a winning situation
     * @return <code>true</code> if there is a winning situation, <code>false</code> otherwise
     */   
   private boolean determineWinner()
   {
	   	Grid<ColorTextPiece> grid = this.getGrid();
	   	for(int i = 0; i < grid.getNumRows(); i++)
	   	{
	   		for(int j = 0; j < grid.getNumCols(); j ++)
	   		{
	   			if(!(grid.get(new Location(i, j)) != null))
	   			{
	   				
	   			}
	   		}
	   	}
     /*
      * Students, one possible way to write this method would be to get all of the objects out of the Grid 
      * in row-major order and place them into an ArrayList. Then, go through that ArrayList making sure
      * that adjacent elements are ordered based on getText() (or your teacher may want you to make the
      * ColorTextPiece class implement Comparable as a nice additional exercise). 
      * Keep in mind that somewhere there will be a null location! You're hoping the null location ends up
      * as the 16th cell, with all other cells being ordered - then you have your winning situation! Try
      * this algorithm with a piece of graph paper and a friend - see if it makes sense to the both of you
      * before you start coding. Or, come up with your own algorithm.
      */
     return false; // remove this stmt when completing this method
   }

    /**
     * The method is called by the GUI when a mouse event takes place. There are no mouse
     * events for this game - so this method immediately returns true;
     * @param loc the selected location in the grid at the time the mouse was pressed 
     * @return <code>true</code> if the world consumes the key press, <code>false</code
     * if the GUI should consume it<br>
     * @see <code>World</code> class
     */   
   public boolean locationClicked(Location loc)
   {
	   return true; // do nothing when mouse-clicked
   }
   
   public static void main(String[] args)
   {
	   World<ColorTextPiece> mw = new GameOfFifteen();
	   System.setProperty("info.gridworld.gui.selection", "hide");
	   mw.show();
   }
}