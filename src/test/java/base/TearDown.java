package base;

import org.testng.annotations.AfterSuite;

public class TearDown {

    @AfterSuite
    public void quitDriver() {

        try {
            if (Setup.driver != null) {
                Setup.driver.quit();
                System.out.println("🔥 Browser closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("⚠ Error while closing browser: " + e.getMessage());
        }
    }
}
