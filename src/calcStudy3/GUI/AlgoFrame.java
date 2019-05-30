package calcStudy3.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-28 15:48
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    public AlgoFrame(String title,int canvasWidth,int canvasHeight){
        super(title);
        this.canvasWidth=canvasWidth;
        this.canvasHeight=canvasHeight;
        //设置好画布
        AlgoCanvas canvas=new AlgoCanvas();
        //canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setContentPane(canvas);
        //让jframe根据画布大小进行调整
        pack();

        //setSize(canvasWidth,canvasHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
    //TODO 设置自己的值
    private Object data;
    public void render(Object circles){
        this.data=data;
        //将jframe控件重新刷新一下。会重新调用paintcomponent
        repaint();
    }
    public int getCanvasWidth(){
        return canvasWidth;
    }
    public int getCanvasHeight(){
        return canvasHeight;
    }

    public AlgoFrame(String title){
        this(title,1024,768);
    }

    private class AlgoCanvas extends JPanel{
        //需要重新绘制时，就会自动传进来graphics 绘制的上下文环境
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //使用graphics2D有更强大的功能
            Graphics2D g2d= (Graphics2D) g;

            //抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            //具体绘制
            //TODO: 绘制自己的数据data
        }
        //系统创建algocanvas就会自动调用这个方法
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth,canvasHeight);
        }
    }
}
