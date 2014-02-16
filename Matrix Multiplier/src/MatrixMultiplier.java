
public class MatrixMultiplier {
	public static void main(String [] args)
	{
		int matrixA[][] = {{2,0},{5,-6}};
		int matrixB[][] = {{1,-5},{-3,4}};
		int matrixC[][] = multiply(matrixA , matrixB);
		display(matrixC);
	}
	public static int [][] multiply(int A[][] , int B[][])
	{
		int length = A.length;
		int width = B[0].length;
		int matrixC[][] = new int[length][width];
		int Ai = 0;
		int Aj = 0;
		int Bi = 0;
		int Bj = 0;
		int Ci = 0;
		int Cj = 0;
		for(Bi = 0; Bi <= (B.length - 1); Bi++,Ci++)
		{
			for(Aj = 0, Cj = 0; Aj <= (A[0].length - 1); Aj++,Cj++)
			{
				for( Ai = 0, Bj = 0;Ai <= (length - 1); Ai ++, Bj++)
				{
					matrixC[Ci][Cj] += A[Ai][Aj] * B[Bi][Bj]; 
				}
			}
		}
		return matrixC;
	}
	public static void display(int C[][])
	{
		int i = 0;
		int j = 0;
		for( j = 0; j<=(C[0].length-1); j++)
		{
			for( i = 0; i<=(C.length-1); i++)
			{
				System.out.print(C[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
