package Servicios;
import Utilidades.Config;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IntercambioDeMonedas {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + Config.getApiKey() + "/latest/";

    public double convert(String from, String to, double amount) {
        try {
            String url = BASE_URL + from;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = json.getAsJsonObject("conversion_rates");
            double rate = conversionRates.get(to).getAsDouble();
            return rate * amount;
        } catch (Exception e) {
            System.out.println("Error en conversión: " + e.getMessage());
            return 0.0;
        }
    }
}