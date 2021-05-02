package controller;

import DAO.*;
import app.Empresa;
import app.EmpresaVerDadosCliente;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.text.html.FormView;
import model.Cliente;

public class EmpresaVerDadosClienteController implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btSair;

    @FXML
    private TextArea txArea;

    @FXML
    private TextField txCPF;

    @FXML
    private TableView<Cliente> tbCliente;

    @FXML
    private TableColumn<Cliente, String> nomeColuna;

    @FXML
    private TableColumn<Cliente, String> telefoneColuna;

    @FXML
    private TableColumn<Cliente, String> cpfColuna;

    @FXML
    private TableColumn<Cliente, Double> rendaColuna;

    ObservableList<Cliente> listCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listarClientes();
        } catch (Exception e) {
            System.out.println("erro listar: " + e);
        }

        btSair.setOnMouseClicked((t) -> {
            Empresa emp = new Empresa();
            fechar();
            try {
                emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });

    }

    private void listarClientes() {

        btConfirmar.setOnMouseClicked((t) -> {

            try {
                listarCLiente();

            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
    }

    public void listarCLiente() throws SQLException {
        Cliente cli = new Cliente();
        ResultSet rs = ClienteDao.consultarClientes();

        while (rs.next()) {
            if (rs.getString("cpf").equals(txCPF.getText())) {
                cli.setNome(rs.getString("nome_completo"));
                cli.setId(rs.getInt("id_usuario"));
                cli.setTelefone(rs.getInt("telefone"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRenda(rs.getDouble("renda"));

                listCliente.add(cli);
            }

            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("Nome"));
            telefoneColuna.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
            cpfColuna.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
            rendaColuna.setCellValueFactory(new PropertyValueFactory<>("Renda"));

            tbCliente.setItems(listCliente);

        }
    }

    public void fechar() {
        EmpresaVerDadosCliente.getStage().close();
    }

}
