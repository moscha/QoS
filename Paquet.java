package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * Created by MOSCHA on 01-05-2016.
 */
public  class Paquet extends Service<Rectangle> {
    protected double number ;
    protected int posFile;

    public  Paquet (double gTime,int posFile){
        this.number = gTime;
        this.posFile = posFile;
    }


    @Override
    protected Task<Rectangle> createTask() {
        return new Task<Rectangle>() {
            @Override
            protected Rectangle call() throws Exception {
                if (isCancelled()) {
                    System.out.println("Annulation");
                }
                Rectangle trame = new Rectangle(100, posFile,30,20);
                trame.setFill(Color.YELLOW);
                trame.setArcWidth(5);
                trame.setArcHeight(5);
                trame.setStroke(Color.BLACK);
                return trame;
            }
        };
    }
    public double getNumber(){
        return  this.number;
    }
    private String doubleToString(){
        return String.valueOf(Math.round(this.getNumber()));
    }
}
