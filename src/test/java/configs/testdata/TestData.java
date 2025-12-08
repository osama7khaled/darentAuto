package configs.testdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestData {

    private JsonNode testData;
    private final String language;   // <-- نخزن اللغة هنا

    public TestData(String fileName, String language) {
        this.language = language.toLowerCase(); // <-- نخزن اللغة النهائية

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.testData = mapper.readTree(new File("src/test/java/configs/testdata/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load test data file: " + fileName, e);
        }
    }

    // --------------------------------------------------------
    // 🔥 استخدام اللغة الداخلية مباشرة (بدون تمرير parameter)
    // --------------------------------------------------------
    public String getBaseUrl() {

        JsonNode envNode = testData.get("base-url").get("env");

        if (envNode == null) {
            throw new RuntimeException("❌ Missing 'env' node in base-url");
        }

        JsonNode urlNode = envNode.get(language);

        if (urlNode == null) {
            throw new RuntimeException("❌ No URL found for language: " + language);
        }

        return urlNode.asText();
    }

    // --------------------------------------------------------
    // باقي الـ getters
    // --------------------------------------------------------

    public JsonNode getUserData(String userType) {
        return testData.get("users").get(userType);
    }
    public JsonNode getHomeData() {
        return testData.get("homePageTexts");
    }


    public JsonNode getLoginData() {
        return testData.get("login");
    }

    public JsonNode getPropertyData() {
        return testData.get("properties");
    }

    public JsonNode getFilterData() {
        return testData.get("filters"); // <-- lowercase (صححناها)
    }

    public JsonNode getPaymentData() {
        return testData.get("payment");
    }

    public JsonNode getReservationData() {
        return testData.get("reservations");
    }

    public JsonNode getHostData() {
        return testData.get("host");
    }
}
