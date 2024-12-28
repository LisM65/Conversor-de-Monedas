package com.alurachallenge.conversormonedas.principal;

import com.alurachallenge.conversormonedas.calculos.ConversorMonedas;
import com.alurachallenge.conversormonedas.calculos.ConversorMonedasExchangeRate;
import com.google.gson.*;

import javax.print.DocFlavor;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequestApi {
    public static void main(String[] args) {
        Scanner lectura1 = new Scanner(System.in);
        List<ConversorMonedas> conversorLista = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();


        while (true){
            System.out.println("----------------------------------------------------\n" +
                    "CONVERSOR DE MONEDAS");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Mostrar historial de conversiones");
            System.out.println("3. Salir");
            System.out.println("----------------------------------------------------\n" +
                    "**  Ingrese opción de conversión: **");
            var busqueda = lectura1.nextLine();
            if (busqueda.equals("3") || busqueda.equalsIgnoreCase("salir")){
                break;
            } else if (busqueda.equals("1") || busqueda.equalsIgnoreCase("convertir monedas")){
                System.out.println("\t 1. Dolar a peso argentino");
                System.out.println("\t 2. Peso argentino a dolar");
                System.out.println("\t 3. Dolar a real brasileño");
                System.out.println("\t 4. Real brasilemo a dolar");
                System.out.println("\t 5. Dolar a peso colombiano");
                System.out.println("\t 6. Peso colombiano a dolar");

                String url = "https://v6.exchangerate-api.com/v6/7a4417955fa96ffb0d3beaa2/";
                String opcion = "pair/";
                System.out.println("Ingrese la opcion deseada ");
                String moneda1 ="", moneda2 ="";
                var busqueda1 = lectura1.nextLine();
                if (busqueda1.equals("1")){
                    moneda1 = "USD";
                    moneda2 = "ARS";
                } else if (busqueda1.equals("2")) {
                    moneda1 = "ARS";
                    moneda2 = "USD";
                } else if (busqueda1.equals("3")) {
                    moneda1 = "USD";
                    moneda2 = "BRL";
                } else if (busqueda1.equals("4")) {
                    moneda1 = "BRL";
                    moneda2 = "USD";
                } else if (busqueda1.equals("5")) {
                    moneda1 = "USD";
                    moneda2 = "COP";
                } else if (busqueda1.equals("6")) {
                    moneda1 = "COP";
                    moneda2 = "USD";
                }

                /*System.out.println("Ingresa moneda a la que desea convertir:");
                var busqueda2 = lectura1.nextLine();
                String moneda2 = busqueda2;
                */System.out.println("Ingrese el monto que desea convertir");
                var busqueda3 = lectura1.nextLine();

                String urlCompleto = url + opcion + moneda1 + "/" + moneda2 +"/" + busqueda3;
                System.out.println(urlCompleto);

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlCompleto))
                        .build();

                String json;

                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json = response.body();
                    String responseBody = response.body();
                    JsonObject json1 = JsonParser.parseString(responseBody).getAsJsonObject();

                    ConversorMonedasExchangeRate conversorExch1 = gson.fromJson(responseBody, ConversorMonedasExchangeRate.class);
                    ConversorMonedas conversores =new ConversorMonedas(conversorExch1);
                    conversorLista.add(conversores);

                } catch (IOException | InterruptedException e) {
                    System.err.println("Ocurrió un error al realizar la solicitud: " + e.getMessage());
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error en la URL");
                }
            } else if (busqueda.equals("2") || busqueda.equalsIgnoreCase("mostrar historial")) {
                System.out.println(conversorLista);
                try (FileWriter escritura = new FileWriter("historial.json")){
                    escritura.write(gson.toJson(conversorLista));
                    System.out.println("historial salida");
                } catch (IOException e) {
                    System.out.println("ocurrio un error" + e.getMessage());
                    e.printStackTrace();

                }
            }

        }



        System.out.println("FINALIZO EL PROGRAMA");

    }
}
