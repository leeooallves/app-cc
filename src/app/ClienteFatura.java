/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jfran
 */
public class ClienteFatura extends Application{
    private static Stage stage;


    @Override
   public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./view/ClienteFatura.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Faturas");
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
         launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ClienteFatura.stage = stage;
    }
    
}
