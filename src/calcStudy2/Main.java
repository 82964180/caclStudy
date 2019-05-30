package calcStudy2;

import calcStudy2.four.LinkedList;
import calcStudy2.three.Queue;

import java.util.Random;

public class Main {

    private static double testQueue(Queue<Integer> q, int opCount){
        long startTime=System.nanoTime();
        Random random=new Random();
        for (int i=0;i<opCount;i++){
            q.enqueue((random.nextInt(Integer.MAX_VALUE)));
        }
        for (int i=0;i<opCount;i++){
            q.dequeue();
        }

        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
    public static void main(String[] args) {
       /* Array<Integer> array=new Array<>();
        for (int i=0;i<10;i++){
            array.addLast(i);
        }
        System.out.println(array);
        array.addLast(11);
        System.out.println(array.toString());
        array.removeElement(2);
        System.out.println(array.toString());*/

     /*  ArrayStack<Integer> stack=new ArrayStack<>();
       for(int i=0;i<5;i++){
           stack.push(i);
       }
        System.out.println(stack.toString());
       stack.pop();
        System.out.println(stack);*/

       /*ArrayQueue<Integer> queue=new ArrayQueue<>();
       for(int i=0;i<10;i++){
           queue.enqueue(i);
           System.out.println(queue);
           if(i%3==2){
              queue.dequeue();
              System.out.println(queue);
           }
       }*/
       /*LoopQueue<Integer> queue=new LoopQueue<>();
       for(int i=0;i<10;i++){
           queue.enqueue(i);
           System.out.println(queue);
           if(i%3==2){
              queue.dequeue();
              System.out.println(queue);
           }
       }*/

         /* int opCount=100000;
          ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
          double time1=testQueue(arrayQueue,opCount);
          System.out.println("ArrayQueue,time:"+time1+" s");

        LoopQueue<Integer> loopQueue=new LoopQueue<>();
        double time2=testQueue(loopQueue,opCount);
        System.out.println("LoopQueue,time:"+time2+" s");*/
        LinkedList<Integer> linkedList=new LinkedList<>();
        for (int i=0;i<5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

    }

}
