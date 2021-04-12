package service;

import org.apache.commons.lang3.StringUtils;

public class WorkWithDozensDigitsNumber implements Number {

    private WorkWithOneDigitsNumber oneDigitsNumber = new WorkWithOneDigitsNumber();

    private String[] dozens = new String[]{"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто"};

    @Override
    public String getNumber(String line) {
        return recordingTwoDigitNumber(getDividedNumber(line), new StringBuilder()).toString();
    }

    @Override
    public String getNumber(long number) {
        return getNumber(Long.toString(number));
    }

    public int getDividedNumber(String line) {
        return (line.length() > 3 ? Integer.parseInt(line.substring(line.length() - 3), 10) :
                Integer.parseInt(line, 10)) % 100;
    }

    public StringBuilder recordingTwoDigitNumber(int resultValue, StringBuilder resultLine) {
        if (resultValue >= 20) {
            resultLine.append(dozens[(resultValue / 10) - 2]);
            resultValue %= 10;
        }

        return recordingOneDigitNumber(resultValue, resultLine);
    }

    public StringBuilder recordingOneDigitNumber(int resultValue, StringBuilder resultLine) {
        return resultValue != 0 ? new StringBuilder(resultLine.append(StringUtils.SPACE)
                        .append(oneDigitsNumber.getNumber(resultValue)).toString().trim()) : resultLine;
    }
}
