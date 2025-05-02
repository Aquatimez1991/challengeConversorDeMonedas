package Utilidades;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/APIKEY")
            .filename(".env")
            .load();

    public static String getApiKey() {
        return dotenv.get("API_KEY");
    }
}
