import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TagExtractorGUI
{
    private JFrame frame;
    private JTextArea textArea;
    private File selectedTextFile;
    private File selectedStopWordsFile;

    public void createAndShowGUI()
    {
        frame = new JFrame("Tag Extractor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton openTextFileButton = new JButton("Choose Text File");
        JButton openStopWordsFileButton = new JButton("Choose Stop Words File");
        JButton extractTagsButton = new JButton("Extract Tags");

        openTextFileButton.addActionListener(e -> chooseFile(true));
        openStopWordsFileButton.addActionListener(e -> chooseFile(false));
        extractTagsButton.addActionListener(e -> extractTags());

        JPanel panel = new JPanel();
        panel.add(openTextFileButton);
        panel.add(openStopWordsFileButton);
        panel.add(extractTagsButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void chooseFile(boolean isTextFile)
    {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            if (isTextFile)
            {
                selectedTextFile = file;
                textArea.append("Selected text file: " + file.getName() + "\n");
            } else
            {
                selectedStopWordsFile = file;
                textArea.append("Selected stop words file: " + file.getName() + "\n");
            }
        }
    }

    private void extractTags()
    {
        if (selectedTextFile == null || selectedStopWordsFile == null)
        {
            JOptionPane.showMessageDialog(frame, "Please select both text and stop words files.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TagExtractorLogic logic = new TagExtractorLogic();
        String result = logic.processFiles(selectedTextFile, selectedStopWordsFile);
        textArea.setText(result);
    }
}