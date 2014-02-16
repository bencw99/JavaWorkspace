
public class Rectangle 
{
	int length;
	int width;
	char drawn;
	public Rectangle(int l, int w, char c)
	{
		length = l;
		width = w;
		drawn = c;
		if(length > 30)
		{
			length = 30;
		}
		if(width > 30)
		{
			width = 30;
		}
		if(length < 1)
		{
			length = 1;
		}
		if(width < 1)
		{
			width = 1;
		}
	}
	public void draw()
	{
		for(int i = 0; i < width; i++)
		{
			System.out.print(drawn + " ");
		}
		System.out.println();
		for(int i = 1; i < length - 1; i++)
		{
			System.out.print(drawn + " ");
			for(int j = 1; j < width - 1; j++)
			{
				System.out.print("  ");
			}
			System.out.println(drawn);
		}
		for(int i = 0; i < width; i++)
		{
			System.out.print(drawn + " ");
		}
	}
	public int getArea()
	{
		return length*width;
	}
	public int getPerim()
	{
		return 2*(length + width);
	}
	public int getLength() 
	{
		return length;
	}
	public void setLength(int length) 
	{
		this.length = length;
	}
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width) 
	{
		this.width = width;
	}
}
