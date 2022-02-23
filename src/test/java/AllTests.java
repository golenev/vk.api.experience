import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;

public class AllTests extends BaseTest{
    @Test
    public void firstTest(){
        browser = AqualityServices.getBrowser();
        browser.goTo("https://vk.com/");
    }
}
