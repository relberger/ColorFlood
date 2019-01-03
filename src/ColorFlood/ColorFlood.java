package ColorFlood;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ColorFlood extends JFrame {

    private JPanel panel;
    private JPanel timerPanel;
    private Board board;
    private JPanel controlsPanel;
    private Countdown gameTimer;
    private JLabel clock;

    private MouseListener firstClickListener;

    private JButton buttonRed;
    private JButton buttonCyan;
    private JButton buttonYellow;
    private JButton buttonGreen;
    private JButton buttonBlue;
    private JButton buttonMagenta;
    private ArrayList<JButton> colorButtons;

    public ColorFlood() {
        initializeGamePanel();

        setUpTimerPanel();
        setUpBoardPanel();
        setUpControlPanel();

        panel.add(timerPanel, BorderLayout.NORTH);
        panel.add(board, BorderLayout.CENTER);
        panel.add(controlsPanel, BorderLayout.SOUTH);

        add(panel);

        gameTimer.runTimer();
    }

    private void initializeGamePanel() {
        panel = new JPanel();
        setTitle("Color Flood");
        setSize(Properties.MAIN_PANEL_WIDTH, Properties.MAIN_PANEL_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel.setLayout(new BorderLayout());
        panel.setBackground(Properties.BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void setUpTimerPanel() {
        timerPanel = new JPanel();
        timerPanel.setPreferredSize(Properties.TIMER_PANEL_SIZE);
        timerPanel.setBackground(Properties.BACKGROUND_COLOR);
        timerPanel.setBorder(new EmptyBorder(10, 0, 50, 0));

        gameTimer = new Countdown();
        String time = gameTimer.getRemainingTimeString();
        clock = new JLabel(time);

        clock.setForeground(Color.white);
        clock.setFont(new Font("clock", Font.BOLD, 30));
        timerPanel.add(clock);
    }

    private void setUpBoardPanel() {
        String difficulty = setDifficultyQuery();
        board = new BoardBuilder(difficulty).getBoard();

        addFirstClickListeners();
    }

    private String setDifficultyQuery() {
        String userInput = (String) JOptionPane.showInputDialog(
                null,
                "Please select the level of difficulty for the game. " +
                        "\nIf you do not answer, the difficulty will be set for you. " +
                        "\n\nWhen you exit this window, you must select the starting cell. " +
                        "\nGood luck!",
                "Level Selection", JOptionPane.QUESTION_MESSAGE,
                null,
                Properties.DIFFICULTY, // Array of choices
                Properties.DIFFICULTY[0]); // Initial choice

        if ((userInput != null) && (userInput.length() > 0)) {
            return userInput;
        } else {
            return Properties.DIFFICULTY[2];
        }
    }

    private void addFirstClickListeners() {
        setUpFirstClickListener();

        for (Cell cellRow[] : board.gameBoard) {
            for (Cell cell : cellRow) {
                cell.addMouseListener(firstClickListener);
            }
        }
    }

    private void setUpFirstClickListener() {
        firstClickListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cell clickedCell = (Cell) e.getSource();

                int col = clickedCell.getCol();
                int row = clickedCell.getRow();

                board.activateFirstCell(row, col);

                removeFirstClickListeners();
                toggleColorControlButtons(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    private void removeFirstClickListeners() {
        for (Cell cellRow[] : board.gameBoard) {
            for (Cell cell : cellRow) {
                cell.removeMouseListener(firstClickListener);
            }
        }
    }

    private void setUpControlPanel() {
        initializeControlPanel();

        setUpControlColorButtons();
    }

    private void initializeControlPanel() {
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(1, 0));
        controlsPanel.setBackground(Properties.BACKGROUND_COLOR);
        controlsPanel.setPreferredSize(Properties.COLOR_BUTTON_SIZE);

        controlsPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
    }

    private void setUpControlColorButtons() {
        setUpColorButtonsList();

        int colorIndex = 0;
        for (JButton button : colorButtons) {

            button.setPreferredSize(Properties.COLOR_BUTTON_SIZE);
            button.setIcon(Properties.createImageIcon(
                    Properties.COLORS[colorIndex],
                    Properties.COLOR_BUTTON_WIDTH ,
                    Properties.COLOR_BUTTON_HEIGHT));

            controlsPanel.add(button);
            colorIndex++;
        }

        addColorControlButtonsListeners();

        toggleColorControlButtons(false);
    }

    private void setUpColorButtonsList() {
        buttonRed = new JButton();
        buttonCyan = new JButton();
        buttonYellow = new JButton();
        buttonGreen = new JButton();
        buttonBlue = new JButton();
        buttonMagenta = new JButton();

        colorButtons = new ArrayList<>();
        colorButtons.add(buttonRed);
        colorButtons.add(buttonCyan);
        colorButtons.add(buttonYellow);
        colorButtons.add(buttonGreen);
        colorButtons.add(buttonBlue);
        colorButtons.add(buttonMagenta);
    }

    private void addColorControlButtonsListeners() {
        buttonRed.addActionListener(actionEvent -> redButtonClicked());
        buttonCyan.addActionListener(actionEvent -> cyanButtonClicked());
        buttonYellow.addActionListener(actionEvent -> yellowButtonClicked());
        buttonGreen.addActionListener(actionEvent -> greenButtonClicked());
        buttonBlue.addActionListener(actionEvent -> blueButtonClicked());
        buttonMagenta.addActionListener(actionEvent -> magentaButtonClicked());
    }

    private void redButtonClicked() {
        board.setSelectedColor(Properties.RED);
        checkGameWon();
    }

    private void cyanButtonClicked() {
        board.setSelectedColor(Properties.CYAN);
        checkGameWon();
    }

    private void yellowButtonClicked() {
        board.setSelectedColor(Properties.YELLOW);
        checkGameWon();
    }

    private void greenButtonClicked() {
        board.setSelectedColor(Properties.GREEN);
        checkGameWon();
    }

    private void blueButtonClicked() {
        board.setSelectedColor(Properties.BLUE);
        checkGameWon();
    }

    private void magentaButtonClicked() {
        board.setSelectedColor(Properties.MAGENTA);
        checkGameWon();
    }

    private void toggleColorControlButtons(Boolean clickable) {
        for (JButton button : colorButtons) {
            button.setEnabled(clickable);
        }
    }

    public class Countdown {
        private final int INITIAL_TIME = 60_000;
        private int remainingTime;
        private java.util.Timer timer;

        Countdown() {
            this.timer = new Timer();
            remainingTime = INITIAL_TIME;
        }

        void runTimer() {
            TimerTask decrement = new TimerTask() {
                @Override
                public void run() {
                    if (remainingTime >= 0) {
                        clock.setText(getRemainingTimeString());
                        remainingTime = remainingTime - 1000;
                        board.setTime(remainingTime);
                        checkTimesUp();
                    } else {
                        timer.cancel();
                        checkTimesUp();
                    }
                }
            };
            timer.schedule(decrement, 50, 1000);
        }

        String getRemainingTimeString() {
            int min = remainingTime / 60_000;
            int sec = remainingTime % 60_000 / 1000;
            return String.format("%02d:%02d", min, sec);
        }
    }

    private void checkGameWon() {
        if ((board.GAME_COLUMNS * board.GAME_ROWS) == board.getActiveCells()) {
            gameWonDialogue();
        }
    }

    private void checkTimesUp() {
        if (board.getTime() < 0) {
            timesUpDialogue();
        }
    }

    private void gameWonDialogue() {
        gameTimer.timer.cancel();
        int userAnswer;

        userAnswer = JOptionPane.showConfirmDialog(null,
                "Congratz. Would you like to play again?",
                "I'm drowning in color!",
                JOptionPane.YES_NO_OPTION);

        if (userAnswer == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Thank you for playing!");
            System.exit(0);
        } else if (userAnswer == JOptionPane.YES_OPTION) {
            resetGame();
        }
    }

    private void timesUpDialogue() {
        int userAnswer;

        userAnswer = JOptionPane.showConfirmDialog(null,
                "Would you like to try again?",
                "Time is not your friend.",
                JOptionPane.YES_NO_OPTION);

        if (userAnswer == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Thanks for trying!");
            System.exit(0);
        } else if (userAnswer == JOptionPane.YES_OPTION) {
            resetGame();
        }
    }

    private void resetGame() {
        this.dispose();
        new ColorFlood().setVisible(true);
    }

    public static void main(String[] args) {
        new ColorFlood().setVisible(true);
    }
}