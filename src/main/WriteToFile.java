package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public void writeToFile(String lokasi, String isi) {
        try {
            String isiFile = isi;
            File file = new File(lokasi);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fwrite = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bwrite = new BufferedWriter(fwrite)) {
                bwrite.write(isiFile);
            }
//            System.out.println("\nFile vektor berhasil digeneralisasi");
        } catch (IOException ioe) {
        }
    }

}
