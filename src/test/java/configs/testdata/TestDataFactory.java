package configs.testdata;

public class TestDataFactory {

    public static TestData getTestData(String branch, String language) {

        String testDataFileName;

        boolean isStaging = branch.equalsIgnoreCase("staging");
        boolean isArabic = language.equalsIgnoreCase("arabic");

        if (isStaging) {
            if (isArabic) {
                testDataFileName = "stagingArabic.json";
            } else {
                testDataFileName = "stagingEnglish.json";
            }
        } else {
            if (isArabic) {
                testDataFileName = "productionArabic.json";
            } else {
                testDataFileName = "productionEnglish.json";
            }
        }
        System.out.println("📌 Loaded Test Data File: " + testDataFileName);
        return new TestData(testDataFileName, language);
    }
}
