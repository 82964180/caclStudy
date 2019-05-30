package calcStudy3.GUI;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-28 19:58
 */
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private Object data;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth,int sceneHeight,int N){
        //初始化数据
        //TODO: 初始化数据

        //java 把页面创建事件分发线程
        EventQueue.invokeLater(()->{
            frame=new AlgoFrame("Welcome",sceneWidth,sceneHeight);
            //TODO： 根据情况决定是否加入键盘鼠标监听事件监听器
            frame.addKeyListener(new AloKeyListener());
            //针对frame创建的鼠标监听器
            frame.addMouseListener(new AlMouseListener());
            //队列被会被阻塞
            new Thread(()->{
                run();
            }).start();

        });
    }

    private void run() {
        //TODO: 编写自己的动画逻辑
    }

    //TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AloKeyListener extends KeyAdapter {

    }

    private class AlMouseListener extends MouseAdapter{

    }


}
