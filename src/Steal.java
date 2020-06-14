import java.util.Random;

public class Steal {
    Random random = new Random();
    Reader reader = new Reader("Data.txt");
    int numberOfSteals = reader.importValue("steal");

    String [] tileString = new String[]{" |C|", " |I|", "|St|"};
    String tileId = tileString[random.nextInt(tileString.length)];


    public double conqueringTheWorld(double money){
        return money + 100;
    }
}
