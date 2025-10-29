package guiproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class SudokuGame extends JFrame {
    private JTextField[][] cells = new JTextField[9][9];
    private int[][] puzzle = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},

        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},

        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    private JLabel timerLabel = new JLabel("Time: 0s");
    private int secondsElapsed = 0;
    private Timer timer;

    public SudokuGame() {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout());

        add(createMenuBar(), BorderLayout.NORTH);
        add(createBoardPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        startTimer();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createBoardPanel() {
        JPanel board = new JPanel(new GridLayout(9, 9));

        Font font = new Font("Arial", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);
                cells[row][col] = cell;

                int value = puzzle[row][col];
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setText("");
                }

                board.add(cell);
            }
        }

        return board;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> resetGame());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(newGameItem);
        gameMenu.add(exitItem);
        menuBar.add(gameMenu);

        return menuBar;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(timerLabel);
        return panel;
    }

    private void startTimer() {
        secondsElapsed = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                secondsElapsed++;
                SwingUtilities.invokeLater(() -> timerLabel.setText("Time: " + secondsElapsed + "s"));
            }
        }, 1000, 1000);
    }

    private void resetGame() {
        timer.cancel();
        // Reload original puzzle
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                int value = puzzle[row][col];
                JTextField cell = cells[row][col];
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setBackground(Color.WHITE);
                }
            }
        startTimer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}
