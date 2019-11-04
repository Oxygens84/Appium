import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest {

    MainClass Main = new MainClass();
    private int resultLocalExpected = 14;
    private int resultClassLimit = 45;
    private String resultStringExpected = "hello".toLowerCase();

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue(
                "getLocalNumber returns " + Main.getLocalNumber() + ", expected " + resultLocalExpected,
                Main.getLocalNumber() == resultLocalExpected);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue(
                "getClassNumber returns " + Main.getClassNumber() + ", expected value greater than " + resultClassLimit,
                Main.getClassNumber() > resultClassLimit);
    }

    @Test
    public void testGetClassString(){
        Assert.assertTrue(
                "containsIgnoreCase returns " + Main.getClassString() + " without " + resultStringExpected,
                Helper.containsIgnoreCase(Main.getClassString(),resultStringExpected));
    }


}