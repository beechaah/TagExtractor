import java.io.*;
import java.util.Map;

public class FileSaver
{
    public void saveTagsToFile(Map<String, Integer> tagFrequency, File outputFile)
    {
        try (PrintWriter writer = new PrintWriter(outputFile))
        {
            for (Map.Entry<String, Integer> entry : tagFrequency.entrySet())
            {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}