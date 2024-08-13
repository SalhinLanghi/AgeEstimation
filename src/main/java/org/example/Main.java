package org.example;

import com.google.gson.Gson;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        Scanner myObj = new Scanner(System.in);
        Gson gson = new Gson();
        Name name = new Name();


        System.out.println("Estimate the age of your name");
        System.out.print("Enter name: ");

        String randomName = myObj.nextLine();
        String url = "https://api.agify.io?name=salhin";
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url);
        urlBuilder.replaceQueryParam("name", randomName);
        String finalUrl = urlBuilder.build().toUriString();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(finalUrl))
                        .GET()
                        .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        name = gson.fromJson(getResponse.body(), Name.class);

        System.out.println("Name is: " +  name.getName() + " age " + name.getAge() + " instances in database " + name.getCount()) ;
    }
}