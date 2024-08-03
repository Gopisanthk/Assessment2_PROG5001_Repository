
/**
 * Write a description of class Marks_Main here.
 *
 * @author (Gopisanth Kobithasan)
 * @version (20240803)
 */
import java.util.Scanner;
public class Marks_Main
{
public static void main(String[] args) {
        MarksView view = new MarksView(); // Create the view instance.
        MarksController controller = new MarksController(view); // Create the controller instance.

        // File containing student data.
        String filename = "prog5001_students_grade_2022.csv"; 
        // Load student data from the file.
        controller.loadMarksModels(filename); 

    }
}
