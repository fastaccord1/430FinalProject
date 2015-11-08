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
        MainClassHolder mainClass = new MainClassHolder();

        if(args.length == 1){
            if(args[0].equals("--fresh")){
                //System.out.println("We made it!");
                //mainClass.freshInstall();
            } else{
                System.exit(1);
            }
        } else{
            mainClass.noInstall();
        }
        //mainClass.close();
    }
}
