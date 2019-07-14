package calculator;
/**
 * <p> Title: RoundOff Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * 
 * @author K V MURALI KRISHNA
 * 
 * @version 1.00	2019-02-03 SETTING DEFAULT ERROR_TERM BASED ON MEASURE_TERM USING ROUNDOFF CLASS.
 * @version 1.01    2019-02-07 UNumber data type implementation into the Calculator.
 * 
 */
import java.math.*;

/**
 * Class for round off.
 */
class RoundOff{
	/**
	 * constructor
	 */
	RoundOff() {}
	/**
	 * { function_RoundOff }
	 *
	 * @param      measureTerm  The measure term
	 *
	 * @return     { rounded_value }
	 */
	public UNumber round(UNumber measureTerm) {
		String s = measureTerm+"";
		// checking if the measure term has a character e or E.
		if (s.indexOf("e") > 0 || s.indexOf("E") > 0) {
			s = String.format ("%.1f", Double.parseDouble(s));	
		}
		// splitting the string s
		String[] tokens = s.split("\\.");
		BigDecimal b1 = new BigDecimal(s);
		int i = b1.scale();
		if (i == 1 && tokens[1].equals("0")) {
			i = 1;
		} 
		else{
			i++;
		}
//		UNumber power = Math.pow(10, -i)*5;   change
		//since the UNumber doesn't support built in math methods we must add
		//new set of code to get the value for the variable.
		int y = 1;
		int xy = 5;
		int z1 =10;
		UNumber mul = new UNumber(xy);
		UNumber z = new UNumber(z1);
		UNumber power1 = new UNumber(y);
		// multiplying the value in a loop instead of using the math.pow() built in method.
		for (int j = 0; j < i; j++) {
			 power1.mpy(z);
		}
		UNumber power = new UNumber(y);
		power.div(power1);
		mul.mpy(power);
		return mul;
	}
}