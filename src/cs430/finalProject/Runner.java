package cs430.finalProject; /**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.MainClassHolder;
import cs430.finalProject.frontEnd.StaffForm;

public class Runner {

    /**
     * This method only starts the program by calling MainClassHolder
     * @param args String array of arguments
     */
    public static void main(String[] args){
        //StaffForm.main(null);
        MainClassHolder mainClass = new MainClassHolder();
        mainClass.close();
    }
}
