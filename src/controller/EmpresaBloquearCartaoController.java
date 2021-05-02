/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import DAO.ConexaoBD;
import app.Empresa;
import app.EmpresaBloquearCartao;
import app.Login;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
public class EmpresaBloquearCartaoController implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    @FXML
    private TextField txCPF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btConfirmar.setOnMouseClicked((t) -> {
            try {
                bloquearCartao();
            } catch (SQLException ex) {
                Logger.getLogger(EmpresaBloquearCartaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void bloquearCartao() throws SQLException {
        CartaoCredito cc = new CartaoCredito();
        Cliente cliente = new Cliente();
        ClienteDao bd = new ClienteDao();

        cliente.setCpf(txCPF.getText());
        cliente = bd.buscarCpf(cliente);
        
        cc.setIdCartao(cliente.getIdCartao());
        cc.setIdUsuario(cliente.getId());
        bd.bloquearCartao(cc);

    }
     public void fechar() {
      EmpresaBloquearCartao.getStage().close();
    }   
    
}
