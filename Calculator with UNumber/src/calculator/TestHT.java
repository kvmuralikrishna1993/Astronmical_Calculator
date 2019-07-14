package calculator;

/**
 * <p> Title: TestCharCode. </p>
 * 
 * <p> Description: A component of the Calculator application </p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @author K V MURALI KRISHNA
 * 
 * @version 4.00	2017-10-18	Initial baseline
 * @version 4.01	2018-03-08	Implemented the Hohmann transfer equations in the test cases.
 * @version 4.02	2018-03-08	Implemented the period of Hohmann transfer orbit
 * 
 */

public class TestHT {

	/**********
	 * This class roots the execution of the test of the BusinessLogic class.  The application 
	 * tests the class by invoking the class methods and checking the result to see if the results 
	 * are proper.
	 * 
	 */
	
	/*********************************************************************************************/
	
	/**********
	 * The check method compares an Expected String to an Actual String and returns true if the 
	 * Strings match and false otherwise.  In addition, the Strings are displayed to the console
	 * and a message is display stating whether or not there is a difference.  If there is a
	 * difference, the character at the point of the difference in the actual String is replaced
	 * with a "?" and both are displayed making it clear what character is the start of the
	 * difference
	 * 
	 * @param Expected	The String object of the expected value
	 * @param Actual		The String object of the actual value
	 */
	private static boolean check(String expected, String actual) {
		// Display the input parameters
		System.out.println("***Expected String");
		System.out.println(expected);
		System.out.println("***Actual String");
		System.out.println(actual);
		
		// Check to see if there is a difference
		int lesserLength = expected.length();
		if (lesserLength > actual.length()) lesserLength = actual.length();
		int ndx = 0;
		while (ndx < lesserLength && expected.charAt(ndx) == actual.charAt(ndx))
			ndx++;
		
		// Explain why the loop terminated and if there is a difference make it clear to the user
		if (ndx < lesserLength || lesserLength < expected.length() || lesserLength < actual.length()) {
			System.out.println("*** There is a difference!\n" + expected.substring(0, ndx) + "? <-----");
			return false;
		}
		System.out.println("*** There is no difference!\n");
		return true;
	}
	
	/*********************************************************************************************/
	
	/**********
	 * This main method roots the execution of this test.  The method ignores the program
	 * parameters.  After initializing several local variables, it performs a sequence of
	 * tests, displaying information accordingly and tallying the number of successes and
	 * failures.
	 * 
	 * @param args	Ignored by this application.
	 */
	public static void main(String[] args) {
		// Display the header message to the console and initialize local variables
		System.out.println("Test BusinessLogic Class\n");
		int numPassed = 0;
		int numFailed = 0;
		
		
		//1. Perform the major axis of the transfer orbit.
		// R1 = earth distance from sun = 149600000 ± 5e4 kilometer
		// R2 = mars distance from sun = 227920000 ± 5e3 kilometer
		// Add the R1 and R2 to get major axis of the transfer orbit
		
		
		System.out.println("*************************************");
		System.out.println("1.Major axis of the transfer orbit");
		System.out.println("*************************************");
		BusinessLogic test = new BusinessLogic();											// Set up for the test
		
		test.setOperand1("149600000");
		test.setOperand1ErrorTerm("5e4");
		test.setUnitOperand1("kilometer");
		test.setOperand2("227920000");
		test.setOperand2ErrorTerm("5e3");
		test.setUnitOperand2("kilometer");
		
		String answer = test.addition();
		
		System.out.println("Operand1 = 149600000");
		System.out.println("ErrorTerm1 = 5e4");
		System.out.println("Unit1 = kilometer");
		System.out.println("Operand2 = 227920000");
		System.out.println("ErrorTerm2 = 5e3");
		System.out.println("Unit2 = kilometer");
		

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.377520000000001033324454307148698717355728149414062500000000000000000000000000000000000000000000000000E+9 +0.55000E+5 kilometer\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.149600000000000999644728632119949907064437866210937500000000000000000000000000000000000000000000000000E+9 +0.5E+5 kilometer\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.227920000000000033679725675028748810291290283203125000000000000000000000000000000000000000000000000000E+9 +0.5E+4 kilometer\n" + 
				"     operand2ErrorMessage = __0\n" + 
				"     operand2Defined = true\n" + 
				"result = +0.377520000000001033324454307148698717355728149414062500000000000000000000000000000000000000000000000000E+9 +0.55000E+5 kilometer\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		//2. Perform semi-major axis of the transfer orbit
		// To get that divide by 2 with above addition R1 + R2
		// a = R1+R2/2
		
		System.out.println("*****************************************");
		System.out.println("2.Semi-Major axis of the transfer orbit");
		System.out.println("*****************************************");
		test = new BusinessLogic();											// Set up for the test
		
		test.setOperand1("3.7752E8");
		test.setOperand1ErrorTerm("6e4");
		test.setUnitOperand1("kilometer");
		test.setOperand2("2");
		test.setOperand2ErrorTerm("");
		test.setUnitOperand2("");
		
		answer = test.division();
		
		System.out.println("Operand1 = 3.7752E8");
		System.out.println("ErrorTerm1 = 6e4");
		System.out.println("Unit1 = kilometer");
		System.out.println("Operand2 = 2");
		System.out.println("ErrorTerm2 = 0.0");
		System.out.println("Unit2 = No Units Selected");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.188760000000000016662227153574349358677864074707031250000000000000000000000000000000000000000000000000E+9 +0.566286E+5 kilometer\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.377520000000000033324454307148698717355728149414062500000000000000000000000000000000000000000000000000E+9 +0.6E+5 kilometer\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1 +0.3E-8 \n" + 
				"     operand2ErrorMessage = \n" + 
				"     operand2Defined = true\n" + 
				"result = +0.188760000000000016662227153574349358677864074707031250000000000000000000000000000000000000000000000000E+9 +0.566286E+5 kilometer\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
		//3. Perform square of above value a*a
		
		System.out.println("**********************************************");
		System.out.println("3.Square of Major axis of the transfer orbit");
		System.out.println("**********************************************");
		test = new BusinessLogic();
		
		test.setOperand1("1.8876E8");
		test.setOperand1ErrorTerm("7e4");
		test.setUnitOperand1("kilometre");
		test.setOperand2("1.8876E8");
		test.setOperand2ErrorTerm("7e4");
		test.setUnitOperand2("kilometre");
		
		answer = test.multiplication();
		
		System.out.println("Operand1 = 1.8876E8");
		System.out.println("ErrorTerm1 = 7e4");
		System.out.println("Unit1 = kilometer");
		System.out.println("Operand2 = 1.8876E8");
		System.out.println("ErrorTerm2 = 7e4");
		System.out.println("Unit2 = kilometer");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.356303376000000046137984099914321306861800645066573083236984020860660393736907280981540679931640625000E+17 +0.1E+6 kilometre\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.188760000000000012221335055073723196983337402343750000000000000000000000000000000000000000000000000000E+9 +0.7E+5 kilometre\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.188760000000000012221335055073723196983337402343750000000000000000000000000000000000000000000000000000E+9 +0.2E-11 kilometre\n" + 
				"     operand2ErrorMessage = __0\n" + 
				"     operand2Defined = true\n" + 
				"result = +0.356303376000000046137984099914321306861800645066573083236984020860660393736907280981540679931640625000E+17 +0.1E+6 kilometre\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
		//4. Perform square of above value (a*a) * a 
		
		System.out.println("*********************************************");
		System.out.println("4.Cube of Major axis of the transfer orbit");
		System.out.println("*********************************************");
		test = new BusinessLogic();
				
		test.setOperand1("3.56303376E16");
		test.setOperand1ErrorTerm("2e5");
		test.setUnitOperand1("kilometre-2");
		test.setOperand2("1.8876E8");
		test.setOperand2ErrorTerm("7e4");
		test.setUnitOperand2("kilometre");
				
		answer = test.multiplication();
				
		System.out.println("Operand1 = 3.56303376E16");
		System.out.println("ErrorTerm1 = 2e5");
		System.out.println("Unit1 = kilometre-2");
		System.out.println("Operand2 = 1.8876E8");
		System.out.println("ErrorTerm2 = 7e4");
		System.out.println("Unit2 = kilometre");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.672558252537600073892449381673943353581011573806692181043019249098158240940392715856432914733886718750E+25 +0.3E+6 kilometre-2\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.356303376000000016077251530077774077653884887695312500000000000000000000000000000000000000000000000000E+17 +0.2E+6 kilometre-2\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.188760000000000012221335055073723196983337402343750000000000000000000000000000000000000000000000000000E+9 +0.1E-19 kilometre\n" + 
				"     operand2ErrorMessage = __0\n" + 
				"     operand2Defined = true\n" + 
				"result = +0.672558252537600073892449381673943353581011573806692181043019249098158240940392715856432914733886718750E+25 +0.3E+6 kilometre-2\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
				
		//5. Find the period of the Hohmann Transfer Orbit:
		//P(Hohmann transfer) = sqrt(4pi × a3 km3/ GM km3/s2)
		// find pi * pi
		
		System.out.println("*************************************");
		System.out.println("5. Square of Pi");
		System.out.println("*************************************");
		test = new BusinessLogic();
				
		test.setOperand1("3.14");
		test.setOperand1ErrorTerm("");
		test.setUnitOperand1("");
		test.setOperand2("3.14");
		test.setOperand2ErrorTerm("");
		test.setUnitOperand2("");
				
		answer = test.multiplication();
				
		System.out.println("Operand1 = 3.14");
		System.out.println("ErrorTerm1 = 0.0");
		System.out.println("Unit1 = No units Selected");
		System.out.println("Operand2 = 3.14");
		System.out.println("ErrorTerm2 = 0.0");
		System.out.println("Unit2 = No units Selected");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.985960000000000078088646660035011973403931240019076106987987954261010159484612813685089349746704101563E+1 +0.E+0 \n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.314000000000000012434497875801753252744674682617187500000000000000000000000000000000000000000000000000E+1 +0.E+0 \n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.314000000000000012434497875801753252744674682617187500000000000000000000000000000000000000000000000000E+1 +0.E+0 \n" + 
				"     operand2ErrorMessage = \n" + 
				"     operand2Defined = true\n" + 
				"result = +0.985960000000000078088646660035011973403931240019076106987987954261010159484612813685089349746704101563E+1 +0.E+0 \n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
	//6. Perform multiplication of above value 4*pi^2
		
		System.out.println("*************************************");
		System.out.println("6.Multiply 4 with square of pi");
		System.out.println("*************************************");
		test = new BusinessLogic();
		
		test.setOperand1("4");
		test.setOperand1ErrorTerm("");
		test.setUnitOperand1("");
		test.setOperand2("9.8596");
		test.setOperand2ErrorTerm("");
		test.setUnitOperand2("");
				
		answer = test.multiplication();
				
		System.out.println("Operand1 = 4");
		System.out.println("ErrorTerm1 = 0.0");
		System.out.println("Unit1 = No Units Selected");
		System.out.println("Operand2 = 9.8956");
		System.out.println("ErrorTerm2 = 0.0");
		System.out.println("Unit2 = No units Selected");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.394384000000000014551915228366851806640625000000000000000000000000000000000000000000000000000000000000E+2 +0.E+0 \n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1 +0.E+0 \n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.985960000000000036379788070917129516601562500000000000000000000000000000000000000000000000000000000000E+1 +0.E+0 \n" + 
				"     operand2ErrorMessage = \n" + 
				"     operand2Defined = true\n" + 
				"result = +0.394384000000000014551915228366851806640625000000000000000000000000000000000000000000000000000000000000E+2 +0.E+0 \n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
				
		//7. Perform square of above value z = 4pi^2 * a^3
		
		System.out.println("**********************************************************************************");
		System.out.println("7.Perform Multiplication of 4pi square with cube of semi-major axis of the orbit");
		System.out.println("**********************************************************************************S");
		test = new BusinessLogic();
		
		test.setOperand1("39.4384");
		test.setOperand1ErrorTerm("");
		test.setUnitOperand1("");
		test.setOperand2("6.725582525376001E24");
		test.setOperand2ErrorTerm("3e5");
		test.setUnitOperand2("kilometre-3");
				
		answer = test.multiplication();
		
		System.out.println("Operand1 = 39.4384");
		System.out.println("ErrorTerm1 = 0.0");
		System.out.println("Unit1 = No Units Selected");
		System.out.println("Operand2 = 6.725582525376001E24");
		System.out.println("ErrorTerm2 = 3e5");
		System.out.println("Unit2 = kilometer-3");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.265246213868788873949706194800092857969665142674610229522880189279021578840911388397216796875000000000E+27 +0.26524627E+6 \n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.394384000000000014551915228366851806640625000000000000000000000000000000000000000000000000000000000000E+2 +0.E+0 \n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.672558252537600065323886155965737998485565185546875000000000000000000000000000000000000000000000000000E+25 +0.1E-20 kilometre-3\n" + 
				"     operand2ErrorMessage = __0\n" + 
				"     operand2Defined = true\n" + 
				"result = +0.265246213868788873949706194800092857969665142674610229522880189279021578840911388397216796875000000000E+27 +0.26524627E+6 \n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();

		
		//8. GM = 1.327e11 ± 5e7 km3/s2
		// A = z/GM
				
		System.out.println("*************************************");
		System.out.println("8.Square of Period of Human Transfer Orbit");
		System.out.println("*************************************");
		test = new BusinessLogic();
				
		test.setOperand1("2.652462138687889E26");
		test.setOperand1ErrorTerm("6e-2");
		test.setUnitOperand1("kilometre-2");
		test.setOperand2("1.327e11");
		test.setOperand2ErrorTerm("5e7");
		test.setUnitOperand2("kilometre-3/seconds-2");
				
		answer = test.division();
				
		System.out.println("Operand1 = 2.652462138687889E26");
		System.out.println("ErrorTerm1 = 6e-2");
		System.out.println("Unit1 = kilometer-2");
		System.out.println("Operand2 = 1.327e11");
		System.out.println("ErrorTerm2 = 5e7");
		System.out.println("Unit2 = kilometre-3/seconds-2");
		
		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.199884109923730086331728380835986065716501105494038435077050934979351580701940670743945295906079640874E+16 +0.5996523304E+8 kilometre-2\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.265246213868789860423819642164744436740875244140625000000000000000000000000000000000000000000000000000E+27 +0.6E-1 kilometre-2\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.132700000000000017941204077942529693245887756347656250000000000000000000000000000000000000000000000000E+12 +0.3E-7 kilometre-3/seconds-2\n" + 
				"     operand2ErrorMessage = __0\n" + 
				"     operand2Defined = true\n" + 
				"result = +0.199884109923730086331728380835986065716501105494038435077050934979351580701940670743945295906079640874E+16 +0.5996523304E+8 kilometre-2\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		
		//9.Period of Hohmann Transfer Orbit
				
		System.out.println("*************************************");
		System.out.println("9.Period of Hohmann Transfer Orbit");
		System.out.println("*************************************");
		test = new BusinessLogic();
				
		test.setOperand1("1.9988411E15");
		test.setOperand1ErrorTerm("6E7");
		test.setUnitOperand1("seconds-2");
				
		answer = test.root();
				
		System.out.println("Operand1 : 1.9988411E15");
		System.out.println("ErrorTerm1 : 6E7");
		System.out.println("Unit1 : seconds-2");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.447084007765879266077531307536122459156402442204125691205263137817382812500000000000000000000000000000E+8 +0.5E+0 seconds-2\n******************\n" + 
				"*\n" + 
				"* Business Logic\n" + 
				"*\n" + 
				"******************\n" + 
				"operand1 = +0.199884110000000992623997968621551990509033203125000000000000000000000000000000000000000000000000000000E+16 +0.6E+8 seconds-2\n" + 
				"     operand1ErrorMessage = __0\n" + 
				"     operand1Defined = true\n" + 
				"operand2 = +0.E+0 +0.E+0 \n" + 
				"     operand2ErrorMessage = \n" + 
				"     operand2Defined = false\n" + 
				"result = +0.447084007765879266077531307536122459156402442204125691205263137817382812500000000000000000000000000000E+8 +0.5E+0 seconds-2\n" +
				"     resultErrorMessage = __0\n" + 
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println("******************************************");
		System.out.println("Number of tests passed: " + numPassed);
		System.out.println();
		System.out.println("Number of tests failed: " + numFailed);
		System.out.println("******************************************");
	}
}
