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
        Font font = new Font("微软雅黑",Font.PLAIN,20);
//        setLayout(new BorderLayout(1, 2));
        Button button = new Button("Conversion");

        Panel topPanel = new Panel();
        Label topTitle = new Label("Research filter conversion");
        topTitle.setFont(font);
        button.setFont(font);
        add(topPanel,BorderLayout.NORTH);
        topPanel.setSize(20,30);
        topPanel.add(topTitle);
        topPanel.setBackground(new Color(45, 203, 239));

        Panel mainPanel = new Panel();
//        TextArea inputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        inputText = new TextArea("",13,33,TextArea.SCROLLBARS_NONE);
//        TextArea outputText = new TextArea("",30,70,TextArea.SCROLLBARS_NONE);
        outputText = new TextArea("",13,33,TextArea.SCROLLBARS_NONE);
        Panel inputTextPanel = new Panel();
        Panel outputTextPanel = new Panel();
        Panel downPanel = new Panel();

        inputText.setFont(font);
        outputText.setFont(font);

        add(mainPanel);
        mainPanel.setLayout(new GridLayout(1,2));
        inputTextPanel.add(inputText);
        outputTextPanel.add(outputText);
        mainPanel.add(inputTextPanel);
        mainPanel.add(outputTextPanel);
        add(downPanel,BorderLayout.SOUTH);
        downPanel.setBounds(400,400,50,50);
        downPanel.add(button);
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
        int tempNum;
        while (true) {
            //处理初始字符串
            if (input.contains("\t\r\n")) {
                temp = input.substring(0, input.indexOf("\t\r\n"));
                tempNum = 3;
            }else if (input.contains("\n")) {
                temp = input.substring(0, input.indexOf("\r\n"));
                tempNum = 2;
            }else{
                throw new UnknownInput();
            }
            //彩=DR，金=PRY，舰装=Q，资金=G，魔方=H，基础=C，心智=H
//            Research_Filter:
            System.out.println(temp);
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
