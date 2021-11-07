package com.cyh.pjo;


public class Books {
    /**
     * @author 有天道
     * @param  id -- 书籍id
     * @param  name -- 书籍姓名
     * @param  category -- 书籍类别
     * @param  author -- 书籍作者
     * @param  descriptor -- 书籍描述信息
     * @param  finalDescriptor -- 大红字的显示
     * @param  bookCover  -- 书籍封面路径
     */
    private Integer id;
    private String name;
    private String author;
    private String category;
    private String descriptor;
    private String finalDescriptor;
    private String bookCover;

    public Books() {
    }

    /**
     * @apiNote 这个api主要用来添加一个book
     * @author 有天道
     * @param  id -- 书籍id
     * @param  name -- 书籍姓名
     * @param  category -- 书籍类别
     * @param  author -- 书籍作者
     * @param  descriptor -- 书籍描述信息
     * @param  finalDescriptor -- 大红字的显示
     * @param  bookCover  -- 书籍封面路径
     */
    public Books(Integer id, String name, String author, String category, String descriptor, String finalDescriptor, String bookCover) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.descriptor = descriptor;
        this.finalDescriptor = finalDescriptor;
        this.bookCover = bookCover;
    }

    /**
     * @apiNote 这个主要用来修改图书封面
     * @param id 图书id
     * @param bookCover 图书封面地址
     */
    public Books(Integer id, String bookCover) {
        this.id = id;
        this.bookCover = bookCover;
    }

    /**
     * @deprecated
     * @apiNote 很久以前添加图书的方法，现在不用了
     * @author 有天道
     * @param  id -- 书籍id
     * @param  name -- 书籍姓名
     * @param  author -- 书籍作者
     * @param  descriptor -- 书籍描述信息
     * @param  finalDescriptor -- 大红字的显示
     * @param  bookCover  -- 书籍封面路径
     */
    public Books(Integer id, String name, String author, String descriptor, String finalDescriptor, String bookCover) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.descriptor = descriptor;
        this.finalDescriptor = finalDescriptor;
        this.bookCover = bookCover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getFinalDescriptor() {
        return finalDescriptor;
    }

    public void setFinalDescriptor(String finalDescriptor) {
        this.finalDescriptor = finalDescriptor;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", finalDescriptor='" + finalDescriptor + '\'' +
                '}';
    }
}
