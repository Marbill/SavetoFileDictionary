package dictionary;

public class Main {

    public static void main(String[] args) {
        MindfulDictionary dict = new MindfulDictionary("src/words.txt");
        dict.load();

        dict.add("tietokone", "computer");

        System.out.println(dict.translate("tietokone"));

        dict.save();

    }
}
