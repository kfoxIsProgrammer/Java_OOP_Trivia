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

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class is used to load and set actions for a menubar as well as direct which Question to be displayed
 * @author kevin
 * @see java.io.File
 * @see java.util.Collections
 * @see java.util.Comparator
 * @see javafx.application.Platform
 * @see javafx.event.ActionEvent
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Alert.AlertType
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Menu
 * @see javafx.scene.control.MenuBar
 * @see javafx.scene.control.MenuItem
 * @see javafx.scene.input.KeyCode
 * @see javafx.scene.input.KeyCodeCombination
 * @see javafx.scene.input.KeyCombination
 * @see javafx.scene.layout.BorderPane
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.paint.Paint
 * @see javafx.scene.text.Font
 * @see javafx.scene.text.Text
 * @see javafx.stage.FileChooser
 * @see javafx.stage.Stage
 * @since build 1.8.0_162-b12
 */
public class Controls {
	
	
	/**** Generic Menu/Menu Item Properties ****/
	private static MenuItem mnuItm;
	private static Menu mnu;
	private static HBox QBox;
	private static Stage stage;
	private static int currentQuestion = 0;
	private static ScoreScreen scoreScreen;

	/***************** MenuBar *****************/
	/**
	 * Creates menubar with menu's and items 
	 * @param primaryStage passing the stage to this class
	 * @return MenuBar That holds the menu's to control the game
	 */
	public static MenuBar getMenuBar(Stage primaryStage) {
		

		MenuBar menu = new MenuBar();
		setStage(primaryStage);

		menu.getMenus().addAll(getMnuFile(), getMnuSettings(), getMnuHelp());

		return menu;
	}

	/******************* Menu ******************/

	/**
	 * Creates a menu that holds the new game and exit
	 * @return Menu: with name File
	 */
	private static Menu getMnuFile() {
		
		mnu = new Menu("_File");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
		mnu.getItems().addAll(getMnuItmNewGame(), getMnuItmExit());
		return mnu;
	}

	/**
	 * Creates a menu that holds the game's question modes
	 * @return Menu: with name settings
	 */
	private static Menu getMnuSettings() {
		
		mnu = new Menu("_Settings");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		mnu.getItems().addAll(getMnuItmRandom(),getMnuItmIncDiffiiculty(),getMnuItmTopic());
		
		return mnu;
	}

	/**
	 * Creates a menu that holds about information
	 * @return Menu: with name Help
	 */
	private static Menu getMnuHelp() {
		
		mnu = new Menu("_Help");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
		mnu.getItems().add(getMnuItmAbout());
		return mnu;
	}

	/***************** MenuItems *****************/
	// Once selected, gets QA from file
	// Then puts QA into a QA Array
	// Each QA has a question and answer
	/**
	 * Creates a menuItem that starts a new game
	 * @return MenuItem: with name New Game
	 */
	private static MenuItem getMnuItmNewGame() {
		
		
		mnuItm = new MenuItem("_New Game");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {

			File def = new File("C:\\TriviaTime");
			FileChooser fc = new FileChooser();
			fc.setInitialDirectory(def);
			File f = fc.showOpenDialog(getStage());
			
			gameResetter();

			FileUtils.setQAArrayList((f.getAbsolutePath()));
			setHBox(getNextQuestionPane());
			qapaneMaker();
		});

		return mnuItm;
	}

	/**
	 * Creates a menuItem that closes the stage
	 * @return MenuItem: with name Exit
	 */
	private static MenuItem getMnuItmExit() {
		
		
	    mnuItm = new MenuItem("_Exit");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> Platform.exit());
		
		return mnuItm;
	}
	/**
	 * Creates a menuItem that holds authors info
	 * @return MenuItem: with name About
	 */
	private static MenuItem getMnuItmAbout() {
		
		
		/* From Marco Jakob, code.makery, */
		/* http://code.makery.ch/blog/javafx-dialogs-official/ */
		MenuItem mnuItm = new MenuItem("_About");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("About Trivia Time");
			alert.setContentText("Author: Kevin Fox");
			alert.showAndWait();
		});
		return mnuItm;
	}
	
	/**
	 * Creates a menuItem that starts a new game with questions in a random order
	 * @return MenuItem: with name Radomize Questions
	 */
	private static MenuItem getMnuItmRandom() {
		
		
		
	    mnuItm = new MenuItem("_Randomize Questions");
	    mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			if(FileUtils.getQAArrayList().isEmpty()) {
				File def = new File("C:\\TriviaTime");
				FileChooser fc = new FileChooser();
				fc.setInitialDirectory(def);
				File f = fc.showOpenDialog(getStage());
				FileUtils.setQAArrayList((f.getAbsolutePath()));
			}
			
			gameResetter();
			Collections.shuffle(FileUtils.getQAArrayList());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Randomizing Questions");
			alert.setHeaderText("Radomize mode!!!");
			alert.setContentText("The Game will restart with the questions in a random order.");
			alert.showAndWait();
			
			qapaneMaker();
			
		});
		return mnuItm;
	}
	
	/**
	 * Creates a menuItem that starts a new game with questions that get harder
	 * @return MenuItem: with name Increasing Difficulty
	 */
	private static MenuItem getMnuItmIncDiffiiculty() {
		
		
	    mnuItm = new MenuItem("_Increasing Difficulty");
	    mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			if(FileUtils.getQAArrayList().isEmpty()) {
				File def = new File("C:\\TriviaTime");
				FileChooser fc = new FileChooser();
				fc.setInitialDirectory(def);
				File f = fc.showOpenDialog(getStage());
				FileUtils.setQAArrayList((f.getAbsolutePath()));
			}
		
		gameResetter();
		Collections.sort(FileUtils.getQAArrayList(), new QACompareInt());
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Increasing Difficulty");
		alert.setHeaderText("Difficulty mode!!!");
		alert.setContentText("The Game will restart with the questions in order by difficulty.");
		alert.showAndWait();
		
		qapaneMaker();   
		
		});
		return mnuItm;
	}
	/**
	 * Creates a menuItem that starts a new game with questions in order of their topics
	 * @return MenuItem: with name By Topic
	 */
	private static MenuItem getMnuItmTopic() {
		
		
		
		mnuItm = new MenuItem("_By Topic");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction((ActionEvent e) -> {
			if(FileUtils.getQAArrayList().isEmpty()) {
				File def = new File("C:\\TriviaTime");
				FileChooser fc = new FileChooser();
				fc.setInitialDirectory(def);
				File f = fc.showOpenDialog(getStage());
				FileUtils.setQAArrayList((f.getAbsolutePath()));
			}
			
			gameResetter();
			Collections.sort(FileUtils.getQAArrayList(), new QACompareString());	
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("By Topic");
			alert.setHeaderText("Topic mode!!!");
			alert.setContentText("The Game will restart with the questions in order by Topic.");
			alert.showAndWait();
			
			qapaneMaker(); 
		});
		
		return mnuItm;
	}
	
	
	

	// Creates next question button
	// Once clicked goes to next question
	/**
	 * Creates a HBox that holds the next question button
	 * @return HBox: that hold the button that goes to the next question
	 */
	public static HBox getNextQuestionPane() {
		

		Button btn = new Button("Next Question");
		QBox = new HBox();
		QBox.getChildren().add(btn);
		QBox.setMaxSize(300, 300);
		btn.setTranslateX(450);
		btn.setTranslateY(-100);
		QBox.setAlignment(Pos.BOTTOM_RIGHT);
		QBox.setDisable(true);
		btn.setOnAction((ActionEvent e) -> {
			QBox.setDisable(true);
			currentQuestion++;
			qapaneMaker();
		});

		return QBox;

	}

	/**
	 * Method used to send a QA to QAPane class to make 
	 * the layout of the Question and answers
	 */
	public static void qapaneMaker() {
		

		setHBox(getNextQuestionPane());
		BorderPane bp = (BorderPane) getStage().getScene().getRoot();
		if (currentQuestion < FileUtils.getQAArrayList().size()) {
			QAPane qa = new QAPane(FileUtils.getQAArrayList().get(currentQuestion));

			Text t = new Text("Question Category " + FileUtils.getQAArrayList().get(currentQuestion).getCategory());
			t.setFont(Font.font("Verdana", 16));
			t.setFill(Paint.valueOf("blue"));
			t.setTranslateY(-35);
			VBox v = new VBox(t);
			v.setAlignment(Pos.CENTER);

			v.getChildren().add(qa.getQAPane());

			bp.setCenter(v);
			bp.setBottom(getHBox());

			bp.setMaxSize(300, 300);
			qa.getQAPane().setAlignment(Pos.CENTER);
			qa.getQAPane().setSpacing(20);

			
			

		} else {

			scoreScreen = new ScoreScreen();
			bp.setCenter(scoreScreen.getVBox());
			bp.setBottom(null);
			

		}

	}

	
	
	public static void resetQuestions() {currentQuestion = 0;}

	public static void setValue() {QBox.setDisable(false);}

	private static void setStage(Stage s) {stage = s;}

	private static Stage getStage() {return stage;}

	private static HBox getHBox() {	return QBox;}

	private static void setHBox(HBox h) {	QBox = h;}

	public static ScoreScreen getScoreScreen() {return scoreScreen;}
	
	private static void gameResetter() {
		QAPane.resetQuestion();
		ScoreScreen.resetScore();
		ScoreScreen.resetFinalScorePage();
		resetQuestions();	
		
	}

}



// jsfrocha, NINCOMPOOP, Yu Hao, (2013).How to use Collections.sort() in Java ? (Specific situation)
// [WebPage] Retrieved from https://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java-specific-situation
/**
 * Class is used to compare two objects by their difficulty
 * and place them in ascending order in an ArrayList
 * @author kevin fox
 */
class QACompareInt implements Comparator<QA>{
	
	

	@Override
	/**
	 * Method to compare two objects by their difficulty
	 * @param o1: first QA object
	 * @param o2: second QA object
	 * @return integer: that places the current QA in the right position in the ArrayList
	 */
	public int compare(QA o1, QA o2) {
		
		
		return Integer.toString(o1.getDifficulty()).compareTo(Integer.toString(o2.getDifficulty()));
	}
	
}


//jsfrocha, NINCOMPOOP, Yu Hao, (2013).How to use collections.sort() in java ? (specific situation)
// [WebPage] Retrieved from https://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java-specific-situation 

/**
 * Class is used to compare two objects by their category
 * and place them in ascending order in an ArrayList
 * @author kevin fox
 */
class QACompareString implements Comparator<QA>{
	@Override
	/**
	 * Method to compare two objects by their category
	 * @param o1: first QA object
	 * @param o2: second QA object
	 * @return integer: that places the current QA in the right position in the ArrayList
	 */
	public int compare(QA o1, QA o2) {
		
		
		return o1.getCategory().compareTo(o2.getCategory());
		
	}
	
}

