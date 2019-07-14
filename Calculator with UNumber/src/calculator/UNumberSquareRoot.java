package calculator;
import java.util.Scanner;

/**
 * <p> Title: DemoNewtonMethod. </p>
 * 
 * <p> Description: Mainline to demo the Newton-Raphson square root method </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @author K V MURALI KRISHNA
 * 
 * @version 1.00	Initial baseline
 * 
 */

/**
 * Class for u number square root.
 */
public class UNumberSquareRoot {
	/**
	 * Constructs the object.
	 */
	UNumberSquareRoot() {
		
	}
	private static int numSignificantDigits;
	/*****
	 * This private method counts how many digits are the same between two estimates
	 */
	/**
	 * { function_howManyDigitsMatch}
	 *
	 * @param      newGuess  The new guess
	 * @param      oldGuess  The old guess
	 *
	 * @return     { no_of_digits_matched }
	 */
	private static int howManyDigitsMatch(UNumber newGuess, UNumber oldGuess) {
		// If the characteristics is not the same, the digits in the mantissa do not matter
		if (newGuess.getCharacteristic() != oldGuess.getCharacteristic()) return 0;		
		// The characteristic is the same, so fetch the mantissas so we can compare them
		byte[] newG = newGuess.getMantissa();
		byte[] oldG = oldGuess.getMantissa();
		// Computer the shorter of the two
		int size = newGuess.length();
		int otherOne = oldGuess.length();
		if (otherOne < size) size = otherOne;
		// Loop through the digits as long as they match
		for (int ndx = 0; ndx < size; ndx++)
			if (newG[ndx] != oldG[ndx]) return ndx;	// If the don't match, ndx is the result
		// If the loop completes, then the size of the shorter is the length of the match
		return size;
	}
	/*****
	 * This is the mainline 
	 * 
	 * @param args	The program parameters are ignored
	 */
	public UNumber sqrt(UNumber args) {
		numSignificantDigits = 15;		
		String input = args+"";
		UNumber result = new UNumber(0); 
		// As long as the length of the input String is positive, continue processing the input
		while (input.length() > 0) {
			Scanner value = new Scanner(input);
			// Does this input line consist of a value?
				// As long as there is another double value, compute the square root of it.
				double inputValue = value.nextDouble();
				UNumber theValue =  new UNumber(inputValue);
				UNumber two = new UNumber(2.0);
				UNumber newGuess = new UNumber(theValue);				// Compute the estimate
				newGuess.div(two);				// Display the first estimate
				UNumber oldGuess;				// Temporary value for determining when to terminate the loop
				int digitsMatch = 0;
				do {
					oldGuess = newGuess;		// Save the old guess// Compute the new guess
					newGuess = new UNumber(theValue, numSignificantDigits);
					newGuess.div(oldGuess);
					newGuess.add(oldGuess);
					newGuess.div(two);
					digitsMatch = howManyDigitsMatch(newGuess, oldGuess);
				} while (digitsMatch < numSignificantDigits);			// Determine if the old and the new guesses are "close enough"							// Display the final result
				UNumber resultSquared = new UNumber(newGuess);
				result = resultSquared;
			value.close();
			System.out.println(result+" root from nrm");
			return result ;
		}
		// An empty input line has been entered, so the tell the user we are stopping
		// System.out.print("Empty line detected... the program stops");
		return result ;
	}
}
