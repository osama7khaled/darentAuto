package tests.traveller.login;

import base.Finder;
import base.Go;
import base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test(priority = 1)
    public void verifyAccountButtonIsDisplayed() {
        Assert.assertTrue(
                Finder.visible(LoginPom.ACCOUNT_BUTTON, 10).isDisplayed(),
                "⚠ زر الحساب غير ظاهر"
        );
    }

    @Test(priority = 2)
    public void verifyAccountButtonIsClickable() {
        Go.click(LoginPom.ACCOUNT_BUTTON, 10);
        Assert.assertTrue(
                Finder.visible(LoginPom.COUNTRY_CODE, 10).isDisplayed(),
                "⚠ لم يظهر رمز الدولة بعد الضغط على الحساب"
        );
    }

    @Test(priority = 3)
    public void enterMobileNumber() {
        String mobile = Setup.testData
                .getUserData("validUser")
                .get("mobile")
                .asText();

        Go.type(LoginPom.MOBILE_INPUT, mobile, 10);
    }

    @Test(priority = 4)
    public void clickOnLoginButton() {
        Go.click(LoginPom.LOGIN_BUTTON, 10);
        Assert.assertTrue(
                Finder.visible(LoginPom.OTP_POPUP_BUTTON, 10).isDisplayed(),
                "⚠ لم يظهر زر إرسال OTP في البوب أب"
        );
    }

    @Test(priority = 5)
    public void clickOnOtpButton() {
        Go.click(LoginPom.OTP_POPUP_BUTTON, 10);
    }

    @Test(priority = 6)
    public void userEnterCorrectOtp() {
        String otp = Setup.testData
                .getUserData("validUser")
                .get("otp")
                .asText();

        Go.type(LoginPom.OTP_FIELD, otp, 10);
        Go.click(LoginPom.OTP_VERIFY_BUTTON, 10);
    }

    @Test(priority = 7)
    public void verifySearchBarIsDisplayed() {
        Assert.assertTrue(
                Finder.visible(LoginPom.SEARCH_BAR, 10).isDisplayed(),
                "⚠ لم يظهر مربع البحث بعد تسجيل الدخول"
        );
    }
}
