package calcStudy2.eleven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-26 20:18
 */
public interface UF {
    int getSize();
    boolean isConnected(int p,int q);
    //合并元素
    void unionElements(int p,int q);

}
