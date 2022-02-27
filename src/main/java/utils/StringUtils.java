package utils;

public class StringUtils {


   public static StringBuilder buildString(String one, String two, int three) {
       StringBuilder stringBuilder = new StringBuilder(one);
       stringBuilder.append(two);
       stringBuilder.append(three);
  return stringBuilder;
   }

    public static void main(String[] args) {
        System.out.println(buildString("one", "two", 3));
    }
}
