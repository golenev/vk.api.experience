import api.ApiMethods;
import api.IndexEnum;
import api.TypeAttributeEnum;
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
    private final LoginPage loginPage = new LoginPage();
    private final MyPage myPage = new MyPage();
    private final  WallPostsForm wallPostsForm = new WallPostsForm();

    @Test
    public void firstTest(){
        browser = AqualityServices.getBrowser();
        browser.goTo(getConfigValue("/url"));
        loginPage.setLoginField(getTestingValue("/login"));
        loginPage.setPasswordField(getTestingValue("/password"));
        loginPage.loginButtonClick();
        myPage.myPageBntClick();
        Logger.getInstance().info("You have logged in to the desired page via GUI");
        Logger.getInstance().info("Step 4 has been started");
        int post_id = ApiMethods.wallPost(RandomGenerator.faker.app().author());
        Logger.getInstance().info("Step 5 has been started");
        Assert.assertEquals(RegExHelper.getIntAfterUnderlining(wallPostsForm.getValueFromPostAttribute(IndexEnum.ZERO.getIndexValue(),
               TypeAttributeEnum.HREF.getType()), IndexEnum.FIRST.getIndexValue()), post_id, "Ids of required posts don`t match");
        Logger.getInstance().info("Step 6 has been started");
        int photo_Id = ApiMethods.savePhotoToWall();
        ApiMethods.wallEdit(post_id, RandomGenerator.faker.address().city(), photo_Id);
        Logger.getInstance().info("Step 7 has been started");
        Assert.assertEquals(RegExHelper.getIntAfterUnderlining(wallPostsForm.getValueFromPhotoAttribute(IndexEnum.ZERO.getIndexValue(),
               TypeAttributeEnum.HREF.getType()), IndexEnum.FIRST.getIndexValue()), photo_Id, "sorry, ids of required photos don`t match");
        Logger.getInstance().info("Step 8 has been started");
        String comment = RandomGenerator.faker.artist().name();
        ApiMethods.wallCreateComment(post_id, comment);
        Logger.getInstance().info("Step 9 has been started");
        wallPostsForm.getHiddenComments();
        Assert.assertEquals(comment, wallPostsForm.getTextFromRequiredComment(IndexEnum.ZERO.getIndexValue()), "sorry, your textBoxes don`t match");
        Logger.getInstance().info("Step 10 has been started");
        wallPostsForm.setRequiredLike(IndexEnum.ZERO.getIndexValue());
        Logger.getInstance().info("Step 11 has been started");
        Assert.assertEquals(ApiMethods.likesIsLiked(post_id), IndexEnum.FIRST.getIndexValue(),"sorry, like`s owner don`t match");
        Logger.getInstance().info("Step 12 has been started");
        ApiMethods.wallDelete(post_id);
        Logger.getInstance().info("Step 13 has been started");
        Assert.assertTrue(wallPostsForm.isListOfPostsPresent(IndexEnum.ZERO.getIndexValue()), "sorry your message is still present");

    }
}
