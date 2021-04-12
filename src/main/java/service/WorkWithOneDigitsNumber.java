package service;

public class WorkWithOneDigitsNumber implements Number {

    private static WorkWithThousandsDigitsNumber withThousandsDigitsNumber = new WorkWithThousandsDigitsNumber();
    private static boolean flag = false;

    private String[] oneAndTwo = new String[]{"одна", "две"};
    private String[] numbersFromOneTillNineteen = new String[]{"один", "два", "три", "четыре", "пять", "шесть",
            "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

    @Override
    public String getNumber(String line) {
        return getChangedDeclension(getIndexOfNumber(getDividedNumber(line), -1)).toString();
    }

    @Override
    public String getNumber(long number) {
        return getNumber(Long.toString(number));
    }

    public int getIndexOfNumber(int resultValue, int index) {
        return resultValue < 10 ? (resultValue % 10) - 1 : resultValue < 20 ? (resultValue % 20) - 1 : index;
    }

    public int getDividedNumber(String line) {
        return (line.length() > 3 ? Integer.parseInt(line.substring(line.length() - 3), 10) :
                Integer.parseInt(line, 10)) % 100;
    }

    public StringBuilder getChangedDeclension(int index) {
        StringBuilder resultLine = new StringBuilder();
        flag = withThousandsDigitsNumber.getFlag();

        if (index < numbersFromOneTillNineteen.length && index != -1) {
            if (!flag) {
                resultLine.append(numbersFromOneTillNineteen[index]);
            } else {
                resultLine.append(oneAndTwo[index]);
                flag = true;
            }
        }

        return resultLine;
    }
}
