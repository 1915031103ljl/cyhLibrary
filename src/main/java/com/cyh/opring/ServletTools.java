package com.cyh.opring;

import com.cyh.servlet.BaseSerlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletTools extends BaseSerlet {
    /**
     * 这个方法用于快速的向客户端发送数据
     * @param t 这个是发送数据的类型
     * @param response
     * @param <T>
     */
    public static  <T> void serverDataSending(T t,HttpServletResponse response){
        try {
            String s = gson.toJson(t);
            PrintWriter writer = response.getWriter();
            writer.println(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
