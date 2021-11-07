package com.cyh.opring;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReaderXMLYT {
    String name;
    String posswoder;

    //第一个是输入xml文件的路径，第二个元素是输入想要获取的对象，第三个是获取对象里面的值
    public Object[] ReaderXMLYT(String file, String nameYuan, String... name) throws DocumentException {
        Object[] xx = new Object[name.length];
        //从xml文档中取出数据，"file:\\D:\\WordTreeTokit\\杨腾项目开发\\WordTree\\src\\com\\wordtree\\wTWritingBao\\UserYT.XML"
        SAXReader ss = new SAXReader();
        Document read = ss.read(file);
        Element rootElement = read.getRootElement();
        Element user = rootElement.element(nameYuan);
//        List<Element> elements = user.elements();获取一个数组
        for (int i = 0; i < name.length; i++) {
            xx[i] = user.element(name[i]).getText();
//            System.out.println("已读取：\t"+user.element(name[i]).getText());
        }
        return xx;
    }
}
