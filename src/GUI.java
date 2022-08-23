import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JPanel
{
    private JButton setTimer;
    private JButton startTimer;
    private JButton stopTimer;
    private JButton sendEmail;
    private JLabel background;
    private JLabel title;
    private JLabel intervalLabel;
    private JLabel timerLabel;
    private int interval;
    private int timeLeft;
    private int secondsRanInTimer;
    private boolean isStopTimer;
    private Manager m;
    private Timer timer;

    public GUI()
    {
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                secondsRanInTimer++;
                timeLeft = interval - secondsRanInTimer;
                timerLabel.setText("Time Left: " + timeLeft);

                if (timeLeft == 0) {
                    timer.stop();
                    timer.restart();
                    secondsRanInTimer = 0;
                    System.out.println("Notification fired");
                    m.fireNotification();
                }
            }

        });
        m = new Manager();
        title = new JLabel("AstroPing");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(302, 6, 207, 110);
        this.add(title);

        intervalLabel = new JLabel("Interval: ");
        intervalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        intervalLabel.setForeground(Color.WHITE);
        intervalLabel.setBounds(272, 144, 200, 36);
        this.add(intervalLabel);

        timerLabel = new JLabel("Time Left: ");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBounds(272, 195, 200, 36);
        this.add(timerLabel);

        setTimer = new JButton(new ImageIcon("src/photo/Set Interval.png"));
        setTimer.setBounds(553, 213, 150, 50);
        setTimer.setFocusable(false);
        setTimer.setContentAreaFilled(false);
        setTimer.setFocusPainted(false);
        setTimer.setBorderPainted(false);
        setTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                while(interval <= 10)
                {
                    String secondsStr = JOptionPane.showInputDialog ("Enter a timer interval greater than 10: ");
                    interval = Integer.parseInt(secondsStr);
                }
                intervalLabel.setText("Interval: " + interval);
            }});
        this.add(setTimer);

        startTimer = new JButton(new ImageIcon("src/photo/Start Timer.png"));
        startTimer.setBounds(325, 369, 150, 50);
        startTimer.setFocusable(false);
        startTimer.setContentAreaFilled(false);
        startTimer.setFocusPainted(false);
        startTimer.setBorderPainted(false);
        startTimer.setVisible(true);
        startTimer.setVisible(true);
        startTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (interval > 10) {
                    startTimer.setVisible(false);
                    startTimer.setEnabled(false);
                    stopTimer.setVisible(true);
                    stopTimer.setEnabled(true);
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Interval is too low. Pls set a higher interval");
                }
            }});
        this.add(startTimer);

        stopTimer = new JButton(new ImageIcon("src/photo/Stop_Timer.png"));
        stopTimer.setBounds(325, 369, 150, 50);
        stopTimer.setFocusable(false);
        stopTimer.setContentAreaFilled(false);
        stopTimer.setFocusPainted(false);
        stopTimer.setBorderPainted(false);
        stopTimer.setVisible(false);
        stopTimer.setEnabled(false);
        stopTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer.setVisible(true);
                startTimer.setEnabled(true);
                stopTimer.setVisible(false);
                stopTimer.setEnabled(false);
                isStopTimer = true;
                timer.stop();
            }});
        this.add(stopTimer);

        sendEmail = new JButton(new ImageIcon("src/photo/Send Email.png"));
        sendEmail.setBounds(77, 212, 150, 50);
        sendEmail.setFocusable(false);
        sendEmail.setContentAreaFilled(false);
        sendEmail.setFocusPainted(false);
        sendEmail.setBorderPainted(false);
        sendEmail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipients = JOptionPane.showInputDialog("Enter your email or multiple emails (seperate using comma):");
                m.fireEmail(recipients);
            }});
        this.add(sendEmail);

        background = new JLabel(new ImageIcon("src/photo/Space Background.png"));
        background.setBounds(0, 0, 800, 600);
        background.setFocusable(false);
        this.add(background);

        this.setPreferredSize(new Dimension(800, 600));
        this.setBounds(0, 0, 800, 600);
        this.setLayout(null);


    }
}

/*
public void startTimer1() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis() + (10 * 1000);
        while (start < end) {
            //timerLabel.setText("Time Left: " + ((end - System.currentTimeMillis()) / 1000));
            if (System.currentTimeMillis() > end) {
                start = System.currentTimeMillis();
                end = System.currentTimeMillis() + (10 * 1000);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Test");
            }
        }
    }
 */