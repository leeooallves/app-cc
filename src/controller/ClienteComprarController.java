/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ConexaoBD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Compra;
import DAO.ClienteDao;
import app.ClienteComprar;
import app.Login;
import model.Cliente;
import app.PrincipalCliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.CartaoCredito;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class ClienteComprarController implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private TextField fxValorCompra;

    @FXML
    private TextField fxDataCompra;

    @FXML
    private TextField fxNomeLoja;

    @FXML
    private Button btCancelar;
    @FXML
    private Button btVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btVoltar.setOnMouseClicked((t) -> {
            voltar();

        });
        btConfirmar.setOnMouseClicked((t) -> {
            try {
                comprar();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteComprarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btCancelar.setOnMouseClicked((t) -> {

        });
    }

    private void comprar() throws SQLException {
        Compra compra = new Compra();
        ClienteDao bd = new ClienteDao();
        Cliente cli = new Cliente();
        cli.setId(Login.idCliente);

        if (bd.verificaPessoaCartao()) {
            if (bd.verificaStatusCartao()==true) {
                if (bd.verificaSaldo(Double.parseDouble(fxValorCompra.getText()))) {
                    compra.setNomeLoja(fxNomeLoja.getText());
                    compra.setValorCompra(Double.parseDouble(fxValorCompra.getText()));
                    compra.setDataCompra(fxDataCompra.getText());
                    bd.comprar(cli, compra);
                    bd.subtrairSaldoDisponivel(cli, compra);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmado");
                    alert.setContentText("Sua compra foi efetuado com sucesso!");
                    alert.show();
                    fechar();
                    voltar();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERRO");
                    alert.setContentText("Saldo insuficiente!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERRO");
                alert.setContentText("Cartao bloqueado!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRO");
            alert.setContentText("Cartao com erro!");
            alert.show();
        }

    }

    public void voltar() {
        PrincipalCliente pc = new PrincipalCliente();
        fechar();
        try {
            pc.start(new Stage());
        } catch (Exception e) {
            System.out.println("Erro");
        }

    }

    private void fechar() {
        ClienteComprar.getStage().close();
    }

}
