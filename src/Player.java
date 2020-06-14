import java.util.Random;

public class Player {
    Random random = new Random();

    double money;
    double moneyBalance;
    String playerID;
    boolean isOnTheMove = false;
    boolean areYouTheWinner = false;

    public Player(double moneyOfPlayer, double moneyBalance, String playerID){
        this.moneyBalance = moneyBalance;
        this.money = moneyOfPlayer;
        this.playerID = playerID;
    }

    public int rollingTheDice(){
        return random.nextInt(2) + 1;
    }
}
