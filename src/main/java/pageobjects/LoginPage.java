package pageobjects;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {

    protected LoginPage(By locator, String name) {
        super(locator, name);
    }
}
