
public class MatrixMultiplier {
	public static void main(String[]args)
	{
		int MatrixA[][] = {{1,-2,4},{-3,1,5},{-6,1,-5}};
		int MatrixB[][] = {{5,3,-1},{-2,-3,4},{1,-7,2}};
		int MatrixC[][] = multiply(MatrixA,MatrixB);
		System.out.println("The product of the two matrices is");
		System.out.println();
		display(MatrixC);
	}
	public static int[][] multiply(int A[][], int B[][])
	{
		int MatrixC[][] = new int[A.length][B[0].length];
		int Ai = 0;
		int Aj = 0;
		int Bi = 0;
		int Bj = 0;
		for(Bi = 0; Bi < B.length ; Bi++)
		{
			for(Aj = 0; Aj < A[0].length; Aj++)
			{
				for(Ai = 0, Bj = 0; Ai < A.length; Ai++, Bj++)
				{
					MatrixC[Bi][Aj] += (A[Ai][Aj] * B[Bi][Bj]);
				}
			}
		}
		return MatrixC;
	}
	public static void display(int C[][])
	{
		int i = 0;
		int j = 0;
		for( j = 0; j<C[0].length; j++)
		{
			for( i = 0; i<C.length; i++)
			{
				System.out.print(C[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
