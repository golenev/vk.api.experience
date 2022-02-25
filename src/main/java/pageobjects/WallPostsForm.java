package pageobjects;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class WallPostsForm extends Form {

    private ITextBox hiddenComment = getElementFactory().getTextBox(By.cssSelector(".js-replies_next_label"), "show all hidden comments");

    public WallPostsForm() {
        super(By.cssSelector("#page_wall_posts"), "posts on the wall");
    }

    public List<IButton> getListOfLikes() {
        return this.getElementFactory().findElements(By.xpath("//div[@class='PostButtonReactions__icon ']"),
                ElementType.BUTTON, ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public void setRequiredLike(int indexLike){
        getListOfLikes().get(indexLike).clickAndWait();
    }

    public List<ITextBox> getListOfPostDate() {
        return this.getElementFactory().findElements(By.xpath("//a[@class='post_link']"), ElementType.TEXTBOX,
                ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public List<IButton> getListOfAllPhotosFromWall(){
        return this.getElementFactory().findElements(By.xpath("//a[contains(@class,'page_post_thumb_last_column')]"), ElementType.BUTTON,
                ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public List <ITextBox> getListOfAllCommentsFromWall(){
        return this.getElementFactory().findElements(By.cssSelector("div[class='wall_reply_text']"), ElementType.TEXTBOX,
                ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public String getValueFromPostAttribute(int indexAttribute, String attribute) {
        return getListOfPostDate().get(indexAttribute).getAttribute(attribute);
    }

    public boolean isListOfPostsPresent(int indexPost) {
        return getListOfPostDate().get(indexPost).state().isDisplayed();
    }

    public String getValueFromPhotoAttribute (int indexPhoto, String attribute){
        return getListOfAllPhotosFromWall().get(indexPhoto).getAttribute(attribute);
    }

    public String getTextFromRequiredComment(int indexComment){
        return getListOfAllCommentsFromWall().get(indexComment).getText();
    }

    public void getHiddenComments(){
        hiddenComment.click();
    }


}
