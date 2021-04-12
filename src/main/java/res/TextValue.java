package res;

public class TextValue {

    private int degree;
    private String[] name;

    public TextValue(int degree, String... name) {
        this.degree = degree;
        this.name = name;
    }

    public int getDegree() {
        return degree;
    }

    public String getName(int number) {
        return name[number];
    }
}
