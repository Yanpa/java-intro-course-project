import java.util.Random;

public class GameBoard {
    Random random = new Random();
    Reader reader = new Reader("Data.txt");
    Traps traps = new Traps();
    Chance chance = new Chance();
    Steal steal = new Steal();
    Invest invest = new Invest();
    PartyHard partyHard = new PartyHard();

    int gameBoardHeight = reader.importValue("height");
    int gameBoardWidth = reader.importValue("width");

    int numberOfTraps = traps.numberOfTraps;
    int numberOfInvestments = invest.numberOfInvestments;
    int numberOfPartyHard = partyHard.numberOfPartyHard;
    int numberOfChances = chance.numberOfChances;
    int numberOfSteals = steal.numberOfSteals;

    //това е дължината на едномерният масив, в който поставям капаните
    int outlineOfTheBoard = (gameBoardHeight * 2) + ((gameBoardWidth * 2) - 4);

    //Едномерният масив, в който поставям капаните
    String[] gameBoardLinear = new String[outlineOfTheBoard];
    //Булевият едномерен масив, който ми помага да поставя капаните в gameBoardLinear
    Boolean[] gameBoardBool = new Boolean[outlineOfTheBoard];

    //Двумерният масив, който се принтира и в който се поставят вече поставените на случаен принцип капани
    String[][] gameBoardVisions = new String[gameBoardHeight][gameBoardWidth];

    /**
     * Единствената работа на този метод е да принтира игровото поле
     */
    public void printingTheFinishedGameBoard(){
        creatingTheGameBoards();
        placingThePossibilitiesOnTheBoard();
        puttingTheTilesOnTheVisionGameBoard();
        printingTheGameBoard();
    }

    /**
     * creatingTheGameBoard е метод, чиято цел е да вземе стойностите на height и width от Reader()
     * и да създаде двумерен масив с празни низове, които по-късно да могат да бъдат замененени с плочките
     * на игровото поле
     */
    private void creatingTheGameBoards() {
        for (int i = 0; i < gameBoardHeight; i++) {
            for (int j = 0; j < gameBoardWidth; j++) {
                gameBoardVisions[i][j] = "    ";
            }
        }
        fillingBooleanBoard();
    }

    /**
     * fillingBooleanBoard е метод който създава едномерен масив от букеви стойности, което спомага
     * за нареждането на капаните по случаен начин на дъската
     */
    private void fillingBooleanBoard() {
        for (int i = 0; i < outlineOfTheBoard; i++) {
            gameBoardBool[i] = false;
        }
        gameBoardBool[0] = true;
    }

    /**
     * printTheGameBoard е метод, който няма нужда от много обяснение, името казва всичко
     */
    private void printingTheGameBoard() {
        for (int i = 0; i < gameBoardHeight; i++) {
            for (int j = 0; j < gameBoardWidth; j++) {
                System.out.print(gameBoardVisions[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * placingTheTiles взима два аргумента и поставя на случаен принцип капаните на игровото поле, в този метод се
     * използва и масива gameBoardBool, който ни помага да разберем дали на мястото което се опитваме да сложим капан
     * вече няма такъв
     *
     * @param numberOfTiles взима число от текстовия файл, което разкрива колко са на брой капаните от всеки вид
     * @param theTile низова репрезентация на капана
     */
    public void placingTheTiles(int numberOfTiles, String theTile) {
        gameBoardLinear[0] = " |S|";
        while (numberOfTiles > 0) {
            int randNumber = random.nextInt(outlineOfTheBoard - 1) + 1;
            if (!gameBoardBool[randNumber]) {
                gameBoardLinear[randNumber] = theTile;
                gameBoardBool[randNumber] = true;
                numberOfTiles--;
            }
        }
    }

    /**
     * placingThePossibilitiesOnTheBoard разпределя всички капани на игровото поле
     */
    private void placingThePossibilitiesOnTheBoard() {
        placingTheTiles(numberOfTraps, " |T|");
        placingTheTiles(numberOfSteals, "|St|");
        placingTheTiles(numberOfChances, " |C|");
        placingTheTiles(numberOfInvestments, " |I|");
        placingTheTiles(numberOfPartyHard, " |P|");
    }

    /**
     * puttingTheTilesOnTheVisionBoard е метод който чрез четири for цикъла успява да постави всички капани на
     * на правилните места, за да се образува подобно на монополи игрово поле.
     *
     * side note: Постарах се да го направя така, че да може да поставя капаните на страните на масива, независимо
     * от стойностите на height и width
     */
    public void puttingTheTilesOnTheVisionGameBoard() {
        int theBigSide = (gameBoardWidth - 1);
        int theSmallSide = (gameBoardHeight - 1);
        int indexOfLinearArray = 0;

        for(int i = theBigSide; i >= 0; i--){
            gameBoardVisions[theSmallSide][i] = gameBoardLinear[indexOfLinearArray];
            indexOfLinearArray++;
        }
        for(int i = theSmallSide - 1; i > 0; i--){
             gameBoardVisions[i][0] = gameBoardLinear[indexOfLinearArray];
             indexOfLinearArray++;
        }
        for(int i = 0; i < theBigSide; i++){
            gameBoardVisions[0][i] = gameBoardLinear[indexOfLinearArray];
            indexOfLinearArray++;
        }
        for(int i = 0; i < theSmallSide; i++){
            gameBoardVisions[i][theBigSide] = gameBoardLinear[indexOfLinearArray];
            indexOfLinearArray++;
        }

    }
}