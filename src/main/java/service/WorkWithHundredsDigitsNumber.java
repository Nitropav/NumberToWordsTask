package service;

import org.apache.commons.lang3.StringUtils;

public class WorkWithHundredsDigitsNumber implements Number {

    private WorkWithDozensDigitsNumber dozensDigitsNumber = new WorkWithDozensDigitsNumber();

    private String[] hundreds = new String[]{"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"};

    @Override
    public String getNumber(String line) {
        return recordingHundredsDigitNumber(getDividedNumber(line)).toString();
    }

    @Override
    public String getNumber(long number) {
        return getNumber(Long.toString(number));
    }

    public int getDividedNumber(String line) {
        return (line.length() > 4 ? Integer.parseInt(line.substring(line.length() - 4), 10) :
                Integer.parseInt(line, 10)) % 1000;
    }

    public StringBuilder recordingHundredsDigitNumber(int resultValue) {
        StringBuilder resultLine = new StringBuilder();

        if (resultValue >= 100) {
            resultLine.append(hundreds[(resultValue / 100) - 1]);
            resultValue %= 100;
        }

        return recordingTwoDigitNumber(resultValue, resultLine);
    }

    public StringBuilder recordingTwoDigitNumber(int resultValue, StringBuilder resultLine) {
        return resultValue != 0 ? new StringBuilder(resultLine.append(StringUtils.SPACE)
                .append(dozensDigitsNumber.getNumber(resultValue)).toString().trim()) : resultLine;
    }
}
