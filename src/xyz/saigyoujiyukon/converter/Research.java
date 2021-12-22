package xyz.saigyoujiyukon.converter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.TextArea.SCROLLBARS_NONE;

public class Research {
    public static void main(String[] args) {
        new ResearchFrame();
    }
}
class ResearchFrame extends Frame{
    public ResearchFrame() {
        setTitle("科研过滤器转换");
        setSize(1270, 720);
        setVisible(true);
//        setLayout(new BorderLayout(1, 2));
        Button button = new Button("转换");

        Panel topPanel = new Panel();
        add(topPanel,BorderLayout.NORTH);
        topPanel.setSize(200,100);

        TextArea inputText = new TextArea();
        TextArea outputText = new TextArea();
        Label label = new Label("====>");
        Panel panel = new Panel();
        Panel textPanel1 = new Panel();
        Panel textPanel2 = new Panel();

        add(panel);
        panel.setLayout(new FlowLayout());
        textPanel1.add(inputText);
        panel.add(label);
        textPanel2.add(outputText);
        add(button,BorderLayout.SOUTH);

        //退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
