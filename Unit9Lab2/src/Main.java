import java.io.FileNotFoundException;
public class Main 
{
	public static void main(String[]args) throws FileNotFoundException
	{
		Date date1 = new Date(20,11,1999);
		Date date2 = new Date(29,9,1997);
		Date date3 = new Date(15,10,2006);
		Date date4 = new Date(11,6,2004);
		MyFileProcessor processor1 = new MyFileProcessor("Date1","Date1");
		processor1.write(date1);
		System.out.println(processor1.read().getDate());
		MyFileProcessor processor2 = new MyFileProcessor("Date2","Date2");
		processor2.write(date2);
		System.out.println(processor2.read().getDate());
		MyFileProcessor processor3 = new MyFileProcessor("Date3","Date3");
		processor3.write(date3);
		System.out.println(processor3.read().getDate());
		MyFileProcessor processor4 = new MyFileProcessor("Date4","Date4");
		processor4.write(date4);
		System.out.println(processor4.read().getDate());
	}
}
