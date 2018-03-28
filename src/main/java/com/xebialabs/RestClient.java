package com.xebialabs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    public void createEmployeeClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        String input = "{\"empId\" : \"0995\", \"empName\" : \"Sachin\", \"empDesignation\" : \"Trainee\", \"empSalary\" : 123}";

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(input.getBytes());
        outputStream.flush();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED){
            throw new RuntimeException("Failed! Http Error Code : " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;

        System.out.println("Response from Server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }

    public void getAllEmployeesClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees/all");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200){
            throw new RuntimeException("Failed! Http Error Code : " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;

        System.out.println("Response from server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }

    public void getEmployeeClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees/0995");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200){
            throw new RuntimeException("Failed! Http Error Code" + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;

        System.out.println("Response from server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }

    public void getFilteredEmployeeClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees/query?empDesignation=Trainee&empSalary=123");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200){
            throw new RuntimeException("Failed! Http Error Code" + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;

        System.out.println("Response from server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }

    public void putEmployeeClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");

        String input = "{\"empId\" : \"0998\", \"empName\" : \"Sachin\", \"empDesignation\" : \"Trainee\", \"empSalary\" : 123}";

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(input.getBytes());
        outputStream.flush();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED){
            throw new RuntimeException("Failed! Http Error Code : " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;

        System.out.println("Response from Server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }

    public void removeEmployeeClient() throws IOException {
        URL url = new URL("http://localhost:8080/employees/0995");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
            throw new RuntimeException("Failed! Http Error Code : " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;

        System.out.println("Response from Server ...\n");
        while ((output = br.readLine()) != null){
            System.out.println(output);
        }

        connection.disconnect();
    }
}
