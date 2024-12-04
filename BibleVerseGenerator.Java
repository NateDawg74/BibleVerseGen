package Main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BibleVerseGenerator {
    private final List<BibleVerse> verses;

    public BibleVerseGenerator(String filePath) {
        this.verses = loadVerses(filePath);
    }

    private List<BibleVerse> loadVerses(String filePath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            return new Gson().fromJson(jsonContent, new TypeToken<List<BibleVerse>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Error loading verses: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String getRandomVerse() {
        if (verses.isEmpty()) {
            return "No verses available.";
        }
        Random random = new Random();
        BibleVerse verse = verses.get(random.nextInt(verses.size()));
        return String.format("%s %d:%d - \"%s\"", verse.getBook(), verse.getChapter(), verse.getVerse(), verse.getText());
    }

    public List<String> searchVerses(String keyword) {
        List<String> results = new ArrayList<>();
        for (BibleVerse verse : verses) {
            if (verse.getText().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(String.format("%s %d:%d - \"%s\"", verse.getBook(), verse.getChapter(), verse.getVerse(), verse.getText()));
            }
        }
        return results;
    }
}
