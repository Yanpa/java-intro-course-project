import java.util.Random;

public class Invest {
    Random random = new Random();
    Reader reader = new Reader("Data.txt");
    int numberOfInvestments = reader.importValue("invest");

    String[] investTable = new String[]{"Evel Co", "Bombs Away", "Clock Work Orange", "Maroders unated", "Fatcat incorporated", "Macrosoft"};

    /**
     * Метода изважда 3 различни фирми и ти ги предлага
     */
    public void choosingTreeCompanies(){
        int temp = -1, rand;
        for(int i = 0; i < 3; i++){
            rand = random.nextInt(investTable.length);
            if(rand != temp){
                System.out.print((i+1) + " " + investTable[rand] + " | ");
                if(investTable[rand].equals("Evel Co")) System.out.print("min: 500 | risk/reward: 0.2\n");
                if(investTable[rand].equals("Bombs Away")) System.out.print("min: 400 | risk/reward: 0.5\n");
                if(investTable[rand].equals("Clock Work Orange")) System.out.print("min: 300 | risk/reward: 1.5\n");
                if(investTable[rand].equals("Maroders unated")) System.out.print("min: 200 | risk/reward: 2.0\n");
                if(investTable[rand].equals("Fatcat incorporated")) System.out.print("min: 100 | risk/reward: 2.5\n");
                if(investTable[rand].equals("Macrosoft")) System.out.print("min: 50 | risk/reward: 5.0\n");
            }
        }
        System.out.println("(N) Не ми се инвестира повече.");

    }

    public Invest(){

    }
}
