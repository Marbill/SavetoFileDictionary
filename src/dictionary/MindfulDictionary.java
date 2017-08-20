package dictionary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author marbi */
public class MindfulDictionary {

    private Map<String, String> finToEnglish;
    private Map<String, String> englishToFin;
    private File file;

    public MindfulDictionary() {
        this.englishToFin = new HashMap<String, String>();
        this.finToEnglish = new HashMap<String, String>();
    }

    public MindfulDictionary(String file) {
        this.file = new File(file);
        this.englishToFin = new HashMap<String, String>();
        this.finToEnglish = new HashMap<String, String>();

    }

    public boolean load() {
        Scanner reader = null;

        try {
            reader = new Scanner(file, "UTF-8");
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                this.add(parts[0], parts[1]);
            }
        } catch (Exception e) {
            return false;
        } finally {
            reader.close();
            return true;
        }
    }

    public boolean save() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            for (String key : this.finToEnglish.keySet()) {
                writer.write(key + ":" + this.finToEnglish.get(key) + '\n');
            }

            writer.close();

        } catch (IOException ex) {
            return false;
        } finally {
            return true;
        }
    }

    public void add(String word, String translation) {
        if (!this.finToEnglish.containsKey(word)) {
            this.finToEnglish.put(word, translation);
        }

        if (!this.englishToFin.containsKey(translation)) {
            this.englishToFin.put(translation, word);
        }
    }

    public String translate(String word) {
        if (this.finToEnglish.containsKey(word)) {
            return this.finToEnglish.get(word);
        } else if (this.englishToFin.containsKey(word)) {
            return this.englishToFin.get(word);
        }

        return null;
    }

    public void remove(String word) {
        if (this.finToEnglish.containsKey(word)) {
            String english = this.finToEnglish.get(word);
            this.englishToFin.remove(english);
            this.finToEnglish.remove(word);
        }

        if (this.englishToFin.containsKey(word)) {
            String fin = this.englishToFin.get(word);
            this.finToEnglish.remove(fin);
            this.englishToFin.remove(word);
        }
    }

}
