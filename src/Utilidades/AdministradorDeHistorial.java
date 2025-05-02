package Utilidades;

import Modelos.AdaptadorDeFechaHoraLocal;
import Modelos.FormatoDeConversion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDeHistorial {
    private final List<FormatoDeConversion> historyList = new ArrayList<>();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new AdaptadorDeFechaHoraLocal())
            .setPrettyPrinting()
            .create();


    public void add(FormatoDeConversion record) {
        historyList.add(record);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(historyList, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar historial: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            FormatoDeConversion[] records = gson.fromJson(reader, FormatoDeConversion[].class);
            if (records != null) {
                historyList.clear();
                historyList.addAll(List.of(records));
            }
        } catch (IOException e) {

        }
    }

    public void printHistory() {
        System.out.println("\n===== HISTORIAL DE CONVERSIONES =====");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (FormatoDeConversion record : historyList) {
            String fechaFormateada = record.getFecha() + " " + record.getHora();
            System.out.printf(
                    "Fecha: %s Hora: %s | Monto (%s): %.2f | Equivalente (%s): %,.2f%n",
                    record.getFecha(),
                    record.getHora(),
                    record.getFrom(),
                    record.getAmount(),
                    record.getTo(),
                    record.getResult()
            );

        }
    }
}
