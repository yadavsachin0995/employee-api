package com.xebialabs;

import java.io.IOException;

public class RestClientApp {

    public static void main(String[] args) throws IOException {
        RestClient restClient = new RestClient();
        restClient.createEmployeeClient();
        //restClient.getAllEmployeesClient();
        restClient.getEmployeeClient();
        //restClient.removeEmployeeClient();
    }

}
