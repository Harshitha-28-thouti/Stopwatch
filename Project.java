import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startButton, stopButton, pauseButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0; // milliseconds
    private boolean running = false;
    private boolean paused = false;

    public Project() {
        setTitle("Stopwatch Project");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Time display label
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        timeLabel.setForeground(Color.BLACK);
        add(timeLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.setBackground(new Color(76, 175, 80)); // green
        startButton.setForeground(Color.WHITE);
        pauseButton.setBackground(new Color(255, 193, 7)); // yellow
        pauseButton.setForeground(Color.BLACK);
        stopButton.setBackground(new Color(244, 67, 54)); // red
        stopButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(96, 125, 139)); // gray-blue
        resetButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        startButton.setFont(buttonFont);
        pauseButton.setFont(buttonFont);
        stopButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Timer updates every second (1000 ms)
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                int hours = elapsedTime / 3600000;
                int minutes = (elapsedTime / 60000) % 60;
                int seconds = (elapsedTime / 1000) % 60;
                timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });

        // Button actions
        startButton.addActionListener(this);
        pauseButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!running) {
                timer.start();
                running = true;
                paused = false;
            }
        } else if (e.getSource() == pauseButton) {
            if (running && !paused) {
                timer.stop();
                paused = true;
            } else if (paused) {
                timer.start();
                paused = false;
            }
        } else if (e.getSource() == stopButton) {
            timer.stop();
            running = false;
            paused = false;
            elapsedTime = 0;
            timeLabel.setText("00:00:00");
        } else if (e.getSource() == resetButton) {
            timer.stop();
            elapsedTime = 0;
            running = false;
            paused = false;
            timeLabel.setText("00:00:00");
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}
