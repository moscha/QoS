package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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
    private  int posFile;
    private  VBox centre;
    private  Group queue ;
    private Path path;
//    BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);

    public  Generator(double lambda,int posFile,VBox centre){
        this.lambda = lambda;
        this.tampon = new LinkedBlockingQueue<Paquet>();
        this.rand = new Random();
        this.posFile = posFile;
        this.centre = centre;
        this.queue = new Group();
        this.centre.getChildren().add(this.queue);
        path = getPath();
        queue.getChildren().add(path);
    }

    private Path getPath() {
        Path path= new Path(new MoveTo(100,posFile),new LineTo(450,posFile));
        path.setStrokeWidth(20);
        path.setStroke(Color.DARKGOLDENROD);
        return path;
    }

    @Override
    public void handle(long currentTime) {
        if(this.previousTime == 0){
            this.previousTime = currentTime;
            return;
        }
        double tempEcoule = (currentTime - this.previousTime) / mediumSpeed;
        double  t_attente = Exp(rand,lambda)*1000 ;
        tempTotal = tempTotal + t_attente;
        if(tempEcoule >= t_attente){
            final Paquet paquet = new  Paquet(tempTotal,this.posFile);
            paquet.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    Node trame = paquet.getValue();
                    PathTransition transition = new PathTransition(Duration.seconds(2),path,trame);
                    transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                    transition.setInterpolator(Interpolator.LINEAR);
                    queue.getChildren().addAll(transition.getNode());
                    transition.play();
                }
            });
            paquet.start();
            this.previousTime = currentTime;

        }
    }

    public static double Exp(Random rand,double lambda) {
        return -(1 / lambda) * Math.log(1 - rand.nextDouble());
    }
}
