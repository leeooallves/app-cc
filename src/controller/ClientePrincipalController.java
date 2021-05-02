/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.ClienteComprar;
import app.ClienteFatura;
import app.ClientePagarFaturas;
import app.PrincipalCliente;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class ClientePrincipalController implements Initializable {

       @FXML
    private Button btVerFaturas;

    @FXML
    private Button btPagarFaturas;

    @FXML
    private Button btComprar;

    @FXML
    private Button btSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btSair.setOnMouseClicked((t) -> {
            fechar();

        });
         btVerFaturas.setOnMouseClicked((t) -> {
            ClienteFatura cf = new ClienteFatura();
            fechar();
            try {
                cf.start(new Stage());
            } catch (IOException ex) {
                System.out.println("Erro ao tentar acessar as faturas");
            }
         });
         btComprar.setOnMouseClicked((t) -> {
            ClienteComprar cc = new ClienteComprar();
            fechar();
            try {
                cc.start(new Stage());
            } catch (IOException ex) {
                System.out.println("Erro ao tentar acessar a area de compras");
            }
         });
         btPagarFaturas.setOnMouseClicked((t) -> {
            ClientePagarFaturas cpf = new ClientePagarFaturas();
            fechar();
            try {
                cpf.start(new Stage());
            } catch (IOException ex) {
                System.out.println("Erro ao tentar acessar a area de compras");
            }
         });
         
       
    }
 public void fechar() {
       PrincipalCliente.getStage().close();
    }    
    
}
