import java.util.*;
import java.math.BigInteger;
// Owen Gao
/* Write a program that should take as input the row and column index
 of an element in the triangle arrPascal[n][r], where n is the row index
 (0 <= n <= 91) and r is the column index (0 <= r <= n).
 It should then print the value of all the elements on the corresponding shallow diagonal and the sum of their values.  
 The program should also output the bottom row of Pascal’s triangle for the given shallow diagonal.   
If an element in Pascal’s triangle exceeds the maximum value for a long, it should be replaced with a 0. 
*/
public class pascal2{
	public static void main(String[] args){
		// create 2D array to store a 92 row pascal triangle using method pascalsTriangle
		long[][] arrPascal = pascalsTriangle();
		Scanner console= new Scanner(System.in);
		//taking in user input to determine which diagonal to consider from row and col
		System.out.print("Input row index: ");
		int row =console.nextInt();
		System.out.print("Input column index: ");
		int column = console.nextInt();
		while(!validateRowCol(row, column)) {
			System.out.println("Invalid Input! Row index should be between 0 and 91. Column index should be between 0 and row index. Please retry.");
			System.out.print("Input row index: ");
			row =console.nextInt();
			System.out.print("Input column index: ");
			column = console.nextInt();
		}
		//set diagonal row start at row+column
		int currentRow = row + column;
		//fibonacci Index that is calculated
		int fibIndex = currentRow + 1; 
		//set diagonal column start at 0
		int currentCol = 0;
		// sum of all values of diagonal
		long sum = 0;
		//keep repeating until column > row (not possible in pascal's triangle)
		while(currentRow >= currentCol){
			// search 2D array for the value at the index
			long element = arrPascal[currentRow][currentCol];
			// add value to total value
			sum += element;
			//convert to string to fit formating 
			String toPrint = "arrPascal[" + currentRow + "][" + currentCol + "]";
			System.out.printf("%-18s", toPrint);
			System.out.println("= " + element);
			// decrease row and increase column to get to next number in the diagonal
			currentRow--;
			currentCol++;
		}

		System.out.println("-----------------------");
		//format output
		System.out.printf("%-18s", "Fibonacci(" + fibIndex + ")");
		System.out.println("= " + sum);
		//print bottom row of Pascal’s triangle for the given shallow diagonal
		int bottomRowIndex = fibIndex - 1;
		System.out.print("[" + arrPascal[bottomRowIndex][0]);
		//traverse  2D array for all values of bottom row
		for(int i = 1; i < arrPascal[bottomRowIndex].length; i++) {
			System.out.print(", " + arrPascal[bottomRowIndex][i]);
		}
		System.out.print("]");
	}

	//construct 92 line pascal triangle
	public static long[][] pascalsTriangle() {
		long[][] arrPascal = new long[92][];
		for(int i = 0; i < 92; i++) {
			//makes array representing the row
			long[] pascalLine = new long[i+1];
			// create and store values for all 92 rows of pascal's triangle required
			for(int j = 0; j <= i; j++) {
				//find the values at index using nCr equation with factorials
				//use factorial method to calculate factorials
				BigInteger nFactorial = new BigInteger(factorial(i));
				BigInteger rFactorial = new BigInteger(factorial(j));
				BigInteger differenceFactorial = new BigInteger(factorial(i-j));
				BigInteger denominator = rFactorial.multiply(differenceFactorial);
				BigInteger nCr = nFactorial.divide(denominator);
				//add number to this line
				pascalLine[j] = convert(nCr.toString());
			}
			//add line to triangle
			arrPascal[i] = pascalLine;
		}
		return arrPascal;
	}

	public static boolean validateRowCol(int row, int col) {
		if(row < 0 || row > 91) {
			return false;
		}
		if(col < 0 || col > row) {
			return false;
		}
		return true;
	}

	// method to calculate factorial using BigInteger
	public static String factorial(int n) {
       BigInteger fact = new BigInteger("1");
       for (int i = 1; i <= n; i++) {
           fact = fact.multiply(new BigInteger(i + ""));
       }
       return fact.toString();
   	}
    
    //checks if value is larger than the largest long
    public static long convert(String x){
    	try {
    		// if it can be parsed to long, it does not exceed
    		long result = Long.parseLong(x); 
		   	return result;
    	} catch(NumberFormatException e) { 
    		// if it can not be parsed it exceeds, set to 0
	    	return 0;
	    }
    	
    }

}