package service;

import org.apache.commons.lang3.StringUtils;
import res.GetDegree;

public class WorkWithBigDigitsNumber implements Number {

    private static final String ENDING_A = "а";
    private static final String ENDING_OV = "ов";
    private static WorkWithThousandsDigitsNumber withThousandsDigitsNumber = new WorkWithThousandsDigitsNumber();
    private static final GetDegree DEGREE = GetDegree.GET_DEGREE;
    private int degreeOfNumber;

    public WorkWithBigDigitsNumber(int degreeOfNumber) {
        this.degreeOfNumber = degreeOfNumber;
    }

    @Override
    public String getNumber(String line) {
        StringBuilder resultLine = new StringBuilder();
        String numberOfLine = setNumber().getNumber(getDigits(line));
        resultLine = getPartOfNumberMoreThanMillion(line, resultLine, numberOfLine);

        if (!StringUtils.EMPTY.equals(numberOfLine)) {
            resultLine.append(numberOfLine);
        }

        return resultLine.toString();
    }

    @Override
    public String getNumber(long number) {
        return getNumber(String.valueOf(number));
    }

    public StringBuilder getPartOfNumberMoreThanMillion(String line, StringBuilder resultLine, String numberLine){
        if (!StringUtils.EMPTY.equals(withThousandsDigitsNumber.getNumber(getDigitsDegree(line)))) {
            resultLine = getDegreeOfNumber(line, resultLine);

            if (!StringUtils.EMPTY.equals(numberLine)) {
                resultLine.append(StringUtils.SPACE);
            }
        }

        return resultLine;
    }

    public Number setNumber() {
        return degreeOfNumber <= 6 ? withThousandsDigitsNumber : new WorkWithBigDigitsNumber(degreeOfNumber - 3);
    }

    public String getDigitsDegree(String line) {
        return line.length() < degreeOfNumber ? StringUtils.EMPTY : line.substring(0, line.length() - degreeOfNumber);
    }

    public String getDigits(String line) {
        return line.length() < degreeOfNumber ? line : line.substring(line.length() - degreeOfNumber);
    }

    public int getDozensDigitsNumbers(String line) {
        return getDigitsDegree(line).length() >= 2 ?
                Integer.parseInt(getDigitsDegree(line).substring(getDigitsDegree(line).length() - 2).substring(0, 2), 10) : 0;
    }

    public int getOneDigitOfNumbers(String line) {
        return Integer.parseInt(getDigitsDegree(line)
                .substring(getDigitsDegree(line).length() - 1).substring(0, 1), 10);
    }

    public StringBuilder getDegreeOfNumber(String line, StringBuilder resultLine) {
        resultLine.append(withThousandsDigitsNumber.getNumber(getDigitsDegree(line)))
                .append(StringUtils.SPACE).append(DEGREE.getDegreeOfNumber(degreeOfNumber));

        return setEnding(getDozensDigitsNumbers(line), getOneDigitOfNumbers(line), resultLine);
    }

    public StringBuilder setEnding(int dozensDigitsOfNumbers, int oneDigitOfNumbers, StringBuilder resultLine) {
        return (oneDigitOfNumbers == 2 || oneDigitOfNumbers == 3 || oneDigitOfNumbers == 4) &&
                dozensDigitsOfNumbers != 12 && dozensDigitsOfNumbers != 13 && dozensDigitsOfNumbers != 14 ?
                resultLine.append(ENDING_A) : oneDigitOfNumbers == 1 && dozensDigitsOfNumbers != 11 ?
                resultLine.append(StringUtils.EMPTY) : resultLine.append(ENDING_OV);
    }
}
