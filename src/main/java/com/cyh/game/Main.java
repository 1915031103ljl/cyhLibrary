package com.cyh.game;

import com.cyh.game.dao.impl._精灵Dao;
import com.cyh.game.opring._美化;
import com.cyh.game.pjo._旅行家;
import com.cyh.game.pjo._精灵;

import java.util.Scanner;

public class Main {
    private static _精灵Dao 最初 = new _精灵Dao();
    private static Scanner 标准输出 = new Scanner(System.in);
    private String 旅行者姓名 ;
    private static boolean 性别 = true;
    private static _旅行家 旅行家 = new _旅行家();
    private static _精灵 精灵;

    public static void main(String[] args) {
        开始界面();
        旅行家界面();

    }

    public static void 开始界面(){
        //创建一个账号
        System.out.println("欢迎来到神奇宝可梦的世界!");
        System.out.println(_美化._很长的杠杆());

        System.out.println("旅行者你的名称是?");
        String 旅行者姓名 = 标准输出.next();
        System.out.println(_美化._很长的杠杆());

        System.out.println("您的性别为?");
        System.out.println("男/女");
        System.out.println(_美化._很长的杠杆());

        String 输出_性别 = 标准输出.next();
        if (输出_性别.equals("女")){
            性别 = false;
        }
        System.out.println(_美化._很长的杠杆());

        //选择精灵宝可梦
        System.out.println("你好我是大田卷博士,很高兴认识你,请你在下面三只");
        System.out.println("1.小火龙");
        System.out.println("2.妙蛙种子");
        System.out.println("3.杰尼龟");
        int i = 标准输出.nextInt();
        switch (i){
            case 1 :{
                System.out.println("你选择了小火龙");
                精灵 = 最初.获取_精灵(1);
                break;
            }
            case 2:{
                System.out.println("你选择了杰尼龟");
                break;
            }
            case 3 :{
                System.out.println("你选择了妙蛙种子");
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }

    public static void 旅行家界面(){
        //冒险主界面
        System.out.println();
        System.out.println();
        System.out.println("现在的时间是:"+"      您已经旅行了");
        System.out.println("您的基本信息:");
        System.out.println("姓名"+旅行家.get姓名());
        System.out.println("拥有精灵数量:");
        System.out.println("目前获取了"+"徽章");
        System.out.println("目前对战胜率:");
        System.out.println();
        System.out.println(_美化._很长的杠杆());

        System.out.println("1.查看精灵状况");
        System.out.println("2.查看队伍情况");
        System.out.println("3.进入主界面");
        int i = 标准输出.nextInt();
        switch (i){
            case 1:{

                break;
            }
            case 2:{
                System.out.println(精灵);
                break;
            }
            case 3:{
                主界面();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }

    public static void 主界面(){
        System.out.println("1.回到旅行者界面");
        System.out.println("2.开始野外探险");
        System.out.println("3.对战系统:精灵们冲啊");

        int i = 标准输出.nextInt();
        switch (i){
            case 1:{
                旅行家界面();
                break;
            }
            case 2:{
                野外探索界面();
                break;
            }
            case 3:{
                主界面();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }

    }

    public static void 野外探索界面(){


    }



}
