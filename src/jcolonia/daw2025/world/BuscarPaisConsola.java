package jcolonia.daw2025.world;

import java.util.List;
import java.util.Scanner;

/**
 * Aplicación de consola para la búsqueda de países en la base de datos World.
 * Permite realizar consultas mediante argumentos de línea de comandos y
 * muestra los resultados paginados de 10 en 10.
 * 
 * @author manueld.garpra
 * @version 1.0
 */
public class BuscarPaisConsola {

	/**
	 * Punto de entrada de la aplicación de consola.
	 * Gestiona la recepción del patrón de búsqueda, la comunicación con la
	 * clase de acceso a datos y el control de la paginación por pantalla.
	 * 
	 * @param args Argumentos de la línea de comandos. Se espera un único 
	 *             argumento con el patrón de búsqueda (ej: "S%").
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Uso: java jcolonia.daw2025.world.BuscarPaisConsola \"patron\"");
			return;
		}

		AccesoBDWorld dao = new AccesoBDWorld();
		
		try {
			List<String> paises = dao.buscarPaises(args[0]);
			Scanner sc = new Scanner(System.in);
			
			if (paises.isEmpty()) {
				System.out.println("No se han encontrado resultados para: " + args[0]);
			} else {
				for (int i = 0; i < paises.size(); i++) {
					System.out.printf("%2d) %s%n", i + 1, paises.get(i));
					
					if ((i + 1) % 10 == 0 && i < paises.size() - 1) {
						System.out.print("\nPulse «Intro» para continuar…");
						sc.nextLine();
						System.out.println();
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error en la aplicación de texto: " + e.getMessage());
		}
	}
}

