package calculator;

//import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * 
 * @author Lynn Robert Carter
 * @author K V MURALI KRISHNA
 * 
 * @version 4.00	2017-10-17	The JavaFX-based GUI for the implementation of a calculator.
 * @version 4.01	2019-02-24	I have added the square root operation in the calculator.
 * @version 4.02	2019-02-27	I have imported the Error Term Recognizer
 * @version 4.03	2019-03-01	Implemented the Roundoff method
 * @version 4.04	2019-03-02	Minor Documentation Update
 * @version 4.05	2019-03-06	Implemented the UNumber in arithmetic operations
 * @version 4.06	2019-03-07	Implemented the Units in UNumber
 * @version 4.07	2019-03-08	Implemented the precision button
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	int x = 0;
	UNumber measuredValue = new UNumber(x);
	UNumber errorTerm = new UNumber(x);
	String unit = "";
	String errorMessage = "";
	String errorMessageET = "";
	String signIndicator="";
	String inputFromConsole = "";
	String inputETFromConsole = "";
	int inputETIndexofError = 0;
	int inputIndexofError = 0;
	UNumber obj = new UNumber();
	Units unit_obj = new Units();
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(UNumber v) {
		measuredValue = v;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = new UNumber(v.measuredValue);
		errorTerm = new UNumber(v.errorTerm);
		unit = new String(v.unit);
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */	
	public CalculatorValue(String s) {
		measuredValue = new UNumber(x);
		signIndicator = s.substring(0,1);
		System.out.println(signIndicator+" __one");
		if(signIndicator.equals("+")||signIndicator.equals("-")) {
			s=s.substring(1,s.length());
			System.out.println(s+" __onebfkj");
		}
		String string = MeasuredValueRecognizer.checkMeasureValue(s);
		if (string.equals("")) {
			if(signIndicator.equals("-")) {
				s = "-" +s;
			}
			if(s.contentEquals("-"))return;
			System.out.println("reached");
//			System.out.println(s+" __this one");
//			double x1 = Double.parseDouble(s);
//			if(s.charAt(0) == '+'  || s.charAt(0) == '-') {
//				s = s.substring(1,s.length());
//			}
//			System.out.println(s+" __this two");
//			measuredValue = convertToUNum(s);
			measuredValue = new UNumber(Double.parseDouble(s));
			inputIndexofError = 0;
			inputFromConsole = "";
			errorMessage = "";
		} else {
			errorMessage = MeasuredValueRecognizer.measuredValueErrorMessage;
			inputFromConsole = MeasuredValueRecognizer.measuredValueInput;
			inputIndexofError = MeasuredValueRecognizer.measuredValueIndexofError; 	
		}
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage + "_" + inputFromConsole + "_" + inputIndexofError;
	}
	
	/*****
	 * Get the error message for error term.
	 */
	public String getErrorMessageforET(){
		return errorMessageET + "_" + inputETFromConsole + "_" + inputETIndexofError;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(UNumber v){
		measuredValue = v;
	}
	/**
	 * Sets the unit.
	 *
	 * @param      selecteditem  The selecteditem
	 */
	public void setUnit(String selecteditem) {
		unit = selecteditem;
	}
	/*****
	 * Get the current error term of a calculator value to a specific long integer
	 */
	public void setErrorTerm(String v){
//		System.out.println(v);
		errorTerm = new UNumber(x);
		String string = ErrorTermRecognizer.checkErrorTerm(v);
		if (string.equals("")) {
//			System.out.println("reached");
//			Double x2 = Double.parseDouble(v);
//			System.out.println(x2);
//			if(v.charAt(0) == '+'  || v.charAt(0) == '-') {
//				v = v.substring(1,v.length());
//			}
			errorTerm = convertToUNum(v);
//			System.out.println(errorTerm);
			inputETIndexofError = 0;
			inputETFromConsole = "";
			errorMessageET = "";
		} else {
//			System.out.println(ErrorTermRecognizer.errorTermErrorMessage);
			errorMessageET = ErrorTermRecognizer.errorTermErrorMessage;
			inputETFromConsole = ErrorTermRecognizer.errorTermInput;
			System.out.println(inputETFromConsole + "-->" + errorMessageET);
			inputETIndexofError = ErrorTermRecognizer.errorTermIndexofError; 	
		}	
	}
	
	/*****
	* Given an string as input, this method converts the representation into UNumber.
	*/
	public UNumber convertToUNum(String str) {
		
			String inputValue = str;
			String[] tokens = new String[2];
			if (inputValue.contains("e")) {
				tokens = inputValue.split("e");
				inputValue = tokens[0];
			} else if (inputValue.contains("E")){
				tokens = inputValue.split("E");
				inputValue = tokens[0];
			}
			
			String digits = "";
			int ndx = 0;
			int exp = 0;
			
			if (tokens[1] != null) {
				if (tokens[1].charAt(0) == '+' || tokens[1].charAt(0) == '-') {
					exp = Integer.parseInt(tokens[1]);
				} else {
					exp = Integer.parseInt(tokens[1]);
				}
			}
//			System.out.println(exp);
			boolean decimalPointFound = false;
			while (ndx < inputValue.length()) {
				if (decimalPointFound) {
					if (inputValue.charAt(ndx) >= '0' && inputValue.charAt(ndx) <= '9') {
						digits += inputValue.charAt(ndx++);
					}
					else {
						System.out.println("*** ERROR *** A non-digit found after a decimal point was found.");
						System.exit(0);
					}
				}
				else {
					if (inputValue.charAt(ndx) == '.') {
						decimalPointFound = true;
						ndx++;
					} 
					else if (inputValue.charAt(ndx) >= '0' && inputValue.charAt(ndx) <= '9') {
						digits += inputValue.charAt(ndx++);
						exp++;
					}
					
					else {
						System.out.println("*** ERROR *** A non-digit that is also not a decimal point was found.");
						System.exit(0);
					}
						
				}
			}
			
			UNumber inputUNumber = new UNumber(digits, exp, true);
			
//			System.out.println(inputUNumber.toDecimalString());
			return inputUNumber;
	}
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue + " " + errorTerm + " " + unit;
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * In this addition we support units and recognize error terms.
	 */
	public void add(CalculatorValue v) {
		System.out.println(unit+" ------->unit1");
		System.out.println(v.unit+" ------>unit2");
		UNumber multiplier = unit_obj.convertUnits(unit,v.unit);
//		String[] s = {unit,v.unit};
//		unit = unit_obj.as_displayunits(s);
		System.out.println(multiplier+" --->factor");
		measuredValue.mpy(multiplier);
		System.out.println(measuredValue+" --->factored measure");
		System.out.println(errorTerm.getDouble()+" cameerror");
		System.out.println(v.errorTerm.getDouble()+" cameverror");
			if (!(errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			RoundOff rou = new RoundOff();
			RoundOff roud = new RoundOff();
			if(errorTerm.getDouble() == 0.0) {
				errorTerm = rou.round(measuredValue);
				System.out.println(errorTerm+" rounded1");
			}
			if(v.errorTerm.getDouble() == 0.0) {
				v.errorTerm = roud.round(v.measuredValue);
				System.out.println(v.errorTerm+" rounded2"); 
			}
		}
//		System.out.println(unit +" unit1");
//		System.out.println(v.unit +" unit2");
		measuredValue.add(v.measuredValue);
		if ((errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			errorTerm = new UNumber(0);
		} else {
			System.out.println(errorTerm+" finalerror");
			System.out.println(v.errorTerm+" finalverror");
			System.out.println(multiplier+" ----> factor");
			errorTerm.mpy(multiplier);
			System.out.println(errorTerm+" ------> after multiply");
			errorTerm.add(v.errorTerm);
		}
		System.out.println(errorTerm+" -----> add");
		errorMessage = "";
	}

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * In this subtraction we support units and recognize error terms.
	 */
	public void sub(CalculatorValue v) {
		UNumber multiplier = unit_obj.convertUnits(unit,v.unit);
//		String[] s = {unit,v.unit};
//		unit = unit_obj.as_displayunits(s);
		measuredValue.mpy(multiplier);
//		System.out.println(errorTerm+" came_error");
//		System.out.println(v.errorTerm+" came_verror");
		if (!(errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			RoundOff rou = new RoundOff();
			RoundOff roud = new RoundOff();
			if(errorTerm.getDouble() == 0.0) {
				errorTerm = rou.round(measuredValue);
//				System.out.println(errorTerm+" rounded1");

			}
			if(v.errorTerm.getDouble() == 0.0) {
				v.errorTerm = roud.round(v.measuredValue);
//				System.out.println(v.errorTerm+" rounded2");
			}
		}
//		System.out.println(errorTerm+" final_error");
//		System.out.println(v.errorTerm+" fina_lverror");
		measuredValue.sub(v.measuredValue);
		if ((errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			errorTerm = new UNumber(0);
		} else {
			errorTerm.mpy(multiplier);
			errorTerm.add(v.errorTerm);
		}
//		System.out.println(errorTerm+" sub");
		errorMessage = "";
	}
	
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * In this multiplication we support units and recognize error terms.
	 */
	public void mpy(CalculatorValue v) {
		UNumber multiplier = unit_obj.convertUnits(unit,v.unit);
//		String[] s = {unit,v.unit};
//		unit = unit_obj.m_displayunits(s);
		measuredValue.mpy(multiplier);
//		System.out.println(errorTerm+" came_error");
//		System.out.println(v.errorTerm+" came_verror");
		if (!(errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			RoundOff rou = new RoundOff();
			RoundOff roud = new RoundOff();
			if(errorTerm.getDouble() == 0.0) {
				errorTerm = rou.round(measuredValue);
//				System.out.println(errorTerm+" rounded1");
			}
			if(v.errorTerm.getDouble() == 0.0) {
				v.errorTerm = roud.round(v.measuredValue);
//				System.out.println(v.errorTerm+" rounded2");
			}
		}
//		System.out.println(errorTerm+" final_error");
//		System.out.println(v.errorTerm+" final_verror");
		measuredValue.mpy(v.measuredValue);
		if ((errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			errorTerm = new UNumber(0);
		} else {
			UNumber absolute =  obj.abs1(measuredValue);
			errorTerm.div(absolute);
			v.errorTerm.div(absolute);
			UNumber value1ErrorTerm = errorTerm;
			UNumber value2ErrorTerm = v.errorTerm;
			value1ErrorTerm.add(value2ErrorTerm);
			value1ErrorTerm.mpy(measuredValue); 
			errorTerm = value1ErrorTerm;
			errorTerm.mpy(multiplier);//factor conversion
		}

		//System.out.println(errorTerm+" mul");
		errorMessage = "";
	}
	
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * In this division  we support units and recognize error terms.
	 */
	public void div(CalculatorValue v) {
		UNumber multiplier = unit_obj.convertUnits(unit,v.unit);
//		String[] s = {unit,v.unit};
//		unit = unit_obj.d_displayunits(s);
		measuredValue.mpy(multiplier);
//		System.out.println(errorTerm+" came_error");
//		System.out.println(v.errorTerm+" came_verror");
		if (!(errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
			RoundOff rou = new RoundOff();
			RoundOff roud = new RoundOff();
			if(errorTerm.getDouble() == 0.0) {
				errorTerm = rou.round(measuredValue);
//				System.out.println(errorTerm+" rounded1");
			}
			if(v.errorTerm.getDouble() == 0.0) {
				v.errorTerm = roud.round(v.measuredValue);
//				System.out.println(errorTerm+" rounded2");
			}
		}
//		System.out.println(errorTerm+" final_error");
//		System.out.println(v.errorTerm+" final_verror");
		if (v.measuredValue.getDouble() != 0.0) {
			measuredValue.div(v.measuredValue);
			if ((errorTerm.getDouble() == 0.0 && v.errorTerm.getDouble() == 0.0)) {
				errorTerm = new UNumber(0);
			} else {
				UNumber absolute =  obj.abs1(measuredValue);
				errorTerm.div(absolute);
				v.errorTerm.div(absolute);
				UNumber value1ErrorTerm = errorTerm;
				UNumber value2ErrorTerm = v.errorTerm;
				value1ErrorTerm.add(value2ErrorTerm);
				value1ErrorTerm.mpy(measuredValue);
				errorTerm = value1ErrorTerm;
				errorTerm.mpy(multiplier); //factor conversion
			}
//			System.out.println(errorTerm+" div");
			errorMessage = "";
		} else {
			measuredValue = new UNumber(x);
			errorMessage = "Division not possible by zero.";
		}
		
	}
	
	
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * In this Square root we support units and recognize error terms.
	 */
	public void root() {
		UNumber zero = new UNumber(x);
//		unit = unit_obj.sq_displayunits(unit);
		UNumberSquareRoot sq = new UNumberSquareRoot();
		System.out.println(measuredValue+" 11111");
		System.out.println(measuredValue.getDouble());
		if (!measuredValue.lessThan(convertToUNum("0"))) {     //here <= we require	
			UNumber absolute = new UNumber(0);
			measuredValue = absolute.abs1(measuredValue);
			if(errorTerm.getDouble() == 0) {
				System.out.println("abc");
				errorTerm = new UNumber(0);
				measuredValue = sq.sqrt(measuredValue);
				errorMessage = "";
			} else {
				if(!(zero.greaterThan(measuredValue))) {
					errorTerm.div(measuredValue);
				}
				measuredValue = sq.sqrt(measuredValue);
				errorTerm.mpy(measuredValue);
				errorTerm.div(new UNumber(2));
				errorMessage = "";
			}
						
		} else{
			measuredValue = new UNumber(x);
			errorTerm = new UNumber(x);
			errorMessage = "number should be positive";
		}
		
	}
}
