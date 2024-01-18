import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatosAcceso {
	private static ArrayList<Integer> numsPedidos = new ArrayList<>();
	private static String RUTA; 
		
		//establecer la ruta donde se encuentra la base de datos
		public static void setPath(String path) {
			RUTA = path;
		}
		
		//método que crea las tablas pedidos y artículos 
		public static void creaTablas() {
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:"+RUTA)){
				Class.forName("org.sqlite.JDBC");
				Statement stmt = con.createStatement();
				String crearPedidos = "CREATE TABLE IF NOT EXISTS pedidos(num_pedido INTEGER, nombre TEXT)";
				//String eliminaTabla = "DROP TABLE IF EXISTS pedidos;";
				String crearArticulos = "CREATE TABLE IF NOT EXISTS articulos(id INTEGER PRIMARY KEY AUTOINCREMENT, num_pedido INTEGER,"
						+ " descripcion TEXT, cantidad INTEGER, "
						+ "FOREIGN KEY(num_pedido) REFERENCES pedidos(num_pedido))";
				//stmt.executeUpdate(eliminaTabla);
				stmt.executeUpdate(crearPedidos);
				stmt.executeUpdate(crearArticulos);
			}
			catch(SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		//método que nos devuelve una lista con los pedidos
		public static ArrayList<Pedido> printData(){
			ArrayList<Pedido> listaP = new ArrayList<>();
			ArrayList<Articulo> listaA;
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:"+RUTA)){
				Statement stmt = con.createStatement();
				ResultSet rsPedidos = stmt.executeQuery("SELECT * FROM pedidos;");
				ResultSet rsArticulos; 
				while(rsPedidos.next()) {
					int numPedido = rsPedidos.getInt("num_pedido");
					rsArticulos = stmt.executeQuery("SELECT * FROM articulos WHERE num_pedido = "+numPedido+";");
					listaA = new ArrayList<>();
					while(rsArticulos.next()) {
						listaA.add(new Articulo(numPedido,rsArticulos.getString("descripcion"),rsArticulos.getInt("cantidad")));
					}
					listaP.add(new Pedido(rsPedidos.getString("nombre"), rsPedidos.getInt("num_pedido"), new ArrayList<>(listaA)));
					listaA.clear();
				}
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return listaP;
		}
		
		//método para insertar un pedido
		public static void insertPedido(Integer numPedido, String nombre) {
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:"+RUTA)){
				if(!numsPedidos.contains(numPedido)) {
					numsPedidos.add(numPedido);
					String insertaPedido = "INSERT INTO pedidos(num_pedido, nombre) VALUES(?,?)";
					PreparedStatement stmt = con.prepareStatement(insertaPedido);
					stmt.setInt(1, numPedido);
					stmt.setString(2, nombre);
					stmt.executeUpdate();
				}
				else
					System.out.println("Pedido número: "+numPedido+" duplicado.");	
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//método para insertar un artículo
		public static void insertArticulo(Integer numPedido, String descripcion, Integer cantidad) {
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:"+RUTA)){
				
				String insertaPedido = "INSERT INTO articulos(num_pedido, descripcion, cantidad) VALUES(?,?,?)";
				PreparedStatement stmt = con.prepareStatement(insertaPedido);
				stmt.setInt(1, numPedido);
				stmt.setString(2, descripcion);
				stmt.setInt(3, cantidad);
				stmt.executeUpdate();
			}
			catch(SQLException e) {	
				e.printStackTrace();
			}
		}
		
		//método para eliminar las tablas creadas
		public static void deleteAll() {
			try(Connection con =DriverManager.getConnection("jdbc:sqlite:"+RUTA)){
				Statement stmt= con.createStatement();
				stmt.executeUpdate("DELETE FROM pedidos;");
				stmt.executeUpdate("DELETE FROM articulos");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		/*
		 public static ArrayList<Integer> mostrarId(){
			ArrayList<Integer> listaI = new ArrayList<>();
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:")){
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id FROM pedidos;");
				while(rs.next()) {
					listaI.add(rs.getInt("id"));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return listaI;
		}
		 */
		
		
		/*
		public static void insert(String titulo, String autor, String editorial, String tematica, Double precio) {
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:")){
				String insertaPersona = "INSERT INTO libros(titulo, autor, editorial,tematica,precio) VALUES (?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(insertaPersona);
				stmt.setString(1, titulo);
				stmt.setString(2, autor);
				stmt.setString(3, editorial);
				stmt.setString(4, tematica);
				stmt.setDouble(5, precio);
				stmt.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		/*
		public static void changeDatos(Integer id, Libro l) {
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:")){
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE libros SET titulo = '"+l.getTitulo()+"', autor = '"+l.getAutor()
						+"', editorial='"+l.getEditorial()+"', tematica='"+l.getTematica()+"', precio = "+l.getPrecio()+" WHERE id= "+id+";");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static ArrayList<Libro> selectByTitle(String titulo) {
			ArrayList <Libro> listaP = new ArrayList<>();
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:")){
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM libros WHERE titulo = '"+titulo+"';");
				
				while(rs.next()) {
					listaP.add(new Libro(rs.getString("titulo"), rs.getString("autor"),rs.getString("editorial"),rs.getString("tematica"),
							rs.getDouble("precio")));
				}
				//listaP.sort((x,y)->x.getNombre().compareTo(y.getNombre()));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return listaP;
		}
		
		public static Libro selectById(Integer id){
			Libro l=null;
			try(Connection con = DriverManager.getConnection("jdbc:sqlite:")){
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM libros WHERE id = "+id+";");
				
				while(rs.next()) {
					l= new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), 
							rs.getString("tematica"), rs.getDouble("precio"));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return l;
		}
	*/
}
