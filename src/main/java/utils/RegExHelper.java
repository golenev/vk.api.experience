package utils;
import static utils.TestingConfigurations.*;

public class RegExHelper {
    public static int getIntAfterUnderlining(String incomingString, int characterMassiveIndex){
         String characters[] = incomingString.split(getRegExValue("/underLining"));
         return Integer.parseInt(characters[characterMassiveIndex]);
    }

 }
