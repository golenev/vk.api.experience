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


    public WallPostsForm() {
        super(By.cssSelector("#page_wall_posts"), "posts on the wall");
    }

    public List<IButton> getListOfLikes() {
        return this.getElementFactory().findElements(By.xpath("//div[@class='PostButtonReactions__icon ']"),
                ElementType.BUTTON, ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public List<ITextBox> getListOfPostDate() {
        return this.getElementFactory().findElements(By.xpath("//a[@class='post_link']"), ElementType.TEXTBOX,
                ElementsCount.MORE_THEN_ZERO, ElementState.EXISTS_IN_ANY_STATE);
    }

    public String getValueFromAttribute(int index, String attribute) {
        return getListOfPostDate().get(index).getAttribute(attribute);
    }

    public boolean isListOfPostsPresent(int index) {
        return getListOfPostDate().get(index).state().isDisplayed();
    }


}
