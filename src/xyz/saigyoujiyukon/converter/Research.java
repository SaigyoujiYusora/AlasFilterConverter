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
//        TextArea inputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        inputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
//        TextArea outputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        outputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
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
//            String input = inputText.getText();
            //没写完↓
//            ConversionFormula.convert(inputText.getText());
            String output = ConversionFormula.convert(inputText.getText());
            outputText.setText(output);
        }
    }
}

class UnknownInput extends NumberFormatException{

}
class ConversionFormula{
//    String output = "";
    public static String convert(String input){
        StringBuilder output = new StringBuilder();
        String temp;//截取出来匹配用的字符串
        String inProcess = "";
        int tempNum = 0;
        while (true) {
            //处理初始字符串
//            input = input.replaceFirst("\t\r\n", "寄");
            if (input.contains("\t\r\n")) {
                temp = input.substring(0, input.indexOf("\t\r\n"));
                tempNum = 3;
            }else if (input.contains("\n")) {
                temp = input.substring(0, input.indexOf("\n"));
            }else{
                throw new UnknownInput();
            }
//            temp = input.substring(0, input.indexOf("寄"));
            //彩=DR，金=PRY，舰装=Q，资金=G，魔方=H，基础=C，心智=H
//            Research_Filter:
            if (temp.contains("舰装")){
//                temp = temp.substring(4,temp.indexOf("h"));
                inProcess = "Q" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
            }else if (temp.contains("定向")){
                if (temp.contains("彩")){
                    inProcess = "DR" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
                }else if (temp.contains("金")){
                    inProcess = "PRY" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
                }
            }else if (temp.contains("资金")){
                inProcess = "G" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
            } else if (temp.contains("魔方") && temp.contains("心智")) {
                inProcess = "H" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
            } else if (temp.contains("基础")) {
                inProcess = "C" + "-" + temp.substring(4, temp.indexOf("h")) + " > ";
            }

            output.append(inProcess);
            //删除已处理的字符串
            input = input.substring(temp.length() + tempNum);

            if (input.length() <= 1) {
                output.append("reset > shortest");
                break;
            }
        }
//        input = output;


        return output.toString();
    }
}