//Class Grocery item
public class GroceryItem 
{
	//Instance variables shelfLife and itemCode
	int shelfLife;
	int itemCode;
	//Parameterized constructor to initialize GroceryItem instance
	public GroceryItem(int shelfLife, int itemCode)
	{
		this.shelfLife = shelfLife;
		this.itemCode = itemCode;
	}
	//Getter for shelfLife, must be initialized
	public int getShelfLife() 
	{
		return shelfLife;
	}
	//Setter for shelfLife
	public void setShelfLife(int shelfLife) 
	{
		this.shelfLife = shelfLife;
	}
	//getter for itemCode, must be initialized
	public int getItemCode() 
	{
		return itemCode;
	}
	//setter for item code
	public void setItemCode(int itemCode) 
	{
		this.itemCode = itemCode;
	}
	
}
