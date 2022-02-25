package utils;

import aquality.selenium.core.logging.Logger;
import org.openqa.selenium.NotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

    public static File loadFile(String path) {
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Logger.getInstance().info("File was successfully read");
        } catch (IOException ex) {
            Logger.getInstance().error("Sorry, file not found");
            Logger.getInstance().warn("Please, check your path");
        }
        return file;
    }
}
