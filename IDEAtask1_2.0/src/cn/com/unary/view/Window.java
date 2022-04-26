package cn.com.unary.view;


import cn.com.unary.controller.WindowsController;
import cn.com.unary.exception.FileCreateException;
import cn.com.unary.exception.FileFormatException;
import cn.com.unary.exception.FileIsNullException;


import javax.swing.*;



public class Window extends JFrame
{
    private JLabel m_sourceLabel = new JLabel("源文件路径：");
    private JTextField m_sourceJtf = new JTextField(25);
    private JButton m_sourceButton = new JButton("浏览");
    private JLabel m_destLabel = new JLabel("目标文件路径：");
    private JTextField m_destJtf2 = new JTextField(25);
    private JButton m_destButton2 = new JButton("浏览");
    private JButton m_copyButton3 = new JButton("拷贝");

    private WindowsController windowsController = new WindowsController();
    private String sourPath;
    private String destPath;
    public Window()
    {
        JFrame jf = new JFrame("文件选择器");
        jf.setBounds(300, 100, 500, 170);
        JPanel panel = new JPanel();
        panel.add(m_sourceLabel);
        panel.add(m_sourceJtf);
        panel.add(m_sourceButton);

        panel.add(m_destLabel);
        panel.add(m_destJtf2);
        panel.add(m_destButton2);

        panel.add(m_copyButton3);

        jf.add(panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //按钮1监听按钮事件
        m_sourceButton.addActionListener(e ->
        {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fc.showOpenDialog(null);    //文件打开对话框
            if (val == fc.APPROVE_OPTION)
            {
                //正常选择文件
                sourPath = fc.getSelectedFile().toString();
                m_sourceJtf.setText(sourPath);
                System.out.println(fc.getSelectedFile().toString());
            } else
            {
                //未正常选择文件，如选择取消按钮
                m_sourceJtf.setText("未选择文件");
            }
        });
        //按钮2监听按钮事件
        m_destButton2.addActionListener(e ->
        {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fc.showOpenDialog(null);    //文件打开对话框
            if (val == fc.APPROVE_OPTION)
            {
                //正常选择文件
                destPath = fc.getSelectedFile().toString();
                m_destJtf2.setText(destPath);
                System.out.println(fc.getSelectedFile().toString());
            } else
            {
                //未正常选择文件，如选择取消按钮
                m_destJtf2.setText("未选择文件");
            }
        });


        //按钮3监听按钮事件
        m_copyButton3.addActionListener(e ->
        {
                String m_errMsg;
                m_errMsg = windowsController.fileController(sourPath,destPath);
                if(m_errMsg.equals("1"))
                {
                    JOptionPane.showMessageDialog(null, "请查看目录:"+destPath, "拷贝成功", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, m_errMsg, "拷贝出错", JOptionPane. ERROR_MESSAGE);
                }



        });

    }


}

