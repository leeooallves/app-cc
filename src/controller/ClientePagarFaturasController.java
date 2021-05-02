/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDao;
import DAO.ConexaoBD;
import app.ClientePagarFaturas;
import app.Login;
import app.PrincipalCliente;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;
import app.Login;
import javafx.scene.control.Alert;
import model.Compra;

/**
 * FXML Controller class
 *
 * @author jfran
 */
public class ClientePagarFaturasController implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    private Button btPagar;

    @FXML
    private TextField txIdFatura;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btVoltar.setOnMouseClicked((t) -> {
            PrincipalCliente pc = new PrincipalCliente();
            fechar();
            try {
                pc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
        btPagar.setOnMouseClicked((t) -> {
            ConexaoBD bd = new ConexaoBD();
            Connection con = bd.getConexao();
            Cliente cli = new Cliente();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Compra compra = new Compra();
            ClienteDao dao = new ClienteDao();

            try {
                stmt = con.prepareStatement("SELECT * FROM compra_cliente WHERE id_cliente = ?");
                stmt.setInt(1, Login.idCliente);
                rs = stmt.executeQuery();
                int b = Integer.parseInt(txIdFatura.getText());
                while (rs.next()) {
                    if (b == rs.getInt("id_compra")) {

                        compra.setNomeLoja(rs.getString("nome_loja"));
                        compra.setValorCompra(Double.parseDouble(rs.getString("valor_compra")));

                        System.out.println("nome loja:" + compra.getNomeLoja());
                        System.out.println("Valor:" + compra.getValorCompra());
                        System.out.println("Id fatura:" + rs.getInt("id_compra"));

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Confirmado");
                        alert.setContentText("Sua compra foi paga com sucesso!");
                        alert.show();

                    }

                }

            } catch (Exception e) {
                System.out.println("erro> " + e);
            }

        });

        // TODO
    }

    public void fechar() {
        ClientePagarFaturas.getStage().close();
    }

}
