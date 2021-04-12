import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.Number;
import service.WorkWithOneDigitsNumber;
import service.WorkWithZeroMinusNumber;
import res.GetDegree;

import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberToWordsTest {

    private static final Logger LOGGER = Logger.getLogger(NumberToWordsTest.class.getName());
    private static final String RESULT_NEGATIVE_NUMBER = "минус пять";
    private static final String ZERO = "ноль";
    private static final GetDegree DEGREE = GetDegree.GET_DEGREE;
    private static final String MILLION = "миллион";
    private static Number number;
    private static WorkWithOneDigitsNumber workWithOneDigitsNumber;

    private static Stream<Arguments> nameDozensDataProvider() {
        return Stream.of(Arguments.arguments("девяносто восемь", 98),
                Arguments.arguments("сорок четыре", 44),
                Arguments.arguments("двадцать два", 22),
                Arguments.arguments("пятьдесят пять", 55),
                Arguments.arguments("шестьдесят шесть", 66),
                Arguments.arguments("семьдесят семь", 77),
                Arguments.arguments("восемьдесят восемь", 88),
                Arguments.arguments("девяносто девять", 99));
    }

    private static Stream<Arguments> indexOneDigitOfNumberDataProvider() {
        return Stream.of(Arguments.arguments(4, 5, -1),
                Arguments.arguments(3, 4, -1),
                Arguments.arguments(16, 17, -1),
                Arguments.arguments(14, 15, -1));
    }

    private static Stream<Arguments> dividedNumberDataProvider() {
        return Stream.of(Arguments.arguments(23, "123"),
                Arguments.arguments(45, "345"));
    }

    private static Stream<Arguments> dividedNumberForNumberMoreThousandDataProvider() {
        return Stream.of(Arguments.arguments(23, "1123"),
                Arguments.arguments(45, "5345"));
    }

    private static Stream<Arguments> indexTwoDigitOfNumberDataProvider() {
        return Stream.of(Arguments.arguments(16, 17, -1),
                Arguments.arguments(14, 15, -1));
    }

    private static Stream<Arguments> nameOneDigitDataProvider() {
        return Stream.of(Arguments.arguments("пять", 4),
                Arguments.arguments("четыре", 3));
    }

    private static Stream<Arguments> nameTwoDigitDataProvider() {
        return Stream.of( Arguments.arguments("семнадцать", 16),
                Arguments.arguments("пятнадцать", 14));
    }

    @BeforeEach
    void initialization() {
        number = new WorkWithZeroMinusNumber();
        workWithOneDigitsNumber = new WorkWithOneDigitsNumber();
    }

    @ParameterizedTest
    @MethodSource("nameDozensDataProvider")
    void nameDozens(String expected, int numberResult) {
        LOGGER.info(numberResult + " = " + number.getNumber(numberResult));
        assertEquals(expected, number.getNumber(numberResult));
    }

    @Test
    void zero() {
        int zero = 0;
        LOGGER.info(zero + " = " + number.getNumber(zero));
        assertEquals(ZERO, number.getNumber(zero));
    }

    @Test
    void negativeNumber() {
        int negativeNumber = -5;
        LOGGER.info(negativeNumber + " = " + number.getNumber(negativeNumber));
        assertEquals(RESULT_NEGATIVE_NUMBER, number.getNumber(negativeNumber));
    }

    @Test
    void degreeMillion() {
        assertEquals(MILLION, DEGREE.getDegreeOfNumber(6));
    }

    @ParameterizedTest
    @MethodSource("indexOneDigitOfNumberDataProvider")
    void indexOneDigitOfNumber(int expected, int resultValue, int index) {
        LOGGER.info(String.valueOf(resultValue));
        assertEquals(expected, workWithOneDigitsNumber.getIndexOfNumber(resultValue, index));
    }

    @ParameterizedTest
    @MethodSource("nameOneDigitDataProvider")
    void nameOneDigit(String expected, int index) {
        assertEquals(expected, workWithOneDigitsNumber.getChangedDeclension(index).toString());
    }

    @ParameterizedTest
    @MethodSource("nameTwoDigitDataProvider")
    void nameTwoDigit(String expected, int index) {
        assertEquals(expected, workWithOneDigitsNumber.getChangedDeclension(index).toString());
    }

    @ParameterizedTest
    @MethodSource("indexTwoDigitOfNumberDataProvider")
    void indexTwoDigitOfNumber(int expected, int resultValue, int index) {
        assertEquals(expected, workWithOneDigitsNumber.getIndexOfNumber(resultValue, index));
    }

    @ParameterizedTest
    @MethodSource("dividedNumberDataProvider")
    void dividedNumber(int expected, String line) {
        assertEquals(expected, workWithOneDigitsNumber.getDividedNumber(line));
    }

    @ParameterizedTest
    @MethodSource("dividedNumberForNumberMoreThousandDataProvider")
    void dividedNumberForNumberMoreThousand(int expected, String line) {
        assertEquals(expected, workWithOneDigitsNumber.getDividedNumber(line));
    }
}
