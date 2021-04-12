import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import service.*;
import service.Number;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCase {

    private static final String FIVE = "пять";
    private static final String ZERO = "ноль";
    private static final String NEGATIVE_NUMBER = "минус двенадцать";
    private static final String NUMBER_TWENTY_THREE_CONVERTING = "двадцать три";
    private static final String NUMBER_453_CONVERTING = "четыреста пятьдесят три";
    private static final String NUMBER_10000000_CONVERTING = "десять миллионов";
    private static final String NUMBER_5320521_CONVERTING = "пять миллионов триста двадцать тысяч пятьсот двадцать один";
    private static final String NUMBER_20132_CONVERTING = "двадцать тысяч сто тридцать два";
    private static final String NUMBER_312544_CONVERTING = "триста двенадцать тысяч пятьсот сорок четыре";
    private static final String RESULT_DECLENSION_OF_NUMBER_ONE_THOUSAND = "одна тысяча";
    private static final String RESULT_DECLENSION_OF_NUMBER_TWO_THOUSAND = "две тысячи";
    private static final String RESULT_DECLENSION_OF_NUMBER_THREE_THOUSAND = "три тысячи";
    private static final String RESULT_DECLENSION_OF_NUMBER_FROM_FIVE_TO_NINE_THOUSAND = " тысяч";
    private static final String RESULT_DECLENSION_OF_NUMBER_FROM_TWO_TO_FOUR_MILLION = " миллиона";
    private static final String RESULT_DECLENSION_OF_NUMBER_ONE_MILLION = "один миллион";
    private static Number number;
    private static WorkWithThousandsDigitsNumber thousandsDigitsNumber;
    private static WorkWithBigDigitsNumber bigDigitsNumber;
    private static WorkWithOneDigitsNumber oneDigitsNumber;
    private static int randomNumberFrom1000Till1999 = (int) ((Math.random() * (1999 - 1000)) + 1000);
    private static int randomNumberFrom2000Till2999 = (int) ((Math.random() * (2999 - 2000)) + 2000);
    private static int randomNumberFrom3000Till3999 = (int) ((Math.random() * (3999 - 3000)) + 3000);
    private static int randomNumberFrom5000Till9999 = (int) ((Math.random() * (9999 - 5000)) + 5000);
    private static int randomNumberFrom1000000Till1999999 = (int) ((Math.random() * (1999999 - 1000000)) + 1000000);
    private static int randomNumberFrom2000000Till4999999 = (int) ((Math.random() * (4999999 - 2000000)) + 2000000);

    @BeforeEach
    void initialization() {
        number = new WorkWithZeroMinusNumber();
        thousandsDigitsNumber = new WorkWithThousandsDigitsNumber();
        bigDigitsNumber = new WorkWithBigDigitsNumber(6);
        oneDigitsNumber = new WorkWithOneDigitsNumber();
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_FIVE_CONVERTING)
    @Test
    void verifyNumberFiveConverting() {
        assertEquals(FIVE, number.getNumber(5));
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_ZERO_CONVERTING)
    @Test
    void verifyNumberZeroConverting() {
        assertEquals(ZERO, number.getNumber(0));
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_TWENTY_THREE_CONVERTING)
    @Test
    void verifyNumberTwentyThreeConverting() {
        assertEquals(NUMBER_TWENTY_THREE_CONVERTING, number.getNumber(23));
    }

    @DisplayName(TestCaseName.VERIFY_NEGATIVE_NUMBER)
    @Test
    void verifyNegativeNumber() {
        assertEquals(NEGATIVE_NUMBER, number.getNumber(-12));
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_453_CONVERTING)
    @Test
    void verifyNumber453Converting() {
        assertEquals(NUMBER_453_CONVERTING, number.getNumber(453));
    }


    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_1000_TILL_1999)
    @Test
    void verifyDeclensionOfNumbersFrom1000Till1999() {
        String line = String.valueOf(randomNumberFrom1000Till1999);
        assertEquals(RESULT_DECLENSION_OF_NUMBER_ONE_THOUSAND, thousandsDigitsNumber.division(line,
                thousandsDigitsNumber.getValueDozensDigitsNumber(line),
                thousandsDigitsNumber.getValue(line), new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_2000_TILL_2999)
    @Test
    void verifyDeclensionOfNumbersFrom2000Till2999() {
        String line = String.valueOf(randomNumberFrom2000Till2999);
        assertEquals(RESULT_DECLENSION_OF_NUMBER_TWO_THOUSAND, thousandsDigitsNumber.division(line,
                thousandsDigitsNumber.getValueDozensDigitsNumber(line),
                thousandsDigitsNumber.getValue(line), new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_3000_TILL_3999)
    @Test
    void verifyDeclensionOfNumbersFrom3000Till3999() {
        String line = String.valueOf(randomNumberFrom3000Till3999);
        assertEquals(RESULT_DECLENSION_OF_NUMBER_THREE_THOUSAND, thousandsDigitsNumber.division(line,
                thousandsDigitsNumber.getValueDozensDigitsNumber(line),
                thousandsDigitsNumber.getValue(line), new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_5000_TILL_9999)
    @Test
    void verifyDeclensionOfNumbersFrom5000Till9999() {
        String line = String.valueOf(randomNumberFrom5000Till9999);
        assertEquals(oneDigitsNumber.getChangedDeclension(
                thousandsDigitsNumber.getOneDigitsNumber(line) - 1) + RESULT_DECLENSION_OF_NUMBER_FROM_FIVE_TO_NINE_THOUSAND,
                thousandsDigitsNumber.division(line, thousandsDigitsNumber.getValueDozensDigitsNumber(line),
                        thousandsDigitsNumber.getValue(line), new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_20132_CONVERTING)
    @Test
    void verifyNumber20132Converting() {
        assertEquals(NUMBER_20132_CONVERTING, number.getNumber(20132));
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_312544_CONVERTING)
    @Test
    void verifyNumber312544Converting() {
        assertEquals(NUMBER_312544_CONVERTING, number.getNumber(312544));
    }

    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_1000000_TILL_1999999)
    @Test
    void verifyDeclensionOfNumbersFrom1000000Till1999999() {
        assertEquals(RESULT_DECLENSION_OF_NUMBER_ONE_MILLION,
                bigDigitsNumber.getDegreeOfNumber(String.valueOf(randomNumberFrom1000000Till1999999),
                        new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_DECLENSION_OF_NUMBERS_FROM_2000000_TILL_4999999)
    @Test
    void verifyDeclensionOfNumbersFrom2000000Till4999999() {
        String line = String.valueOf(randomNumberFrom2000000Till4999999);
        assertEquals(
                oneDigitsNumber.getChangedDeclension(
                        bigDigitsNumber.getOneDigitOfNumbers(line) - 1) + RESULT_DECLENSION_OF_NUMBER_FROM_TWO_TO_FOUR_MILLION,
                bigDigitsNumber.getDegreeOfNumber(line, new StringBuilder()).toString());
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_5320521_CONVERTING)
    @Test
    void verifyNumber5320521Converting() {
        assertEquals(NUMBER_5320521_CONVERTING, number.getNumber(5320521));
    }

    @DisplayName(TestCaseName.VERIFY_ZERO_NUMBER_IS_NOT_CONTAIN_MINUS_WORD)
    @Test
    void verifyZeroNumberIsNotContainMinusWord() {
        assertEquals(ZERO, number.getNumber(0));
    }

    @DisplayName(TestCaseName.VERIFY_NUMBER_10000000_CONVERTING)
    @Test
    void verifyNumber10000000Converting() {
        assertEquals(NUMBER_10000000_CONVERTING, number.getNumber(10000000));
    }
}
