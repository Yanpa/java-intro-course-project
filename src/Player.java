import java.util.Random;

public class Player {
    Random random = new Random();
    Reader reader = new Reader("Data.txt");
    double moneyYouStartWith = reader.importValue("money");

    double money;
    double moneyBalance;
    String playerID;
    boolean areYouTheWinner = false;

    /**
     * Дава стойности на променливите
     * @param moneyOfPlayer
     * @param moneyBalance
     * @param playerID
     */
    public Player(double moneyOfPlayer, double moneyBalance, String playerID){
        this.moneyBalance = moneyBalance;
        this.money = moneyOfPlayer;
        this.playerID = playerID;
    }

}
