package com.cyh.game.pjo;

public class Base精灵 {
    private int 序号;
    private String 姓名;
    private int 种族值;
    private int 技能;
    private double 血量;
    private String 属性;
    private double 攻击力;
    private double 防御力;
    private double 特殊攻击力;
    private double 特殊防御力;
    private int 速度;
    private int 等级;

    @Override
    public String toString() {
        return "Base精灵{" +
                "序号=" + 序号 +
                ", 姓名='" + 姓名 + '\'' +
                ", 种族值=" + 种族值 +
                ", 技能=" + 技能 +
                ", 血量=" + 血量 +
                ", 属性='" + 属性 + '\'' +
                ", 攻击力=" + 攻击力 +
                ", 防御力=" + 防御力 +
                ", 特殊攻击力=" + 特殊攻击力 +
                ", 特殊防御力=" + 特殊防御力 +
                ", 速度=" + 速度 +
                ", 等级=" + 等级 +
                '}';
    }

    public Base精灵(int 序号, String 姓名, int 种族值, int 技能, double 血量, String 属性, double 攻击力, double 防御力, double 特殊攻击力, double 特殊防御力, int 速度, int 等级) {
        this.序号 = 序号;
        this.姓名 = 姓名;
        this.种族值 = 种族值;
        this.技能 = 技能;
        this.血量 = 血量;
        this.属性 = 属性;
        this.攻击力 = 攻击力;
        this.防御力 = 防御力;
        this.特殊攻击力 = 特殊攻击力;
        this.特殊防御力 = 特殊防御力;
        this.速度 = 速度;
        this.等级 = 等级;
    }

    public Base精灵() {
    }

    public int get序号() {
        return 序号;
    }

    public void set序号(int 序号) {
        this.序号 = 序号;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public int get种族值() {
        return 种族值;
    }

    public void set种族值(int 种族值) {
        this.种族值 = 种族值;
    }

    public int get技能() {
        return 技能;
    }

    public void set技能(int 技能) {
        this.技能 = 技能;
    }

    public double get血量() {
        return 血量;
    }

    public void set血量(double 血量) {
        this.血量 = 血量;
    }

    public String get属性() {
        return 属性;
    }

    public void set属性(String 属性) {
        this.属性 = 属性;
    }

    public double get攻击力() {
        return 攻击力;
    }

    public void set攻击力(double 攻击力) {
        this.攻击力 = 攻击力;
    }

    public double get防御力() {
        return 防御力;
    }

    public void set防御力(double 防御力) {
        this.防御力 = 防御力;
    }

    public double get特殊攻击力() {
        return 特殊攻击力;
    }

    public void set特殊攻击力(double 特殊攻击力) {
        this.特殊攻击力 = 特殊攻击力;
    }

    public double get特殊防御力() {
        return 特殊防御力;
    }

    public void set特殊防御力(double 特殊防御力) {
        this.特殊防御力 = 特殊防御力;
    }

    public int get速度() {
        return 速度;
    }

    public void set速度(int 速度) {
        this.速度 = 速度;
    }

    public int get等级() {
        return 等级;
    }

    public void set等级(int 等级) {
        this.等级 = 等级;
    }
}
