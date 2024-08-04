
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
import java.io.IOException;

public class MarksController
{

    private List<MarksModel> marksmodels;
    private MarksView view;   
    
    public MarksController(MarksView view) 
    {
        this.marksmodels = new ArrayList<>();
        this.view = view;
    }
    
    //loads marks
    public void loadMarksModels(String filename) 
    {
        try (Scanner scanner = new Scanner(new File(filename))) 
        {
            // Skip the first two header lines
            if (scanner.hasNextLine()) 
            {
                scanner.nextLine(); // Skip the unit name line
            }
            if (scanner.hasNextLine()) 
            {
                scanner.nextLine(); // Skip the actual header line
            }
            while (scanner.hasNextLine()) 
            {
                String[] parts = scanner.nextLine().trim().split(",");

                // Ensure there are at least 4 parts (lastName, firstName, studentId, and at least 1 mark)
                if (parts.length >= 4) {
                    String lastName = parts[0].trim();
                    String firstName = parts[1].trim();
                    String studentId = parts[2].trim();
                    double[] marks = new double[3];

                    // Parse each mark, handle any potential parsing errors
                    try 
                    {
                        for (int i = 0; i < 3; i++) 
                        {
                            // Check if the part is empty and set it to 0.0 if so
                            if (i + 3 < parts.length && !parts[i + 3].trim().isEmpty()) 
                            {
                                marks[i] = Double.parseDouble(parts[i + 3].trim());
                            } 
                            else 
                            {
                                marks[i] = 0.0;
                            }
                        }
                        marksmodels.add(new MarksModel(lastName, firstName, studentId, marks));
                    } 
                    catch (NumberFormatException e) 
                    {
                        view.displayMessage("Error parsing marks for student: " + lastName + " " + firstName + " - " + e.getMessage());
                    } 
                }
            }
        } 
        catch (IOException e) 
        {
            view.displayMessage("Error reading file: " + e.getMessage());
        }
    }

    
    //Displays all students and their total marks
    public void displayAllStudents() 
    {
        view.displayMessage("\nAll Students and their Total Marks:");
        view.displayMarksModels(marksmodels);
    }
    
    //sort the list of students based on total marks
    private List<MarksModel> bubbleSort(List<MarksModel> studentList) 
    {
        List<MarksModel> sortedStudents = new ArrayList<>(studentList);
        for (int i = 0; i < sortedStudents.size() - 1; i++) 
        {
            for (int j = 0; j < sortedStudents.size() - 1 - i; j++) 
            {
                if (sortedStudents.get(j).getTotal() > sortedStudents.get(j + 1).getTotal()) 
                {
                    MarksModel temp = sortedStudents.get(j);
                    sortedStudents.set(j, sortedStudents.get(j + 1));
                    sortedStudents.set(j + 1, temp);
                }
            }
        }
        return sortedStudents;
    }

    //filter student marks below threshold
    public void filterStudentsByThreshold(double threshold) 
    {
        List<MarksModel> filteredStudents = new ArrayList<>();
        for (MarksModel marksmodel : marksmodels) 
        {
            if (marksmodel.getTotal() < threshold) 
            {
                filteredStudents.add(marksmodel);
            }
        }
        view.displayMessage("\nStudents with Total Marks below Threshold:");
        view.displayMarksModels(filteredStudents);
    }


    //display top5 and low5 marks
    public void displayTop5HighestAndLowest() 
    {
        List<MarksModel> sortedStudents = bubbleSort(marksmodels);

        // Create lists for top 5 highest and lowest
        List<MarksModel> top5Lowest = new ArrayList<>();
        List<MarksModel> top5Highest = new ArrayList<>();

        // Add top 5 lowest students
        for (int i = 0; i < 5 && i < sortedStudents.size(); i++) 
        {
            top5Lowest.add(sortedStudents.get(i));
        }

        // Add top 5 highest students
        for (int i = sortedStudents.size() - 1; i >= sortedStudents.size() - 5 && i >= 0; i--) 
        {
            top5Highest.add(sortedStudents.get(i));
        }

        view.displayMessage("\nTop 5 Students with Lowest Total Marks:");
        view.displayMarksModels(top5Lowest);

        view.displayMessage("\nTop 5 Students with Highest Total Marks:");
        view.displayMarksModels(top5Highest);
    }
    
    //display menu for user input
    public void startMenu() 
    {
        Scanner scanner = new Scanner(System.in);
        while (true) 
        {
            view.displayMessage("\nMenu:");
            view.displayMessage("1. Display All Students");
            view.displayMessage("2. Filter Students by Threshold");
            view.displayMessage("3. Display Top 5 Highest and Lowest");
            view.displayMessage("4. Exit");
            view.displayMessage("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) 
            {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    view.displayMessage("Enter threshold: ");
                    double threshold = scanner.nextDouble();
                    filterStudentsByThreshold(threshold);
                    break;
                case 3:
                    displayTop5HighestAndLowest();
                    break;
                case 4:
                    view.displayMessage("Exiting program.");
                    scanner.close();
                    return;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }
    }

}
