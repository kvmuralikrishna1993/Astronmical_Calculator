package calculator;

import java.text.DecimalFormat;
import java.util.HashMap;
import calculator.UNumber;

/**
 * <p> Title: Units Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that assigns Units to the operands in the calculator </p>
 * 
 * 
 * @author K V MURALI KRISHNA
 *
 * @version 1.00	2019-03-05 The JavaFX-based GUI implementation of the units and its conversions internally based on the operands.
 * 
 */
public class Units {
	public Units() {

	}
//	private String [] arrayUnits = {"- No Units Selected -","kilometer", "meter", "feet", "hours", "minutes",
//			"seconds","grams","pounds","kilograms","meter/seconds","kilometer/hours","meter/seconds-2",
//			"kilometer/hours-2","Newton","Dyne","meter-3/kilograms.seconds-2","meter-2","kilometer-2","feet-2",
//			"kilograms.meter/seconds","grams.centimeter/seconds"};
	private HashMap<String, Double> category = new HashMap<String, Double>();
	private DecimalFormat df = new DecimalFormat("#.##");
	//loading units as per category 
	public void loadcategory() {
		category.put("No Units",1.0);
		category.put("kilometer",12.0);
		category.put("meter",12.0);
		category.put("feet",12.0);
		category.put("hours",4.0);
		category.put("minutes",4.0);
		category.put("seconds",4.0);
		category.put("grams",5.0);
		category.put("pounds",5.0);
		category.put("kilograms",5.0);
		category.put("seconds-2",16.0);
		category.put("meter/seconds", Double.parseDouble(df.format(12.0/4.0)));
		category.put("kilometer/hours",Double.parseDouble(df.format(12.0/4.0)));
		category.put("meter/seconds-2",Double.parseDouble(df.format(12.0/16.0)));
		category.put("kilometer/hours-2",Double.parseDouble(df.format(12.0/16.0)));
		category.put("Newton",60.0/16);
		category.put("Dyne", 60.0/16);
		category.put("meter-3/kilograms.seconds-2",Double.parseDouble(df.format(1728.0/80.0)));
		category.put("kilometer-3/seconds-2",Double.parseDouble(df.format(1728.0/16.0)));
		category.put("meter-2",144.0);
		category.put("kilometer-2",144.0);
		category.put("feet-2",144.0);
		category.put("kilograms.meter/seconds",Double.parseDouble(df.format(60.0/4.0)));
		category.put("grams.centimeter/seconds",Double.parseDouble(df.format(60.0/4.0)));
		category.put("meter-3",1728.0);
		category.put("kilometer-3",1728.0);
		category.put("feet-3",1728.0);
	}
//checking unit in the hash_map by using keys.
//	private Boolean checkunitkeys(String unit) {
//		for (String key : category.keySet()) {
//			if(key.equals(unit)) {
//				return true;
//			}
//		}
//		return false;
//	}
	/**
	 * Checking the unit value for the operands.
	 * 
	 * @param unit
	 * @return true or false
	 */
	public Boolean checkunitvalues(double unit) {
		for (Double value : category.values()) {
			if(value.equals(unit)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * validating addition,subtraction and square root
	 * 
	 * @param selectedItems
	 * @return true or false
	 */
	public Boolean as_ValidateUnits(String[] selectedItems) {
		String unit1 = selectedItems[0];
		String unit2 = selectedItems[1];
		if(unit1.equals("- No Units Selected -") && unit2.equals("- No Units Selected -")) {
			return true;
		}
		if(unit1.equals("- No Units Selected -")) {
			return false;
		}
		if(unit2.equals("- No Units Selected -")) {
			return false;
		}
		loadcategory();
		if(unit1.equals(unit2)) {
			System.out.println(unit1+" "+unit2);
			return true;
		}
		if(category.get(unit1).equals(category.get(unit2))) {
			return true;
		}
		return false;
	}
	/**
	 * validating multiplication
	 * 
	 * @param selectedItem
	 * @return true or false
	 */
	public Boolean m_ValidateUnits(String[] selectedItems) {
		String unit1 = selectedItems[0];
		String unit2 = selectedItems[1];
		if(unit1.equals("- No Units Selected -") && unit2.equals("- No Units Selected -")) {
			return true;
		}
		loadcategory();
		if(unit1.equals("- No Units Selected -")) {
			double val = Double.parseDouble(df.format(category.get(unit2)));
			return checkunitvalues(val);
		}
		if(unit2.equals("- No Units Selected -")) {
			double val = Double.parseDouble(df.format(category.get(unit1)));
			return checkunitvalues(val);
		}
		double val = Double.parseDouble(df.format(category.get(unit1)*category.get(unit2)));
		System.out.println(val+"      <------val");
		return checkunitvalues(val);

	}
	/**
	 * validating division
	 * 
	 * @param selectedItem
	 * @return true or false
	 */
	public Boolean d_ValidateUnits(String[] selectedItems) {
		String unit1 = selectedItems[0];
		String unit2 = selectedItems[1];
		if(unit1.equals("- No Units Selected -") && unit2.equals("- No Units Selected -")) {
			return true;
		}
		loadcategory();
		if(unit1.equals("- No Units Selected -")) {
			double val = Double.parseDouble(df.format(category.get(unit2)));
			return checkunitvalues(1.0/val);
		}
		if(unit2.equals("- No Units Selected -")) {
			double val = Double.parseDouble(df.format(category.get(unit1)));
//			System.out.println(val+"  val");
			return checkunitvalues(val);
		}
		if(unit1.equals(unit2)) {
			return true;
		}
		double val = Double.parseDouble(df.format(category.get(unit1)/category.get(unit2)));
		System.out.println(val+"  --val---");
		if(val == 1.0) {
			return true;
		}
		return checkunitvalues(val);


	}
	/**
	 * validating square_root
	 * 
	 * @param selectedItem
	 * @return true or false
	 */
	public Boolean sq_ValidateUnits(String selectedItem) {
		String unit = selectedItem;
		if(unit.equals("- No Units Selected -")) {
			return true;
		}
		loadcategory();
		if(unit.equals("meter-2")||unit.equals("feet-2")||unit.equals("kilometer-2")||unit.equals("seconds-2")) {
			return true;
		}
		return false;

	}
	public UNumber convertUnits(String selectedItem1,String selectedItem2) {
	 	String unit1 = selectedItem1;
		String unit2 = selectedItem2;
		switch(unit1){
			//converting the units kilometer into (meter or feet).
			case "kilometer":
				if(unit2.equals("meter")) { 
					UNumber multiplier = new UNumber(1000);
					return multiplier;
				}else if(unit2.equals("feet")) {
					UNumber multiplier = new UNumber(328084);
					return multiplier;
				} else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units meter into (kilometer or feet).
			case "meter": 
				if(unit2.equals("kilometer")) {
					UNumber multiplier = new UNumber(0.001);
					return multiplier;
				}else if(unit2.equals("feet")) {
					UNumber multiplier = new UNumber(3.28084);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units feet into (kilometer or meter)
			case "feet":
				if(unit2.equals("kilometer")) {
					UNumber multiplier = new UNumber(0.0003048);
					return multiplier;
				}else if(unit2.equals("meter")) {
					UNumber multiplier = new UNumber(0.3048);
					return multiplier;
				} else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units hours into (minutes or seconds)
			case "hours":
				if(unit2.equals("minutes")) {
					UNumber multiplier = new UNumber(60);
					return multiplier;
				}else if(unit2.equals("seconds")) {
					UNumber multiplier = new UNumber(3600);
					return multiplier;
				}else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units minutes into (hours or seconds)
			case "minutes":
				if(unit2.equals("hours")) {
					UNumber multiplier = new UNumber(0.0166667);
					return multiplier;
				}else if(unit2.equals("seconds")) {
					UNumber multiplier = new UNumber(60);
					return multiplier;
				}else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units seconds into (hours or minutes)
			case "seconds":
				if(unit2.equals("hours")) {
					UNumber multiplier = new UNumber(0.0166667);
					return multiplier;
				}else if(unit2.equals("minutes")) {
					UNumber multiplier = new UNumber(0.000277778);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units grams into (kilograms or pounds)
			case "grams":
				if(unit2.equals("kilograms")) {
					UNumber multiplier = new UNumber(0.00220462);
					return multiplier;
				}else if(unit2.equals("pounds")) {
					UNumber multiplier = new UNumber(0.001);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units pounds into (grams or kilograms)
			case "pounds":
				if(unit2.equals("grams")) {
					UNumber multiplier = new UNumber(453.592);
					return multiplier;
				}else if(unit2.equals("kilograms")) {
					UNumber multiplier = new UNumber(0.453592);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units kilograms into (grams or pounds)
			case "kilograms":
				if(unit2.equals("grams")) {
					UNumber multiplier = new UNumber(1000);
					return multiplier;
				} else if(unit2.equals("pounds")) {
					UNumber multiplier = new UNumber(2.20462);
					return multiplier;
				} else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units into meter/seconds into kilometer/hours
			case "meter/seconds":
				if(unit2.equals("kilometer/hours")) {
					UNumber multiplier = new UNumber(3.6);
					return multiplier;
				} else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units kilometer/hours into meter/second
			case "kilometer/hours":
				if(unit2.equals("meter/seconds")) {
					UNumber multiplier = new UNumber(0.277778);
					return multiplier;
				} else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units meter/seconds-2 into kilometer/hours-2
			case "meter/seconds-2":
				if(unit2.equals("kilometer/hours-2")) {
					UNumber multiplier = new UNumber(12960);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units kilometer/hours-2 into meter/seconds-2
			case "kilometer/hours-2":
				if(unit2.equals("meter/seconds-2")) {
					UNumber multiplier = new UNumber(7.716049383);
					return multiplier;
				}else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units newton into Dyne
			case "Newton":
				if(unit2.equals("Dyne")) {
					UNumber multiplier = new UNumber(100000);
					return multiplier;
				}else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units Dyne into Newton
			case "Dyne":
				if(unit2.equals("Newton")) {
					UNumber multiplier = new UNumber(0.000001);
					return multiplier;
				}else{
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units grams.centimeter/seconds into kilogram.meter/seconds
			case "grams.centimeter/seconds":
				if(unit2.equals("kilograms.meter/seconds")) {
					UNumber multiplier = new UNumber(0.000001);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units kilometer.meter/seconds into grams.centimeter/seconds
			case "kilograms.meter/seconds":
				if(unit2.equals("grams.centimeter/seconds")) {
					UNumber multiplier = new UNumber(10);
					return multiplier;
				}else {
					UNumber multiplier = new UNumber(1);
					return multiplier;
				}
			//converting the units meter-3/kilograms.seconds-2
			case "meter-3/kilograms.seconds-2":
				UNumber multiplier = new UNumber(1);
				return multiplier;
			default:
				return new UNumber(1);
		}
	}
	
	/**
	 * display units for addition and subtraction
	 * 
	 * @param units
	 * @return resultant unit.
	 */
		public String as_displayunits(String[] units) {
			if(units[0].equals("- No Units Selected -") && units[1].equals("- No Units Selected -")) {
				return "- No Units Selected -";
			}
			if(units[0].equals("- No Units Selected -")) {
				return units[1];
			}
			if(units[1].equals("- No Units Selected -")) {
				return units[1];
			}
			return units[1];
		}
		/**
		 * display units for multiplication
		 * 
		 * @param units
		 * @return resultant unit.
		 */
		public String m_displayunits(String[] units) {
			System.out.println(units[0]+" ---> unit1");
			System.out.println(units[1]+" ---> unit2");
			if(units[0].equals("- No Units Selected -") && units[1].equals("- No Units Selected -")) {
				return "- No Units Selected -";
			}else if(units[0].equals("- No Units Selected -")) {
				return units[1];
			} else if(units[1].equals("- No Units Selected -")) {
				return units[0];
			} else if(units[0].equals(units[1])) {
				return units[1]+"-2";
			}else {
				if(units[0].equals("meter") && units[1].equals("meter-2")) {
					return "meter-3";
				}else if(units[1].equals("meter-2") && units[0].equals("meter")) {
					return "meter-3";
				} else if(units[0].equals("kilometer") && units[1].equals("kilometer-2")) {
					return "kilometer-3";
				}else if(units[1].equals("kilometer-2") && units[0].equals("kilometer")) {
					return "kilometer-3";
				}else if(units[0].equals("feet") && units[1].equals("feet-2")) {
					return "feet-3";
				}else if(units[1].equals("feet-2") && units[0].equals("feet")) {
					return "feet-3";
				}else if(units[0].equals("kilograms") && units[1].equals("meter/seconds-2")) {
					return "Newton";
				} else {
					return units[0]+"."+units[1];
				}
			}
		}
		/**
		 * display units for division
		 * 
		 * @param units
		 * @return resultant unit.
		 */
		public String d_displayunits(String[] units) {
			if(units[0].equals("- No Units Selected -") && units[1].equals("- No Units Selected -")) {
				return "- No Units Selected -";
			}else if(units[0].equals("- No Units Selected -")) {
				return "1/"+units[1];
			} else if(units[1].equals("- No Units Selected -")) {
				return units[0];
			} else if(units[0].equals(units[1])) {
				return "No Units";
			} else if(category.get(units[0]).equals(category.get(units[1]))) {
				return "No Units";
			} else {
				if(units[0].equals("meter-2") && units[1].equals("meter")) {
					return "meter";
				}else if(units[0].equals("kilometer-2") && units[1].equals("kilometer")) {
					return "kilometer";
				}else if(units[0].equals("feet-2") && units[1].equals("feet")) {
					return "feet";
				} else if((units[0].equals("kilometer-3") && units[1].equals("kilometer-3/seconds-2"))){
					return "seconds-2";
				}else {
					return units[0]+"/"+units[1];
				}
			}
		}
		/**
		 * display units for square_root.
		 * 
		 * @param selectedItem
		 * @return selected unit
		 */
		public String sq_displayunits(String selectedItem) {
			String unit = selectedItem;
			if(unit.equals("- No Units Selected -")) {
				return unit;
			} else {
				return unit.substring(0,unit.indexOf("-"));
			}
		}
}