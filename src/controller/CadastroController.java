/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import DAO.ConexaoBD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Cliente;

public class CadastroController implements Initializable {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txUsuario;

    @FXML
    private TextField txSenha;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txTelefone;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txEstado;

    @FXML
    private TextField txPais;

    @FXML
    private TextField txRua;

    @FXML
    private TextField txNumero;

    @FXML
    private TextField txCidade;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField txRenda;

    @FXML
    private TextField txBairro;

    public void initialize(URL url, ResourceBundle rb) {
//        btCadastrar.setOnMouseClicked((t) -> {
//            try {
//                cadastrarCliente();
//            } catch (SQLException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
    }
//
//    public void cadastrarCliente() throws SQLException {
//       
//        
//        
//        
//    }
}

/**
 * Initializes the controller class.
 */
