package sample;

import javafx.animation.AnimationTimer;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by MOSCHA on 01-05-2016.
 */
public class Generator extends AnimationTimer {
    private  double previousTime=0;
    private double  tempTotal = 0;
    final private double SECOND=1000;
    final private int heighSpeed  = 10000000;
    final private int mediumSpeed = 100000000;
    final private int slowSpeed   = 1000000000;
    private  Random rand;
    // lambda est le nombre moyen de paquet par seconde [Débit]
    private double lambda;
    private BlockingQueue<Paquet> tampon;
//    BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
    public  Generator(double lambda){
        this.lambda = lambda;
        this.tampon = new LinkedBlockingQueue<Paquet>();
        this.rand = new Random();
    }

    @Override
    public void handle(long currentTime) {
        if(previousTime == 0){
            previousTime = currentTime;
            return;
        }
        double tempEcoule = (currentTime - previousTime) / mediumSpeed;
        double  t_attente = Exp(rand,lambda)*1000 ;
        tempTotal = tempTotal + t_attente;
        if(tempEcoule >= t_attente){
            new  Paquet(tempTotal).start();
            previousTime = currentTime;
        }
    }

    public static double Exp(Random rand,double lambda) {
        return -(1 / lambda) * Math.log(1 - rand.nextDouble());
    }
}
