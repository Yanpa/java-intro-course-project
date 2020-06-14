import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    Scanner myScan;

    public Reader(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            myScan = new Scanner(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int importValue(String valueName){
        while (myScan.hasNextLine()){
            String line=myScan.nextLine();
            if(line.contains(valueName)) return extractValue(line);
        }
        return -1;
    }

    private int extractValue(String line) {
        String[] lineParts = line.split("=");
        return Integer.parseInt(lineParts[1]);
    }
}
