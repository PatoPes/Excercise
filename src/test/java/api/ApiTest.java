package api;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ApiTest {

    private static String endpoint ="https://credapi.credify.tech/api/loanapp/v1/states";
    private static StatesService state;

    @BeforeClass
    public static void setup(){
       state = new StatesService(endpoint);
       System.out.println("Starting to execute the API tests\n");
    }

    @DataProvider(name="ListOfStates")
    public Object[][] createTestStates(){
        return new Object[][]{
                {"Alabama"},{"Alaska"},{"Arizona"}, {"Arkansas"},{"California"},
                {"Connecticut"},{"Delaware"},{"District of Columbia"},{"Florida"},
                {"Georgia"},{"Hawaii"},{"Idaho"},{"Illinois"},{"Indiana"},{"Kansas"},
                {"Kentucky"},{"Louisiana"},{"Maine"},{"Maryland"},{"Massachusetts"},
                {"Michigan"},{"Minnesota"},{"Mississippi"},{"Missouri"},{"Montana"},
                {"Nebraska"},{"Nevada"},{"New Hampshire"},{"New Jersey"},{"New Mexico"},
                {"New York"},{"North Carolina"},{"North Dakota"},{"Ohio"},{"Oklahoma"},
                {"Oregon"},{"Pennsylvania"},{"Rhode Island"},{"South Carolina"},{"South Dakota"},
                {"Tennessee"},{"Texas"},{"Utah"},{"Vermont"},{"Virginia"},{"Washington"},
                {"Wisconsin"},{"Wyoming"}
        };
    }

    //Test that is only checking the states numbers using Rest Assured
    @Test(priority = 0, groups = {"api-group"})
    public void statesNumbersShouldBe48Test(){
        when().get(endpoint).
        then().
                statusCode(200).
        and().
                contentType(ContentType.JSON).
        and().
                assertThat().body("states",hasSize(48));
    }

    @Test(priority = 1, dataProvider = "ListOfStates",groups = {"api-group"})
    public void testStatesNumberAndNames(String stName){
        List<String> statesNames = state.getStatesNames();
        //Assert the state number to be 48
        Assert.assertEquals(statesNames.size(),48);
        //Assert the state names are valid
        Assert.assertTrue(statesNames.contains(stName),"The state name " + stName + " is not valid");
    }

    @Test(priority = 2, groups = {"api-group"})
    public void testMinAge19(){
        List<String> stateWith19Only = state.getStatesWithMinAge(19);
        //Asserting only one element has MinAge = 19
        Assert.assertEquals(stateWith19Only.size(),1);
        //Printing state name
        stateWith19Only.forEach(System.out::println);
    }

    @Test(priority = 3, groups = {"api-group"})
    public void testGeorgiaMinLoanAmount(){
        List<String> minAmount = state.getStatesWithMinLoanAmount(3005);
        //Asserting only one element has MinLoanAmount 3005
        Assert.assertEquals(minAmount.size(),1);
        //Asserting state name
        Assert.assertEquals(minAmount.get(0), "Georgia");
    }
}