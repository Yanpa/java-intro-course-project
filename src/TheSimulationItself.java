import java.util.Random;
import java.util.Scanner;

public class TheSimulationItself {
    static double moneyYouBeginWith = 1000;
    static double playerMoney = moneyYouBeginWith, botMoney = moneyYouBeginWith;
    static double playerBalance = playerMoney, botBalance = botMoney;
    static  int yourPositionPlayer = 0, yourPositionBot = 0;

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static Reader reader = new Reader("Data.txt");

    static GameBoard gameBoard = new GameBoard();
    static Chance chance = new Chance();
    static Traps traps = new Traps();
    static Steal steal = new Steal();
    static Invest invest = new Invest();
    static PartyHard partyHard = new PartyHard();

    static Player player = new Player(playerMoney, playerBalance, "Me");
    static Player bot = new Player(botMoney, botBalance, "Bot");

    public static void main(String[] args) {
        theGameItself();
    }

    public static void theGameItself(){
        gameBoard.printingTheFinishedGameBoard();
        int count = 0, temp = whoIsStartingFirst();;
        if(temp == 1)
            count = 1;
        else if(temp == 2)
            count = 2;
        while(!player.areYouTheWinner && !bot.areYouTheWinner){
            if(count % 2 == 0){
                System.out.println("Бота е на ход");
                yourPositionBot += dice();
                if(yourPositionBot >= 20) {
                    yourPositionBot = 0;
                    botBalance = botMoney;
                    System.out.println("Баланса на бота е: " + botMoney);
                }
                if(yourPositionBot == 0){
                    count++;
                    continue;
                }

                if(botBalance > playerBalance) count = 1;
                if(botBalance <= 0) player.areYouTheWinner = true;

                int rand = random.nextInt(5) + 1;
                botMechanics(yourPositionBot, Integer.toString(rand));
                System.out.println("Баланса на бота е: " + botMoney);

                if(player.areYouTheWinner) System.out.println("Ти печелиш!");
                if(bot.areYouTheWinner) System.out.println("Бота печели!");
            }


            if(count % 2 != 0){
                System.out.println("Играча е на ход");
                yourPositionPlayer += dice();
                if(yourPositionPlayer >= 20){
                    yourPositionPlayer = 0;
                    playerBalance = playerMoney;
                    System.out.println("Твоят баланс от шоколадови парички е: " + playerMoney);
                }
                if(playerBalance > botBalance) count = 2;
                if(playerBalance <= 0) bot.areYouTheWinner = true;
                if(yourPositionPlayer == 0){
                    count++;
                    continue;
                }
                playerMechanics(yourPositionPlayer);
                System.out.println("Твоят баланс от шоколадови парички е: " + playerMoney);

                if(player.areYouTheWinner) System.out.println("Ти печелиш!");
                if(bot.areYouTheWinner) System.out.println("Бота печели!");
            }
            count++;

        }
    }

    public static int whoIsStartingFirst(){
        int coinFlip = random.nextInt(10) + 1;
        if(coinFlip % 2 == 0){
            System.out.println("Играчът започва първи");
            return 1;
        }
        if(coinFlip % 2 != 0){
            System.out.println("Бота започва първи");
            return 2;
        }
        return 0;
    }

    public static String whichTileYouAreOn(int yourPosition){
        return gameBoard.gameBoardLinear[yourPosition];
    }

    public static int dice(){
        return random.nextInt(2) + 1;
    }

    public static void playerMechanics(int yourPosition) {
        String tileYouAreOn = whichTileYouAreOn(yourPosition);
        if(tileYouAreOn.equals(" |T|")) playerMoney = playerAndTrap();
        if(tileYouAreOn.equals(" |C|")) playerMoney = chance.rollingTheDice(playerMoney);
        if(tileYouAreOn.equals(" |P|")) playerMoney = partyHard.partyFee(playerMoney);
        if(tileYouAreOn.equals("|St|")) {
            for (int i = 0; i < gameBoard.gameBoardLinear.length; i ++){
                if(gameBoard.gameBoardLinear[i].equals(steal.tileId) && i == yourPositionPlayer)
                    playerMoney = steal.conqueringTheWorld(playerMoney);
            }
        }
        if(tileYouAreOn.equals(" |I|")) {
            invest.choosingTreeCompanies();
            System.out.print("Твоят избор: ");
            String choice = scanner.nextLine().trim().toLowerCase();

        }
    }

    public static void botMechanics(int yourPosition, String choice){
        String tileYouAreOn = whichTileYouAreOn(yourPosition);
        if(tileYouAreOn.equals(" |T|")) botMoney = botAndTraps(choice);
        if(tileYouAreOn.equals(" |C|")) botMoney = chance.rollingTheDice(botMoney);
        if(tileYouAreOn.equals(" |P|")) botMoney = partyHard.partyFee(botMoney);
        if(tileYouAreOn.equals("|St|")) {
            for (int i = 0; i < gameBoard.gameBoardLinear.length; i ++){
                if(gameBoard.gameBoardLinear[i].equals(steal.tileId) && i == yourPositionBot)
                    botMoney = steal.conqueringTheWorld(botMoney);
            }
        }
        if(tileYouAreOn.equals(" |I|"));
    }

    public static double playerAndTrap(){
        if (!traps.isThereATrap) {
            System.out.println("Желаете ли да заложите капан?\n" +
                    "* (1) : Данъчна ревизия (100 шп)\n" +
                    "* (2) : Развод по котешки (200 шп)\n" +
                    "* (3) : Пропаганда (100 шп)\n" +
                    "* (4) : Проглеждане (50 шп)\n" +
                    "* (5) : Хазартен бос (100 шп)\n" +
                    "* (N) : Не, благодаря, не вярвам в злото");
            System.out.print("Избери опция: ");
            String choice = scanner.nextLine().toLowerCase().trim();
            return playerMoney = traps.givingThePlayerChoice(playerMoney, playerBalance, player.playerID, choice);
        } else {
            if (traps.isTaxAuditActivated) {
                return playerMoney = traps.taxAudit(playerMoney, player.playerID);
            } else if (traps.isDivorceForCatsActivated) {
                return playerMoney = traps.divorceForCats(playerMoney, playerBalance, player.playerID);
            } else if (traps.isPropagandaActive){
                return playerMoney = traps.propaganda(playerMoney, player.playerID);
            } else if (traps.isSeeingTheBiggerPictureActive){
                return playerMoney = traps.seeingTheBiggerPicture(playerMoney, player.playerID);
            } else return playerMoney = traps.hazardBoss(playerMoney, player.playerID);
        }
    }

    public static double botAndTraps(String choice){
        if (!traps.isThereATrap) {
            return botMoney = traps.givingThePlayerChoice(botMoney, botBalance, bot.playerID, choice);
        } else {
            if (traps.isTaxAuditActivated) {
                return botMoney = traps.taxAudit(botMoney, bot.playerID);
            } else if (traps.isDivorceForCatsActivated) {
                return botMoney = traps.divorceForCats(botMoney, botBalance, bot.playerID);
            } else if (traps.isPropagandaActive){
                return botMoney = traps.propaganda(botMoney, bot.playerID);
            } else if (traps.isSeeingTheBiggerPictureActive){
                return botMoney = traps.seeingTheBiggerPicture(botMoney, bot.playerID);
            } else return botMoney = traps.hazardBoss(botMoney, bot.playerID);
        }
    }
}
