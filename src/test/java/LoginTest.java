import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @DataProvider(name = "users")
    public Object[][] getUser() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"qwe123", ""},
                {"", "secret_sauce"},
                {"qwe123", "secret_sauce"},
                {"qwe123", "qwe123"}
        };
}

    @Test(testName = "Проверяем наличие инпутов и кнопки")
    public void checkFields() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login();
    }

    @Test
    public void checkResourceName() {
        new LoginPage()
                .checkResourceName("Swag Labs");
    }

    @Test(dataProvider = "users")
    public void checkLogin(String userName, String password) {
        LoginPage loginPage = new LoginPage();
        switch (userName) {
            case "standard_user":
            case "problem_user":
            case "performance_glitch_user":
                loginPage
                        .setLogin(userName)
                        .setPassword(password)
                        .login()
                        .checkLogin();
                break;
            default:
                loginPage
                        .setLogin(userName)
                        .setPassword(password)
                        .login();
                loginPage
                        .checkError();
        }
    }

    @Test
    public void checkCloseError() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .setLogin("")
                .setPassword("")
                .login();
        loginPage
                .checkError()
                .closeError();

    }
}
