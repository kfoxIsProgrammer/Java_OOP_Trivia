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

/**
 * Class holds all the fields for each question and answer set
 * @author kevin
 * @version 2
 * @since build 1.8.0_162-b12
 */
public class QA extends QARequirements {
	
	
	
	private String question;
	private String[] answers;
	private String category;
	private String explanation;
	private int difficulty;
	private int points;
	private int correctAnswer;
	private boolean result;

	

	
/**
 * 
 * @param question A question string
 * @param answers A String array of possible answers
 * @param category A string holding the category of the question
 * @param explanation A string holding an explanation of the question and answers
 * @param difficulty An int holding the question's difficulty
 * @param points An int holding the questions point value
 * @param correctAnswer An int that holds the correct answer of the String array of answers
 */
	public QA(String question, String[] answers, String category, String explanation, int difficulty, int points,
			int correctAnswer) {
		setQuestion(question);
		setAnswers(answers);
		setDifficulty(difficulty);
		setExplanation(question);
		setCategory(category);
		setPoints(points);
		setCorrectAnswerNumber(correctAnswer);

	}

	@Override
	public String getQuestion() {return question;}

	@Override
	public void setQuestion(String question) {this.question = question;}

	@Override
	public String[] getAnswers() {return answers;}

	@Override
	/**
	 * Takes Reference and places it into a string 
	 * Then places string into the new String array
	 * @param answers: A string array of possible answers
	 */
	public void setAnswers(String[] answers) {
		
		
		String[] str = new String[answers.length];
		for(int i = 0; i < answers.length; i++) {
		String f = new String(answers[i]);
		str[i] = f;
		}
	}

	@Override
	public String getExplanation() {return explanation;}

	@Override
	public void setExplanation(String explanation) {this.explanation = explanation;}

	@Override
	public String getCategory() {return category;}

	@Override
	public void setCategory(String category) {this.category = category;}

	@Override
	public int getDifficulty() {return difficulty;}

	@Override
	public void setDifficulty(int difficulty) {this.difficulty = difficulty;}

	@Override
	public int getPoints() {return points;}

	@Override
	public void setPoints(int points) {this.points = points;}

	@Override
	public int getCorrectAnswerNumber() {return correctAnswer;}

	@Override
	public void setCorrectAnswerNumber(int correctAnswers) {correctAnswer = correctAnswers;}

	@Override
	public boolean isCorrect() {return result;}

	@Override
	public void setResult(boolean b) {result = b;}

}
