package calculator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.ComboBoxListViewSkin;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with 
 * String objects and passes work to other classes to deal with all other aspects of the 
 * computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author K V MURALI KRISHNA
 * 
 * @version 4.00	2017-10-17	The JavaFX-based GUI for the implementation of a calculator.
 * @version 4.01	2019-02-24	I have added the square root operation in the calculator.
 * @version 4.02	2019-02-27	I have imported the Error Term Recognizer
 * @version 4.03	2019-03-01	Implemented the Roundoff method
 * @version 4.04	2019-03-02	Minor Documentation Update
 * @version 4.05	2019-03-06	Implemented Double to UNumber Calculator
 * @version 4.06	2019-03-07	Implemented the Units in the User Interface
 * @version 4.07	2019-03-08	Implemented the Precision value in the User Interface
 */

public class UserInterface {
	//for the precision button.
	private Boolean check; 
	/**********************************************************************************************
	 * 
	 * Attributes
	 * 
	 **********************************************************************************************/
	/*
	 * Constants used to parameterize the graphical user interface. We do not use a
	 * layout manager for this application. Rather we manually control the location
	 * of each graphical element for exact control of the look and feel.
	 */

	// These set the width and positioning of buttons
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("UNumber Calculator with Error terms and Units");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField(); // No initial value
	private Label label_Operand1ErrorTerm = new Label("Error Term");
	private TextField text_Operand1ErrorTerm = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField(); // No initial value
	private Label label_Operand2ErrorTerm = new Label("Error Term");
	private TextField text_Operand2ErrorTerm = new TextField();
	private Label label_Result = new Label("Result");
	private Label label_ResultErrorTerm = new Label("Error Term");
	private TextField text_Result = new TextField(); // No initial value
	private TextField text_ResultErrorTerm = new TextField();
	private Label label_plusminus_operand1 = new Label("\u00B1");
	private Label label_plusminus_operand2 = new Label("\u00B1");
	private Label label_plusminus_result = new Label("\u00B1");
	private TextField text_Result_Unit = new TextField(); // No initial value
	private Label labelSelectAUnit1 = new Label("Select a Unit");
	private Label labelSelectAUnit2 = new Label("Select a Unit");
	private Label labelResultUnit = new Label("Units"); 
	// These are the buttons that perform the calculator operations when pressed
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("*");
	private Button button_Div = new Button("/");
	private Button button_Root = new Button("\u221A");
	// These are the labels that are used to display error messages. Since they are
	// empty, nothing
	// shows on the user interface.
	
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand1ET = new Label("");
	private Text errOperand1MVPart1 = new Text();
	private Text errOperand1MVPart2 = new Text();
	private Text errOperand1ETPart1 = new Text();
	private Text errOperand1ETPart2 = new Text();
	private TextFlow errOperand1MeasuredValue;
	private TextFlow errOperand1ErrorTerm;
	private Label label_errOperand2 = new Label("");
	private Label label_errOperand2ET = new Label("");
	private Text errOperand2MVPart1 = new Text();
	private Text errOperand2MVPart2 = new Text();
	private Text errOperand2ETPart1 = new Text();
	private Text errOperand2ETPart2 = new Text();
	private TextFlow errOperand2MeasuredValue;
	private TextFlow errOperand2ErrorTerm;
	private Label label_errResult = new Label("");
	private Label label_errResultUnit = new Label("");
	
	//precision buttons and label
	private Button precision = new Button("Precision");
    private Label precision_check = new Label("");
	
	private String [] arrayUnits = {"- No Units Selected -","kilometer", "meter", "feet", "hours", "minutes",
			"seconds","seconds-2","grams","pounds","kilograms","meter/seconds","kilometer/hours","meter/seconds-2",
			"kilometer/hours-2","Newton","Dyne","meter-3/kilograms.seconds-2","meter-2","kilometer-2","feet-2","meter-3","kilometer-3","feet-3",
			"kilogram.meter/seconds","gram.centimeter/seconds","kilometer-3/seconds-2"};	
	public ComboBox <String> comboboxSelectUnit1 = new ComboBox <String>();
	public ComboBox <String> comboboxSelectUnit2 = new ComboBox <String>();
	boolean boolUpdatingComboBox = false;
		
	// The select list state variable keeps track of how many previously selected
	// items are at the top of the enhanced select list
	private int selectListState = 0;
	
	// This string hold the first item string for doing a reset;
	public String stringDefaultItem1 = "";
	public String stringDefaultItem2 = "";
	
	
	// The following are the three most recent selections
	private String stringSelectItem11 = "";
	private int intFoundIndex11 = 0;
	private String stringSelectItem12 = "";
	private int intFoundIndex12 = 0;
	private String stringSelectItem13 = "";
	private int intFoundIndex13 = 0;

	private String stringSelectItem21 = "";
	private int intFoundIndex21 = 0;
	private String stringSelectItem22 = "";
	private int intFoundIndex22 = 0;
	private String stringSelectItem23 = "";
	private int intFoundIndex23 = 0;

	
	ActionListener selectUnitActionListner1 = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			if (!boolUpdatingComboBox) selectUnitItem1();
		}
	};
	ActionListener selectUnitActionListner2 = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			if (!boolUpdatingComboBox) selectUnitItem2();
		}
	};

	// UI ready for the user flag
	Boolean UserInterfaceIsReady = false;

	private double buttonSpace; // This is the white space between the operator buttons.

	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();
	public RoundedTerm rt = new RoundedTerm(); //created a object here.
	public ScientificConvert sc = new ScientificConvert(); //created a object here
	String[] result = new String[2];
	Units unit = new Units();
	/**********************************************************************************************
	 * 
	 * Constructors
	 * 
	 **********************************************************************************************/

	/**********
	 * This constructor initializes all of the elements of the graphical user
	 * interface. These assignments determine the location, size, font, color, and
	 * change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
		//compatible units for calculator.
		
		//precision check
		check = false;
		
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 5;

		// Label theScene with the name of the calculator, centered at the top of the
		// pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 20, 40);

		// Establish the first text input operand field and when anything changes in
		// operand 1 measured value,
		// measured value process all fields to ensure that we are ready to perform as
		// soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH / 2 + 30, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {
			setOperand1();
		});

		// Establish an error message for the first operand just above it with, left
		// aligned
		setupLabelUI(label_errOperand1, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 22, 120);
		label_errOperand1.setTextFill(Color.RED);
		
		label_plusminus_operand1.setFont(Font.font("Arial", FontPosture.REGULAR, 22));
		label_plusminus_operand1.setLayoutX(Calculator.WINDOW_WIDTH/2 + 50);
		label_plusminus_operand1.setLayoutY(70);
		// Label the first operand error term just above it, left aligned
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
				Calculator.WINDOW_WIDTH / 2 + 70, 40);

        // Load up the ComboBox will all of the items for the select list
        loadComboBoxData1(arrayUnits);
        //        setupLabelUI(labelComboBoxExperiment, "Dialog", 24, 800, Pos.CENTER, 0, 20);
        setupLabelUI(labelSelectAUnit1, "Arial", 18, 210, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 350, 45);
        setupComboBoxUI(comboboxSelectUnit1, "Arial", 18, 220, Calculator.WINDOW_WIDTH / 2 + 350, 70); 
        comboboxSelectUnit1.getSelectionModel().selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		selectUnitItem1();setUnitOperand1();
        	});
		
		// Establish the Error Term textfield for the first operand. If anything
		// changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 70, 70,
				true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {
			setOperand1ErrorTerm();
		});

		// Establish an error message for the first operand just above it with, left
		// aligned
		setupLabelUI(label_errOperand1ET, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
				Calculator.WINDOW_WIDTH / 2 + 50, 120);
		label_errOperand1ET.setTextFill(Color.RED);
		
		label_plusminus_operand2.setFont(Font.font("Arial", FontPosture.REGULAR, 22));
		label_plusminus_operand2.setLayoutX(Calculator.WINDOW_WIDTH/2 + 50);
		label_plusminus_operand2.setLayoutY(165);
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> {
			text_Operand2.requestFocus();
		});

		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 135);

		// Label the second operand error term just above it, left aligned
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
				Calculator.WINDOW_WIDTH / 2 + 70, 135);

		// Establish the second text input operand field and when anything changes in
		// operand 2,
		// process both fields to ensure that we are ready to perform as soon as
		// possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH / 2 + 30, Pos.BASELINE_LEFT, 10, 165, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {
			setOperand2();
		});

		// Establish the Error Term textfield for the first operand. If anything
		// changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 70, 165,
				true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {
			setOperand2ErrorTerm();
		});
		
		 // Load up the ComboBox will all of the items for the select list
        loadComboBoxData2(arrayUnits);
        //        setupLabelUI(labelComboBoxExperiment, "Dialog", 24, 800, Pos.CENTER, 0, 20);
        setupLabelUI(labelSelectAUnit2, "Arial", 18, 210, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 350, 145);
        setupComboBoxUI(comboboxSelectUnit2, "Arial", 18, 220, Calculator.WINDOW_WIDTH / 2 + 350, 165); 
        comboboxSelectUnit2.getSelectionModel().selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		selectUnitItem2();setUnitOperand2();
        	});		
		
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> {
			text_Result.requestFocus();
		});

		// Establish an error message for the second operand just above it with, left
		// aligned
		setupLabelUI(label_errOperand2, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 22, 215);
		label_errOperand2.setTextFill(Color.RED);

		// // Establish an error message for the second operand just above it with, left
		// aligned
		setupLabelUI(label_errOperand2ET, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
				Calculator.WINDOW_WIDTH / 2 + 50, 215);
		label_errOperand2ET.setTextFill(Color.RED);
		
		label_plusminus_result.setFont(Font.font("Arial", FontPosture.REGULAR, 22));
		label_plusminus_result.setLayoutX(Calculator.WINDOW_WIDTH/2 + 50);
		label_plusminus_result.setLayoutY(270);
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 240);

		// Label the result just above the result output field, left aligned
		setupLabelUI(label_ResultErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
				Calculator.WINDOW_WIDTH / 2 + 70, 240);

		// Establish the result output field. It is not editable, so the text can be
		// selected and copied,
		// but it cannot be altered by the user. The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH / 2 + 30, Pos.BASELINE_LEFT, 10, 270, false);

//		// Establish the Error Term textfield for the first operand.  If anything changes, process
//		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_ResultErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 70, 270,
				true);
	//result output field	
		//**************************************************************************************************
		setupTextUI(text_Result_Unit, "Arial", 18, 300, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 350, 270,
				false);
		// Label of result unit
		setupLabelUI(labelResultUnit, "Arial", 18, 230, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH / 2 + 350, 240);
		setupLabelUI(label_errResultUnit, "Arial", 18, 300,  Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH /2 + 350 , 300);
		label_errResultUnit.setTextFill(Color.RED);
		//**************************************************************************************************
		// Establish an error message for the second operand just above it with, left
		// aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 300);
		label_errResult.setTextFill(Color.RED);

		// Establish the ADD "+" button, position it, and link it to methods to
		// accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 0.5 * buttonSpace - BUTTON_OFFSET, 350);
		button_Add.setOnAction((event) -> {
			addOperands();
		});

		// Establish the SUB "-" button, position it, and link it to methods to
		// accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1.5 * buttonSpace - BUTTON_OFFSET, 350);
		button_Sub.setOnAction((event) -> {
			subOperands();
		});

		// Establish the MPY "Ã—" button, position it, and link it to methods to
		// accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2.5 * buttonSpace - BUTTON_OFFSET, 350);
		button_Mpy.setOnAction((event) -> {
			mpyOperands();
		});

		// Establish the DIV "Ã·" button, position it, and link it to methods to
		// accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3.5 * buttonSpace - BUTTON_OFFSET, 350);
		button_Div.setOnAction((event) -> {
			divOperands();
		});

		// root
		setupButtonUI(button_Root, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4.5 * buttonSpace - BUTTON_OFFSET, 350);
		button_Root.setOnAction((event) -> {
			rootOperands();
		});

		//precision button
		setupButtonUI(precision, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2.2 * buttonSpace - BUTTON_OFFSET, 430);
        precision.setOnMouseClicked((event) -> {
            reset();
        });
        // message after the precision is clicked.
        setupLabelUI(precision_check, "Arial", 18, Calculator.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT,
                Calculator.WINDOW_WIDTH / 2 - 110, 520);
        label_errOperand2ET.setTextFill(Color.RED);
		
		// Error Message for the Measured Value for operand 1
		errOperand1MVPart1.setFill(Color.BLACK);
		errOperand1MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand1MVPart2.setFill(Color.RED);
		errOperand1MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand1MeasuredValue = new TextFlow(errOperand1MVPart1, errOperand1MVPart2);
		errOperand1MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH - 10);
		errOperand1MeasuredValue.setLayoutX(21);
		errOperand1MeasuredValue.setLayoutY(100);

		// Error Message for the Measured Value for operand 1
		errOperand1ETPart1.setFill(Color.BLACK);
		errOperand1ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand1ETPart2.setFill(Color.RED);
		errOperand1ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand1ErrorTerm = new TextFlow(errOperand1ETPart1, errOperand1ETPart2);
		errOperand1ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH - 10);
		errOperand1ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH / 2 + 70);
		errOperand1ErrorTerm.setLayoutY(100);

		// Error Message for the Measured Value for operand 2
		errOperand2MVPart1.setFill(Color.BLACK);
		errOperand2MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand2MVPart2.setFill(Color.RED);
		errOperand2MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand2MeasuredValue = new TextFlow(errOperand2MVPart1, errOperand2MVPart2);
		errOperand2MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH - 10);
		errOperand2MeasuredValue.setLayoutX(21);
		errOperand2MeasuredValue.setLayoutY(195);

		// Error Message for the Error Term for operand 2
		errOperand2ETPart1.setFill(Color.BLACK);
		errOperand2ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand2ETPart2.setFill(Color.RED);
		errOperand2ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand2ErrorTerm = new TextFlow(errOperand2ETPart1, errOperand2ETPart2);
		errOperand2ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH - 10);
		errOperand2ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH / 2 + 70);
		errOperand2ErrorTerm.setLayoutY(195);

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1,
				label_errOperand1ET, errOperand1ErrorTerm, label_errOperand2ET, label_Operand2, label_Operand1ErrorTerm,
				errOperand2ErrorTerm, text_Operand1ErrorTerm, text_Operand2, text_Operand2ErrorTerm,
				label_Operand2ErrorTerm, label_errOperand2, label_Result, text_Result, label_errResult,
				label_ResultErrorTerm, errOperand1MeasuredValue, errOperand2MeasuredValue, text_ResultErrorTerm,
				button_Add, button_Sub, button_Mpy, button_Div, button_Root, label_plusminus_operand1, label_plusminus_operand2, label_plusminus_result,
				labelSelectAUnit1,comboboxSelectUnit1,labelSelectAUnit2,comboboxSelectUnit2, text_Result_Unit,precision,precision_check,label_errResultUnit,labelResultUnit);

	}
	/**
	 * /method for resetting the precision button.
	 */
	private void reset() {
        if(check == false) {
            check = true;
            text_Operand1ErrorTerm.setText("");
            text_Operand1ErrorTerm.setEditable(false);   
            text_Operand2ErrorTerm.setText("");
            text_Operand2ErrorTerm.setEditable(false);
            text_ResultErrorTerm.setText("");
            text_ResultErrorTerm.setEditable(false);
            precision_check.setText("Precision button clicked");

        }
        // if the text fields are disabled this condition will reverse that operation.
        else {
            text_Operand1ErrorTerm.setText("");
            text_Operand1ErrorTerm.setEditable(true);   
            text_Operand2ErrorTerm.setText("");
            text_Operand2ErrorTerm.setEditable(true);
            text_ResultErrorTerm.setText("");
            text_ResultErrorTerm.setEditable(false);
            precision_check.setText("");
            check = false;
        }   
    }	
	/**********
	 * Private local method to initialize the standard fields for a ComboBox
	 * 
	 * @param c		The ComboBox object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the ComboBox
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupComboBoxUI(ComboBox <String> c, String ff, double f, double w, double x, double y){
		c.setStyle("-fx-font: " + f + " " + ff + ";");
		c.setMinWidth(w);
		c.setLayoutX(x);
		c.setLayoutY(y);
	}
	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l  The Label object to be initialized
	 * @param ff The font to be used
	 * @param f  The size of the font to be used
	 * @param w  The width of the label
	 * @param p  The alignment (e.g. left, centered, or right)
	 * @param x  The location from the left edge (x axis)
	 * @param y  The location from the top (y axis)
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t  The TextField object to be initialized
	 * @param ff The font to be used
	 * @param f  The size of the font to be used
	 * @param w  The width of the text field
	 * @param p  The alignment (e.g. left, centered, or right)
	 * @param x  The location from the left edge (x axis)
	 * @param y  The location from the top (y axis)
	 * @param e  true if the field should be editable, else false
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b  The Button object to be initialized
	 * @param ff The font to be used
	 * @param f  The size of the font to be used
	 * @param w  The width of the Button
	 * @param p  The alignment (e.g. left, centered, or right)
	 * @param x  The location from the left edge (x axis)
	 * @param y  The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y) {
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}

	
    /**
     * Code to handle the "SelectUnit" ComboBox selection event.  When
     * the user selects an item, this code is performed.  (This method is
     * not called when the boolBuildingComboBox variable is true.)
     * 
     * @param e		The actual action event object - it is ignored in this method
     */
    private void selectUnitItem1() {
    	// Do not perform this code if it is being performed! (Do not allow recursion)
    	if (boolUpdatingComboBox) return;
   	
    	// There are fours cases depending on how many previously selected list items
    	// have been selected
		
		boolUpdatingComboBox=true;
		String theSelectedItem = (String) comboboxSelectUnit1.getValue();
		int theSelectedIndex = comboboxSelectUnit1.getSelectionModel().getSelectedIndex();
		
    	switch (selectListState) {
    	case 1:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit1.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    		case 2:
    			selectListState = 0;
    			stringSelectItem11 = "";
    			intFoundIndex11 = 0;
    			loadComboBoxData1(arrayUnits);
    			break;
    		default:
    			stringSelectItem12 = stringSelectItem11;
    			stringSelectItem11 = theSelectedItem;
    			intFoundIndex12 = intFoundIndex11;
    			intFoundIndex11 = theSelectedIndex - 2;
    			selectListState = 2;
    			loadComboBoxData1(arrayUnits);
    			break;
    		}
    		break;
    		
    	case 2:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit1.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			String stringTemp = stringSelectItem11;
    			stringSelectItem11 = stringSelectItem12;
    			stringSelectItem12 = stringTemp;
    			int intTemp = intFoundIndex11;
    			intFoundIndex11 = intFoundIndex12;
    			intFoundIndex12 = intTemp;
    			loadComboBoxData1(arrayUnits);
    			break;
    		case 2:
    		case 3:
    			selectListState = 0;
    			stringSelectItem11 = "";
    			stringSelectItem12 = "";
    			intFoundIndex11 = 0;
    			intFoundIndex12 = 0;
    			loadComboBoxData1(arrayUnits);
    			break;
    		default:
    			stringSelectItem13 = stringSelectItem12;
    			stringSelectItem12 = stringSelectItem11;
    			stringSelectItem11 = theSelectedItem;
    			intFoundIndex13 = intFoundIndex12;
    			intFoundIndex12 = intFoundIndex11;
    			intFoundIndex11 = theSelectedIndex - 3;
    			selectListState = 3;
    			loadComboBoxData1(arrayUnits);
    			break;
    		}
    		break;
    		
    	case 3:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit1.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			String stringTemp = stringSelectItem11;
    			stringSelectItem11 = stringSelectItem12;
    			stringSelectItem12 = stringTemp;
    			int intTemp = intFoundIndex11;
    			intFoundIndex11 = intFoundIndex12;
    			intFoundIndex12 = intTemp;
    			loadComboBoxData1(arrayUnits);
    			break;
    		case 2:
    			stringTemp = stringSelectItem11;
    			stringSelectItem11 = stringSelectItem13;
    			stringSelectItem13 = stringSelectItem12;
    			stringSelectItem12 = stringTemp;
    			intTemp = intFoundIndex11;
    			intFoundIndex11 = intFoundIndex13;
    			intFoundIndex13 = intFoundIndex12;
    			intFoundIndex12 = intTemp;
    			loadComboBoxData1(arrayUnits);
    			break;
    		case 3:
    		case 4:
    			selectListState = 0;
    			stringSelectItem11 = "";
    			stringSelectItem12 = "";
    			stringSelectItem13 = "";
    			intFoundIndex11 = 0;
    			intFoundIndex12 = 0;
    			intFoundIndex13 = 0;
    			loadComboBoxData1(arrayUnits);
    			break;
    		default:
    			stringSelectItem13 = stringSelectItem12;
    			stringSelectItem12 = stringSelectItem11;
    			stringSelectItem11 = theSelectedItem;
    			intFoundIndex13 = intFoundIndex12;
    			intFoundIndex12 = intFoundIndex11;
    			intFoundIndex11 = theSelectedIndex - 4;
    			selectListState = 3;
    			loadComboBoxData1(arrayUnits);
    			break;
    		}
    		break;
    		
    	default:
     		stringSelectItem11 = theSelectedItem;
     		intFoundIndex11 = theSelectedIndex;
			selectListState = 1;
			loadComboBoxData1(arrayUnits);
    		break;
    	}
		boolUpdatingComboBox=false;
    }
    
	/**
	 * The action item selection lists in the ComboBoxes are dynamic. They can
	 * be changed by the program.
	 * 
	 * The buildingComboBox flag is used to signal the rest of this screen that
	 * a ComboBox is in the process of being updated. Changes to the ComboBox
	 * whether brought about by the user or by code, results in change events. 
	 * We do not want change events that come from defining any ComboBox select
	 * list via code to generate update events so we can treat all of those as
	 * coming from the user.
	 * 
	 * This routine assumes that the order of the action items in the array is
	 * precisely the correct order for display in the ComboBox. Sorting must be
	 * done elsewhere.
	 * 
	 * @param names
	 *            String[] - This is the array of names for the select list
	 * 
	 */
	private void loadComboBoxData1(String[] names) {

		// Define a new list of countries.
		List<String> list = new ArrayList<String>();
				
		// Load up the select list with each of the items from the array
		if (names != null) { // If names is null, there are no action items
			
			// Based on the state of the select list, display the right number
			// of previously selected items on the top of the whole list
			switch (selectListState) {
			case 1:
				list.add(stringSelectItem11);
				list.add("----------");
				break;
			case 2:
				list.add(stringSelectItem11);
				list.add(stringSelectItem12);
				list.add("----------");
				break;
			case 3:
				list.add(stringSelectItem11);
				list.add(stringSelectItem12);
				list.add(stringSelectItem13);
				list.add("----------");
				break;
			default:
				break;
			}
			stringDefaultItem1 = names[0];
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
		}
		
		comboboxSelectUnit1.setItems(FXCollections.observableArrayList(list));
		comboboxSelectUnit1.getSelectionModel().select(0);
		
        if (UserInterfaceIsReady) {
            // Force the skin to scroll to the same place as the selection.  I have no idea why
        	// one would think that we should have to do this!  The following web page helped me
        	// to get this to work: https://bugs.openjdk.java.net/browse/JDK-8091560
            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>)comboboxSelectUnit1.getSkin();
            ((ListView<?>)skin.getPopupContent()).scrollTo(0);
        }
	}
	
	/**
     * Code to handle the "SelectUnit" ComboBox selection event.  When
     * the user selects an item, this code is performed.  (This method is
     * not called when the boolBuildingComboBox variable is true.)
     * 
     * @param e		The actual action event object - it is ignored in this method
     */
    private void selectUnitItem2() {
    	// Do not perform this code if it is being performed! (Do not allow recursion)
    	if (boolUpdatingComboBox) return;
   	
    	// There are fours cases depending on how many previously selected list items
    	// have been selected
		
		boolUpdatingComboBox=true;
		String theSelectedItem = (String) comboboxSelectUnit2.getValue();
		int theSelectedIndex = comboboxSelectUnit2.getSelectionModel().getSelectedIndex();
    	switch (selectListState) {
    	case 1:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit2.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    		case 2:
    			selectListState = 0;
    			stringSelectItem21 = "";
    			intFoundIndex21 = 0;
    			loadComboBoxData2(arrayUnits);
    			break;
    		default:
    			stringSelectItem22 = stringSelectItem21;
    			stringSelectItem21 = theSelectedItem;
    			intFoundIndex22 = intFoundIndex21;
    			intFoundIndex21 = theSelectedIndex - 2;
    			selectListState = 2;
    			loadComboBoxData2(arrayUnits);
    			break;
    		}
    		break;
    		
    	case 2:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit2.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			String stringTemp = stringSelectItem21;
    			stringSelectItem21 = stringSelectItem22;
    			stringSelectItem22 = stringTemp;
    			int intTemp = intFoundIndex21;
    			intFoundIndex21 = intFoundIndex22;
    			intFoundIndex22 = intTemp;
    			loadComboBoxData2(arrayUnits);
    			break;
    		case 2:
    		case 3:
    			selectListState = 0;
    			stringSelectItem21 = "";
    			stringSelectItem22 = "";
    			intFoundIndex21 = 0;
    			intFoundIndex22 = 0;
    			loadComboBoxData2(arrayUnits);
    			break;
    		default:
    			stringSelectItem23 = stringSelectItem22;
    			stringSelectItem22 = stringSelectItem21;
    			stringSelectItem21 = theSelectedItem;
    			intFoundIndex23 = intFoundIndex22;
    			intFoundIndex22 = intFoundIndex21;
    			intFoundIndex21 = theSelectedIndex - 3;
    			selectListState = 3;
    			loadComboBoxData2(arrayUnits);
    			break;
    		}
    		break;
    		
    	case 3:
    		switch (theSelectedIndex) {
    		case 0:
    			boolUpdatingComboBox=true;
    			comboboxSelectUnit2.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			String stringTemp = stringSelectItem21;
    			stringSelectItem21 = stringSelectItem22;
    			stringSelectItem22 = stringTemp;
    			int intTemp = intFoundIndex21;
    			intFoundIndex21 = intFoundIndex22;
    			intFoundIndex22 = intTemp;
    			loadComboBoxData2(arrayUnits);
    			break;
    		case 2:
    			stringTemp = stringSelectItem21;
    			stringSelectItem21 = stringSelectItem23;
    			stringSelectItem23 = stringSelectItem22;
    			stringSelectItem22 = stringTemp;
    			intTemp = intFoundIndex21;
    			intFoundIndex21 = intFoundIndex23;
    			intFoundIndex23 = intFoundIndex22;
    			intFoundIndex22 = intTemp;
    			loadComboBoxData2(arrayUnits);
    			break;
    		case 3:
    		case 4:
    			selectListState = 0;
    			stringSelectItem21 = "";
    			stringSelectItem22 = "";
    			stringSelectItem23 = "";
    			intFoundIndex21 = 0;
    			intFoundIndex22 = 0;
    			intFoundIndex23 = 0;
    			loadComboBoxData2(arrayUnits);
    			break;
    		default:
    			stringSelectItem23 = stringSelectItem22;
    			stringSelectItem22 = stringSelectItem21;
    			stringSelectItem21 = theSelectedItem;
    			intFoundIndex23 = intFoundIndex22;
    			intFoundIndex22 = intFoundIndex21;
    			intFoundIndex21 = theSelectedIndex - 4;
    			selectListState = 3;
    			loadComboBoxData2(arrayUnits);
    			break;
    		}
    		break;
    		
    	default:
     		stringSelectItem21 = theSelectedItem;
     		intFoundIndex21 = theSelectedIndex;
			selectListState = 1;
			loadComboBoxData2(arrayUnits);
    		break;
    	}
		boolUpdatingComboBox=false;
    }
    
	/**
	 * The action item selection lists in the ComboBoxes are dynamic. They can
	 * be changed by the program.
	 * 
	 * The buildingComboBox flag is used to signal the rest of this screen that
	 * a ComboBox is in the process of being updated. Changes to the ComboBox
	 * whether brought about by the user or by code, results in change events. 
	 * We do not want change events that come from defining any ComboBox select
	 * list via code to generate update events so we can treat all of those as
	 * coming from the user.
	 * 
	 * This routine assumes that the order of the action items in the array is
	 * precisely the correct order for display in the ComboBox. Sorting must be
	 * done elsewhere.
	 * 
	 * @param names
	 *            String[] - This is the array of names for the select list
	 * 
	 */
	private void loadComboBoxData2(String[] names) {

		// Define a new list of countries.
		List<String> list = new ArrayList<String>();
				
		// Load up the select list with each of the items from the array
		if (names != null) { // If names is null, there are no action items
			
			// Based on the state of the select list, display the right number
			// of previously selected items on the top of the whole list
			switch (selectListState) {
			case 1:
				list.add(stringSelectItem21);
				list.add("----------");
				break;
			case 2:
				list.add(stringSelectItem21);
				list.add(stringSelectItem22);
				list.add("----------");
				break;
			case 3:
				list.add(stringSelectItem21);
				list.add(stringSelectItem22);
				list.add(stringSelectItem23);
				list.add("----------");
				break;
			default:
				break;
			}
			stringDefaultItem2 = names[0];
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
		}
		
		comboboxSelectUnit2.setItems(FXCollections.observableArrayList(list));
		comboboxSelectUnit2.getSelectionModel().select(0);
		
        if (UserInterfaceIsReady) {
            // Force the skin to scroll to the same place as the selection.  I have no idea why
        	// one would think that we should have to do this!  The following web page helped me
        	// to get this to work: https://bugs.openjdk.java.net/browse/JDK-8091560
            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>)comboboxSelectUnit2.getSkin();
            ((ListView<?>)skin.getPopupContent()).scrollTo(0);
        }
	}	
	//units of calculator.
	public String[] getSelectedItems() {
		String[] theSelectedItem = new String[2];
		theSelectedItem[0] = (String) comboboxSelectUnit1.getValue();
		theSelectedItem[1] = (String) comboboxSelectUnit2.getValue();
		return theSelectedItem;
	}
		 /**********************************************************************************************
	 * 
	 * User Interface Actions
	 * 
	 **********************************************************************************************/
	/**********
	 * Private local method used to set the value of the first operand given a text
	 * value. The method uses the business logic class to perform the work of
	 * checking the string to see it is a valid value and if so, saving that value
	 * internally for future computations. If there is an error when trying to
	 * convert the string into a value, the called business logic method returns
	 * false and actions are taken to display the error message appropriately.
	 */
	
	private void setUnitOperand1() {
		perform.setUnitOperand1((String) comboboxSelectUnit1.getValue());
	}
	private void setUnitOperand2() { 
		perform.setUnitOperand2((String) comboboxSelectUnit2.getValue());
	}
	private void setOperand1() {
		text_Result.setText(""); // Any change of an operand probably
		text_Operand1ErrorTerm.setText(""); //bug
		label_Result.setText("Result"); // invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK); // the old result and the error
		label_errResult.setText(""); // message
		text_ResultErrorTerm.setText("");
//		perform.setUnitOperands(getSelectedItems());
		if (perform.setOperand1(text_Operand1.getText())) { // Set the operand and see if there was
			label_errOperand1.setText(""); // an error. If no error, clear this
			errOperand1MVPart2.setText("");
			errOperand1MVPart1.setText("");
			System.out.println("true");
			if (text_Operand2.getText().length() == 0) // operands error. If the other operand
				label_errOperand2.setText(""); // is empty, clear its error as well.
		} else { // If there's an issue with the operand,
			performGo1(); // display the error
		} // message that was generated. This error generation is done using the
			// function mentioned here.
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text
	 * value. The logic is exactly the same as used for the first operand, above.
	 */
	private void setOperand1ErrorTerm() {
		text_Result.setText(""); // Any change of an operand probably
		label_Result.setText("Result"); // invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK); // the old result and the error
		label_errResult.setText(""); // message
		text_ResultErrorTerm.setText("");
		System.out.println(text_Operand1ErrorTerm.getText() + "-->"
				+ perform.setOperand1ErrorTerm(text_Operand1ErrorTerm.getText()));
		System.out.println(perform.setOperand1ErrorTerm(text_Operand1ErrorTerm.getText()));
		if (perform.setOperand1ErrorTerm(text_Operand1ErrorTerm.getText())) { // Set the operand and see if there was
//			System.out.println("reached4");
			label_errOperand1ET.setText(""); // an error. If no error, clear this
			errOperand1ETPart2.setText("");
			errOperand1ETPart1.setText("");
//			System.out.println("true");
			if (text_Operand2.getText().length() == 0) // operands error. If the other operand
				label_errOperand2.setText(""); // is empty, clear its error as well.
		} else { // If there's an issue with the operand,
			performGo1(); // display the error
		}
	}

	/**********
	 * Private local method to set the value of the second operand given a text
	 * value. The logic is exactly the same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText(""); // See setOperand1's comments. The logic
		text_Operand2ErrorTerm.setText(""); //bug
		label_Result.setText("Result"); // is the same!
		label_Result.setTextFill(Color.BLACK);
		label_errResult.setText("");
		text_ResultErrorTerm.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			errOperand2MVPart2.setText("");
			errOperand2MVPart1.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		} else
			performGo2();
	}

	/**********
	 * Private local method to set the value of the second operand given a text
	 * value. The logic is exactly the same as used for the first operand, above.
	 */
	private void setOperand2ErrorTerm() {
		text_Result.setText(""); // Any change of an operand probably
		label_Result.setText("Result"); // invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK); // the old result and the error
		label_errResult.setText(""); // message
		text_ResultErrorTerm.setText("");
		if (perform.setOperand2ErrorTerm(text_Operand2ErrorTerm.getText())) { // Set the operand and see if there was
			label_errOperand2ET.setText(""); // an error. If no error, clear this
			errOperand2ETPart2.setText("");
			errOperand2ETPart1.setText("");
			if (text_Operand1.getText().length() == 0) // operands error. If the other operand
				label_errOperand1.setText(""); // is empty, clear its error as well.
		} else { // If there's an issue with the operand,
			performGo2(); // display the error
		}
	}

	/**********
	 * This method is called when an binary operation button has been pressed. It
	 * assesses if there are issues with either of the binary operands or they are
	 * not defined. If not return false (there are no issues)
	 * 
	 * @return True if there are any issues that should keep the calculator from
	 *         doing its work.
	 */
	private boolean binaryOperandIssues() {
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK); // Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage(); // Fetch the error messages, if
		String errorMessage2 = perform.getOperand2ErrorMessage(); // any, from the two operands
		if (errorMessage1.length() > 3) { // Check the first. If not empty
			label_errOperand1.setText(errorMessage1); // there's an error, so display it.
			if (errorMessage2.length() > 3) { // Do the same with the 2nd operand
				label_errOperand2.setText(errorMessage2);
				return true; // Return true if both have errors
			} else {
				return true; // Return true if only the first
			} // has an error
		} else if (errorMessage2.length() > 3) { // No error with the first, so check
			label_errOperand2.setText(errorMessage2); // the second operand the same way
			return true; // Return true if only the 2nd has
		} // an error.

		// If the code reaches here, neither the first nor the second has an error
		// condition. The
		// following code check to see if the operands are defined.
		if (!perform.getOperand1Defined()) { // Is first operand defined? If not,
			label_errOperand1.setText("No value found"); // it is an issue for this operator
			if (!perform.getOperand2Defined()) { // Check the second operand. If it
				label_errOperand2.setText("No value found"); // is not defined, two messages
				return true; // should be displayed. Signal there
			} // are issues by returning true.
			return true;
		} else if (!perform.getOperand2Defined()) { // If the first is defined, check the
			label_errOperand2.setText("No value found"); // second. Both operands must be
			return true; // defined. Signal there are issues
		}

		return false; // No issues, so return false
	}

	/**********************************************************************************************
	 * This portion of the class defines the actions that take place when the
	 * various calculator buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This add routine is called when the user pressed the add button. It calls the
	 * business logic class to do the actual work. Notice that this method is really
	 * more about doing things with the user interface. That is, it interacts with
	 * the user and delegates all of the computation work to the business logic
	 * class and the other classes that it uses. This class and its methods are work
	 * with Strings.
	 */
	//method returns the selected items in combo box.
		
	private void addOperands() {
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) // If there are issues, return
			return; // without doing anything
		if(!unit.as_ValidateUnits(getSelectedItems())) {
			text_Result.setText("");
			label_Result.setText("Result");
			text_ResultErrorTerm.setText("");
			text_Result_Unit.setText("");
			label_errResultUnit.setText("Uncompatible Units");
			return;
		}else {
			text_Result_Unit.setText(unit.as_displayunits(getSelectedItems()));
			label_errResultUnit.setText("");
		}
		// If the operands are defined and valid, request the business logic method to
		// do the
		// addition and return the result as a String. If there is a problem with the
		// actual
		// computation, an empty string is returned.
		String theAnswer = perform.addition(); // The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText(""); // Reset the result error messages
		if (theAnswer.length() > 0) { // See if a result was returned
			
			//multiplying conversion value
			result = rt.roundedTerm(answersTokens[0],answersTokens[1]);
			text_Result.setText(sc.checkErrorTerm(Double.parseDouble(result[0]))); // If so, display it and change the
			text_ResultErrorTerm.setText(sc.checkErrorTerm(Double.parseDouble(result[1])));	
			label_Result.setText("Sum"); // title of the field to "Sum"
		} else { // There is no result.
			text_Result.setText(""); // Do not display a result.
			label_Result.setText("Result"); // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage()); // Display error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands() {
		if (binaryOperandIssues()) // If there are issues, return
			return; // without doing anything
		// If the operands are defined and valid, request the business logic method to
		// do the
		// addition and return the result as a String. If there is a problem with the
		// actual
		// computation, an empty string is returned.
		if(!unit.as_ValidateUnits(getSelectedItems())) {
			text_Result.setText("");
			label_Result.setText("Result");
			text_ResultErrorTerm.setText("");
			text_Result_Unit.setText("");
			label_errResultUnit.setText("Uncompatible Units");
			return;
		}else {
			text_Result_Unit.setText(unit.as_displayunits(getSelectedItems()));
			label_errResultUnit.setText("");
		}
		String theAnswer = perform.subtraction(); // The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText(""); // Reset the result error messages
		if (theAnswer.length() > 0) { // See if a result was returned
			result = rt.roundedTerm(answersTokens[0],answersTokens[1]); 
			text_Result.setText(sc.checkErrorTerm(Double.parseDouble(result[0]))); // If so, display it and change the
			text_ResultErrorTerm.setText(sc.checkErrorTerm(Double.parseDouble(result[1])));
			label_Result.setText("Subtraction"); // title of the field to "Subtraction"
		} else { // There is no result.
			text_Result.setText(""); // Do not display a result.
			label_Result.setText("Result"); // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage()); // Display error message.
		}
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands() {
		if (binaryOperandIssues()) // If there are issues, return
			return; // without doing anything
		if(!unit.m_ValidateUnits(getSelectedItems())) {
			text_Result.setText("");
			label_Result.setText("Result");
			text_ResultErrorTerm.setText("");
			text_Result_Unit.setText("");
			label_errResultUnit.setText("Uncompatible Units");
			return;
		}else {
			text_Result_Unit.setText(unit.m_displayunits(getSelectedItems()));
			label_errResultUnit.setText("");
		}
		// If the operands are defined and valid, request the business logic method to
		// do the
		// addition and return the result as a String. If there is a problem with the
		// actual
		// computation, an empty string is returned.
		
		String theAnswer = perform.multiplication(); // The business logic does the multiplication
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText(""); // Reset the result error messages
		if (theAnswer.length() > 0) { // See if a result was returned
			result = rt.roundedTerm(answersTokens[0],answersTokens[1]); 
			text_Result.setText(sc.checkErrorTerm(Double.parseDouble(result[0]))); // If so, display it and change the
			text_ResultErrorTerm.setText(sc.checkErrorTerm(Double.parseDouble(result[1])));
			label_Result.setText("Product"); // title of the field to "Product"
		} else { // There is no result.
			text_Result.setText(""); // Do not display a result.
			label_Result.setText("Result"); // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage()); // Display error message.
		}
	}

	/**********
	 * This is the divide routine. If the divisor is zero, the divisor is declared
	 * to be invalid.
	 * 
	 */
	private void divOperands() {
		if (binaryOperandIssues()) // If there are issues, return
			return; // without doing anything
		if(!unit.d_ValidateUnits(getSelectedItems())) {
			text_Result.setText("");
			label_Result.setText("Result");
			text_ResultErrorTerm.setText("");
			text_Result_Unit.setText("");
			label_errResultUnit.setText("Uncompatible Units");
			return;
		}else {
			text_Result_Unit.setText(unit.d_displayunits(getSelectedItems()));
			label_errResultUnit.setText("");
		}
		// If the operands are defined and valid, request the business logic method to
		// do the
		// addition and return the result as a String. If there is a problem with the
		// actual
		// computation, an empty string is returned.
		String theAnswer = perform.division(); // The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText(""); // Reset the result error messages
		if (theAnswer.length() > 0) { // See if a result was returned
			result = rt.roundedTerm(answersTokens[0],answersTokens[1]); 		
			text_Result.setText(sc.checkErrorTerm(Double.parseDouble(result[0]))); // If so, display it and change the
			text_ResultErrorTerm.setText(sc.checkErrorTerm(Double.parseDouble(result[1])));
			label_Result.setText("Quotient"); // title of the field to "Division"
		} else { // There is no result.
			text_Result.setText(""); // Do not display a result.
			label_Result.setText("Result"); // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage()); // Display error message.
		}
	}
	/**********
	 * This is the root routine.If the operand is negative, the operand is declared
	 * to be invalid.
	 *  
	 */

	private void rootOperands() {
		String theSelectedItem = (String) comboboxSelectUnit1.getValue();
		if(!unit.sq_ValidateUnits(theSelectedItem)) {
			text_Result.setText("");
			label_Result.setText("Result");
			text_ResultErrorTerm.setText("");
			text_Result_Unit.setText("");
			label_errResultUnit.setText("Uncompatible Units");
			return;
		}else {
			text_Result_Unit.setText(unit.sq_displayunits(theSelectedItem));
			label_errResultUnit.setText("");
		}
		// If the operands are defined and valid, request the business logic method to
		// do the
		// addition and return the result as a String. If there is a problem with the
		// actual
		// computation, an empty string is returned.
		String theAnswer = perform.root(); // The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText(""); // Reset the result error messages
		if (theAnswer.length() > 0) { // See if a result was returned
			result = rt.roundedTerm(answersTokens[0],answersTokens[1]); 
			text_Result.setText(sc.checkErrorTerm(Double.parseDouble(result[0]))); // If so, display it and change the
			text_ResultErrorTerm.setText(sc.checkErrorTerm(Double.parseDouble(result[1])));
			label_Result.setText("Square Root"); // title of the field to "Division"
		} else { // There is no result.
			text_Result.setText(""); // Do not display a result.
			label_Result.setText("Result"); // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage()); // Display error message.
		}

	}
	
	/**********
	 * This is the perform routine 1
	 * 
	 */
	private void performGo1() {
		System.out.println(perform.getOperand1ErrorMessage());
		String errMessage = perform.getOperand1ErrorMessage();
		String[] tokens = errMessage.split("_");
		if (!tokens[0].equals("")) {

			if (tokens.length > 1) {
				label_errOperand1.setText(tokens[0]);
				if (Integer.parseInt(tokens[2]) <= -1)
					return;
				String input = tokens[1];
				errOperand1MVPart1.setText(input.substring(0, Integer.parseInt(tokens[2])));
				errOperand1MVPart2.setText("\u21EB");
			}
		}
		// we use print staments to check if it works properly.
		else {
//			System.out.println("reached2");
			String errMessageET = perform.getOperand1ErrorMessageforET();
//			System.out.println(errMessageET);
			String[] tokens1 = errMessageET.split("_");
			if (!tokens1[0].equals("")) {
//				System.out.println(text_Operand1ErrorTerm.getText() + "-->" + Arrays.toString(tokens1));
				if (tokens1.length > 1) {
					label_errOperand1ET.setText(tokens1[0]);
//					System.out.println(Arrays.toString(tokens));
					if (Integer.parseInt(tokens1[2]) <= -1)
						return;
					String input1 = tokens1[1];
					errOperand1ETPart1.setText(input1.substring(0, Integer.parseInt(tokens1[2])));
					errOperand1ETPart2.setText("\u21EB");
				}
			}
		}

	}
	
	/**********
	 * This is the perform go routine 2
	 * 
	 */
	private void performGo2() {
		String errMessage = perform.getOperand2ErrorMessage();
		String[] tokens = errMessage.split("_");
		if (!tokens[0].equals("")) {
			if (tokens.length > 1) {
				label_errOperand2.setText(tokens[0]);
				if (Integer.parseInt(tokens[2]) <= -1)
					return;
				String input = tokens[1];
				errOperand2MVPart1.setText(input.substring(0, Integer.parseInt(tokens[2])));
				errOperand2MVPart2.setText("\u21EB");
			}
		}
		// we use print staments to check if it works properly.
		else {
			System.out.println("reached2");
			String errMessageET = perform.getOperand2ErrorMessageforET();
			System.out.println(errMessageET);
			String[] tokens1 = errMessageET.split("_");
			if (!tokens1[0].equals("")) {
				if (tokens1.length > 1) {
					label_errOperand2ET.setText(tokens1[0]);
					if (Integer.parseInt(tokens1[2]) <= -1)
						return;
					String input1 = tokens1[1];
					errOperand2ETPart1.setText(input1.substring(0, Integer.parseInt(tokens1[2])));
					errOperand2ETPart2.setText("\u21EB");
				}
			}
//			 
		}

	}
}
