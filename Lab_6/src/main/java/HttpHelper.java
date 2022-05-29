//package com.http;


//import com.dataclasses.Car;
//import com.dataclasses.Driver;
//import com.dataclasses.Fine;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpHelper {
    private static String sessionID = null;
    private static String ip = "25.32.34.76:8080";
    public static RequestResponse sendGET(String urlString, String parameters) throws IOException {
        URL url = new URL(urlString + "?" + parameters);
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (sessionID != null)
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionID);

        //connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedInputStream responseStream = null;
        responseStream = new BufferedInputStream(connection.getInputStream());
        RequestResponse response = new RequestResponse(connection.getResponseCode(), responseStream);

        BufferedInputStream in = (BufferedInputStream) responseStream;

        //connection.disconnect();

        return response;
    }

    public static RequestResponse sendDEL(String urlString, String parameters) throws IOException {
        URL url = new URL(urlString + "?" + parameters);
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (sessionID != null)
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionID);

        //connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        connection.setRequestMethod("DELETE");
        connection.connect();

        BufferedInputStream responseStream = null;
        responseStream = new BufferedInputStream(connection.getInputStream());
        RequestResponse response = new RequestResponse(connection.getResponseCode(), responseStream);

        BufferedInputStream in = (BufferedInputStream) responseStream;

        //connection.disconnect();

        return response;
    }

    private static RequestResponse sendPOST(String urlString, String parameters) throws IOException {
        URL url = new URL(urlString);
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (sessionID != null)
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionID);

        //connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.connect();

        final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(parameters);
        out.flush();
        out.close();

        BufferedInputStream responseStream = new BufferedInputStream(connection.getInputStream());

        RequestResponse response = new RequestResponse(connection.getResponseCode(), responseStream);

        //connection.disconnect();

        return response;
    }

    private static RequestResponse sendPUT(String urlString, String parameters) throws IOException {
        URL url = new URL(urlString);
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (sessionID != null)
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionID);

        //connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("UPDATE");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.connect();

        final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(parameters);
        out.flush();
        out.close();

        BufferedInputStream responseStream = new BufferedInputStream(connection.getInputStream());

        RequestResponse response = new RequestResponse(connection.getResponseCode(), responseStream);

        //connection.disconnect();

        return response;
    }

    public static ArrayList<Car> getCarList(String driverLicense) {
        try {
            // Отправка запроса
            String url = "http://"+ip+"/officeCars";
            String parameters = "dl=" + driverLicense;
            RequestResponse response = sendGET(url, parameters);
            BufferedInputStream in = (BufferedInputStream) response.responseStream;

            // Десериализация полученного JSON ответа
            JsonArray accountsJSON = null;
            accountsJSON = (JsonArray) Jsoner.deserialize(new InputStreamReader(in));
            in.close();

            // Формирование списка авто из JSON
            ArrayList<Car> carsList = new ArrayList<>();

            accountsJSON.forEach(entry -> {
                JsonObject JSONCar = (JsonObject) entry;
                String model = (String) JSONCar.get("model");
                String plate = (String) JSONCar.get("plate");
                String color = (String) JSONCar.get("color");
                String insurance = (String) JSONCar.get("insurance");

                Car car = new Car(
                        model,
                        plate,
                        color,
                        insurance);

                carsList.add(car);
            });

            return carsList;
        }catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Car>();
    }

    public static void setCar(String license,String model,String car_number, String color, String insurance){
        String url = "http://"+ip+"/officeCars";
        String parameters =
                "license=" + license +
                        "&model=" + model +
                        "&plate=" + car_number +
                        "&color=" + color +
                        "&insurance=" + insurance;

        try {
            sendPOST(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delCar(String plate){
        String url = "http://"+ip+"/officeCars";
        String parameters =
                "plate=" + plate;

        try {
            sendDEL(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Driver> getDriverList(){
        try {
            // Отправка запроса
            String url = "http://"+ip+"/officeDrivers";
            String parameters = "";
            RequestResponse response = sendGET(url, parameters);
            BufferedInputStream in = (BufferedInputStream) response.responseStream;
            // Десериализация полученного JSON ответа
            JsonArray accountsJSON = null;
            accountsJSON = (JsonArray) Jsoner.deserialize(new InputStreamReader(in));
            in.close();
            ArrayList<Driver> driverList = new ArrayList<>();
            accountsJSON.forEach(entry -> {
                JsonObject JSONCar = (JsonObject) entry;
                String license = (String) JSONCar.get("license");
                String firstname = (String) JSONCar.get("firstName");
                String lastname = (String) JSONCar.get("lastName");

                Driver driver = new Driver(
                        license,
                        firstname,
                        lastname);

                driverList.add(driver);
            });

            return driverList;
        } catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Driver>();
    }

    public static String getSum(){
        try{
            String url = "http://"+ip+"/officeFinesSum";
            String parameters = "";
            RequestResponse response = sendGET(url, parameters);
            BufferedInputStream in = (BufferedInputStream) response.responseStream;
            // Десериализация полученного JSON ответа
            JsonObject JSON_sum = (JsonObject) Jsoner.deserialize(new InputStreamReader(in));
            in.close();
            String sum = JSON_sum.get("sum").toString();
            return sum;
        }catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return new String();
    }

    public static ArrayList<Driver> getDriverSortList(String num){
        try {
            // Отправка запроса
            String url = "http://"+ip+"/officeBadDrivers";
            String parameters = "finesCount=" + num;
            RequestResponse response = sendGET(url, parameters);
            BufferedInputStream in = (BufferedInputStream) response.responseStream;
            // Десериализация полученного JSON ответа
            JsonArray accountsJSON = null;
            accountsJSON = (JsonArray) Jsoner.deserialize(new InputStreamReader(in));
            in.close();
            ArrayList<Driver> driverList = new ArrayList<>();
            accountsJSON.forEach(entry -> {
                JsonObject JSONCar = (JsonObject) entry;
                String license = (String) JSONCar.get("license");
                String firstname = (String) JSONCar.get("firstName");
                String lastname = (String) JSONCar.get("lastName");

                Driver driver = new Driver(
                        license,
                        firstname,
                        lastname);

                driverList.add(driver);
            });

            return driverList;
        } catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Driver>();
    }

    public static void setDriver(Driver d){
        String url = "http://"+ip+"/officeDrivers";
        String parameters =
                "license=" + d.getLicense() +
                        "&firstName=" + d.getFirst_name() +
                        "&lastName=" + d.getLast_name();

        try {
            sendPOST(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delDriver(String license){
        String url = "http://"+ip+"/officeDrivers";
        String parameters = "license=" + license;
        try {
            sendDEL(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeFines(String fineId,String license,String discription,String size){
        String url = "http://"+ip+"/officeFines";
        String parameters =
                "fieId=" + fineId +
                        "&license=" + license +
                        "&discription=" + discription +
                        "&size=" + size;
        try {
            sendPUT(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delFines(String fineid){
        String url = "http://"+ip+"/officeFines";
        String parameters = "fineId=" + fineid;
        try {
            sendDEL(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFines(String license,String discription,String size){
        String url = "http://"+ip+"/officeFines";
        String parameters =
                "license=" + license +
                        "&description=" + discription +
                        "&size=" + size;

        try {
            sendPOST(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Fine> getFines(String license){
        try{
            // Отправка запроса
            String url = "http://"+ip+"/officeFines";
            String parameters = "license="+license;
            RequestResponse response = sendGET(url, parameters);
            BufferedInputStream in = (BufferedInputStream) response.responseStream;
            // Десериализация полученного JSON ответа
            JsonArray accountsJSON = null;
            accountsJSON = (JsonArray) Jsoner.deserialize(new InputStreamReader(in));
            in.close();
            ArrayList<Fine> fines = new ArrayList<>();
            accountsJSON.forEach(entry -> {
                JsonObject JSONInsurance = (JsonObject) entry;
                String num = (String) JSONInsurance.get("fineId");
                String data = (String) JSONInsurance.get("date");
                String opisanie = (String) JSONInsurance.get("description");
                String razmer = (String) JSONInsurance.get("size").toString();

                Fine fine = new Fine(
                        num,
                        data,
                        opisanie,
                        razmer);

                fines.add(fine);
            });
            return fines;
        } catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Fine>();
    }

}
