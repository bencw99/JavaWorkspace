
public class Fibonacci {
public static void main(String [] args)
{
int y = 1;
int x = 2;
int prevx1 = 1;
int prevx2 = 0;
System.out.println( "1. 1" );
while ( x<=100000000 )
{
	x = prevx1 + prevx2;
	prevx2 = prevx1;
	prevx1 = x;
	System.out.println( ++y + ". " + x );
}
}
}
