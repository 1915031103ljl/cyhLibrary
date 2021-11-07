package com.cyh.pjo;

public class 精灵 {
	private double 攻击力;
	private double 防御力;
	private double 血量;
	
	
	
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

	public double get血量() {
		return 血量;
	}

	public void set血量(double 血量) {
		this.血量 = 血量;
	}
	
	

	public double 攻击(double 血量,double 剩余的攻击力) {
		return 血量 - 剩余的攻击力 ;
	}
	
	public double 防御(double 攻击力) {
		return 攻击力 - 防御力;
	}
	
	public static void main(String[] args) {
		System.out.println("杨腾吃屎");
		精灵 精灵1 = new 精灵();
		精灵 精灵2 = new 精灵();
		double 剩余的血量 = 精灵1.攻击(精灵2.血量, 精灵2.防御(精灵1.攻击力));
		System.out.println(剩余的血量);
	}

}
