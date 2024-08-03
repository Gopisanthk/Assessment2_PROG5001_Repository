
/**
 * Write a description of class MarksController here.
 *
 * @author (Gopisanth Kobithasan)
 * @version (20240803)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class MarksController
{

    private List<MarksModel> marksmodels;
    private MarksView view;   
    
    public MarksController(MarksView view) {
        this.marksmodels = new ArrayList<>();
        this.view = view;
    }
    
    //loads marks
    public void loadMarksModels(String filename) {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
 
            String[] parts = line.split(",");
            String lastName = parts[0].trim();
            String firstName = parts[1].trim();
            String studentId = parts[2].trim();
            double[] marks = new double[3];

            marksmodels.add(new MarksModel(lastName, firstName, studentId, marks));
        }
        scanner.close();
    }

    
    //Displays all students and their total marks
    public void displayAllStudents() {
        view.displayMessage("\nAll Students and their Total Marks:");
        view.displayMarksModels(marksmodels);
    }
    

}
