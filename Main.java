package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    final double lambda = 2;
    private Stage fenetre;

    @Override
    public void start(Stage primaryStage) throws Exception{
        fenetre = primaryStage;
        fenetre.setScene(new Scene(createContent()));
        fenetre.show();


    }

    private Parent createContent(){
        Pane root = new Pane();
        Button go = new Button("Go");
        go.setOnAction(e -> go());
        root.getChildren().add(go);
        root.setPrefSize(800,300);
        return root;
    }

    private void go() {
        //Instanstantiation de générateurs selon le nonbre de files avec leur propre lambda
        new Generator(lambda).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
