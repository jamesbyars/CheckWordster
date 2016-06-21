package test.com.capitalone.checkwordster;

import com.capitalone.checkwordster.CheckWordster;
import com.capitalone.checkwordster.CheckWordsterException;
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
        assertThat(checkWordster.getWords(), is(numberInWords));
    }

    @When("^I convert \"([^\"]*)\" into words, an exception \"([^\"]*)\" should be thrown$")
    public void i_convert_into_words_an_exception_should_be_thrown(String stringToConvert, String exceptedExceptionMessage) {
        try {
            checkWordster = new CheckWordster(stringToConvert);
            assertThat("Supposed to throw a \"" + exceptedExceptionMessage + "\" exception", true, is(false));
        } catch (CheckWordsterException e) {
            assertThat(e.getMessage(), is(exceptedExceptionMessage));
        }
    }


}
