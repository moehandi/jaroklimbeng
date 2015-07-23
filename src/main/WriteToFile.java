package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public void writeToFile(String location, String content) {
        try {
            String fileContent = content;
            File file = new File(location);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fwrite = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bwrite = new BufferedWriter(fwrite)) {
                bwrite.write(fileContent);
            }
        } catch (IOException ioe) {
        }
    }

}
