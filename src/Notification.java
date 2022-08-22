import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notification {
    public void displayTray(String mainMessage, String details, boolean isImage, String url) {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        if (isImage) {
            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                    }
                    catch (java.io.IOException f) {
                        System.out.println(f.getMessage());
                    }
                }
            });
        }
        //trayIcon.setToolTip("System tray icon demo");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        trayIcon.displayMessage(mainMessage, details, TrayIcon.MessageType.INFO);
    }
}
