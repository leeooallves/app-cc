
package DAO;

import com.mysql.cj.xdevapi.Statement;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.lang.System.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author leoal
 */
public class ConexaoBD {

    private Connection con = null;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/app_cc";
    private static final String user = "root";
    private static final String password = "";

    public Connection getConexao() {
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Conexão falhou" + ex);
        }
        return con;

    }

    public static void fechaConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("DAO.ConexaoBD.fechaConexao()" + e);
        }
    }
//        
//        public static void fechaConexao(Connection con, PreparedStatement stmt) {
//		fechaConexao(con);
//
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			Logger.(ConexaoBD.class.getName()).log(Level.SEVERE, null, e);
//		}
//	}
//        
//        public static void fechaConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
//		fechaConexao(con, stmt);
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//		} catch (SQLException e) {
//			Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, e);
//		}
//	}
//        
//        public void verificarBanco() {
//		if (ConexaoBD.getConexao() != null) {
//			fechaConexao(con);
//		} else {
//		}
//	}
}
