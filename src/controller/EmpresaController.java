/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.Empresa;
import app.EmpresaAprovar;
import app.EmpresaBloquearCartao;
import app.EmpresaCancelarCartao;
import app.EmpresaDesbloquearCartao;
import app.EmpresaVerDadosCliente;
import java.io.IOException;
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
public class EmpresaController implements Initializable {

    @FXML
    private Button btVerDados;

    @FXML
    private Button btAprovarClientes;

    @FXML
    private Button btBloquearCartao;

    @FXML
    private Button btCancelarCartao;

    @FXML
    private Button btDesbloquearCartao;

    @FXML
    private Button btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btSair.setOnMouseClicked((t) -> {
            fechar();

        });

        btAprovarClientes.setOnMouseClicked((t) -> {
            EmpresaAprovar ea = new EmpresaAprovar();
            fechar();
            
            try {
                 ea.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro entra aqui " + e);
            }
           
        });
        
         btVerDados.setOnMouseClicked((t) -> {
            EmpresaVerDadosCliente evdc = new EmpresaVerDadosCliente ();
            fechar();
            try {
                 evdc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }
           
        });
          btCancelarCartao.setOnMouseClicked((t) -> {
            EmpresaCancelarCartao ecc = new EmpresaCancelarCartao();
            fechar();
            try {
                 ecc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }
           
        });
          btBloquearCartao.setOnMouseClicked((t) -> {
            EmpresaBloquearCartao ebc = new EmpresaBloquearCartao();
            fechar();
            try {
                 ebc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }
           
        });
          
          btDesbloquearCartao.setOnMouseClicked((t) -> {
            EmpresaDesbloquearCartao edc = new EmpresaDesbloquearCartao();
            fechar();
            try {
                 edc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }
           
        });
    }
    // TODO

    public void fechar() {
        Empresa.getStage().close();
    }

}
