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
        System.out.println("вход на стену выполнен");

        int post_id = ApiMethods.wallPost("testtest");
        System.out.println("рандомное сообщение оставлено");

        int photo_Id = ApiMethods.savePhotoToWall();
        ApiMethods.wallEdit(post_id, "newRandon", photo_Id);

        String comment = "1q2w2w3e3";
        ApiMethods.wallCreateComment(post_id, comment);

       // ApiMethods.wallDelete(post_id);



    }
}
