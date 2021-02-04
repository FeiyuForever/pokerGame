package com.feiyu.ent.pojo;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xzhou.James
 * @Date 2020/8/29  12:16
 */
public class Player implements Runnable {
    /**
     * 线程名称
     */
    private String name;
    /**
     * 翻牌次数
     */
    private Integer turnOver = 0;
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
    /**
     * 考虑到Map主键不能重复，让人物名称作为key
     * <p>
     * 填充分数
     */
    private static final Map<String, Integer> map = new HashMap<String, Integer>();

    public Player(String name, LinkedList<String> sortPoker) {
        this.name = name;
        this.sortPoker = sortPoker;
    }

    public Player(String name, Poker poker) {
        this.name = name;
        this.poker = poker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Integer turnOver) {
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
        while (true) {
            synchronized (poker) {
                try {
                    long start = System.currentTimeMillis();
                    int index = poker.getIndex();
                    //获取条件是否足够
                    if (turnOver == 13) {
                        getPrintDetail(name, grade, index);
                        break;
                    }
                    //看牌是否留下
                    String pokerName = Poker.getAllPoker().get(index);
                    turnOver += 1;
                    if (pokerName.length() == 2) {
                        boolean flag = getValueByLength(pokerName, index);
                        if (flag) break;
                    } else {
                        boolean flag = getValueByLength(pokerName, index);
                        if (flag) break;
                    }
                    poker.setIndex(index + 1);
                    Thread.sleep(1);
                    long end = System.currentTimeMillis();
                    takeTime += (end - start);
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }

    /**
     * 玩家判断
     *
     * @param names
     */
    private void getPrintDetail(String names, int grade, int index) {
        if (names.equals("甲")) {
            printInfos(grade, index);
        } else if (names.equals("乙")) {
            printInfos(grade, index);
        } else if (names.equals("丙")) {
            printInfos(grade, index);
        } else if (names.equals("丁")) {
            printInfos(grade, index);
        }
    }

    /***
     * 输出获胜者
     *
     * @param grade
     * @param index
     */
    private void printInfos(int grade, int index) {
        System.out.println("name :" + name + "  turnOver :" + turnOver +
                "  takeTime :" + takeTime + "  grade :" + grade);
        System.out.println("CARDS :" + sortPoker);
        map.put(name, grade);
        //fixme 初始传参
        if (map.size() == 4) {
            String winMan = getMaxPokerPerosn(map);
            if (index >= 51) {
                System.out.println("WINNER = " + "【 " + winMan + " 】");
            }
        }
    }

    /**
     * 找出获胜者
     * 这里只寻找最多前两个value,如果大于等于三个，则无获胜者
     * <p>
     *
     * @param map
     * @return
     */
    private String getMaxPokerPerosn(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        if (list.get(list.size() - 1) == list.get(list.size() - 2)) {
            StringBuilder stringBuilder = new StringBuilder();
            Map.Entry<String, Integer> max1 = list.get(list.size() - 1);
            String first1 = max1.getKey();
            Map.Entry<String, Integer> max2 = list.get(list.size() - 2);
            String first2 = max2.getKey();
            return stringBuilder.append(first1).append("  , ").append(first2).toString();
        } else {
            Map.Entry<String, Integer> max = list.get(list.size() - 1);
            return max.getKey();
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
        //拿到 牌面分数
        Integer look = Integer.valueOf(pokerName.substring(0, pokerName.length() - 1));
        if (sortPoker.size() == 0) {
            //摸到牌
            sortPoker.add(Poker.getAllPoker().get(index));
            grade += look;
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
        //Integer contrast = Integer.valueOf(temp.substring(0, temp.length() - 1));
        //总共52张牌，4个人分，总分364，人均91，一人13张牌，每张牌均分7分
        /*if (contrast > look && contrast - look > 7) {
            return true;
        } else {
            sortPoker.add(Poker.getAllPoker().get(index));
            grade += look;
        }*/
        sortPoker.add(Poker.getAllPoker().get(index));
        grade += look;
        return false;
    }
}
