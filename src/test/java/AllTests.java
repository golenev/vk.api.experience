import api.ApiMethods;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyPage;
import pageobjects.WallPostsForm;
import utils.RegExHelper;

public class AllTests extends BaseTest{
    LoginPage loginPage = new LoginPage();
    MyPage myPage = new MyPage();
    WallPostsForm wallPostsForm = new WallPostsForm();

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
        Assert.assertEquals( ApiMethods.postPin(post_id), 1);
        //[UI] Не обновляя страницу убедиться, что на стене появилась запись с нужным текстом от правильного пользователя
       Assert.assertEquals(RegExHelper.getIntAfterUnderlining(wallPostsForm.getValueFromAttribute(0, "href"), 1), post_id, "Ids of required posts don`t match");
//[API] Отредактировать запись через запрос к API - изменить текст и добавить (загрузить) любую картинку.
        int photo_Id = ApiMethods.savePhotoToWall();
        ApiMethods.wallEdit(post_id, "newRandon", photo_Id);

//[API] Используя запрос к API добавить комментарий к записи со случайным текстом
        String comment = "1q2w2w3e3";
        ApiMethods.wallCreateComment(post_id, comment);
//[UI] Не обновляя страницу убедиться, что к нужной записи добавился комментарий от правильного пользователя
        //вот тут ниже написать

  //[UI] Через UI оставить лайк к записи.
        wallPostsForm.getListOfLikes().get(0).clickAndWait();
        Assert.assertEquals(ApiMethods.likesIsLiked(post_id), 1,
                " the post did not get a like from the correct user");



//[API] Через запрос к API удалить созданную запись
        ApiMethods.wallDelete(post_id);
       // [UI] Не обновляя страницу убедиться, что запись удалена
Assert.assertTrue(wallPostsForm.isListOfPostsPresent(0), "sorry your message is still present");



    }
}
