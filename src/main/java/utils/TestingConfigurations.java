package utils;

public class TestingConfigurations {
    private TestingConfigurations() {
    }

    public static String getStartUrl() {
        return TestingEnvironment.getCurrentEnvironment("testing.json").getValue("/start_url").toString();
    }

}
