package view;
import javax.swing.*;
import java.io.*;

public class RuleFrame extends JFrame {
    private JTextArea textArea;

    public RuleFrame(File file) throws IOException {
        setTitle(file.getName());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 将 JTextArea 设置为不可编辑的，以便只用于显示文本内容
        textArea.setEditable(false);

        // 读取文档内容并插入到 JTextArea 中
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            textArea.append(line + "\n");
        }
        reader.close();

        // 将 JScrollPane 添加到 JFrame 中
        add(scrollPane);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("DOUSHOUQI\\resource\\rule.txt");
        new RuleFrame(file);
    }
}
