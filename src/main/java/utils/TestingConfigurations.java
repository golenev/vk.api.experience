package utils;

public class TestingConfigurations {

    private TestingConfigurations() {
    }

    public static String getConfigValue (String key){
        return TestingEnvironment.getCurrentEnvironment("configData.json").getValue(key).toString();
    }

    public static String getTestingValue (String key){
        return TestingEnvironment.getCurrentEnvironment("testingData.json").getValue(key).toString();
    }


}
