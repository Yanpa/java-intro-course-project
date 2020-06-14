import java.util.Random;
import java.util.Scanner;

public class Traps{
    Reader reader = new Reader("Data.txt");
    Random random = new Random();

    Scanner scanner = new Scanner(System.in);
    int numberOfTraps = reader.importValue("trap");

    boolean isThereATrap = false;

    /**
     *
     * @return Връща различни видове методи, в зависимост от отговора
     */
    public double givingThePlayerChoice(double money, double moneyBalance, String playerID, String choice){
        if(choice.equals("1")) return taxAudit(money, playerID);
        if(choice.equals("2")) return divorceForCats(money, moneyBalance, playerID);
        if(choice.equals("3")) return propaganda(money, playerID);
        if(choice.equals("4")) return seeingTheBiggerPicture(money, playerID);
        if(choice.equals("5")) return hazardBoss(money, playerID);
        if(choice.equals("n")) System.out.println("Разбрано, продължаваме напред!\n");

        return -1;
    }

    boolean isTaxAuditActivated = false;
    String ownerOfTheTaxAudit;

    /**
     * Метод, който отговаря на първото условие от таблицата за плочката Traps, дава възможност да заложим капан, а
     * ако вече има заложен капан на нея, се налагат санкциите
     *
     * @param money Това са парите, с които играча разполага
     * @param playerID   Id-то на играча
     * @return връща парите на играча
     */
    public double taxAudit(double money, String playerID){
        if(!isTaxAuditActivated) {
            ownerOfTheTaxAudit = playerID;
            isTaxAuditActivated = true;
            isThereATrap = true;
            System.out.println("Заложихте капан, плащате 100 шп\n");
            return money - 100;
        }

        if(isTaxAuditActivated){
            System.out.println("Губите 10 процента от всичките\n" +
                    "си приходи в край на цикъла.");
            double tax = money * 0.1;
            money = money - tax;
            isTaxAuditActivated = false;
            return money;
        }

        if(isTaxAuditActivated && playerID.equals(ownerOfTheTaxAudit)){
            int chance = random.nextInt(10) + 1;
            if(chance % 3 != 0){
                System.out.println("Голям карък сте!\n Губите 10 процента от всичките\n" +
                        "си приходи в край на цикъла.");
                double tax = money * 0.1;
                money = money - tax;
                isTaxAuditActivated = false;
                return money;
            }
        }

        return money;
    }

    boolean isDivorceForCatsActivated = false;
    String ownerOfDivorcingCats;

    /**
     * Метод, който отговаря на второто условие от таблицата за плочката Traps, дава възможност да заложим капан, а
     * ако вече има заложен капан на нея, се налагат санкциите
     *
     * @param money Това са парите, с които играча разполага
     * @param moneyBalance Това са парите с които започва всеки играч в началото на рунда
     * @param playerID   Id-то на играча
     * @return връща парите на играча
     */
    public double divorceForCats(double money, double moneyBalance, String playerID){
        if(isDivorceForCatsActivated){
            System.out.println("Хвърля се 10-стенен зар, ако стойността му е 2 или 8 не полушавш шп");
            int chance = random.nextInt(10) + 1;
            if((chance == 2 || chance == 8) && money >= moneyBalance){
                System.out.println("Днес, наистина нямаш късмет");
                money = moneyBalance;
                isDivorceForCatsActivated = false;
                return money;
            }
        }

        if(isDivorceForCatsActivated && playerID.equals(ownerOfDivorcingCats)){
            int rand = random.nextInt(10) + 1;
            if(rand % 3 != 0) {
                System.out.println("Хвърля се 10-стенен зар, ако стойността му е 2 или 8 не полушавш шп");
                int chance = random.nextInt(10) + 1;
                if ((chance == 2 || chance == 8) && money >= moneyBalance) {
                    money = moneyBalance;
                    isDivorceForCatsActivated = false;
                    return money;
                }
            }
        }
        if(!isDivorceForCatsActivated){
            ownerOfDivorcingCats = playerID;
            isDivorceForCatsActivated = true;
            isThereATrap = true;
            System.out.println("Заложихте капан, плащате 200 шп\n");
            return money - 200;
        }
        return money;
    }

    boolean isPropagandaActive = false;
    String ownerOfPropaganda;

    /**
     * Мисля че вече се досещате че това е третото условие от Traps
     *
     * @param money Това са парите, с които играча разполага
     * @param playerID   Id-то на играча
     * @return връща парите на играча
     */
    public double propaganda(double money, String playerID){
        if(isPropagandaActive){

        }

        if(isPropagandaActive && playerID.equals(ownerOfPropaganda)){
            int rand = random.nextInt(10) + 1;
            if(rand % 3 != 0) {

            }
        }
        if(!isPropagandaActive){
            ownerOfPropaganda = playerID;
            isPropagandaActive = true;
            isThereATrap = true;
            System.out.println("Заложихте капан, плащате 100 шп\n");
            return money - 100;
        }
        return money;
    }

    boolean isSeeingTheBiggerPictureActive = false;
    String ownerOfBiggerPicture;

    /**
     * Четвъртото условие на Traps
     * @param money Това са парите, с които играча разполага
     * @param playerID   Id-то на играча
     * @return връща парите на играча
     */
    public double seeingTheBiggerPicture(double money, String playerID){
        if(isSeeingTheBiggerPictureActive){

        }

        if(isSeeingTheBiggerPictureActive && playerID.equals(ownerOfBiggerPicture)){
            int rand = random.nextInt(10) + 1;
            if(rand % 3 != 0) {

            }
        }

        if(!isSeeingTheBiggerPictureActive){
            ownerOfBiggerPicture = playerID;
            isSeeingTheBiggerPictureActive = true;
            isThereATrap = true;
            System.out.println("Заложихте капан, плащате 50 шп\n");
            return money - 50;
        }
        return money;
    }

    boolean isHazardBossActivated = false;
    String ownerOfHazardBoss;

    /**
     * Петото и последно условие на Traps
     * @param money Това са парите, с които играча разполага
     * @param playerID   Id-то на играча
     * @return връща парите на играча
     */
    public double hazardBoss(double money, String playerID){
        if(isHazardBossActivated){

        }

        if(isHazardBossActivated && playerID.equals(ownerOfHazardBoss)){
            int rand = random.nextInt(10) + 1;
            if(rand % 3 != 0) {

            }
        }

        if(!isHazardBossActivated){
            ownerOfHazardBoss = playerID;
            isHazardBossActivated = true;
            isThereATrap = true;
            System.out.println("Заложихте капан, плащате 100 шп\n");
            return money - 100;
        }
        return money;
    }
}
