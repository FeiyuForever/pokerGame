package com.feiyu.ent;

import com.feiyu.ent.pojo.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author xzhou.James
 * @Date 2020/8/29  12:13
 * <p>
 * 主函数运行口
 */
public class PokerMain {

    /**
     * 启动指令
     */
    private static final String ENTER = "start";

    /**
     * 设置线程优先级
     */
    private static final Integer PRIPROTY = 2;

    public static void main(String[] args) throws Exception {
        System.out.println("===========抢大牌游戏开始============");
        System.out.println("请输入【start】开始抢牌：");
        //startGetPoker();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            if (str.equals(ENTER)) {
                startGetPoker();
            } else {
                System.out.println("输入指令有误,请重新输入！");
            }
        }
    }

    /**
     * 线程启动
     */
    private static void startGetPoker() {
        Poker poker = new Poker();
        Poker.makePoker();

        Player player1 = new Player("甲", poker);
        Player player2 = new Player("乙", poker);
        Player player3 = new Player("丙", poker);
        Player player4 = new Player("丁", poker);

        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);
        Thread thread3 = new Thread(player3);
        Thread thread4 = new Thread(player4);

        thread1.setPriority(PRIPROTY);
        thread2.setPriority(PRIPROTY);
        thread3.setPriority(PRIPROTY);
        thread4.setPriority(PRIPROTY);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
