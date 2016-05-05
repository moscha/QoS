package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.util.Duration;


/**
 * Created by MOSCHA on 01-05-2016.
 */
public class Paquet extends Service {
    private double number ;
    public  Paquet(double gTime){
        this.number = gTime;
    }
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                if (isCancelled()) {
                    System.out.println("Annulation");
                }
                System.out.println("paquet généré le : " + number);
                //toto : Génération aléatoire des paquet

                return null;
            }
        };
    }
}
