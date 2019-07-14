package calculator;
import java.lang.Math;
import java.math.BigInteger;
/**
 * <p> Title: RoundedTerm Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * 
 * @author K V MURALI KRISHNA
 *
 * @version 1.00	2019-02-03 MEASURE_TERM ROUNDING AND ERROR_TERM ROUNDING USING ROUNDED_TERM CLASS.
 * 
 */
public class RoundedTerm{
	/**
	 * constructor for the rounded term.
	 */
	RoundedTerm() {

}
	/**
	 * rounding masure_term using rounde_error_term.
	 * @param m_term
	 * @param e_term
	 * @return string of measure term or error term
	 */
	private String measure_round(String m_term, String e_term) {
		System.out.println("measure in measure round " + m_term);
		System.out.println("error  in measure round " + e_term);
		String measure_term = m_term;
		String error_term = e_term;
		if (error_term.indexOf(".") > 0) {
			if (measure_term.indexOf(".") > 0) {
				int roundlength_errorterm = (error_term.substring(
					error_term.indexOf(".")+1, error_term.length())).length();
				String measure_term_before_decimal = measure_term.substring(
					0,measure_term.indexOf("."));
				String measure_term_after_decimal = measure_term.substring(
					measure_term.indexOf(".")+1, measure_term.length());
				if (roundlength_errorterm >= measure_term_after_decimal.length()) {
					return measure_term;
				}
				int rounding_value = Character.getNumericValue(
					measure_term_after_decimal.charAt(roundlength_errorterm));
				if(rounding_value >= 5) {
					rounding_value = Character.getNumericValue(
					measure_term_after_decimal.charAt(roundlength_errorterm -1))+1;
					if(rounding_value > 9.0) {
						return Double.toString(Double.parseDouble(measure_term_before_decimal)+1);
					}
				} else {
					rounding_value = Character.getNumericValue(
					measure_term_after_decimal.charAt(roundlength_errorterm -1));
				}
				return measure_term_before_decimal+"."+
				measure_term_after_decimal.substring(0,roundlength_errorterm - 1)+
				Integer.toString(rounding_value);	
			} else {
				int roundlength_errorterm = error_term.substring(
					error_term.indexOf(".")+1, error_term.length()).length();
				int index = measure_term.length() - roundlength_errorterm;
				int rounding_value = Character.getNumericValue(
					measure_term.charAt(index+1));
				if(rounding_value >= 5) {
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index))+1;
				} else {
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index));
				}
				String temp = measure_term.substring(0,index)+
				Integer.toString(rounding_value);
				int m = measure_term.length();
				int t = temp.length();
				for(int i = 0; i < m-t; i++) {
					temp = temp+"0";
				}
				return temp;
			}
		} else {
			if(measure_term.indexOf(".") > 0) {
				int roundlength_errorterm = error_term.length();
				String measure_term_before_decimal = measure_term.substring(
						0,measure_term.indexOf("."));
				int index = measure_term_before_decimal.length() - roundlength_errorterm;
				if(index < 0) { //measure less than error
					return measure_term.substring(0,measure_term.indexOf("."));
				}
				int rounding_value = Character.getNumericValue(
					measure_term.charAt(index+1));
				
				if(index == 0) { //same digit numbers
					if(rounding_value == -1) {
						String temp = measure_term.substring(measure_term.indexOf(".")+1
								,measure_term.indexOf(".")+2);
						if(Double.parseDouble(temp)>=5.0) {
							Double res=  Double.parseDouble(measure_term.substring(0,measure_term.indexOf(".")))+1;
							return Double.toString(res);
						}else {
							return measure_term.substring(0,measure_term.indexOf("."));
						}
						
					}
					if(rounding_value >= 5)
					rounding_value = Character.getNumericValue(measure_term_before_decimal.charAt(index))+1;
					String temp = Integer.toString(rounding_value);
					for(int i = 0; i < measure_term_before_decimal.length()-1; i++) {
						temp = temp+"0";
					}
					return temp;
					
				} else if(rounding_value >= 5) { //measure value has more digits than error
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index))+1;
					//condition 10
					if (rounding_value == 10) { //if rounded value has 9 digit then.
						int rounding_value_temp = Character.getNumericValue(
								measure_term.charAt(index-1))+1;
						rounding_value = 0;
						String temp = measure_term_before_decimal.substring(0,index-1)+rounding_value_temp+
								Integer.toString(rounding_value);
						int m = measure_term_before_decimal.length();
						int t = temp.length();
						for(int i = 0; i < m-t; i++) {
							temp = temp+"0";
						}
						return temp;
					}
				}
				
				else {
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index));
				}
				String temp = measure_term_before_decimal.substring(0,index)+
				Integer.toString(rounding_value);
				int m = measure_term_before_decimal.length();
				int t = temp.length();
				for(int i = 0; i < m-t; i++) {
					temp = temp+"0";
				}
				return temp+".0";
			} else {
				int roundlength_errorterm = error_term.length();
				int index = measure_term.length() - roundlength_errorterm;
				int rounding_value = Character.getNumericValue(
					measure_term.charAt(index+1));
				if(rounding_value >= 5) {
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index))+1;
				} else {
					rounding_value = Character.getNumericValue(
					measure_term.charAt(index));
				}
				String temp = measure_term.substring(0,index)+
				Integer.toString(rounding_value);
				int m = measure_term.length();
				int t = temp.length();
				for(int i = 0; i < m-t; i++) {
					temp = temp+"0";
				}
				return temp+".0"; 
			}
		}
	}
//*********************************************************************
	/**
	 * checking E in number.
	 * returning a boolean value 
	 * if present then true otherwise false.
	 * 
	 * @param str
	 * @return
	 */
	private Boolean check_e(String str) {
		if(str.indexOf("e") >= 0) {
			return true;
		} else if(str.indexOf("E") >= 0) {
			return true;
		}
		return false;
	}
//*********************************************************************
	/**
	 * converting the e to number.
	 * if the scientifc notation e is present then we convert it into a number.
	 * 
	 * @param str
	 * @return converted string
	 */
	private String convert_e_to_number(String str) {
		String string = str;
		int index = 0;
		if(string.indexOf("e") > 0) {
			index = string.indexOf("e");
//			System.out.println(index);
		} else {
			index = string.indexOf("E");
//			System.out.println(index+" index");
		}
		String string1 = string.substring(0,index);
		Double number = Double.parseDouble(string1);
		String string2 = string.substring(index+1,string.length());
//		System.out.println(string2+" power");
		int power = Integer.parseInt(string2);
		Double convertednumber = number*Math.pow(10, power);
		string = String.format ("%.16f", convertednumber);
		// string = Double.toString(converted number);
		return string;
	}
//*********************************************************************
	/**
	 * rounding the error term.
	 * checking decimal **if decimal exists goes into if otherwise into else con
	 * @param str
	 * @return string of rounded error
	 */
	private String round_error(String str) {
		String string = str;
		int n2 = string.indexOf(".");
		if(n2 > 0) { 
			String string_before_decimal = string.substring(0, string.indexOf("."));
			String string_after_decimal = string.substring(string.indexOf(".")+1, string.length());
			int increasing_string = Integer.parseInt(string_before_decimal.substring(0,1));
			Boolean flag  = false;
			int index = 0;
			if (string_before_decimal.equals("0")) {
		//if the initial(before decimal) string is equal to zero then --> 0.1121 
				for(int i = 0; i <string_after_decimal.length(); i++) {
					int each_number = Character.getNumericValue(string_after_decimal.charAt(i));
					if (each_number > 0) {
						index = i;
						increasing_string = each_number;
						flag = true;
						break;
					} 
				}
					// ------------------------------------------
				for(int i = index+1; i <string_after_decimal.length(); i++) {
					int each_number = Character.getNumericValue(string_after_decimal.charAt(i));
					if (each_number > 0) {
						increasing_string = increasing_string+1;
						System.out.println(increasing_string+ " increasing_string");
						flag = true;
						break;
					} 
				}

	//if there is value before decimal point then this will follows
			} else {
				for(int i = 0; i <string_before_decimal.length(); i++) {
					int each_number = Character.getNumericValue(string_before_decimal.charAt(i));
					if (each_number > 0) {
						increasing_string = increasing_string+1;
						flag = true;
						break;
					}
				}
				}
		// if there are only zeros after the number 50000.121 this 
		//will check the next number after decimal.
			if (!flag) {
				for(int i = 1; i <string_after_decimal.length(); i++) {
					int each_number = Character.getNumericValue(string_after_decimal.charAt(i));
					if (each_number > 0) {
						increasing_string = increasing_string+1;
						break;
					}
				}
		//if before decimal is zero this will check and add zeros after the decimal and string_before_decimal it.
			} else {
				// System.out.println(index);
				if(string_before_decimal.equals("0")) {
					String newstring_before_decimal = "";
					for (int i =0;i<index;i++) {
						newstring_before_decimal = newstring_before_decimal + "0";
					}
					string_after_decimal = newstring_before_decimal+Integer.toString(increasing_string);
				} else {
					string_after_decimal = "0";
				} 

			}
			String newstring = "";
		// final concatenation based on number.
			if (!string_before_decimal.equals("0")) {
				newstring = Integer.toString(increasing_string);
				for (int i = 0; i < n2-1; i++ ) {
					newstring = newstring+"0";
				}
			} else {
				newstring = "0";
			}
			if (Integer.parseInt(string_after_decimal) > 9) {
				return Integer.toString(Integer.parseInt(string_after_decimal)/10);
			} else {
				return newstring+"."+string_after_decimal;
			}

	//no decimal point then this will execute this.
		} else {
			int increasing_string = Integer.parseInt(string.substring(0,1));
			for(int i = 1; i <string.length(); i++) {
				int each_number = Character.getNumericValue(string.charAt(i));
				if (each_number > 0) {
					increasing_string = increasing_string+1;
					break;
				}
			}
			String newstring = Integer.toString(increasing_string);
			for (int i= 1;i < string.length() ;i++ ) {
				newstring = newstring+"0";
			}
			return newstring;
		}
	}
//*********************************************************************

	/**
	 * rounding error term and measure term.
	 * 
	 * @param measure_term
	 * @param error_term
	 * @return array of strings
	 */
	public String[] roundedTerm(String measure_term, String error_term) {
		String result[] = new String[2];
		String string = error_term;
		String measure = measure_term;
		String roundederror = "";
		System.out.println(string+"  came error");
		System.out.println(measure+"  came measure");
		if(check_e(string)) {
			string = convert_e_to_number(string);
			System.out.println(string+"  convert_e");
			if (Double.parseDouble(string) == 0) {
				roundederror = 0.0+"";
			} else {
				if(string.indexOf(".")> 0) {
					String string_after_decimal = string.substring(string.indexOf(".")+1, string.length());
					BigInteger num_after_decimal = new BigInteger(string_after_decimal);
					BigInteger zero = new BigInteger("0");
					if (zero.compareTo(num_after_decimal) == 0) {
							string = string.substring(0,string.indexOf("."));
							roundederror = round_error(string);
					} else {
						roundederror = round_error(string);
					}
				} else {
					roundederror = round_error(string);
				}
			}
		} else  {
			if(string.indexOf(".")> 0) {
				String string_after_decimal = string.substring(string.indexOf(".")+1, string.length());
				BigInteger num_after_decimal = new BigInteger(string_after_decimal);
				BigInteger zero = new BigInteger("0");
				if (zero.compareTo(num_after_decimal) == 0) {
						string = string.substring(0,string.indexOf("."));
						roundederror = round_error(string);
				} else {
					roundederror = round_error(string);
				}
				
			} else {
				roundederror = round_error(string);
			}
		}
		System.out.println(roundederror+"  roundeerror");
		if(roundederror.indexOf(".") > 0) {
			String rounder_after_decimal = roundederror.substring(roundederror.indexOf(".")+1, roundederror.length());
			if (Integer.parseInt(rounder_after_decimal) == 0) {
				roundederror = roundederror.substring(0, roundederror.indexOf("."));
			}
		}
		result[1] = roundederror;
		if (roundederror.equals("0")) {
			result[0] = measure_term;
			return result;
		}
		if(check_e(measure)) {
			measure = convert_e_to_number(measure);
		}
		result[0] = measure_round(measure,roundederror);
		System.out.println(result[0]+"  roundedmeasure");
		return result;
	}
}