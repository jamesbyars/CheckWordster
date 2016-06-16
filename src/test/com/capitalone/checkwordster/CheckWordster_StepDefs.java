package test.com.capitalone.checkwordster;

import com.capitalone.checkwordster.CheckWordster;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckWordster_StepDefs {
    private String whichServer;
    private CheckWordster checkWordster;

    @Given("^I am doing \"([^\"]*)\" server testing of the CheckWordster microservice core logic$")
    public void whereWeWillTest(String whichServer) throws Throwable {
        this.whichServer = whichServer;
    }

    @When("^I convert \"([^\"]*)\" into words$")
    public void i_convert_into_words(String stringToConvert) throws Throwable {
        checkWordster = new CheckWordster(stringToConvert);
    }

    @Then("^it should be \"([^\"]*)\"$")
    public void it_should_be(String numberInWords) throws Throwable {
        assertThat(numberInWords, is(checkWordster.getWords()));
    }

    @Then("^an exception \"([^\"]*)\" should be thrown$")
    public void an_exception_should_be_thrown(String arg1) throws Throwable {
    }


}
