
/**
 * Write a description of class MarksModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MarksModel
{
    private String firstName;
    private String lastName;
    private String studentId;
    private double[] marks;
    private double total;
    
    
    //calculate the total marks
     private void calculateTotal() {
        total = 0;
        for (double mark : marks) {
            total += mark;
        }
    }
    
        public MarksModel(String lastName, String firstName, String studentId, double[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.marks = marks;
        calculateTotal();
    }
    
    

}
