package Servicios;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Utilidades.Config;

public class ConvertidorDeMonedas {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + Config.getApiKey() + "/latest/USD";

    private JsonObject conversionRates;

    public boolean loadRates() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            conversionRates = json.getAsJsonObject("conversion_rates");

            return true;

        } catch (Exception e) {
            System.out.println("Error al obtener tasas de cambio: " + e.getMessage());
            return false;
        }
    }

    public Map<String, Double> getAvailableCurrencies() {
        return conversionRates.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getAsDouble()));
    }

    public double convert(String from, String to, double amount) {
        double rateFrom = conversionRates.get(from).getAsDouble();
        double rateTo = conversionRates.get(to).getAsDouble();
        return amount / rateFrom * rateTo;
    }
}
