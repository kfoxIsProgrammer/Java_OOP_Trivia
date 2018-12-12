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

import java.io.Serializable;
/**
 * Holds the outline of a QA
 * @author kevin
 * @see java.io.Serializable
 */
public abstract class QARequirements implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	
	public abstract String getQuestion();
	public abstract void setQuestion(String question);
	
	public abstract String[] getAnswers();
	public abstract void setAnswers(String[] answers);
	
	public abstract String getExplanation();
	public abstract void setExplanation(String explanation);
	
	public abstract String getCategory();
	public abstract void setCategory(String category);
	
	public abstract int getDifficulty();
	public abstract void setDifficulty(int difficulty);
	
	public abstract int getPoints();
	public abstract void setPoints(int points);
	
	public abstract int getCorrectAnswerNumber();
	public abstract void setCorrectAnswerNumber(int correctAnswer);
	
	public abstract boolean isCorrect();
	public abstract void setResult(boolean b);

}
