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

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
/**
 * Class is used to take a file and download QA objects into an ArrayList
 * @author kevin
 * @see java.io.EOFException
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.IOException
 * @see java.io.ObjectInputStream
 * @see java.util.ArrayList
 * @since build 1.8.0_162-b12
 */
public class FileUtils {
	

	private static ArrayList<QA> qaAr = new ArrayList<QA>();
	/**
	 * Method to open a .trivia file and load them into a arraylist of QA
	 * @param absPath The path to the .trivia file
	 */
	public static void setQAArrayList(String absPath) {
		qaAr = new ArrayList<QA>();
		if (fileExists(absPath)) {
			try (
					 FileInputStream fis
					 = new FileInputStream(absPath);
					 ObjectInputStream ois
					 = new ObjectInputStream(fis);){
					 while (true)
					 // load the QA ArrayList here

					qaAr.add((QA) (ois.readObject()));

				
			} catch (EOFException e) {}
					 catch (IOException |
					 ClassNotFoundException e) {}
			
		} else
			qaAr = null;
	}
/**
 * 
 * @return The ArrayList with all the QAs
 */
	public static ArrayList<QA> getQAArrayList() {
		return qaAr;
	}
/**
 * Checks if a file exists
 * @param f File that holds the current .trivia file
 * @return a boolean is the file exists or not
 */
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length() > 2));
	}
/**
 * Checks if a file exists
 * @param s String that is the absolute path for a .trivia file
 * @return boolean if that file exists
 */
	public static boolean fileExists(String s) {
		return (fileExists(new File(s)));
	}
/**
 * Gets the files absolute path
 * @param f File holding .trivia file
 * @return String with the path to that .trivia file
 */
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

}
