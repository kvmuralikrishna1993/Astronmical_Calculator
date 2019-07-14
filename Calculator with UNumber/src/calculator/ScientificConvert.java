package calculator;
import java.math.*;
/**
 * <p> Title: ScientificConvert Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that converts the scientific value into a numeric value. </p>
 * 
 * 
 * @author K V MURALI KRISHNA
 * @version 1.00	2019-02-03 CONVERTING TERM, BASED ON MEASURE_TERM INTO SECIENTIFIC TERMS USING SCIENTIFICCONVERT CLASS.
 * 
 */
/**
 * Class for scientific convert.
 */
class ScientificConvert {
	/**
	 * constructor for the Scientific convert class.
	 */
    ScientificConvert() {

    }
    /**
     * Checking the error term for any scientific notation in the given value.
     * 
     * @param input
     * @return converted input
     */
    public String checkErrorTerm(double input) {
    	// checking the value of error term and returning 0.
    	if(input == 0) {
    		return "0.0";
    	}
    	//checking the value of error term that contains the scientific notation "e".
        if(input <= 9000 && input >= 0.001 && (input+"").contains("E")){
            return toNumber(input);
        }
        //checking the value of error term that contains the scientific notation "E".
        else if(!(input <= 9000 && input >= 0.001) && !(input+"").contains("E")){
            return toScientific(input);
        }else{
            return input+ "";
        }
    }
    /**
     * Converting the input from number format to scientific notation.
     * 
     * @param num
     * @return converting into scientific notation
     */
    public String toScientific(double num) {
          String result="";
          String sign="";
        int power=(int)(Math.log(num)/Math.log(10));
          if(power < 0) power--;
          double fraction=num/Math.pow(10,power);
        result += fraction + "e" + sign + power;
        return result;
    }
    /**
     * converting the input into number.
     * 
     * @param num
     * @return converting into number form
     */
    public String toNumber(double num){
        double temp = new BigDecimal(num+"").doubleValue();
        return temp +"";
    }
}