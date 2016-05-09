package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    final double lambda = 2;
    private Stage fenetre;
    private  BorderPane root;
    private VBox centre;
    private VBox left;
    @Override
    public void start(Stage primaryStage) throws Exception{
        fenetre = primaryStage;
        root = createContent();
        fenetre.setScene(new Scene(root));
        fenetre.show();
    }

    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setTop(getMenubar());
        root.setLeft(getLeft());
        centre = new VBox(20);
        root.setCenter(centre);
        root.setPrefSize(800, 600);
        return root;
    }

    private Node getMenubar() {
        Menu fileMenu = new Menu("Fichier");

        MenuItem demarrer = new MenuItem("Démarrer");
        demarrer.setOnAction(e -> demarrer());
        fileMenu.getItems().add(demarrer);

        MenuItem ouvrir = new MenuItem("Ouvrir");
        ouvrir.setOnAction(e -> ouvrir());
        fileMenu.getItems().add(ouvrir);

        MenuItem quitter = new MenuItem("Quitter");
        quitter.setOnAction(e -> quitter());
        fileMenu.getItems().add(quitter);

        Menu editMenu = new Menu("Edition");
        //...

        Menu configMenu = new Menu("Configuration");
        //...

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(fileMenu,editMenu,configMenu);
        return menubar;
    }

    private VBox getLeft() {
        VBox vBox = new VBox();
        TableView<Flux> table = new TableView<>();
        TableColumn<Flux,String>  namec = new TableColumn<>("name");
        namec.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Flux,Integer>  numc = new TableColumn<>("numQueue");
        numc.setCellValueFactory(new PropertyValueFactory<>("numQueue"));

        TableColumn<Flux,Double>  tauxc = new TableColumn<>("Taux");
        tauxc.setCellValueFactory(new PropertyValueFactory<>("Taux"));

        TableColumn<Flux,Double>  capacitec = new TableColumn<>("Capacité");
        capacitec.setCellValueFactory(new PropertyValueFactory<>("Capacité"));

        table.setItems(getFlux());
        table.getColumns().addAll(namec, numc, tauxc, capacitec);
        vBox.getChildren().add(table);

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        TextField name = new TextField();
        name.setPromptText("Nom ");
        name.setMinWidth(100);

        TextField numQueue = new TextField();
        numQueue.setPromptText("Numéro queue");
        numQueue.setMinWidth(100);

        TextField taux = new TextField();
        taux.setPromptText("Taux ");

        TextField capacite = new TextField();
        capacite.setPromptText("Capacité");

        Button add = new Button("Ajouter");
        Button del = new Button("Supprimer");

        hBox1.setPadding(new Insets(5,5,5,5));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(name,numQueue);
        vBox.getChildren().add(hBox1);

        hBox2.setPadding(new Insets(5,5,5,5));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(taux,capacite);
        vBox.getChildren().add(hBox2);

        hBox3.setPadding(new Insets(5,5,5,5));
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(add,del);
        vBox.getChildren().add(hBox3);
        return vBox;
    }

    private void quitter() {
    }


    private void ouvrir() {
    }

    private void demarrer() {
        //Instanstantiation de générateurs selon le nonbre de files avec leur propre lambda
        new Generator(lambda,50,centre).start();
        new Generator(3,100,centre).start();
        new Generator(4,100,centre).start();
    }

    private ObservableList<Flux> getFlux(){
        ObservableList<Flux> flux = FXCollections.observableArrayList();
        flux.add(new Flux("A",0,2,1024));
        flux.add(new Flux("B",1,2,1024));
        flux.add(new Flux("C",2,3,1024));
        flux.add(new Flux("D",3,2,1024));
        flux.add(new Flux("E",4,3,1024));
        return flux;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
