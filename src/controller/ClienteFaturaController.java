package controller;

import DAO.ConexaoBD;
import app.ClienteFatura;
import app.Login;
import app.PrincipalCliente;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CartaoCredito;
import model.Cliente;
import model.Compra;

public class ClienteFaturaController implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    public TableView<Compra> tbFaturas;

    @FXML
    public TableColumn<Compra, String> lojaCol;
    
    @FXML
    public TableColumn<Compra, Double> valorColuna;
    
    @FXML
    public TableColumn<Compra, Integer> idFatura;
    
    
    ObservableList<Compra> listaClientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarFaturas();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
//        ConexaoBD bd = new ConexaoBD();
//        Connection con = bd.getConexao();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = con.prepareStatement("SELECT * FROM compra_cliente WHERE id_cliente = ?");
//            stmt.setInt(1, Login.idCliente);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Compra compra = new Compra();
//                compra.setNomeLoja(rs.getString("nome_loja"));
//                compra.setValorCompra(Double.parseDouble(rs.getString("valor_compra")));
//
//                System.out.println("nome loja:" + compra.getNomeLoja());
//                System.out.println("Valor:" + compra.getValorCompra());
//                System.out.println("Id fatura:" + rs.getInt("id_compra"));
//
//                listaClientes.add(compra);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Erro" + e);
//        }
//
//        
//
//        lojaCol.setCellValueFactory(new PropertyValueFactory<>("nomeLoja"));
//        valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
//
//        tbFaturas.setItems(listaClientes);

        btVoltar.setOnMouseClicked((t) -> {
            PrincipalCliente pc = new PrincipalCliente();
            fechar();
            try {
                pc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro" + e);
            }

        });
    }

    private void listarFaturas() {
        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM compra_cliente WHERE id_cliente = ?");
            stmt.setInt(1, Login.idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setNomeLoja(rs.getString("nome_loja"));
                compra.setValorCompra(Double.parseDouble(rs.getString("valor_compra")));
                compra.setIdCompra(rs.getInt("id_compra"));

                listaClientes.add(compra);
            }

        } catch (Exception e) {
            System.out.println("Erro" + e);
        }

        lojaCol.setCellValueFactory(new PropertyValueFactory<>("nomeLoja"));
        valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
        idFatura.setCellValueFactory(new PropertyValueFactory<>("idCompra"));

        tbFaturas.setItems(listaClientes);

    }

    public void fechar() {
        ClienteFatura.getStage().close();
    }

}
