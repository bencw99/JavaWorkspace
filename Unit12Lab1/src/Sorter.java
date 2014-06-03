
/*
 * This program creates 50 grocery items and sorts and displays them based on their shelf life
 * @ author Benjamin Cohen-Wang
 */
import java.util.ArrayList;
import java.util.Random;
public class Sorter 
{
	public static void main(String[]args)
	{
		//Two ArrayLists for different shelfLives
		ArrayList<GroceryItem> setOne = new ArrayList<GroceryItem>();
		ArrayList<GroceryItem> setTwo = new ArrayList<GroceryItem>();
		//Iterator to sort GroceryItems based on shelfLife
		for(int i = 0; i < 50; i++)
		{
			Random random = new Random();
			int life = random.nextInt(30) + 1;
			if(life <= 7)
			{
				setOne.add(new GroceryItem(life, i + 1));
			}
			else
			{
				setTwo.add(new GroceryItem(life, i + 1));
			}
		}
		//Print Statements to display info to user
		System.out.println("List one (7 or less): " + setOne.size() + " items");
		for(GroceryItem item : setOne)
		{
			System.out.println(" - Grocery Item : " + item.getItemCode() + " Shelf Life: " + item.getShelfLife());
		}
		System.out.println("List two (8 or more): " + setTwo.size() + " items");
		for(GroceryItem item : setTwo)
		{
			System.out.println(" - Grocery Item : " + item.getItemCode() + " Shelf Life: " + item.getShelfLife());
		}
	}
}
