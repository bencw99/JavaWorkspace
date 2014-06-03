 public class Rectangle
 {
	 //Private instance variables length and width
	 private double length;
	 private double width;
	 //Default constructor
	 public Rectangle()
	 {
		 length = 0;
		 width = 0;
	 }
	 //Parameterized constructor
	 public Rectangle( double l, double w )
	 {
		 setLength(l);
		 setWidth(w);
	 }
	 //Sets length to argument, can't be < 0
	 public void setLength( double l )
	 {
		 length = l;
		 if(length < 0)
		 {
			 length = 0;
		 }
	 }  
	 //Sets width to argument, can't be < 0
	 public void setWidth( double w )
	 {
		 width = w;
		 if(width < 0)
		 {
			 width = 0;
		 }
	 }  
	 //Sets sides using setLength() and setWidth()
	 public void setSides( double l, double w )
	 {
		 setLength(l);
		 setWidth(w);
	 }
	 //returns length given instance initialized
	 public double getLength()
	 {
		 return length;
	 }  
	 //returns width given instance initialized
	 public double getWidth()
	 {
		 return width;
	 }
}