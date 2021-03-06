import java.util.Random;

public class Chance {
    Reader reader = new Reader("Data.txt");
    int numberOfChances = reader.importValue("chance");
    Random random = new Random();

    /**
     * rollingTheDice е е метода, който на случаен принцип решава дали ти ще получиш пари или ще ти бъдат отнети пари
     * @param money Парите на играча в момента на стъпването му на плочката
     * @return Добавя или взима пари от хората
     */
    public double rollingTheDice(double money){
        int randNumber = random.nextInt(10) + 1;
        if(randNumber % 2 == 0) {
            return highHopes(money);
        }

        return thousandAndOneNights(money);
    }

    /**
     * thousandAndOneNights е метода с лошият късмет на дъската Chance, той винаги ще ти взима пари
     * @param money Парите с които играча разполага при стъпването на плочката
     * @return Взима пари от джобовете ти
     */
    private double thousandAndOneNights(double money){
        int chance = random.nextInt(100) + 1;
        if(chance >= 1 && chance <= 39){
            System.out.println("Вдигате толкова голям купон, че\n" +
                    "не знаете къде се намирате на\n" +
                    "следващата седмица. С мъка\n" +
                    "установявате, че телевизорът Ви е\n" +
                    "откраднат.\n" +
                    "Губите 50 шп.");
            return money - 50;
        }
        if(chance >= 40 && chance <= 65){
            System.out.println("Вие сте баща на три абитуриентки,\n" +
                    "бъдете готови за стабилни\n" +
                    "разходи.\n" +
                    "Губите 100 шп.");
            return money - 100;
        }
        if(chance >= 66 && chance <= 79){
            System.out.println("Най-добрият Ви служител\n" +
                    "получава повиквателна за\n" +
                    "казармата. Губите обучен\n" +
                    "персонал.\n" +
                    "Губите 150 шп.");
            return money - 150;
        }
        if(chance >= 80 && chance <= 94){
            System.out.println("На връщане от супермаркета,\n" +
                    "чудовище се опитва да ви изяде.\n" +
                    "Справяте се с неприятеля,\n" +
                    "използвайки карате, но се налага\n" +
                    "да пишете обяснения, които водят\n" +
                    "до пропускане на важна среща и\n" +
                    "загуба на бизнес партньор.\n" +
                    "Губиш 200 шп.");
            return money - 200;
        }

        System.out.println("Част от бизнесите Ви фалират,\n" +
                "заради задаваща се епидемия от\n" +
                "потна треска.\n" +
                "По-лош късмет не можехте да имате. Губите 250 шп");
        return money - 250;
    }

    /**
     * highHopes е джедаите на вселената на Chance, този метод е тук, за да донесе баланс на хората ида им даде пари.
     * За разлика от по-големият си брат, този метод не носи тъга, когато бъде видян на конзолата.
     * @param money Парите с които играча разполага, когато стъпи на плочката
     * @return
     */
    private double highHopes(double money){
        int chance = random.nextInt(100) + 1;
        if(chance >= 1 && chance <= 39){
            System.out.println("Осиновявате група\n" +
                    "сирачета, за да си вдигнете\n" +
                    "социалното реноме.\n" +
                    "Социалните мрежи са във\n" +
                    "възторг, получавате\n" +
                    "окуражителни дарения от\n" +
                    "обществото.\n" +
                    "Губите 50 шп.");
            return money + 50;
        }
        if(chance >= 40 && chance <= 65){
            System.out.println("Хващате си младо гадже,\n" +
                    "малка котка с големи\n" +
                    "възможности. Получавате\n" +
                    "вечното уважение на\n" +
                    "кварталните пичове, както\n" +
                    "и легендарен статус на\n" +
                    "вечен играч.\n" +
                    "Печелите 100 шп.");
            return money + 100;
        }
        if(chance >= 66 && chance <= 79){
            System.out.println("Напускате университета и\n" +
                    "ставате милионер. Честито!\n" +
                    "Печелите 150 шп.");
            return money + 150;
        }
        if(chance >= 80 && chance <= 94){
            System.out.println("Тийнейджъри представят\n" +
                    "гениална идея за\n" +
                    "рационализиране на\n" +
                    "производствените\n" +
                    "мощности. Получавате\n" +
                    "стабилен бонус.\n" +
                    "Печелите 200 шп.");
            return money + 200;
        }

        System.out.println("Наемате джудже за личен\n" +
                "асистент, обществото е\n" +
                "уверено че междувидовата\n" +
                "сегрегация е в историята.\n" +
                "Уважението към вас е\n" +
                "безгранично.\n" +
                "Печелите 250 шп");
        return money + 250;
    }
}

