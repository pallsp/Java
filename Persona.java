
public class Persona {
	private Integer edad, id;
	private String nombre, apellidos;
	private static Integer idSiguiente =0;
	
	public Persona(String nombre, String apellidos, Integer edad) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.edad=edad;
		this.id = idSiguiente;
		idSiguiente ++;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "Persona [edad=" + edad + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}

}
