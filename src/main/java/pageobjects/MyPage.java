package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {
    private IButton myPageBtn = getElementFactory().getButton(By.id("l_pr"), "my page button");

    public MyPage() {
        super(By.cssSelector(".page_avatar_img"), "avatar holder");
    }
    public void myPageBntClick(){
        myPageBtn.click();
    }
}
