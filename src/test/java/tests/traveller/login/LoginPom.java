package tests.traveller.login;

import org.openqa.selenium.By;

public class LoginPom {

    public static final By ACCOUNT_BUTTON = By.cssSelector(
            "body > div.ui-footer.for-mobile > div > div > a:nth-child(4) > div.ui-fl-icn.inct"
    );

    public static final By COUNTRY_CODE = By.className("iti__selected-dial-code");

    public static final By MOBILE_INPUT = By.cssSelector("#phone-input-mobile");

    public static final By LOGIN_BUTTON = By.cssSelector("#sendconfirmotpbtn");

    public static final By OTP_POPUP_BUTTON = By.cssSelector(
            "#enterotp > div > div > div.modal-body.cm-simple-body > div > form > div.col-md-12.text-center > button.theme-btn.w-100.phone-btn.mt-3.sendotpbtn"
    );

    public static final By OTP_FIELD = By.cssSelector("#otpfield");

    public static final By OTP_VERIFY_BUTTON = By.cssSelector(
            "#enterotp > div > div > div.modal-body.cm-simple-body > div > form > div.col-md-12.text-center > button.theme-btn.w-100.phone-btn.otpverify.mt-3"
    );

    public static final By SEARCH_BAR = By.cssSelector("#float-form > div.float-hd");
}
