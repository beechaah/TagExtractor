import java.io.*;
import java.util.*;

public class TagExtractorLogic
{
    public String processFiles(File textFile, File stopWordsFile)
    {
        try {
            Set<String> stopWords = loadStopWords(stopWordsFile);

            Map<String, Integer> tagFrequency = analyzeTextFile(textFile, stopWords);

            return formatTags(tagFrequency);

        } catch (IOException e)
        {
            e.printStackTrace();
            return "An error occurred during processing.";
        }
    }

    private Set<String> loadStopWords(File stopWordsFile) throws IOException
    {
        Set<String> stopWords = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(stopWordsFile)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                stopWords.add(line.trim().toLowerCase());
            }
        }
        return stopWords;
    }

    private Map<String, Integer> analyzeTextFile(File textFile, Set<String> stopWords) throws IOException
    {
        Map<String, Integer> tagFrequency = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textFile)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                for (String word : words)
                {
                    if (!stopWords.contains(word) && !word.isEmpty())
                    {
                        tagFrequency.put(word, tagFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        return tagFrequency;
    }

    private String formatTags(Map<String, Integer> tagFrequency)
    {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : tagFrequency.entrySet())
        {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }
}