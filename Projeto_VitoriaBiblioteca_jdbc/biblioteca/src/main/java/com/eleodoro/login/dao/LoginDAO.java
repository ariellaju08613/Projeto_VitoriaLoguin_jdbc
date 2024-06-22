import com.eleodoro.login.conexao;
import java.sql.PrepareStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import LoginDAO;

import com.eleodoro.conexao.Conexao;
import com.eleodoro.modelo.Login;

public class LoginDAO{

    private Login login;
    private final String SQLINCLUIR = "INSERT INTO LOGIN VALUES (?, ?, ?)";
    private final String SQLALTERAR = "UPDATE LOGIN SET NOME = ?, SENHA = ? WHERE ID = ?";
    private final String SQLEXCLUIR = "DELETE FROM LOGIN WHERE ID = ?";
    private final String SQLCONSULTAR = "SELECT * FROM LOGIN WHERE ID = ?";

    public LoginDAO (Login login) {
        this.login = login;
    }

    public boolean incluir () {
        try {
            PreparedStatement ps = Conexao.getConexao () .PreparedStatement(SQLINCLUIR);
            ps.setInt(1, login.getId());
            ps.setString(2, login.getNome());
            ps.setString(3, login.getSenha());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível incluir o login");
            return false;
        }
    }

    public boolean alterar () {
        try {
            PreparedStatement ps = Conexao.getConexao () .PreparedStatement(SQLALTERAR);
            ps.setString(1, login.getNome());
            ps.setString(2, login.getSenha());
            ps.setInt(3, login.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível alterar o login");
            return false;
        }
    }

    public boolean excluir(){
        try {
           PreparedStatement ps = Conexao.getConexao () .PreparedStatement(SQLEXCLUIR);
           ps.setInt(1, login.getId());
           ps.executeUpdate();
           return true;
        } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Não foi possivel excluir o login");
           return false;
        }
    }

    public boolean consultar () {
        try {
            PreparedStatement ps = Conexao.getConexao () .PreparedStatement(SQLCONSULTAR);
            ps.setInt(1, login.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                login.setNome(rs.getString(2));
                login.setSenha(rs.getString(3));
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível consultar o login");
            return false;
        }
    }
}
