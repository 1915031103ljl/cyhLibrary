package com.cyh.game.dao.impl;

import com.cyh.dao.BaseDao;
import com.cyh.game.pjo.Base精灵;

import java.util.List;

public class _最初的精灵DaoImpl extends BaseDao {
    public Base精灵 获取_精灵(int id) {
        return queryOneData(Base精灵.class, "select * from `最初的精灵表` where `序号` = ?", id);
    }
    public List<Base精灵> 获取_所有的精灵数据() {
        return queryForList(Base精灵.class, "select * from `最初的精灵表`");
    }
    public int 删除_精灵(int id){
        return queryUpData("DELETE FROM `最初的精灵表` WHERE id = ?",id);
    }
    public int 添加_精灵(int id,String name,int 种族值,int 技能,double 血量,String 属性,double 攻击力,double 防御力,double 特殊攻击力,double 特殊防御力,int 速度,int 等级){
        return queryUpData("INSERT INTO `最初的精灵表` VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",id,name,种族值,技能,血量,属性,攻击力,防御力,特殊攻击力,特殊防御力,速度,等级);
    }
}
