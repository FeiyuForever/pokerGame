package com.feiyu.ent.bak;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author xzhou.James
 * @Date 2020/8/29  12:16
 */
public class Player1 implements Runnable {
    /**
     * 线程名称
     */
    private String name;
    /**
     * 翻牌次数
     */
    private List<Integer> turnOver = new ArrayList<Integer>();
    /**
     * 总分数
     */
    private Integer grade = 0;
    /**
     * 花费时间
     */
    private Long takeTime = 0L;
    /**
     * 每个玩家拿到的所有纸牌
     */
    private LinkedList<String> sortPoker = new LinkedList<String>();
    /**
     * 引进纸牌
     */
    private Poker poker;

    public Player1(String name, LinkedList<String> sortPoker) {
        this.name = name;
        this.sortPoker = sortPoker;
    }

    public Player1(String name, Poker poker) {
        this.name = name;
        this.poker = poker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(List<Integer> turnOver) {
        this.turnOver = turnOver;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public LinkedList<String> getSortPoker() {
        return sortPoker;
    }

    public void setSortPoker(LinkedList<String> sortPoker) {
        this.sortPoker = sortPoker;
    }

    public Poker getPoker() {
        return poker;
    }

    public void setPoker(Poker poker) {
        this.poker = poker;
    }

    /**
     * 线程运行
     */
    @Override
    public void run() {
        System.out.println("线程名称:" + Thread.currentThread().getName() + "-线程ID:-" + Thread.currentThread().getId());
        while (true) {
            synchronized (poker) {
                try {
                    long start = System.currentTimeMillis();
                    //获取摸到的是第几张牌
                    if (poker.getIndex() > 51) {
                        System.out.println("name:" + name + "turnOver:" + turnOver.size() + "takeTime:" + takeTime + "grade:" + grade);
                        System.out.println("CARDS:" + sortPoker);
                        break;
                    }
                    int index = poker.getIndex();
                    System.out.println("index = " + index);
                    //看牌
                    String pokerName = Poker.getAllPoker().get(index);
                    System.out.println("pokerName = " + pokerName);
                    turnOver.add(1);
                    if (pokerName.length() == 2) {
                        boolean flag = getValueByLength(pokerName, index);
                        if (flag) break;
                    } else {
                        boolean flag = getValueByLength(pokerName, index);
                        if (flag) break;
                    }
                    poker.setIndex(index + 1);
                    long end = System.currentTimeMillis();
                    takeTime += (end - start);
                    Thread.sleep(1);
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }

    /**
     * 翻牌之后加入到集合
     *
     * @param pokerName
     * @param index
     * @return
     */
    private boolean getValueByLength(String pokerName, int index) {
        Integer look = Integer.valueOf(pokerName.substring(0, pokerName.length() - 1));
        System.out.println("look = " + look);
        if (sortPoker.size() == 0) {
            //摸到牌
            sortPoker.add(Poker.getAllPoker().get(index));
        } else {
            //玩家翻牌，看完之后考虑拿牌的判断
            String temp = sortPoker.get(sortPoker.size() - 1);
            if (temp.length() == 2) {
                boolean result = getNeedPokerValue(temp, look, index);
                return result;
            } else {
                boolean result = getNeedPokerValue(temp, look, index);
                return result;
            }
        }
        return false;
    }

    /**
     * 根据前后元素的差值，判断是否加入到个人牌集中
     *
     * @param temp
     * @param look
     * @param index
     * @return
     */
    private boolean getNeedPokerValue(String temp, Integer look, int index) {
        Integer contrast = Integer.valueOf(temp.substring(0, temp.length() - 1));
        //总共52张牌，4个人分，总分364，人均91，一人13张牌，每张牌均分7分
        if (contrast > look && contrast - look > 7) {
            return true;
        } else {
            sortPoker.add(Poker.getAllPoker().get(index));
            grade += look;
        }
        return false;
    }
}
