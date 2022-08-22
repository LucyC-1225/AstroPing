import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JPanel
{
    private JButton setTimer;
    private JButton startTimer;
    private JButton sendEmail;
    private JLabel background;
    private JLabel title;
    private JLabel intervalLabel;
    private JLabel timerLabel;
    private int interval;
    private int timeLeft;
    private Manager m;

    public GUI()
    {
        m = new Manager();
        title = new JLabel("AstroPing");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(302, 6, 207, 110);
        this.add(title);

        intervalLabel = new JLabel("Interval: ");
        intervalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        intervalLabel.setForeground(Color.WHITE);
        intervalLabel.setBounds(272, 144, 134, 36);
        this.add(intervalLabel);

        timerLabel = new JLabel("Time Left: ");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBounds(272, 195, 134, 36);
        this.add(timerLabel);

        setTimer = new JButton(new ImageIcon("res/Set Interval.png"));
        setTimer.setBounds(553, 213, 150, 50);
        setTimer.setFocusable(false);
        setTimer.setContentAreaFilled(false);
        setTimer.setFocusPainted(false);
        setTimer.setBorderPainted(false);
        setTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                interval = Integer.parseInt(JOptionPane.showInputDialog("Enter the interval you want in seconds:"));
                intervalLabel.setText("Interval: " + interval);
            }});
        this.add(setTimer);

        startTimer = new JButton(new ImageIcon("res/Start Timer.png"));
        startTimer.setBounds(325, 369, 150, 50);
        startTimer.setFocusable(false);
        startTimer.setContentAreaFilled(false);
        startTimer.setFocusPainted(false);
        startTimer.setBorderPainted(false);
        startTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                m.fireNotification();
            }});
        this.add(startTimer);

        sendEmail = new JButton(new ImageIcon("res/Send Email.png"));
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

        background = new JLabel(new ImageIcon("res/Space Background.png"));
        background.setBounds(0, 0, 800, 600);
        background.setFocusable(false);
        this.add(background);

        this.setPreferredSize(new Dimension(800, 600));
        this.setBounds(0, 0, 800, 600);
        this.setLayout(null);
    }


}