import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest {

    MainClass Main = new MainClass();
    private int resultExpected;

    @Before
    public void setResultExpected(){
        resultExpected = 14;
    }

    @Test
    public void getLocalNumberTest() {
        Assert.assertTrue(
                "getLocalNumber returns " + Main.getLocalNumber() + " instead of " + resultExpected,
                Main.getLocalNumber() == resultExpected);
    }

}
