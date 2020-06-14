public class PartyHard {
    Reader reader = new Reader("Data.txt");
    int numberOfPartyHard = reader.importValue("partyHard");

    /**
     * Такасата за разпускането не е ниска
     * @param money
     * @return
     */
    public double partyFee(double money){
        System.out.println("Време е за малко почивка, но тя си има своята цена, а тя е 25 шп");
        return money - 25;
    }
}
