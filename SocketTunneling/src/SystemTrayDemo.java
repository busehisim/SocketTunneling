import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

public class SystemTrayDemo {
	SystemTray tray;
	TrayIcon icon ;
  public SystemTrayDemo(String iconimage, String title) throws Exception {
    if (!SystemTray.isSupported()) {
      System.out.println("SystemTray is not supported");
      return;
    }

    String iconstring = "src/"+iconimage;
     tray = SystemTray.getSystemTray();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage(iconstring);
   
    
    PopupMenu menu = new PopupMenu();

    MenuItem messageItem = new MenuItem("About");
    messageItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aleyna Buse Hýþým 20160702072");
      }
    });
    menu.add(messageItem);

    MenuItem closeItem = new MenuItem("Exit");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(closeItem);
     icon = new TrayIcon(image, title, menu);
    icon.setImageAutoSize(true);

    tray.add(icon);
  }
  public void CloseTray()
  {
	  tray.remove(icon);
  }
}
