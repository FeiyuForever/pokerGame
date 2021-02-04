package com.feiyu.ent.bak;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author xzhou.James
 * @Date 2020/8/29  12:23
 */
public class Poker {
    /**
     * 存放所有的扑克牌
     */
    private static LinkedList<String> allPoker = new LinkedList<String>();
    /**
     * 玩家
     */
    private Integer player = (int)(Math.random() * 4 + 1);

    /**
     * 裁判
     */
    private Integer judgeMent = 5;
    /**
     * 索引
     */
    private Integer index = 0;

    public static LinkedList<String> getAllPoker() {
        return allPoker;
    }

    public static void setAllPoker(LinkedList<String> allPoker) {
        Poker.allPoker = allPoker;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getJudgeMent() {
        return judgeMent;
    }

    public void setJudgeMent(Integer judgeMent) {
        this.judgeMent = judgeMent;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 制造牌和洗牌
     */
    public static LinkedList<String> makePoker() {
        String[] type = {"A", "B", "C", "D"};
        for (int i = 0; i < type.length; i++) {
            for (int j = 1; j <= 13; j++) {
                String elem = String.valueOf(j + type[i]);
                allPoker.add(elem);
            }
        }
        Collections.shuffle(allPoker);
        return allPoker;
    }

    public static void main(String[] args) {
        makePoker();
        Poker poker = new Poker();
        int a = poker.getIndex();
        System.out.println(a);
    }
}
