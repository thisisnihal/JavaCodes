package guiproj;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class MiniIDE extends JFrame {

    private JTextArea codeArea = new JTextArea();
    private JTextArea outputArea = new JTextArea();
    private JFileChooser fileChooser = new JFileChooser();
    private File currentFile = null;

    public MiniIDE() {
        setTitle("Mini IDE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);

        add(new JScrollPane(codeArea), BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        outputArea.setPreferredSize(new Dimension(800, 150));

        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(e -> {
            codeArea.setText("");
            currentFile = null;
        });
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveFile());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        // Run menu
        JMenu runMenu = new JMenu("Run");
        JMenuItem runItem = new JMenuItem("Compile & Run");
        runItem.addActionListener(e -> compileAndRun());

        runMenu.add(runItem);

        // Config menu
        JMenu configMenu = new JMenu("Config");
        JMenuItem fontSizeItem = new JMenuItem("Font Size");
        fontSizeItem.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Font size:", 16);
            try {
                int size = Integer.parseInt(input);
                codeArea.setFont(new Font("Monospaced", Font.PLAIN, size));
            } catch (Exception ignored) {}
        });

        JMenuItem themeItem = new JMenuItem("Toggle Theme");
        themeItem.addActionListener(e -> toggleTheme());

        configMenu.add(fontSizeItem);
        configMenu.add(themeItem);

        bar.add(fileMenu);
        bar.add(runMenu);
        bar.add(configMenu);
        return bar;
    }

    private void openFile() {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                codeArea.setText("");
                String line;
                while ((line = reader.readLine()) != null)
                    codeArea.append(line + "\n");
            } catch (IOException ex) {
                showError("Error reading file");
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                if (!currentFile.getName().endsWith(".java")) {
                    currentFile = new File(currentFile.getAbsolutePath() + ".java");
                }
            } else {
                return;
            }
        }

        try (FileWriter writer = new FileWriter(currentFile)) {
            writer.write(codeArea.getText());
        } catch (IOException ex) {
            showError("Error saving file");
        }
    }

    private void compileAndRun() {
        if (currentFile == null) {
            showError("Save the file first before running.");
            return;
        }

        saveFile(); // save current state

        outputArea.setText("Compiling...\n");

        try {
            String fileName = currentFile.getName();
            String className = fileName.substring(0, fileName.lastIndexOf("."));

            Process compile = Runtime.getRuntime().exec("javac " + currentFile.getAbsolutePath());
            compile.waitFor();

            if (compile.exitValue() != 0) {
                BufferedReader err = new BufferedReader(new InputStreamReader(compile.getErrorStream()));
                outputArea.setText("Compilation Failed:\n");
                String line;
                while ((line = err.readLine()) != null) {
                    outputArea.append(line + "\n");
                }
                return;
            }

            outputArea.setText("Running...\n");

            Process run = Runtime.getRuntime().exec("java -cp " + currentFile.getParent() + " " + className);
            BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(run.getErrorStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                outputArea.append(line + "\n");
            }
            while ((line = err.readLine()) != null) {
                outputArea.append(line + "\n");
            }

        } catch (Exception ex) {
            outputArea.setText("Error running program: " + ex.getMessage());
        }
    }

    private void toggleTheme() {
        Color bg = codeArea.getBackground();
        if (bg.equals(Color.WHITE)) {
            codeArea.setBackground(new Color(30, 30, 30));
            codeArea.setForeground(Color.WHITE);
        } else {
            codeArea.setBackground(Color.WHITE);
            codeArea.setForeground(Color.BLACK);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("hellooo " + n * n);
        SwingUtilities.invokeLater(MiniIDE::new);
    }
}
