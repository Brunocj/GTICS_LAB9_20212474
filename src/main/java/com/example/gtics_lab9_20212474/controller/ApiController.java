package com.example.gtics_lab9_20212474.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ApiController {
    public ApiController() {
    }

    public List<List<String>> listar() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail";
        List<List<String>> error = new ArrayList();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[0]);
            System.out.println(response);
            if (response.getStatusCode().is5xxServerError()) {
                System.out.println("Error del servidor: " + response.getStatusCodeValue());
                return error;
            } else if (response.getStatusCode().is2xxSuccessful()) {
                return responseToList(response);
            } else {
                System.out.println("Respuesta inesperada: " + response.getStatusCodeValue());
                return error;
            }
        } catch (RestClientException var8) {
            if (var8 instanceof HttpStatusCodeException httpException) {
                System.out.println("Error HTTP: " + httpException.getStatusCode().value());
            } else {
                System.out.println("Error al realizar la solicitud: " + var8.getMessage());
            }

            return error;
        }
    }

    public static List<List<String>> responseToList(ResponseEntity<String> response) {
        List<List<String>> cocktails = new ArrayList();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse((String)response.getBody());
            JSONArray drinksArray = (JSONArray)jsonObject.get("drinks");
            if (drinksArray != null) {
                int count = 0;

                for(Iterator var6 = drinksArray.iterator(); var6.hasNext(); ++count) {
                    Object drinkObj = var6.next();
                    if (count >= 12) {
                        break;
                    }

                    List<String> cocktail = new ArrayList();
                    JSONObject drink = (JSONObject)drinkObj;
                    String name = (String)drink.get("strDrink");
                    String thumbnail = (String)drink.get("strDrinkThumb");
                    String id = (String)drink.get("idDrink");
                    cocktail.add(name);
                    cocktail.add(thumbnail);
                    cocktail.add(id);
                    cocktails.add(cocktail);
                }
            } else {
                System.out.println("No se encontraron cócteles en la respuesta.");
            }
        } catch (ParseException var13) {
            var13.printStackTrace();
        }

        return cocktails;
    }
}
