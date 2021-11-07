package com.cyh.dao.impl;

import com.cyh.pjo.Books;
import com.mysql.cj.jdbc.Blob;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class BooksDaoImplTest {
    @Test
    public void getOneBook() throws Exception {
        BooksDaoImpl booksDao = new BooksDaoImpl();
        booksDao.addBook(new Books(2, "龙族", "江南", "幻想皇帝", "龙五更新", "D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\商城.png"));
        Books oneBook = booksDao.getOneBook(2);
        FileOutputStream out = new FileOutputStream("D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon");

        System.out.println(oneBook);
    }
    @Test
    public void getIcon(){
        BooksDaoImpl booksDao = new BooksDaoImpl();
        booksDao.getIcon(new Books(2,"龙族"));
        booksDao.ifOneBookName("龙族");
    }
    @Test
    public void updateBookCover(){
        BooksDaoImpl booksDao = new BooksDaoImpl();
        booksDao.addBook(new Books(4,"雪中悍刀行","大内总管","武侠","xx钢大败xx","D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\首页.png"));
        booksDao.updateBookCover(new Books(1,"D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\首页.png"));
    }

}