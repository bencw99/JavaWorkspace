
public class TestScores 
{
public static void main (String [] args)
{
float testscore1 = 97;
float testscore2 = 92;
float testaverage = (((testscore1) + (testscore2)) / 2);
System.out.println("Your average test score is " + testaverage);

if ( testaverage >= 90 && testaverage <= 100 )
{
System.out.println("Your letter grade is A");
}

if ( testaverage >= 80 && testaverage <= 89 )
{
System.out.println("Your letter grade is B");
}

if ( testaverage >= 70 && testaverage <= 79 )
{
System.out.println("Your letter grade is C");

if ( testaverage >= 65 && testaverage <= 69 )
{
System.out.println("Your letter grade is D");
}

if ( testaverage <= 64 )
{
System.out.println("Your letter grade is F");
}
}
}
}
