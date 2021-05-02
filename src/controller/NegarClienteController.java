/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import app.EmpresaAprovar;
import app.EmpresaDefinirNumero;
import app.NegarCliente;
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
public class NegarClienteController implements Initializable {

    @FXML
    private Button btOk;

    @FXML
    private TextField txId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btOk.setOnMouseClicked((t) -> {
            EmpresaAprovar emp = new EmpresaAprovar();
            try {
                negar();
                emp.start(new Stage());
            } catch (Exception e) {
            }

        });

    }

    public void negar() {
        CartaoCredito cli = new CartaoCredito();
        ClienteDao cldao = new ClienteDao();
        try {
            cli.setIdUsuario(Integer.parseInt(txId.getText()));
            cldao.recusaCliente(cli.getIdUsuario());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cliente recusado com sucesso");
            alert.show();
            fechar();

        } catch (Exception e) {
            System.out.println("Erro");
        }

    }

    public void fechar() {
        NegarCliente.getStage().close();
    }

}
