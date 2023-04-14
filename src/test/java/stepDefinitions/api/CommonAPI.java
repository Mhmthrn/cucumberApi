package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import pojos.Pojo_RegisterCustomer;
import utilities.Authentication;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class CommonAPI {
    public  static String fullPath;
    Pojo_RegisterCustomer reqBod;
    JSONObject reqBodyJO;



    @Given("Api kullanicisi {string} path parametrelerini set eder")
    public void api_kullanicisi_path_parametrelerini_set_eder(String rawPath) {

        // HooksAPI.spec.pathParams("pp1","api","pp2","register");

        //https://trendlifebuy.com/api/register

       String [] paths= rawPath.split("/");

       StringBuilder tempPath=new StringBuilder("{");

        for (int i = 0; i <paths.length ; i++) {

            String key = "pp"+i;
            String value= paths[i].trim();

            HooksAPI.spec.pathParam(key,value);

            tempPath.append(key+"}/{");

        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath=tempPath.toString();

    }

    @Then("Register Costumer icin gerekli Request Body {string},{string},{string},{string},{string},{string},{string},{string},{string} hazirla")
    public void register_costumer_icin_gerekli_request_body_hazirlar(String first_name, String last_name, String username, String email, String password, String password_confirmation, String user_type, String phone, String referral_code) {

       reqBod=new Pojo_RegisterCustomer(first_name,last_name,username,email,password,password_confirmation,user_type,phone,referral_code);


    }


    @Then("Register Costumer icin Post request gonder")
    public void registerCostumerIcinPostRequestGonder() {

        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON).header("Accept","application/json")
                            .when().body(reqBod).post(fullPath);

        response.prettyPrint();


    }


    @Then("Login icin {string} ve {string} hazirla")
    public void loginIcinVeHazirla(String email, String password) {

         reqBodyJO=new JSONObject();

        reqBodyJO.put("email", ConfigReader.getProperty(email));
        reqBodyJO.put("password",ConfigReader.getProperty(password));
    }

    @Then("Login icin Post request gonderilir")
    public void loginIcinPostRequestGonderilir() {

        Response response=given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJO.toString()).post(fullPath);
        response.prettyPrint();
    }

    @Then("AllCountries icin Get request gonderilir")
    public void allcountriesIcinGetRequestGonderilir() {

        Response response=given().spec(HooksAPI.spec)
                .headers("Authorization","Bearer "+ HooksAPI.token)
                .contentType(ContentType.JSON).headers("Accept","application/json")
                .when().get(fullPath);

        response.prettyPrint();

    }
}
