/**
 * Created by kreuter on 10/18/15.
 */

import setup.*;

public class MainClass {
    public static void main(String[] args){
        if(!NewInstall.checkInstall()){

            NewInstall install = new NewInstall();

            install.createInstall();
        }


    }
}
