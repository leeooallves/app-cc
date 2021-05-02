/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import app.Empresa;
import app.EmpresaDesbloquearCartao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CartaoCredito;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class EmpresaDesbloquearCartaoController implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    @FXML
    private TextField txCPF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btConfirmar.setOnMouseClicked((t) -> {
            desbloquearCartao();
        }
        );
          btSair.setOnMouseClicked((t) -> {
            Empresa emp = new  Empresa ();
            fechar();
            try {
                 emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
    }
    
     private void desbloquearCartao(){
        CartaoCredito cc = new CartaoCredito();
        Cliente cliente = new Cliente();
        ClienteDao bd = new ClienteDao();

        cliente.setCpf(txCPF.getText());
        cliente = bd.buscarCpf(cliente);
        
        cc.setIdCartao(cliente.getIdCartao());
        cc.setIdUsuario(cliente.getId());
        bd.desbloquearCartao(cc);
    }
     
     public void fechar() {
      EmpresaDesbloquearCartao.getStage().close();
    }       


}
