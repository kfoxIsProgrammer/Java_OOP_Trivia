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



import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
/**
 * Class is used to build the final score screen 
 * with the list answers the users has gotten correct and incorrect
 * @author kevin
 * @see javafx.geometry.Pos
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.text
 * @since build 1.8.0_162-b12
 */

public class ScoreScreen {
	
	
	
	private static int score;
	private static VBox scoreVBox;
	private static VBox answersCorrect = new VBox();

	/**
	 * Creates the final Score screen with the final score
	 */
	public ScoreScreen() {
		
		
		Text text = new Text("You got " + Integer.toString(getScore()) + " points");
			
		VBox tempVBox = new VBox();
		text.setFont(Font.font("verdana",36));
		tempVBox.setAlignment(Pos.CENTER);
		tempVBox.getChildren().addAll(getAnswersCorrect(),text);
		setVBox(tempVBox);

	}
	
	/**
	 * Adds a string with the question number and if it is correct or not
	 * @param qa: a QA class to get the points valued for its question
	 * @param num: Which question number was answered
	 */
	public static void addCorrect(QA qa,int num) {
		
		
		String str;   
		if (qa.isCorrect()) {
			str = ("     Correct: "+Integer.toString(qa.getPoints()) + " Points Awarded") ;
		}
		else 
		{str = ("     Incorrect: 0 Points Awarded");}
		
		Text text = new Text("Question " + Integer.toString(num) + str );
		VBox vbox = getAnswersCorrect();
		text.setFont(Font.font("verdana",16));	
		
		vbox.getChildren().add(text);
		vbox.setSpacing(10);
		
		vbox.setMaxSize(300, 300);
		setAnswersCorrect(vbox);
		}
	
/**
 * Used to get private answersCorrect
 * @return VBox static  answersCorrect VBox
 */
	public static VBox getAnswersCorrect() {
		return answersCorrect;
	}
	/**
	 * Sets private answersCorrect with temp
	 * @param temp used to set answerCorrect
	 */
	public static void setAnswersCorrect(VBox temp) {
		answersCorrect = temp;
	}
	/**
	 * sets scoreVbox with sBox
	 * @param sBox holds the string of answers that have been answered
	 */
	public void setVBox(VBox sBox) {scoreVBox = sBox;}
/**
 * 
 * @return VBox holding all the of answers that have been completed
 */
	public VBox getVBox() {return scoreVBox;}
/**
 * 
 * @return int that is the total score
 */
	public int getScore() {return score;}
/**
 * Resets score to 0
 */
	public static void resetScore() {score = 0;}
	/**
	 * Resets the VBox holding the answers that have been completed 
	 */
	public static void resetFinalScorePage() {answersCorrect = new VBox();}
/**
 * Adds the answered questions points to the total score
 * @param diffPoints holds the points for a certain question
 */
	public static void scoreCorrect(int diffPoints) {score += diffPoints;}

}
