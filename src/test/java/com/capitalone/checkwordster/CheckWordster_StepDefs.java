package test.java.com.capitalone.checkwordster;

import test.java.com.capitalone.checkwordster.client.CheckWordsterClient;
import com.capitalone.checkwordster.core.CheckWordster;
import com.capitalone.checkwordster.core.CheckWordsterException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckWordster_StepDefs {
    private static String whichServerStarted = "";
    private static CheckWordsterClient checkWordsterClientStarted;

    private String stringToConvert;

    @Given("^I start the \"([^\"]*)\" server$")
    public void i_start_the_local_server(String whichServer) throws Throwable {
        if (!whichServerStarted.equals(whichServer)) {
            if (!whichServerStarted.equals("")) checkWordsterClientStarted.stopServer();
            checkWordsterClientStarted = new CheckWordsterClient(whichServer);
            checkWordsterClientStarted.startServer();
            whichServerStarted = whichServer;
        }
    }

    @Then("^I stop the server$")
    public void i_stop_the_server() throws Throwable {
        if (!whichServerStarted.equals("")) checkWordsterClientStarted.stopServer();
        whichServerStarted = "";
    }

    @When("^I convert \"([^\"]*)\" into words$")
    public void i_convert_into_words(String stringToConvert) throws Throwable {
        this.stringToConvert = stringToConvert;
    }

    @Then("^it should be \"([^\"]*)\"$")
    public void it_should_be(String numberInWords) throws Throwable {

        if (whichServerStarted.equals("no")) {
            CheckWordster checkWordster = new CheckWordster(stringToConvert);
            assertThat(checkWordster.getWords(), is(numberInWords));
        }
        else {
            assertThat(checkWordsterClientStarted.getWords(stringToConvert), is(numberInWords));
        }
    }

    @When("^I convert \"([^\"]*)\" into words, an exception \"([^\"]*)\" should be thrown$")
    public void i_convert_into_words_an_exception_should_be_thrown(String stringToConvert, String exceptedExceptionMessage) {
        try {
            CheckWordster checkWordster = new CheckWordster(stringToConvert);
            assertThat("Supposed to throw a \"" + exceptedExceptionMessage + "\" exception", true, is(false));
        } catch (CheckWordsterException e) {
            assertThat(e.getMessage(), is(exceptedExceptionMessage));
        }
    }


}
