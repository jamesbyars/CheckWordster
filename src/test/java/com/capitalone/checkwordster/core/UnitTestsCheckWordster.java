package test.java.com.capitalone.checkwordster.core;

import com.capitalone.checkwordster.core.CheckWordster;
import com.capitalone.checkwordster.core.CheckWordsterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UnitTestsCheckWordster {

    @Rule
    public ExpectedException checkWordsterException = ExpectedException.none();

    @Test
    public void testNullString() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Null number");
        new CheckWordster("");
    }

    @Test
    public void testInvalidCharacters() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid characters");
        new CheckWordster("Person2Person");
    }

    @Test
    public void testInvalidSignedNumber1() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid signed number");
        new CheckWordster("-200.+00");
    }

    @Test
    public void testInvalidSignedNumber2() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid signed number");
        new CheckWordster("++200.00");
    }

    @Test
    public void testSignedNumber() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Signed number");
        new CheckWordster("-200.00");
    }

    @Test
    public void testInvalidFormat1() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid format");
        new CheckWordster("200,000,0000");
    }

    @Test
    public void testInvalidFormat2() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid format");
        new CheckWordster("200.000");
    }

    @Test
    public void testInvalidFormat3() throws CheckWordsterException {
        checkWordsterException.expect(CheckWordsterException.class);
        checkWordsterException.expectMessage("Invalid format");
        new CheckWordster("200.00.00");
    }

    @Test
    public void testDigitWordExtraction1() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("20");
        assertThat(checkWordster.wordsForUpTo1000("20"), is("twenty"));
    }

    @Test
    public void testWordForDigits1() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("20");
        assertThat(checkWordster.getWords(), is("Twenty"));
    }

    @Test
    public void testDigitWordExtraction2() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("25");
        assertThat(checkWordster.wordsForUpTo1000("25"), is("twenty five"));
    }

    @Test
    public void testWordForDigits2() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("25");
        assertThat(checkWordster.getWords(), is("Twenty five"));
    }

    @Test
    public void testDigitWordExtraction3a() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32768");
        assertThat(checkWordster.wordsForUpTo1000("32"), is("thirty two"));
    }

    @Test
    public void testDigitWordExtraction3b() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32768");
        assertThat(checkWordster.wordsForUpTo1000("768"), is("seven hundred sixty eight"));
    }

    @Test
    public void testWordForDigits3() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32768");
        assertThat(checkWordster.getWords(), is("Thirty two thousand seven hundred sixty eight"));
    }

    @Test
    public void testDigitWordExtraction4a() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32000");
        assertThat(checkWordster.wordsForUpTo1000("32"), is("thirty two"));
    }

    @Test
    public void testDigitWordExtraction4b() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32000");
        assertThat(checkWordster.wordsForUpTo1000("000"), is(""));
    }

    @Test
    public void testWordForDigits4() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32000");
        assertThat(checkWordster.getWords(), is("Thirty two thousand"));
    }
    @Test
    public void testDigitWordExtraction5a() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32001");
        assertThat(checkWordster.wordsForUpTo1000("32"), is("thirty two"));
    }

    @Test
    public void testDigitWordExtraction5b() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32001");
        assertThat(checkWordster.wordsForUpTo1000("001"), is("one"));
    }

    @Test
    public void testWordForDigits5() throws CheckWordsterException {
        CheckWordster checkWordster = new CheckWordster("32001");
        assertThat(checkWordster.getWords(), is("Thirty two thousand one"));
    }
}
