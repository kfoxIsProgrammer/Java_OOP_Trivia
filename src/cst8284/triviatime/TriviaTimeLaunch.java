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



import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Class builds the splashScreen and gets the menubar for options in the game
 * @author kevin
 * @see javafx.animation.FadeTransition
 * @see javafx.animation.ParallelTransition
 * @see javafx.animation.RotateTransition
 * @see javafx.animation.ScaleTransition
 * @see javafx.animation.TranslateTransition
 * @see javafx.application.Application
 * @see javafx.geometry.Pos
 * @see javafx.scene.Scene
 * @see javafx.scene.effect.Light
 * @see javafx.scene.effect.Lighting
 * @see javafx.scene.text.Font
 * @see javafx.scene.text.Text
 * @see javafx.scene.layout.BorderPane
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.paint.Color
 * @see javafx.stage.Stage
 * @see javafx.util.Duration
 * @since build 1.8.0_162-b12
 */
public class TriviaTimeLaunch extends Application {
	

	private static BorderPane rootPane;

	@Override
	public void start(Stage primaryStage) {
		// Display Splash Screen
		setRootPane("Welcome to Trivial Time");
		getRootPane().setTop(Controls.getMenuBar(primaryStage));
		Scene scene = new Scene(getRootPane(), 1024, 512);
		primaryStage.setTitle("Trivia Time");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	
	public void setRootPane(String logo) {

		getTransitions(logo);
		HBox hbox = new HBox(getTransitions(logo));
		hbox.setAlignment(Pos.CENTER);
		BorderPane temp = new BorderPane();
		temp.setCenter(hbox);
		rootPane = temp;

	}

	public BorderPane getRootPane() {

		return rootPane;
	}
	
	
	

	//Oracle. (2011)(2014) JavaFX: Transformations, Animations, and Visual Effects
	//[WebPage] Retrieved from https://docs.oracle.com/javase/8/javafx/visual-effects-tutorial/basics.htm#BEIIJJJB
	
	/**
	 * Creates transitions for the splashscreen's text
	 * @param t, String that holds the splashscreen's logo
	 * @return Text: logo with transitions
	 */
	public Text getTransitions(String t) {
		
		
		
		Text text = new Text(t);
		Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        
        text.setEffect(lighting);
        text.setFill(Color.RED);
		
        
       
        text.setFont(Font.font("Times New Roman", 36));
        
        
		ScaleTransition scale = 
				new ScaleTransition(Duration.millis(5000),text);
		scale.setToX(2f);
        scale.setToY(2f);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
         
  
        
        RotateTransition rotate = 
                new RotateTransition(Duration.millis(3000), text);
            rotate.setByAngle(180f);
            rotate.setCycleCount(2);
            rotate.setAutoReverse(true);
            
            
            
            FadeTransition fade = 
            		new FadeTransition(Duration.millis(3000), text);
                fade.setFromValue(1.0f);
                fade.setToValue(0.3f);
                fade.setCycleCount(2);
                fade.setAutoReverse(true);
                
                
                TranslateTransition translate =
                        new TranslateTransition(Duration.millis(2000), text);
                    translate.setFromX(375);
                    translate.setToX(0);
                    translate.setCycleCount(1);
                    translate.setAutoReverse(true);
                    
                    
                    ParallelTransition parrallel = new ParallelTransition();
              parrallel.getChildren().addAll(scale,rotate,fade,translate);
              parrallel.setCycleCount(1);
              parrallel.play();
        	
		
		
		return text;
	}
}