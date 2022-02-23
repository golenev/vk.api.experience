import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class AllTests extends BaseTest{
    LoginPage loginPage = new LoginPage();


    @Test
    public void firstTest(){
        browser = AqualityServices.getBrowser();
        browser.goTo("https://vk.com/");
        loginPage.setLoginField("+79054825726");
        loginPage.setPasswordField("Volgograd!23");
        loginPage.loginButtonClick();
    }
}
