package com.cyh.opring;

public class _计算器 {
	public int 计算(int x,int y,String name) {
		if (name.equals("整数")) {
			return x+y;
		}else if (name.equals("减法")) {
			return x-y;
		}else if (name.equals("乘法")) {
			return x*y;
		}else if (name.equals("除法")) {
			return x/y;
		}
		return x+y;
	}
	public String 卡成了球() {
		return "计算机卡成了球";
	}
	
	public static void main(String[] args) {
		_计算器  计算器 = new _计算器();
		int y = 计算器.计算(1,2,"加法");
		System.out.println(y);
		System.out.println(计算器.卡成了球());
	}
}
