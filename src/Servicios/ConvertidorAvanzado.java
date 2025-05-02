package Servicios;

import Modelos.FormatoDeConversion;
import Utilidades.AdministradorDeHistorial;

import java.util.Map;
import java.util.Scanner;

public class ConvertidorAvanzado {
    public static void run(Scanner scanner, AdministradorDeHistorial historyManager) {
        ConvertidorDeMonedas converter = new ConvertidorDeMonedas();

        if (!converter.loadRates()) {
            return;
        }

        Map<String, Double> currencies = converter.getAvailableCurrencies();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n============================");
            System.out.println("  CONVERSOR AVANZADO");
            System.out.println("============================");
            System.out.println("Monedas disponibles:");
            printCurrencies(currencies);

            System.out.print("Ingrese moneda origen (ej. USD): ");
            String from = scanner.next().toUpperCase();
            while (!currencies.containsKey(from)) {
                System.out.print("Código no válido. Intente nuevamente: ");
                from = scanner.next().toUpperCase();
            }

            System.out.print("Ingrese moneda destino (ej. CLP): ");
            String to = scanner.next().toUpperCase();
            while (!currencies.containsKey(to)) {
                System.out.print("Código no válido. Intente nuevamente: ");
                to = scanner.next().toUpperCase();
            }

            System.out.print("Ingrese cantidad a convertir: ");
            double amount = scanner.nextDouble();

            double result = converter.convert(from, to, amount);
            System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, from, result, to);


            java.time.LocalDateTime timestamp = java.time.LocalDateTime.now();
            String fecha = timestamp.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
            String hora = timestamp.format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME);


            historyManager.add(new FormatoDeConversion(from, to, amount, result, fecha, hora));
            historyManager.saveToFile("Historial.json");

            System.out.println("\n¿Deseas realizar otra conversión avanzada? (s/n): ");
            String respuesta = scanner.next().toLowerCase();
            continuar = respuesta.equals("s") || respuesta.equals("si");
        }
    }

    private static void printCurrencies(Map<String, Double> currencies) {
        int count = 0;
        for (String code : currencies.keySet().stream().sorted().toList()) {
            System.out.printf("%-6s", code);
            count++;
            if (count % 30 == 0) {
                System.out.println();
            }
        }
        if (count % 30 != 0) {
            System.out.println();
        }
        System.out.println();
    }
}
