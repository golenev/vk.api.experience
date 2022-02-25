package utils;

public class RegExHelper {
    public static int getIntAfterUnderlining(String incomingString, int characterMassiveIndex){
         String characters[] = incomingString.split("_");
         return Integer.parseInt(characters[characterMassiveIndex]);
    }
}
