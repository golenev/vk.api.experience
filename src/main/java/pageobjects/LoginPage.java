package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {

    private ITextBox loginField = getElementFactory().getTextBox(By.cssSelector("#index_email"), "field for login");
    private ITextBox passwordField = getElementFactory().getTextBox(By.cssSelector("#index_pass"), "field for password");
    private IButton loginBtn = getElementFactory().getButton(By.cssSelector("#index_login_button"), "login button");

    public LoginPage() {
        super(By.cssSelector("#index_login"), "authorization window");
    }
    public void setLoginField (String login){
        loginField.clearAndType(login);
    }
    public void setPasswordField (String password) {
        passwordField.clearAndType(password);
    }
    public void loginButtonClick(){
        loginBtn.click();
    }

}
