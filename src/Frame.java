import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame
{
    private GUI gui;

    public static Frame frame;
    public Frame()
    {
        frame = this;
        gui = new GUI();
        this.setSize(800, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(gui);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}