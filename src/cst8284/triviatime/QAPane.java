/**
 * @File[Fox_Kevin_Assignment2]
 * @Author[kevin,040735040]
 * @Course[CST8284 - OOP]
 * @Assignment [2]
 * @Date [2018-04-18]
 * @Proffesor[David Houtman]
 * @Purpose[Holds content of each Question, Answer pair]
 */
package cst8284.triviatime;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Class is used to build a question pane with a set of 
 * radio buttons with correct and incorrect answers
 * @author kevin
 * @see javafx.event.ActionEvent
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.RadioButton
 * @see javafx.scene.control.ToggleGroup
 * @see javafx.scene.control.Alert.AlertType
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.paint.Paint
 * @see javafx.scene.text.Font
 * @see javafx.scene.text.Text
 * @since build 1.8.0_162-b12
 */
public class QAPane {
	
	private static RadioButton[] rbAr;
	private VBox qaPane;
	private ToggleGroup group;
	private static int questionNumber = 0;
    private static Button checkMyAnswer;

	public QAPane() {

	}

	/**
	 * Takes the passed QA and builds the pane
	 * @param qa: the question and answer set to be tested in the current pane
	 */
	public QAPane(QA qa) {
		
		
		setQuestionNumber();
		
		Text t = new Text("Question # " + Integer.toString(getQuestionNumber())+"\nQuestion Difficulty of " 
		+ Integer.toString(qa.getDifficulty()) + "\n\n" +qa.getQuestion());
		
		t.setFont(Font.font("Verdana", 16));
		setQAPane(new VBox(t, getAnswerPane(qa.getAnswers()),checkAnswer(qa)));
		
		this.getQAPane().setAlignment(Pos.CENTER);
		this.getQAPane().setSpacing(40);
		this.getQAPane().setTranslateY(-20);

	}
	
	//Method to get check answer button
	//Then when clicked checks if  selected button is the answer
	/**
	 * Takes the passed QA and gets the correct answer and compares to the selected answer
	 * @param qa: that holds the correct answer
	 * @return Button when clicked checks the users answer choice vs the correct answer
	 */
	public Button checkAnswer(QA qa) {
		
		
		
		checkMyAnswer = new Button("Check Answer");
		checkMyAnswer.setDisable(true);
		checkMyAnswer.setOnAction((ActionEvent e) -> {
			checkMyAnswer.setDisable(true);
			Controls.setValue();
			
			if (getRadioButtonSelected() == qa.getCorrectAnswerNumber()) {
				qa.setResult(true);// Adding question correct to final Scorescreen 
				ScoreScreen.scoreCorrect(qa.getPoints());//Adds points to total score
				ScoreScreen.addCorrect(qa,getQuestionNumber());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Questions");
				alert.setHeaderText(qa.getExplanation());
				alert.setContentText("Correct");
				alert.showAndWait();
				
				
				
			} else {
				qa.setResult(false); // Adding question incorrect to final Scorescreen 
				ScoreScreen.addCorrect(qa,getQuestionNumber());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Incorrect");
				alert.setHeaderText(qa.getExplanation());
				alert.showAndWait();
				qaPane.setDisable(true);
				
				
			}
		});

		return checkMyAnswer;
	}
	
	
	//Creates Radio Buttons
	//Places them into a toggle group
	/**
	 * Takes the passed QA and gets the possible answers and builds a radio button for each
	 * @param ansStrAr: string array with possible answers
	 * @return VBox: with radio buttons with a possible answer on each
	 */
	public VBox getAnswerPane(String[] ansStrAr) {
		
		
		VBox temp = new VBox();
		group = new ToggleGroup();
		temp.setSpacing(2);
		
			RadioButton[] tempbtn = new RadioButton[ansStrAr.length];
			group = new ToggleGroup();
			
		for (int i =0; i < ansStrAr.length; i++ ) {
			RadioButton btn = new RadioButton(ansStrAr[i].toString());
			btn.setOnAction((ActionEvent e) -> {
				checkMyAnswer.setDisable(false);
			});
			btn.setToggleGroup(group);
			btn.setTranslateX(100);
			btn.setFont(Font.font("Verdana", 14));
			btn.setTextFill(Paint.valueOf("green"));
			tempbtn[i] = btn;
			temp.getChildren().add(btn);
			}
			
			setrbAr(tempbtn);
			temp.setSpacing(20);
			temp.setMaxSize(700, 500);
			return temp;
			
			}
		
		
		//Gets what button is selected  
	/**
	 * Checks for which radio button is selected
	 * @return integer: number corresponding to which radio button was selected
	 */
	public int getRadioButtonSelected() {

		for(int i = 0; i <rbAr.length; i++) {
			if(rbAr[i].isSelected()) {
				return i+1;
			}
		}
		
		 return 0;
	}
										
		   
			
	//Getters and Setters
	/**
	 * Sets the questionNumber +1
	 */
	public void setQuestionNumber() {questionNumber++;}
	/**
	 * Resets the questionNumber to 0
	 */
	public static void resetQuestion() {questionNumber = 0;}
/**
 * 
 * @return int that holds the questionNumber
 */
	public static int getQuestionNumber() {return questionNumber;}
/**
 * Sets the RadioButton array with buttons
 * @param arTemp holds the answers radio buttons for the QA
 */
	public void setrbAr(RadioButton[] arTemp) {rbAr = arTemp;}
/**
 * Returns the RadioButton Array
 * @return RadioButton array that holds all the questions possible answers
 */
	public RadioButton[] getrbAr() {return rbAr;}
/**
 * Sets current QA pane with the one made for the current question
 * @param vb that holds the answers, question and the check my answer button
 */
	private void setQAPane(VBox vb) {this.qaPane = vb;}
/**
 * Returns the current qaPane
 * @return VBox that holds the holds the answers, question and the check my answer button
 */
	public VBox getQAPane() {return qaPane;}


}
