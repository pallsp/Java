import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoDatos {
	//EN SQLITE DOUBLE ES REAL 
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM personas;");
			
			while(rs.next()) {
				listaP.add(new Persona(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaP;
	}
	
	public static void insert(String nombre, String apellidos, Integer edad) {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db")){
			String insertaPersona = "INSERT INTO personas(nombre, apellidos, edad) VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(insertaPersona);
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setInt(3, edad);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteAll() {
		try(Connection con =DriverManager.getConnection("jdbc:sqlite:datos.bd")){
			Statement stmt= con.createStatement();
			stmt.executeUpdate("DELETE FROM personas;");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void changeDatos(Integer id) {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db")){
			Statement stmt = con.createStatement();
			String nombre = Leer.pedirCadena("Introduce el nuevo nombre: ");
			String apellidos= Leer.pedirCadena("Introduce el nuevo apellido: ");
			Integer edad = Leer.pedirEntero("Introduce la nueva edad: ");
			stmt.executeUpdate("UPDATE personas SET nombre = '"+nombre+"', apellidos = '"+apellidos
					+"', edad = "+edad+" WHERE id= "+id+";");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Persona> selectByName(String nombre) {
		ArrayList <Persona> listaP = new ArrayList<>();
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db")){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM personas WHERE nombre = '"+nombre+"';");
			
			while(rs.next()) {
				listaP.add(new Persona(rs.getString("nombre"), rs.getString("apellidos"),(rs.getInt("edad"))));
			}
			//listaP.sort((x,y)->x.getNombre().compareTo(y.getNombre()));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaP;
	}

}
