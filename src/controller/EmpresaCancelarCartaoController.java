/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import app.Empresa;
import app.EmpresaCancelarCartao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CartaoCredito;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class EmpresaCancelarCartaoController implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    @FXML
    private TextField txCPF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btSair.setOnMouseClicked((t) -> {
            Empresa emp = new Empresa();
            fechar();
            try {
                emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
        btConfirmar.setOnMouseClicked((t) -> {

            CartaoCredito cli = new CartaoCredito();
            ClienteDao cldao = new ClienteDao();
            try {
               // cli.setIdUsuario(Integer.parseInt(txId.getText()));
                cldao.recusaCliente(cli.getIdUsuario());
                cldao.cancelarCartao(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Cliente recusado com sucesso");
                alert.show();
                fechar();

            } catch (Exception e) {
                System.out.println("Erro");
            }

            Empresa emp = new Empresa();
            fechar();
            try {
                emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });

        // TODO
    }

    public void fechar() {
        EmpresaCancelarCartao.getStage().close();
    }

}
