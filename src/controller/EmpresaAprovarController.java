package controller;

import DAO.ClienteDao;
import DAO.ConexaoBD;
import app.Empresa;
import app.EmpresaAprovar;
import app.EmpresaDefinirNumero;
import app.NegarCliente;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Client;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;

public class EmpresaAprovarController implements Initializable {

    @FXML
    private Button btAprovar;

    @FXML
    private Button btRecusar;

    @FXML
    private Button btVoltar;

    @FXML
    private TextField txTela;

    @FXML
    public TableView<Cliente> tbListaAprovacao;

    @FXML
    public TableColumn<Cliente, String> nomeColuna;

     @FXML
    public TableColumn<Cliente, Double> rendaColuna;
    @FXML
    public TableColumn<Cliente, Integer> statusColuna;
    
    
     public TableColumn<Cliente, Integer> idColuna;
    @FXML

    ObservableList<Cliente> clienteLista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            visualizar();

        } catch (Exception e) {
            System.out.println("erro aquii " + e);
        }
        tbListaAprovacao.setItems(clienteLista);
        btAprovar.setOnMouseClicked((t) -> {
            EmpresaDefinirNumero edn = new EmpresaDefinirNumero();
            fechar();
            try {
                edn.start(new Stage());
            } catch (IOException e) {
                System.out.println("Erro aqui " + e);
            }

        });
          btVoltar.setOnMouseClicked((t) -> {
            Empresa emp = new  Empresa ();
            fechar();
            try {
                 emp.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });
          btRecusar.setOnMouseClicked((t) -> {
            NegarCliente nc = new  NegarCliente ();
            fechar();
            try {
                 nc.start(new Stage());
            } catch (Exception e) {
                System.out.println("Erro");
            }

        });

    }

    public void visualizar() {

        ConexaoBD bd = new ConexaoBD();
        Connection con = bd.getConexao();
        java.sql.PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE status_cadastro = ?");
            stmt.setInt(1, 0);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNome(rs.getString("nome_completo"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRenda(Double.parseDouble(rs.getString("renda")));
                cli.setStatus(rs.getInt("status_cadastro"));
                 cli.setId(rs.getInt("id_usuario"));

                clienteLista.add(cli);
            }

        } catch (Exception e) {
            System.out.println("erro: entra qqui" + e);
        }

        nomeColuna.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        rendaColuna.setCellValueFactory(new PropertyValueFactory<>("Renda"));
        statusColuna.setCellValueFactory(new PropertyValueFactory<>("Status"));
        idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbListaAprovacao.setItems(clienteLista);

    }

    public void fechar() {
        EmpresaAprovar.getStage().close();
    }

}
