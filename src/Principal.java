import Modelos.FormatoDeConversion;
import Servicios.IntercambioDeMonedas;
import Servicios.ConvertidorAvanzado;
import Utilidades.AdministradorDeHistorial;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntercambioDeMonedas service = new IntercambioDeMonedas();
        AdministradorDeHistorial history = new AdministradorDeHistorial();
        history.loadFromFile("Historial.json");

        while (true) {
            System.out.println("***********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println();
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Conversión avanzada (otras monedas)");
            System.out.println("8) Ver historial de conversiones");
            System.out.println("9) Salir");
            System.out.print("Elija una opción válida: ");

            int option = scanner.nextInt();

            String from = "", to = "";
            switch (option) {
                case 1 -> { from = "USD"; to = "ARS"; }
                case 2 -> { from = "ARS"; to = "USD"; }
                case 3 -> { from = "USD"; to = "BRL"; }
                case 4 -> { from = "BRL"; to = "USD"; }
                case 5 -> { from = "USD"; to = "COP"; }
                case 6 -> { from = "COP"; to = "USD"; }
                case 7 -> {
                    System.out.println("Entrando al conversor avanzado...");
                    ConvertidorAvanzado.run(scanner, history);
                    continue;
                }

                case 8 -> {
                    history.printHistory();
                    continue;
                }
                case 9 -> {
                    System.out.println("Gracias por usar el conversor.");
                    return;
                }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            System.out.print("Ingrese el monto a convertir: ");
            double amount = scanner.nextDouble();
            double result = service.convert(from, to, amount);
            System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, from, result, to);

            java.time.LocalDateTime timestamp = java.time.LocalDateTime.now();
            String fecha = timestamp.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
            String hora = timestamp.format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME);


            history.add(new FormatoDeConversion(from, to, amount, result, fecha, hora));
            history.saveToFile("Historial.json");
        }
    }
}
