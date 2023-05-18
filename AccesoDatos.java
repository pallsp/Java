import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoDatos {
	
	public static void creaTabla() {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db")){
			Statement stmt = con.createStatement();
			String creaTabla = "CREATE TABLE IF NOT EXISTS personas(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, "
					+ "apellidos TEXT, edad INTEGER);";
			String eliminaTabla = "DROP TABLE IF EXISTS personas;";
			stmt.executeUpdate(eliminaTabla);
			stmt.executeUpdate(creaTabla);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Persona> mostrarDatos(){
		ArrayList<Persona> listaP = new ArrayList<>();
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db")){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM personas");
			
			while(rs.next()) {
				listaP.add(new Persona(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaP;
	}

}
