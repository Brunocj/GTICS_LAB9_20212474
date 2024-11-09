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
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        List<List<String>> error = new ArrayList();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[0]);
            //System.out.println(response);
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

    public List<List<String>> buscar(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String urlbase = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String url = urlbase + nombre;
        List<List<String>> error = new ArrayList();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[0]);
            System.out.println("Respuesta obtenida: " +response);
            if (response.getStatusCode().is5xxServerError()) {
                System.out.println("Error del servidor: " + response.getStatusCodeValue());
                return error;
            } else if (response.getStatusCode().is2xxSuccessful()) {
                return responseToListMeals(response);
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


    public List<String> info(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String urlbase = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
        String url = urlbase + id;
        List<String> error = new ArrayList();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[0]);
            System.out.println("Respuesta obtenida: " +response);
            if (response.getStatusCode().is5xxServerError()) {
                System.out.println("Error del servidor: " + response.getStatusCodeValue());
                return error;
            } else if (response.getStatusCode().is2xxSuccessful()) {
                return response2ToList(response);
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
        List<List<String>> cocktails = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
            JSONArray drinksArray = (JSONArray) jsonObject.get("categories");

            if (drinksArray != null) {
                for (Object drinkObj : drinksArray) {
                    List<String> cocktail = new ArrayList<>();
                    JSONObject drink = (JSONObject) drinkObj;

                    String name = (String) drink.get("strCategory");
                    String thumbnail = (String) drink.get("strCategoryThumb");
                    String desc = (String) drink.get("strCategoryDescription");
                    String id = (String) drink.get("idCategory");

                    cocktail.add(name);
                    cocktail.add(thumbnail);
                    cocktail.add(desc);
                    cocktail.add(id);

                    cocktails.add(cocktail);
                }
            } else {
                System.out.println("No se encontraron cócteles en la respuesta.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cocktails;
    }

    public static List<List<String>> responseToListMeals(ResponseEntity<String> response) {
        List<List<String>> cocktails = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
            JSONArray drinksArray = (JSONArray) jsonObject.get("meals");

            if (drinksArray != null) {
                for (Object drinkObj : drinksArray) {
                    List<String> cocktail = new ArrayList<>();
                    JSONObject drink = (JSONObject) drinkObj;

                    String name = (String) drink.get("strMeal");
                    String thumbnail = (String) drink.get("strMealThumb");
                    String desc = (String) drink.get("strCategory");
                    String id = (String) drink.get("idMeal");

                    cocktail.add(name);
                    cocktail.add(thumbnail);
                    cocktail.add(desc);
                    cocktail.add(id);

                    cocktails.add(cocktail);
                }
            } else {
                System.out.println("No se encontraron cócteles en la respuesta.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cocktails;
    }


    public static List<String> response2ToList(ResponseEntity<String> response) {
        List<String> info = new ArrayList<>();
        try {
            // Parsear el JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
            JSONArray drinksArray = (JSONArray)jsonObject.get("meals");
            JSONObject drink = (JSONObject)drinksArray.get(0);
            System.out.println("JSONOBJECT: " + drink);

            // Extraer los valores de las claves y añadirlos a la lista
            info.add((String) drink.get("strMeal"));
            info.add((String) drink.get("strMealThumb"));
            info.add(((String) drink.get("strCategory")));
            info.add((String) drink.get("strArea"));
            info.add((String) drink.get("strTags"));
            info.add((String) drink.get("strInstructions"));
            info.add((String) drink.get("strIngredient1"));
            info.add((String) drink.get("strMeasure1"));
            info.add((String) drink.get("strIngredient2"));
            info.add((String) drink.get("strMeasure2"));
            info.add((String) drink.get("strIngredient3"));
            info.add((String) drink.get("strMeasure3"));
            info.add((String) drink.get("strIngredient4"));
            info.add((String) drink.get("idDrink"));
            System.out.println("Info: " + info);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return info;
    }
}
