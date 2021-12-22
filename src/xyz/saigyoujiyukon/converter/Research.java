package xyz.saigyoujiyukon.converter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Research {
    public static void main(String[] args) {
        new ResearchFrame();
    }
}
class ResearchFrame extends Frame{

    TextArea inputText,outputText;

    public ResearchFrame() {
        setTitle("科研过滤器转换");
        setSize(1270, 720);
        setResizable(false);
//        setLayout(new BorderLayout(1, 2));
        Button button = new Button("转换");


        Panel topPanel = new Panel();
        Label topTitle = new Label("科研过滤器转换");
        add(topPanel,BorderLayout.NORTH);
        topPanel.setSize(20,30);
        topPanel.add(topTitle);
        topPanel.setBackground(Color.red);

        Panel mainPanel = new Panel();
        TextArea inputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        TextArea outputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        Label label = new Label("====>");
        Panel inputTextPanel = new Panel();
        Panel outputTextPanel = new Panel();
        Panel downPanel = new Panel();

        add(mainPanel);
        mainPanel.setLayout(new GridLayout(1,2));
//        panel.setLayout(new FlowLayout());
        inputTextPanel.add(inputText);
        outputTextPanel.add(outputText);
        mainPanel.add(inputTextPanel);
//        panel.add(label);
//        add(label,BorderLayout.CENTER);
        mainPanel.add(outputTextPanel);
        add(downPanel,BorderLayout.SOUTH);
        downPanel.setBounds(400,400,50,50);
        downPanel.add(button);
//        downPanel.setSize(20,90);
        button.addActionListener(new ConversionProcess());


        //显示窗口
        setVisible(true);
        //退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private class ConversionProcess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputText.getText();
            String output;
            output = input;
            outputText.setText(output);

        }
    }
}
