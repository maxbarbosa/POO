import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
  
        public void adicionarEntrega(Entrega en) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO Entrega(hora, dataE, NumVen, Placa, CodMot) VALUES(?,?,?,?,?)");
            stmt.setString(1, en.getHora());
            stmt.setDate(2, (Date) en.getDataE());
            stmt.setInt(3, en.getNumVen());
            stmt.setString(4, en.getPlaca());
            stmt.setInt(5, en.getCodMot());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public void removerEntrega(int NumVen) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM Entrega WHERE NumVen=?");
            stmt.setInt(1, NumVen);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Entrega> listarEntrega() {
        List<Entrega> listaRetorno = new ArrayList<>();

        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM Entrega ORDER BY NumVen");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entrega en = new Entrega(rs.getString("hora"),rs.getDate("dataE"),rs.getInt("NumVen"),rs.getString("Placa"),rs.getInt("CodMot"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
}  