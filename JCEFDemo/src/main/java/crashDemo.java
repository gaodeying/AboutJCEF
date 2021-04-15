import javax.swing.*;
import java.awt.*;

public class crashDemo extends JFrame {
   public  crashDemo() {
       setTitle("点击可制造crash");
       setSize(400, 200);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container container = getContentPane();
       JButton btn = new JButton("来个Crash");
       btn.setSize(40, 40);
       btn.setLocation(30, 30);
       btn.setBackground(Color.BLUE);
       btn.addActionListener((e)->{
           btn.setBackground(Color.red);
           int i = 0;
           i = 5/0;
       });
       container.add(btn);
       setVisible(true);
   }
}
