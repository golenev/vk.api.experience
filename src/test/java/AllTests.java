import api.ApiMethods;
import aquality.selenium.browser.AqualityServices;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyPage;
import static api.ApiMethods.*;

public class AllTests extends BaseTest{
    LoginPage loginPage = new LoginPage();
    MyPage myPage = new MyPage();



    @Test
    public void firstTest(){
        browser = AqualityServices.getBrowser();
        browser.goTo("https://vk.com/");
        loginPage.setLoginField("+79054825726");
        loginPage.setPasswordField("Volgograd!23");
        loginPage.loginButtonClick();
        myPage.myPageBntClick();

        int post_id = ApiMethods.wallPost("testtest");


    }
}
