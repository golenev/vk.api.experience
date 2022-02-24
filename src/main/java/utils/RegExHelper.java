package utils;

public class RegExHelper {
    public static int getIntAfterUnderlining(String string, int index){
         String characters[] = string.split("_");
         return Integer.parseInt(characters[index]);
    }
}
