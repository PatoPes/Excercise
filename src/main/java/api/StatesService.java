package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;

public class StatesService {
    String url;
    String jsonAsString;
    Response response;

    public StatesService(String url){
        this.url = url;
        response =
                when().
                        get(url).
                        then().
                        contentType(ContentType.JSON).  // check that the content type return from the API is JSON
                        extract().response();// extract the response
        jsonAsString = response.asString();
    }

    public List<String> getStatesWithMinAge(int minAge){
        String closure = "states.findAll{it.minAge == " + minAge + "}.label";
        return from(jsonAsString).getList(closure);
    }

    public List<String> getStatesWithMinLoanAmount(int loanAmount){
        String closure = "states.findAll{it.minLoanAmount == " + loanAmount + "}.label";
        return from(jsonAsString).getList(closure);
    }

    public List<String> getStatesNames(){
        return  when().get(url).
                then().statusCode(200).extract().body().jsonPath().getList("states.label", String.class);
    }
}
