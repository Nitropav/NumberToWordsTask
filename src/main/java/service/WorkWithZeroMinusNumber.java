package service;

import org.apache.commons.lang3.StringUtils;

public class WorkWithZeroMinusNumber implements Number {

    private Number number = new WorkWithBigDigitsNumber(303);

    private static final String ZERO = "ноль";
    private static final String MINUS = "минус";
    private static final String MINUS_SIGN = "-";

    @Override
    public String getNumber(String line) {
        boolean flag = false;

        if (line.startsWith(MINUS_SIGN)) {
            flag = true;
            line = line.substring(1);
        }

        return StringUtils.EMPTY.equals(number.getNumber(line)) ? ZERO :
                (flag ? MINUS.concat(StringUtils.SPACE).concat(number.getNumber(line)) : number.getNumber(line));
    }

    @Override
    public String getNumber(long number) {
        return getNumber(String.valueOf(number));
    }
}
