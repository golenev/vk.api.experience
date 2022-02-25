package utils;

public class TestingConfigurations {
    private TestingConfigurations() {
    }

    public static String getStartUrl() {
        return TestingEnvironment.getCurrentEnvironment("testing.json").getValue("/start_url").toString();
    }

    public static String getConfigValue (String key){
        return TestingEnvironment.getCurrentEnvironment("testing.json").getValue(key).toString();
    }

    public static void main(String[] args) {
        System.out.println(getConfigValue("/start_url"));
    }

}
