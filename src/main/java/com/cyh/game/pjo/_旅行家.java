package com.cyh.game.pjo;

import java.util.Date;

public class _旅行家 {
    private String 姓名 = null;
    private boolean 性别 = true;
    private Date 旅行时间 = null;
    private _精灵[] 精灵 ;

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public boolean is性别() {
        return 性别;
    }

    public void set性别(boolean 性别) {
        this.性别 = 性别;
    }

    public Date get旅行时间() {
        return 旅行时间;
    }

    public void set旅行时间(Date 旅行时间) {
        this.旅行时间 = 旅行时间;
    }

    public _精灵[] get精灵() {
        return 精灵;
    }

    public void set精灵(_精灵[] 精灵) {
        this.精灵 = 精灵;
    }
}
