# 💱 Conversor de Monedas – Aplicación de Consola en Java

Una aplicación de consola escrita en Java que permite convertir entre distintas monedas utilizando la API pública de ExchangeRate-API.  
Registra un historial de conversiones con marcas de tiempo y emplea características modernas del lenguaje como `record`, `HttpClient` y librerías externas como `dotenv-java` y `Gson`.

---

## 🚀 Características

- Conversión en tiempo real entre múltiples monedas 🌍
- Registro de historial con fecha y hora 🕓
- Uso de API pública (ExchangeRate-API)
- Carga de variables de entorno desde `.env`
- Uso de `record` (Java 16+) y `HttpClient` (Java 11+)
- Código modular, limpio y fácil de mantener ☕

---

## 📦 Librerías utilizadas

| Librería       | Versión | Descripción                                          |
|----------------|---------|------------------------------------------------------|
| `dotenv-java`  | 2.2.4   | Carga variables de entorno desde el archivo `.env`   |
| `gson`         | 2.13.1  | Parseo y mapeo de datos JSON                         |

---

## 📁 Estructura del proyecto

```bash
challengeConversorDeMonedas-main/
├── src/
│   ├── models/               # Clases record para modelar datos
│   ├── services/             # Lógica de conversión y consumo de API
│   ├── utils/                # Utilidades generales (lectura de env, etc.)
│   └── App.java              # Clase principal
├── .env                      # Clave API
├── README.md                 # Documentación del proyecto
└── libs/                     # Librerías externas (Gson y dotenv)

```
---
## 🔧 Configuración inicial
---
1. Asegúrate de tener Java 17 o superior instalado.
2. Coloca tus librerías `gson-2.13.1.jar` y `dotenv-java-2.2.4.jar` en la carpeta `libs/`.
3. Crea un archivo `.env` con tu clave de API: API_KEY=tu_clave_aqui

---

## 🏁 Cómo ejecutar el proyecto

Compilar:

```bash
javac -cp "libs/*" -d bin src/**/*.java
```

Ejecutar:

```bash
java -cp "libs/*:bin" App
```

---


## 🖥️ Ejemplo de uso

```bash
=== Conversor de Monedas ===
Moneda origen: USD
Moneda destino: EUR
Cantidad: 100

Resultado: 100 USD = 91.53 EUR
Conversión registrada el 2025-05-02T15:34:20
```

---

## 📝 Historial de conversiones

Cada conversión se guarda automáticamente con su marca de tiempo en consola o archivo si decides extenderlo.

---

## 📃 Licencia

Este proyecto es de uso libre para fines educativos y personales.

---
```
