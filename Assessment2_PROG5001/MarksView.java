
/**
 * Write a description of class MarksView here.
 *
 * @author (Gopisanth Kobithasan)
 * @version (20240803)
 */

import java.util.List;

public class MarksView
{
    //display single student details
    public void displayMarksModel(MarksModel marksmodel) 
    {
        System.out.printf("Name: %s, ID: %s, Marks: %s, Total: %.1f\n",
            marksmodel.getFullName(), marksmodel.getStudentId(),
            arrayToString(marksmodel.getMarks()), marksmodel.getTotal());
    }    
    
    
    //convert the array of doubles to string format
    private String arrayToString(double[] array) 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) 
        {
            sb.append(array[i]);
            if (i < array.length - 1) 
            {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }   
    
    //display student list
    public void displayMarksModels(List<MarksModel> marksmodels) 
    {
        for (MarksModel marksmodel : marksmodels) 
        {
            displayMarksModel(marksmodel);
        }
    }
    
    //displays message to the user
    public void displayMessage(String message) 
    {
        System.out.println(message);
    }
    
    
}
