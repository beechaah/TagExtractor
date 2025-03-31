import javax.swing.*;

public class TagExtractorApp
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            TagExtractorGUI gui = new TagExtractorGUI();
            gui.createAndShowGUI();
        });
    }
}