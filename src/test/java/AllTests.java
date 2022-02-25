import api.ApiMethods;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyPage;
import pageobjects.WallPostsForm;
import utils.RandomGenerator;
import utils.RegExHelper;
import static utils.TestingConfigurations.*;

public class AllTests extends BaseTest{
    LoginPage loginPage = new LoginPage();
    MyPage myPage = new MyPage();
    WallPostsForm wallPostsForm = new WallPostsForm();

    @Test
    public void firstTest(){
        browser = AqualityServices.getBrowser();
        browser.goTo(getConfigValue("/url"));
        loginPage.setLoginField(getTestingValue("/login"));
        loginPage.setPasswordField(getTestingValue("/password"));
        loginPage.loginButtonClick();
        myPage.myPageBntClick();
//4[API] С помощью запроса к API создать запись со случайно сгенерированным текстом на стене и получить id записи из ответа
        Logger.getInstance().info("Step 4 has been started");
        int post_id = ApiMethods.wallPost(RandomGenerator.faker.app().author());
//5[UI] Не обновляя страницу убедиться, что на стене появилась запись с нужным текстом от правильного пользователя
        Logger.getInstance().info("Step 5 has been started");
       Assert.assertEquals(RegExHelper.getIntAfterUnderlining(wallPostsForm.getValueFromPostAttribute(0, "href"), 1), post_id, "Ids of required posts don`t match");
//6[API] Отредактировать запись через запрос к API - изменить текст и добавить (загрузить) любую картинку.
        Logger.getInstance().info("Step 6 has been started");
        int photo_Id = ApiMethods.savePhotoToWall();
        ApiMethods.wallEdit(post_id, RandomGenerator.faker.address().city(), photo_Id);

//7[UI] Не обновляя страницу убедиться, что изменился текст сообщения и добавилась загруженная картинка(убедиться, что картинки одинаковые)
        Logger.getInstance().info("Step 7 has been started");
       Assert.assertEquals(RegExHelper.getIntAfterUnderlining(wallPostsForm.getValueFromPhotoAttribute(0, "href"), 1), photo_Id, "sorry, ids of required photos don`t match");

//8[API] Используя запрос к API добавить комментарий к записи со случайным текстом
        Logger.getInstance().info("Step 8 has been started");
        String comment = RandomGenerator.faker.artist().name();
        ApiMethods.wallCreateComment(post_id, comment);

//9[UI] Не обновляя страницу убедиться, что к нужной записи добавился комментарий от правильного пользователя
        Logger.getInstance().info("Step 9 has been started");
        wallPostsForm.getHiddenComments();
        Assert.assertEquals(comment, wallPostsForm.getTextFromRequiredComment(0), "sorry, your textBoxes don`t match");

//10[UI] Через UI оставить лайк к записи.
        Logger.getInstance().info("Step 10 has been started");
        wallPostsForm.setRequiredLike(0);

//11[API] Через запрос к API убедиться, что у записи появился лайк от правильного пользователя
        Logger.getInstance().info("Step 11 has been started");
        Assert.assertEquals(ApiMethods.likesIsLiked(post_id), 1,"sorry, like`s owner don`t match");

//12[API] Через запрос к API удалить созданную запись
        Logger.getInstance().info("Step 12 has been started");
        ApiMethods.wallDelete(post_id);
//13[UI] Не обновляя страницу убедиться, что запись удалена
        Logger.getInstance().info("Step 13 has been started");
        Assert.assertTrue(wallPostsForm.isListOfPostsPresent(0), "sorry your message is still present");



    }
}
