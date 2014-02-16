
public class InterestProgram {
	public static void main (String [] args)
	{
		double S;
		double P = Input.getDouble("How much money is being deposited? ");
		double i = Input.getDouble("What is the percent interest? Write your answer as a percent with no symbol.");
		double n = Input.getDouble("For how many years will the money be in the account?");
		S = Interest(P, i, n);
		S *= 100;
		S = (int) S;
		S /= 100;
		System.out.println("By the end of the savings, the money in the acount will be " + S + "$. The account has gained " + (S - P) + "$ after " + n + " years.");
	}
	public static double Interest(double P, double i, double n)
	{
		double S = P * Math.pow( 1 + i/100 , n );
		return S;
		
	}
}
