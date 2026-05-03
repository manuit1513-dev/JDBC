package jcolonia.daw2025.JDBC;

import java.util.List;
import java.util.Scanner;

public class BuscarPaisConsola {
	public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java jcolonia.daw2025.world.BuscarPaisConsola \"patron\"");
            return;
        }

        AccesoBDWorld dao = new AccesoBDWorld();
        try {
            List<String> paises = dao.buscarPaises(args[0]);
            Scanner sc = new Scanner(System.in);
            
            for (int i = 0; i < paises.size(); i++) {
                System.out.printf("%2d) %s%n", i + 1, paises.get(i));
                if ((i + 1) % 10 == 0 && i < paises.size() - 1) {
                    System.out.print("Pulse «Intro» para continuar…");
                    sc.nextLine();
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
