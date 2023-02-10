import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leer {

	
	static public void mostrarEnPantalla(String mensaje) {
		System.out.println(mensaje);
	}
	static public void mostrarEnPantalla(char letra) {
		System.out.println(letra);
	}
	static public boolean pedirBooleano(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato = "";
		boolean error = true,valor=false;
		while (error) {
			try {
				dato = "";
				mostrarEnPantalla(texto);
				mostrarEnPantalla("S-sí  N-no");
				dato = dataIn.readLine();
				if(dato.equals("S")||dato.equals("s"))valor=true;
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return valor;
	}
	static public String pedirCadena(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato = "";
		boolean error = true;
		while (error) {
			try {
				dato = "";
				mostrarEnPantalla(texto);
				dato = dataIn.readLine();
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;
	}
	static public char pedirCaracter(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		char dato=' ';
		boolean error = true;
		while (error) {
			try {
				dato=' ';
				mostrarEnPantalla(texto);
				dato = dataIn.readLine().charAt(0);
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;
	}
	static public char pedirCaracterLetra(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String er="[A-Za-z]";
		char dato=' ';
		boolean error = true;
		while (error) {
			try {
				dato=' ';
				mostrarEnPantalla(texto);
				dato = dataIn.readLine().charAt(0);
				if(!String.valueOf(dato).matches(er)) {
					mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
					error=true;
				}
				else error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;
	}
	static public String pedirCadenaTexto(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato = "", er="[A-Za-z]{1,}";
		boolean error = true;
		while (error) {
			try {
				dato = "";
				mostrarEnPantalla(texto);
				dato = dataIn.readLine();
				if(dato.matches(er)) error = false;
				else mostrarEnPantalla("Vuelve a introducir el dato, por favor:");
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;
	}

	
	static public int pedirEntero(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Integer.parseInt(dataIn.readLine());
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}
	static public int pedirEnteroPositivo(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Integer.parseInt(dataIn.readLine());
				if(dato<0) {
					mostrarEnPantalla("Vuelve a introducir el dato, por favor");
					error=true;
				}
				else error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	
	static public int pedirEntero(final String[] texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = -1;
		boolean error = true;
		while (error) {
			try {
				while (dato < 0 || dato > texto.length - 1) {
					for (int i = 1; i < texto.length; i++) {
						mostrarEnPantalla(i + " " + texto[i]);
					}
					mostrarEnPantalla(0 + " " + texto[0]);

					dato = Integer.parseInt(dataIn.readLine());
					error = false;
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	
	static public double pedirDouble(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		double dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Double.parseDouble(dataIn.readLine()); 
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es decimal");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}
	
	static public double pedirDoublePositivo(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		double dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Double.parseDouble(dataIn.readLine()); 
				if(dato<0) {
					mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
					error=true;
				}
				else error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es decimal");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	
	static public float pedirFloat(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		float dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Float.parseFloat(dataIn.readLine());
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es decimal");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}
}
