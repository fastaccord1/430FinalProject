package setup;

import backEnd.Database;

/**
 * Created by kreuter on 10/20/15.
 */
public class PostInstall {
    private Cryptography cryptography;

    public PostInstall(Cryptography cryptography){
        this.cryptography = cryptography;
    }

    public Database createDatabase(){
        Database database = null;

        return database;
    }
}
