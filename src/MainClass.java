/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.*;

import setup.*;
public class MainClass {
    public static void main(String[] args){
        NewInstall test = new NewInstall("test", "test");
        if(!test.checkInstall()){
            test.createInstall();
        }


    }
}
