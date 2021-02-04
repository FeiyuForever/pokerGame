package com.feiyu.ent.bak;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author xzhou.James
 * @Date 2020/8/29  14:23
 */
public class ThreadMain {

    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                4,
                16,
                1000,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    private static final Map<String,List<Callable<String>>> map1 = new HashMap<String,List<Callable<String>>>();
    private static final Map<String,List<Callable<String>>> map2 = new HashMap<String,List<Callable<String>>>();
    private static final Map<String,List<Callable<String>>> map3 = new HashMap<String,List<Callable<String>>>();
    private static final Map<String,List<Callable<String>>> map4 = new HashMap<String,List<Callable<String>>>();

    private static List<Callable<String>> callables1 = new ArrayList<>();
    private static List<Callable<String>> callables2 = new ArrayList<>();
    private static List<Callable<String>> callables3 = new ArrayList<>();
    private static List<Callable<String>> callables4 = new ArrayList<>();


    /**
     * 启动指令
     */
    private static final String ENTER = "start";

    public static void main(String[] args) throws Exception {
        System.out.println("===========抢大牌游戏开始============");
        System.out.println("请输入【start】开始抢牌：");
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

    private static void startGetPoker() {
        LinkedList<String> poker = new LinkedList<String>();
        getAllPoker(poker);
        for(Iterator<String> iterator = poker.iterator();iterator.hasNext();){
            String name = iterator.next();
            getPokerByOneSelf(name,poker.size());
        }
    }

    private static void getPokerByOneSelf(final String name,int size) {
        callables1.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return name;
            }
        });

        callables2.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return name;
            }
        });
        callables3.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return name;
            }
        });
        callables4.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return name;
            }
        });
    }

    /**
     * 制造扑克牌和洗牌
     *
     * @param getPoker
     */
    private static void getAllPoker(LinkedList<String> getPoker) {
        String[] type = {"A", "B", "C", "D"};
        for (int i = 0; i < type.length; i++) {
            for (int j = 1; j <= 13; j++) {
                String elem = String.valueOf(j + type[i]);
                getPoker.add(elem);
            }
        }
        Collections.shuffle(getPoker);
    }
}
