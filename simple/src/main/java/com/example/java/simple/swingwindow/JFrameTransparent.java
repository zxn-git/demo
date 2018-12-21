package com.example.java.simple.swingwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class JFrameTransparent extends JFrame {

    private static int oX;
    private static int oY;

    public JFrameTransparent() {
        super();
        init(this);

    }

    private void init(JFrame frame) {
        //初始化一个容器
        Container container = getContentPane();

        //设置关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //初始化一个jlable
        JLabel emptyLable = new JLabel("");
        freach(emptyLable);
        //初始化一个panel
        JPanel panel = new JPanel(new BorderLayout());

        //设置布局
        container.setLayout(new FlowLayout());

        panel.add(emptyLable);

        //把panel添加到容器
        container.add(panel);

        //设置透明度
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setOpacity(0.3F);

        //设置大小
        frame.setSize(100, 50);

        frame.setBackground(Color.BLUE);
        //设置位置
        frame.setLocation(100, 100);

        //设置可见性
        frame.setVisible(true);

        addListener(frame);

    }


    private void initJButton(JPanel panel) {
        //初始化一个按钮
        JButton startAutoGen = new JButton("开始生成");
        panel.add(startAutoGen);
    }

    private void addListener(JFrame frame) {
        /**
         * 监听鼠标按下的位置
         */
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oX = e.getX();
                oY = e.getY();
            }
        });

        //监听拖动，并设置位置
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = frame.getLocation();
                frame.setLocation(p.x + e.getX() - oX, p.y + e.getY() - oY);
            }
        });
    }

    private void initMenu(JFrame frame) {

        //初始化一个菜单栏
        JMenuBar menuBar = new JMenuBar();
        //初始化菜单
        JMenu menu1 = new JMenu("文件");
        JMenu menu2 = new JMenu("编辑");
        JMenu menu3 = new JMenu("资源");

        //把菜单添加到菜单栏
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        //设置菜单栏
        frame.setJMenuBar(menuBar);

    }

    private String getUsdt() {
        return WindowsData.OKEX();
    }

    /**
     * @ Description: 刷新文本内容
     * @ Author: xiaonan.zhang
     * @ Date: 下午12:46 2018/12/21
     */
    public void freach(JLabel emptyLable) {
        new Thread(() -> {
            while (true) {
                emptyLable.setText(getUsdt());
//
//                if (Double.parseDouble(emptyLable.getText()) > 500.00) {
//                    emptyLable.setForeground(Color.red);
//
//                }
                emptyLable.repaint();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    public static void main(String[] args) {
        JFrameTransparent jFrameTransparent = new JFrameTransparent();

    }
}