import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProgressBarGUI {

    private JFrame frame;
    private JPanel panel;
    private JProgressBar progressBar;
    private ProgressUpdater progressUpdater;

    public ProgressBarGUI(ProgressUpdater updater) {
        this.progressUpdater = updater;
        frame = new JFrame("Progress Bar Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> simulateLongTask());

        panel.add(progressBar);
        panel.add(startButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void simulateLongTask() {
        ActionListener task = new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                progressBar.setValue(progressUpdater.getProgress());
                if (progressUpdater.getProgress() >= 100) {
                    ((Timer) e.getSource()).stop();
                }
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        };

        new Timer(10, task).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgressBarGUI(new DummyProgressUpdater()));
    }
}

interface ProgressUpdater {
    int getProgress();
}

class DummyProgressUpdater implements ProgressUpdater {
    private int progress = 0;

    public DummyProgressUpdater() {
        new Thread(() -> {
            while (progress < 100) {
                progress++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int getProgress() {
        return progress;
    }
}