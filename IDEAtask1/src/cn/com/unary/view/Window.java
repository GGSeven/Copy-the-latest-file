package cn.com.unary.view;


import cn.com.unary.controller.WindowsController;
import cn.com.unary.exception.FileException;
import cn.com.unary.exception.FileException2;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame
{
    private JLabel label = new JLabel("源文件路径：");
    private JTextField jtf = new JTextField(25);
    private JButton button = new JButton("浏览");
    private JLabel labe2 = new JLabel("目标文件路径：");
    private JTextField jtf2 = new JTextField(25);
    private JButton button2 = new JButton("浏览");
    private JButton button3 = new JButton("拷贝");

    private WindowsController windowsController = new WindowsController();
    private String sourPath;
    private String destPath;
    public Window()
    {
        JFrame jf = new JFrame("文件选择器");
        jf.setBounds(300, 100, 500, 200);
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(jtf);
        panel.add(button);

        panel.add(labe2);
        panel.add(jtf2);
        panel.add(button2);

        panel.add(button3);

        jf.add(panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //按钮1监听按钮事件
        button.addActionListener(e ->
        {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fc.showOpenDialog(null);    //文件打开对话框
            if (val == fc.APPROVE_OPTION)
            {
                //正常选择文件
                sourPath = fc.getSelectedFile().toString();
                jtf.setText(sourPath);
                System.out.println(fc.getSelectedFile().toString());
            } else
            {
                //未正常选择文件，如选择取消按钮
                jtf.setText("未选择文件");
            }
        });
        //按钮2监听按钮事件
        button2.addActionListener(e ->
        {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fc.showOpenDialog(null);    //文件打开对话框
            if (val == fc.APPROVE_OPTION)
            {
                //正常选择文件
                destPath = fc.getSelectedFile().toString();
                jtf2.setText(destPath);
                System.out.println(fc.getSelectedFile().toString());
            } else
            {
                //未正常选择文件，如选择取消按钮
                jtf2.setText("未选择文件");
            }
        });

        //按钮3监听按钮事件
        button3.addActionListener(e ->
        {
            try{
                windowsController.fileController(sourPath,destPath);
                JOptionPane.showMessageDialog(null, "请查看目录:"+destPath, "拷贝成功", JOptionPane.INFORMATION_MESSAGE);
            }
             catch (FileException e1)
             {
                 JOptionPane.showMessageDialog(null, e1.getMessage(), "拷贝出错", JOptionPane. ERROR_MESSAGE);
                 System.out.println("文件异常，请检查是否有空或者格式错误文件");
             }
            catch (FileException2 e2)
            {
                JOptionPane.showMessageDialog(null, e2.getMessage(), "拷贝出错", JOptionPane. ERROR_MESSAGE);
                System.out.println("文件异常，请检查是否有空或者格式错误文件");
            }

        });

    }


}

