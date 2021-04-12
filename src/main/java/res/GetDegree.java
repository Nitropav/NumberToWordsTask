package res;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public enum GetDegree {

    GET_DEGREE;

    private static final Logger LOGGER = Logger.getLogger(GetDegree.class.getName());
    private static final String FILE_PATH = "src/main/resources/value/value.txt";
    private static TextValue[] valueTxts;

    static {
        try {
            valueTxts = getValueTxt();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getDegreeOfNumber(int degree) {
        for (TextValue textValue : valueTxts) {
            if (textValue.getDegree() == degree) {
                return textValue.getName(this.ordinal());
            }
        }

        return StringUtils.EMPTY;
    }

    private static TextValue[] getValueTxt() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(FILE_PATH), String.valueOf(StandardCharsets.UTF_8));
        ArrayList<Integer> listNumber = new ArrayList<Integer>();
        ArrayList<String> namesNumber = new ArrayList<String>();
        String[] separation;

        while (scanner.hasNext()) {
            separation = scanner.nextLine().split(StringUtils.SPACE);
            listNumber.add(Integer.parseInt(separation[0]));
            namesNumber.add(separation[1]);
        }

        TextValue[] valueTxts = new TextValue[listNumber.size()];

        for (int i = 0; i < valueTxts.length; i++) {
            valueTxts[i] = new TextValue(listNumber.get(i), namesNumber.get(i));
        }

        return valueTxts;
    }
}