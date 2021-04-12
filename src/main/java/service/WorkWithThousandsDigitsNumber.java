package service;

import org.apache.commons.lang3.StringUtils;
import res.GetDegree;

public class WorkWithThousandsDigitsNumber implements Number {

    private static final String ENDING_A = "а";
    private static final String ENDING_E = "и";
    private static boolean flagForOneAndTwo = false;
    private static GetDegree getDegree = GetDegree.GET_DEGREE;
    private WorkWithHundredsDigitsNumber withHundredsNumber = new WorkWithHundredsDigitsNumber();

    @Override
    public String getNumber(String line) {
        StringBuilder resultLine = new StringBuilder();

        resultLine = division(line, getValueDozensDigitsNumber(line), getValue(line), resultLine);

        if (!StringUtils.EMPTY.equals(withHundredsNumber.getNumber(getValue(line))) && (getValue(line) >= 1000)) {
            resultLine.append(StringUtils.SPACE);
        }

        resultLine.append(withHundredsNumber.getNumber(getValue(line)));

        return resultLine.toString();
    }

    @Override
    public String getNumber(long number) {
        return getNumber(Long.toString(number));
    }

    public int getValueDozensDigitsNumber(String line) {
        return line.length() > 6 ? Integer.parseInt(line.substring(line.length() - 5).substring(0, 2), 10) : 0;
    }

    public int getValue(String line) {
        return StringUtils.EMPTY.equals(line) ? 0 : line.length() > 6 ?
                Integer.parseInt(line.substring(line.length() - 6), 10) : Integer.valueOf(line, 10);
    }

    public int getOneDigitsNumber(String line) {
        int oneDigitsNumbers = 0;

        if (getValue(line) >= 1000) {
            oneDigitsNumbers = Integer.parseInt(line.substring(line.length() - 4).substring(0, 1), 10);
        }

        return oneDigitsNumbers;
    }

    public StringBuilder division(String line, int dozensDigitsNumbers, int value, StringBuilder resultLine) {
        if (value >= 1000) {
            int oneDigitsNumbers;

            if (line.length() > 5) {
                dozensDigitsNumbers = Integer.parseInt(line.substring(line.length() - 5).substring(0, 2), 10);
                oneDigitsNumbers = Integer.parseInt(line.substring(line.length() - 4).substring(0, 1), 10);
            } else {
                if (line.length() > 4) {
                    dozensDigitsNumbers = Integer.parseInt(line.substring(line.length() - 5).substring(0, 2), 10);
                    oneDigitsNumbers = Integer.parseInt(line.substring(line.length() - 4).substring(0, 1), 10);
                } else {
                    oneDigitsNumbers = Integer.parseInt(line.substring(line.length() - 4).substring(0, 1), 10);
                }
            }

            return divisionForEnding(oneDigitsNumbers, dozensDigitsNumbers, value, resultLine);
        }

        return new StringBuilder();
    }

    public StringBuilder divisionForEnding(int valueSinglesDigitsNumbers, int valueDozensDigitsNumbers, int value, StringBuilder resultLine) {
        if ((valueSinglesDigitsNumbers == 1 || valueSinglesDigitsNumbers == 2) &&
                (valueDozensDigitsNumbers != 11) && (valueDozensDigitsNumbers != 12)) {
            flagForOneAndTwo = true;
            resultLine.append(withHundredsNumber.getNumber(value / 1000));
            flagForOneAndTwo = false;
        } else {
            resultLine.append(withHundredsNumber.getNumber(value / 1000));
            flagForOneAndTwo = false;
        }

        resultLine.append(StringUtils.SPACE)
                .append(getDegree.getDegreeOfNumber(3), 0, getDegree.getDegreeOfNumber(3).length() - 1);

        return setEnding(valueSinglesDigitsNumbers, valueDozensDigitsNumbers, resultLine);
    }

    public StringBuilder setEnding(int oneDigitsNumbers, int dozensDigitsNumbers, StringBuilder resultLine) {
        return ((oneDigitsNumbers == 2) || oneDigitsNumbers == 3 || oneDigitsNumbers == 4)
                && (dozensDigitsNumbers != 12) && (dozensDigitsNumbers != 13) && (dozensDigitsNumbers != 14) ?
                resultLine.append(ENDING_E) : (oneDigitsNumbers == 1) && (dozensDigitsNumbers != 11) ?
                resultLine.append(ENDING_A) : resultLine;
    }

    public boolean getFlag() {
        return flagForOneAndTwo;
    }
}
