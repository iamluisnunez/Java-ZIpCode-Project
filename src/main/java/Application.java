import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.Address;
import com.kenzie.app.data.zipcode.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;

import java.util.Scanner;

public class Application {
    public static void main_backup(String[] args) {

        try {
            String testUrl = "https://api.zippopotam.us/us/wi/milwaukee";
            String response = HttpUtil.makeGETRequest(testUrl);
            //System.out.println(response);

            //JSON object Mapping
            //1. instantiate ObjectMapper
            //2. create DTO (in order to map data into)
            //3. readValue()

            ObjectMapper objectMapper = new ObjectMapper();
            ZipCodeDTO zipObj;
            zipObj = objectMapper.readValue(response, ZipCodeDTO.class);

            //print out state, city(place name), zipcode(post code)
            //make sure i have getters and setters to get data in DTO
//            System.out.println("state: " + zipObj.getState());
//            System.out.println("city: " + zipObj.getPlaces().get(0).getPlace_name());
//            System.out.println("city: " + zipObj.getPlaces().get(0).getPostCode());

            //theres only one item, set zipcode to that
            if(zipObj.getPlaces().size() == 1){
                System.out.println("Only one zipCode: " + zipObj.getPlaces().get(0).getPostCode());
            }
            //list is greate than 1
            else if (zipObj.getPlaces().size() > 1){
                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                    System.out.println("Zone " + i);
                    System.out.println("state: " + zipObj.getState());
                    System.out.println("city: " + zipObj.getPlaces().get(i).getPlace_name());
                    System.out.println("Zip Code: " + zipObj.getPlaces().get(i).getPostCode());
                    System.out.println();
                }
            }
                {

            }

            String testStr = "123 Main St.";
            AddressFormatUtil.initCodeTable();
            System.out.println(AddressFormatUtil.replaceAbbreviation(testStr));

        }
        catch (Exception e){
            System.out.println("Unexpected exception: " + e);
        }

    }

    public static void main(String[] args) {
        //declare variables
        try{


        String baseURL = "https://api.zippopotam.us/us/";
        Scanner sc = new Scanner(System.in);
        String recipientName;
        String streetAddress;
        String city;
        String state;
        String zipCode;

        //read in user input - scanner
        System.out.println("Enter Reciepient Name: ");
        recipientName = sc.nextLine();
        System.out.println("Enter Street Address");
        streetAddress = sc.nextLine();
        System.out.println("Enter in the city");
        city = sc.nextLine();
        System.out.println("Enter in the state");
        state = sc.nextLine();

        //clean city - Los Angeles
        String tempCity = city.replace(" ", "%20");

        //format URL with user city and state
        String finalURL = baseURL + state + "/" + tempCity;
        System.out.println(finalURL);


        //call GET
        String httpResponse = HttpUtil.makeGETRequest(finalURL);

        //if return string contains 404, dont objectMap
        if(httpResponse.contains("GET request failed")){
            System.out.println("No zip code found");
            zipCode = "";
        }
        else {
            //objectMapper to retrieve zip code
            ObjectMapper objectMapper = new ObjectMapper();
            ZipCodeDTO zipCodeDTO;
            zipCodeDTO = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

            if(zipCodeDTO.getPlaces().size() == 1){
                zipCode = zipCodeDTO.getPlaces().get(0).getPostCode();
            }
            else {
                for (int i = 0; i < zipCodeDTO.getPlaces().size(); i++){
                    System.out.println(i + " " + zipCodeDTO.getPlaces().get(i).getPostCode());
                }
            }
            System.out.println("Choose a zip code");
            int choice = sc.nextInt();
            sc.nextLine();

            //set zipCode based on selection
            zipCode = zipCodeDTO.getPlaces().get(choice).getPostCode();

            //print final address
            System.out.println(recipientName);
            System.out.println(streetAddress);
            System.out.println(city + "," + state + " " + zipCode);
        }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
