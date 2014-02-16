
public class InsuranceProgram {
public static void main ( String [] args )
{
int age = 22;
int numClaims = 2;
int PolicyIncrease = 0;

if ( age >= 25 && numClaims > 0 )
{
	PolicyIncrease = 50;
}
if ( numClaims == 0 )
{
	PolicyIncrease = 0;
}
if ( age < 25 && numClaims > 0 )
{
	PolicyIncrease = 100;
}
System.out.println( "Age = " + age + ". Claims = " + numClaims + ". Policy Increase = $" + PolicyIncrease + "." );

}
}
