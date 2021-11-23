package com.cyh.dao.impl;

import com.cyh.dao.BaseDao;
import com.cyh.dao.JdbcUtils;
import com.cyh.dao.PageList;
import com.cyh.pjo.Books;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BooksDaoImpl extends BaseDao implements PageList<Books> {
    //判断一本书籍是否存在
    public Number ifOneBook(int id){
        return (Number) queryForSingleValue("SELECT COUNT(*) FROM `books_table` WHERE `id`=?",id);
    }
    //判断一本小说是否重名

    /**
     * @param name 书籍名称
     * @return 当返回值为0的时候表示该名称没有被使用，当返回值>1时表示不止一个人使用
     */
    public Number ifOneBookName(String name){
        return (Number) queryForSingleValue("SELECT COUNT(*) FROM `books_table` WHERE `name`=?",name);
    }

    //获取一本书籍
    public Books getOneBook(int id) {
        return queryOneData(Books.class, "select `id`,`name`,author,category,descriptor,final_descriptor As finalDescriptor,book_cover_text AS bookCover from `books_table` where `id` = ?", id);
    }

    //获取所有的书籍数据
    public List<Books> getAllBooks() {
        return queryForList(Books.class, "SELECT `id`,`name`,author,category,descriptor,final_descriptor As finalDescriptor,book_cover_text AS bookCover FROM books_table");
    }

    //删除一本书籍的数据
    public int delBook(int id){
        return queryUpData("DELETE FROM `books_table` WHERE id = ?",id);
    }

    //添加

    /**
     *
     * @param book
     * @return
     * @apiNote 添加图书的封面
     */
    public int addBook(Books book){
        FileInputStream in = null;
        try {
            if (book.getBookCover() != null){
                in = new FileInputStream(book.getBookCover());
            }else {
                in = new FileInputStream("D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\文件类型-Txt.png");
            }
            int i = queryUpData("INSERT INTO `books_table` VALUES(?,?,?,?,?,?,?,?)", book.getId(), book.getName(), book.getAuthor(),book.getCategory(), book.getDescriptor(), book.getFinalDescriptor(), in,book.getBookCover());
            return i;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     *
     * @param book
     * @return
     * @apiNote 修改图书的封面
     */
    public int updateBookCover(Books book){
        FileInputStream in = null;
        try {
            if (book.getBookCover() != null){
                in = new FileInputStream(book.getBookCover());
            }else {
                in = new FileInputStream("D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\文件类型-Txt.png");
            }
            int i = queryUpData("UPDATE `books_table` SET `book_cover` = ? WHERE `id`=? ",in,book.getId());
            return i;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //获取封面

    /**
     * @param book 书籍信息
     * @apiNote  这个用来获取书籍封面
     */
    public void getIcon(Books book){
        Connection connection = JdbcUtils.getConnection();
        try {
            PreparedStatement prep = connection.prepareStatement("SELECT book_cover FROM books_table WHERE id=?");
            prep.setInt(1,book.getId());
            ResultSet resultSet = prep.executeQuery();
            if (resultSet.next()){
                java.sql.Blob book_cover = resultSet.getBlob("book_cover");
                InputStream binaryStream = book_cover.getBinaryStream();
                FileOutputStream out = new FileOutputStream("D:\\ytjava\\ideayt\\cyhLibrary\\src\\main\\webapp\\icon\\"+book.getName()+".png");
                int i;
                while ((i = binaryStream.read())!=-1){
                    out.write(i);
                }
                binaryStream.close();
                out.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @param book
     * @return 返回值为零说明没有更新数据,返回值为-1说明更新失败,返回值为1则更新正常
     * @apiNote 更新数据
     */
    public int upOneBook(Books book){
        updateBookCover(book);
        return queryUpData("UPDATE `books_table` SET `name`=?,`author`=?,`descriptor`=?,`final_descriptor`=?,`book_cover`=? WHERE `id`=?",book.getName(),book.getAuthor(),book.getDescriptor(),book.getFinalDescriptor(),book.getBookCover(),book.getId());
    }

    /**
     * @apiNote 更新一个集合的数据
     * @param list 需要booksList
     * @return 返回值必须大于0
     */
    public int upListBooks(List<Books> list){
        int i = -1;
        for (Books book : list){
            updateBookCover(book);
            i += queryUpData("UPDATE `books_table` SET `name`=?,`author`=?,category=?,`descriptor`=?,`final_descriptor`=?,`book_cover_text`=? WHERE `id`=?", book.getName(), book.getAuthor(),book.getCategory(), book.getDescriptor(), book.getFinalDescriptor(), book.getBookCover(), book.getId());
        }
        return i;
    }


    /**
     * @apiNote 获取页面信息
     * @param start 开始页数
     * @param end 结束页数
     * @return 返回值为一个book集合
     */
    public List<Books> getPageUser(int start,int end) {
        return queryForList(Books.class, "SELECT `id`,`name`,author,category,descriptor,final_descriptor As finalDescriptor,book_cover AS bookCover FROM `books_table` LIMIT ?,?",start,end);
    }

    /**
     * @apiNote 获取书籍数量
     * @return 返回值为书籍的数量
     */
    public Number getSumUser(){
        return (Number) queryForSingleValue("SELECT COUNT(*) FROM `books_table`");
    }

    @Override
    public int sumPage() {
        return Integer.parseInt((Number) queryForSingleValue("SELECT COUNT(*) FROM `books_table`")+"");
    }

    @Override
    public List<Books> listPage(int start, int end) {
        return queryForList(Books.class, "SELECT `id`,`name`,author,category,descriptor,final_descriptor As finalDescriptor,book_cover AS bookCover FROM `books_table` LIMIT ?,?",start,end);
    }
}
