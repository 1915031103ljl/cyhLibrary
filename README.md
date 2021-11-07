# Web开发书城项目实战

## 一.项目分析

### 1.业务需求

####  a. 登入功能

+ 实现书城登入功能
+ 如果用户没有账号,进行注册,注册之后跳到商品页面
+ 用户登入之后进入商城首页
+ 数据库录用用户数据返回到管理员页面
+ 管理员进行对书城用户以及商品的增删改查

#### b. 图书管理功能

+ 实现书城主页面,有购买,预读功能
+ 录入用户购买信息
+ 管理员对书城信息进行管理

### 2.版本控制

1. jdk版本1.8
2. tomcat版本9.5
3. maven版本10
4. 数据库版本mysql8,数据库连接包也是8
5. 使用idea进行开发

### 3.项目地址:

github地址:

~~~text
https://github.com/aiShuiJiaoDeXioShou/cyhLibrary.git
~~~

## 二.功能实现

### 1.书城登入功能

#### v-视窗层实现

1. 书城首页**,viwe层**实现

   > ![image-20211107221452342](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107221452342.png)

2. 点击注册进入注册页面

   > ![image-20211107221557028](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107221557028.png)

3. 进行用户注册

   > ![image-20211107221709756](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107221709756.png)

    注册成功之后跳到商品页面

   > ![image-20211107221904289](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107221904289.png)

4. 使用注册成功的账号进行登入

   > ![image-20211107222039358](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222039358.png)

   登入失败的话清空所有输入信息

   > ![image-20211107222216034](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222216034.png)

   > ![image-20211107222119420](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222119420.png)

5. 实现管理员登入

   管理员内部id为999,servlet层进行独立判断,密码为404

   > ![](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222341122.png)

   管理员账号登入之后的页面,如下:

   > ![image-20211107222423235](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222423235.png)

#### servlet层实现

1. **简单思路**:只有页面发送请求,服务器才能回应反应,根据请求信息调用不同的DAO层,对数据进行增删改查

   **实现登入功能**:浏览器请求->服务器调用Dao(**userDao.getListUser();**)->跳转到对应页面

   **所以页面代码如下**->

   > ![image-20211107222827330](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222827330.png)

   > ![image-20211107222912966](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107222912966.png)

2. **javaServlet服务器代码-**>

   + 定义**BaseServlet**代码,使用反射将页面请求跳转到对应类的对应方法

   ~~~java
   @WebServlet("/BaseSerlet")
   public abstract class BaseSerlet extends HttpServlet {
   	public UserDao userDao;
   	public Gson gson;
   	@Override
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		userDao = new UserDao();
   		gson = new Gson();
   		response.setContentType("text/html; charset=UTF-8");
   		String action = request.getParameter("action");
   		try {
               Method method = this.getClass().getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
               method.invoke(this,request,response);
           } catch (Exception e) {
               e.printStackTrace();
           }
   	}
   
   
   	@Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		doGet(request, response);
   	}
   
   }
   ~~~

   + 定义**IndexServlet**类处理登入业务,继承**BaseServlet**

     用户登入方法实现-->**userLogin**:

     ~~~java
     @WebServlet("/IndexServlet")
     public class IndexServlet extends BaseSerlet {
     
     	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
     		System.err.println("访问了userLogin");
     		//设置中文
     		//获取用户名和密码
     		String pass = request.getParameter("pass");
     		String user = request.getParameter("user");
     		//获取数据库里面的集合
     		List<User> listUser = userDao.getListUser();
     		try {
     			//验证用户登入
     			if(user.equals(userDao.getUser(999).getName())&&pass.equals(userDao.getUser(999).getPassword())) {
     				//请求重定向
     				//response.sendRedirect("http://localhost:8080/caiyuhao2/viwe/html/login.html");
     				//请求转发
     				try {
     					request.getRequestDispatcher("/UserServlet?action=userPage&nowPage=1").forward(request, response);
     				} catch (ServletException | IOException e) {
     					e.printStackTrace();
     				}
     			}else if(ergodicJudgement(listUser,user,pass)){
     				System.out.println("登入成功");
     				response.sendRedirect("http://localhost:8080/caiyuhao2/viwe/html/Shopping.html");
     			} else {
     				System.out.println(ergodicJudgement(listUser,user,pass));
     				System.out.println(listUser);
     				response.sendRedirect("http://localhost:8080/caiyuhao2/index.html");
     				//response.getWriter().append("登入失败");
     			}
     		}catch (Exception e){
     			e.printStackTrace();
     		}
     
     	}
     	public static boolean ergodicJudgement(List<User> list,String use , String password){
     		for (User user : list){
     			if (user.getName().equals(use)&&password.equals(user.getPassword())){
     				return true;
     			}
     		}
     	    return false;
     	}
     
     
     }
     ~~~

     核心代码:

     > ![image-20211107223752143](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107223752143.png)

#### DAO层代码

1. 获取数据库连接(这里引用了**druid.jar**包)

   ~~~java
   package com.cyh.dao;
   import com.alibaba.druid.pool.DruidDataSource;
   import com.alibaba.druid.pool.DruidDataSourceFactory;
   import java.io.InputStream;
   import java.sql.Connection;
   import java.sql.SQLException;
   import java.util.Properties;
   
   public class JdbcUtils {
       private static DruidDataSource dataSource;
       private static ThreadLocal<Connection> con = new ThreadLocal<Connection>();
       static {
           try {
               Properties properties = new Properties();
               InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("Jdbc.properties");
               properties.load(resourceAsStream);
               dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       public static Connection getConnection(){
           Connection conn = con.get();
           if (conn == null){
               try {
                   conn = dataSource.getConnection();
                   con.set(conn);
                   conn.setAutoCommit(false);
               } catch (Exception throwables) {
                   throwables.printStackTrace();
               }
           }
           return conn;
       }
   
       public static void setClose() {
           Connection connection = con.get();
           if (connection != null){
               try {
                   connection.commit();
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }finally {
                   try {
                       connection.close(); // 关闭连接，资源资源
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
           }
           con.remove();
       }
   }
   
   ~~~

   

2. 定义父类BaseDao

~~~java
public abstract class BaseDao {
    //创建工具类
    private QueryRunner queryRunner = new QueryRunner();
    //执行数据操作并且返回受影响的行数
    public int queryUpData(String sqlQuery,Object... arg){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sqlQuery,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("数据查询失败！");
        }finally {
            JdbcUtils.setClose();
        }
        return -1;
    }

    //查询一条语句
    public <T> T queryOneData(Class<T> type, String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("数据查询失败！");
        }finally {
            JdbcUtils.setClose();
        }
        return null;
    }

    //查询一组数据
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.setClose();
        }
        return null;
    }

    public Object queryForSingleValue(String sql, Object... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.setClose();
        }
        return null;
    }


}

~~~

3. 三编写对应表代码

   **表:**

   <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107224455519.png" alt="image-20211107224455519" style="zoom:33%;" />

   **用户实体类:**

   ~~~java
   package com.cyh.pjo;
   
   /**
    * @author 有天道
    * @apiNote 这个是处理用户登入的类
    */
   public class User {
   	private int id;
   	private String name;
   	private String password;
   
   	public int getId() {
   		return id;
   	}
   
   	public User() {
   		super();
   	}
   
   	public User(int id, String name, String password) {
   		super();
   		this.id = id;
   		this.name = name;
   		this.password = password;
   	}
   
   	@Override
   	public String toString() {
   		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
   	}
   
   	public void setId(int id) {
   		this.id = id;
   	}
   
   	public String getName() {
   		return name;
   	}
   
   	public void setName(String name) {
   		this.name = name;
   	}
   
   	public String getPassword() {
   		return password;
   	}
   
   	public void setPassword(String password) {
   		this.password = password;
   	}
   
   }
   
   ~~~

   **UserDao**:

   ~~~java
   package com.cyh.dao.impl;
   
   import java.util.List;
   
   import com.cyh.dao.BaseDao;
   import com.cyh.pjo.User;
   
   public class UserDao extends BaseDao{
   	public User getUser(int id) {
   		return queryOneData(User.class, "select * from `user_table` where `id` = ?", id);
   	}
   	public List<User> getListUser() {
   		return queryForList(User.class, "select * from `user_table`");
   	}
   	public int delUser(int id){
   		return queryUpData("DELETE FROM `user_table` WHERE id = ?",id);
   	}
   	public int addUser(int id,String name,String password){
   	    return queryUpData("INSERT INTO `user_table` VALUES(?,?,?)",id,name,password);
   	}
   	public int upDataUser(String name,String password,int id){
   		return queryUpData("UPDATE `user_table` SET `name`=?,`password`=? WHERE `id`=?",name,password,id);
   	}
   	public List<User> getPageUser(int start,int end) {
   		return queryForList(User.class, "SELECT * FROM `user_table` LIMIT ?,?",start,end);
   	}
   	public Number getSumUser(){
   		return (Number) queryForSingleValue("SELECT COUNT(*) FROM `user_table`");
   	}
   
   }
   
   ~~~

### 2.管理员对用户的增删改查

#### v-视窗层

##### 1.**添加一个用户**

> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107224804539.png" alt="image-20211107224804539" style="zoom:50%;" />
>
> ![image-20211107224822472](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107224822472.png)
>
> 

##### 2.删除一个用户

> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225026544.png" alt="image-20211107225026544" style="zoom:50%;" />
>
> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225104742.png" alt="image-20211107225104742" style="zoom:50%;" />

##### 3.修改一个用户,修改之后变成了杨腾2

> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225211403.png" alt="image-20211107225211403" style="zoom:50%;" />
>
> ![image-20211107225237048](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225237048.png)

##### 4.添加一个用户

> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225330934.png" alt="image-20211107225330934" style="zoom:50%;" />
>
> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225448841.png" alt="image-20211107225448841" style="zoom:50%;" />
>
> <img src="https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225507665.png" alt="image-20211107225507665" style="zoom:50%;" />

##### 5.分页功能

> ![image-20211107225614122](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225614122.png)
>
> ![image-20211107225632556](https://gitee.com/theCompassWillAlsoGetLost/typora-picture-resources2/raw/master/img/image-20211107225632556.png)

#### jsp和servlet-代码

##### 0.**总代码与实现思路**

**思路:**服务器请求->UserServlet->返回请求页面地址,**下面是java代码**:

~~~ java
package com.cyh.servlet;

import com.cyh.dao.impl.UserDao;
import com.cyh.pjo.Page;
import com.cyh.pjo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends BaseSerlet{
    private int page;
    //删除
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        System.err.println("访问了DelSerlet");
        try {
            String id = request.getParameter("id");
            userDao.delUser(Integer.parseInt(id));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    //添加
    public void addUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            if (id==null){
                id= String.valueOf(100+userDao.getListUser().size());
            }
            String pass = request.getParameter("pass");
            System.err.println("访问了addUser");
            System.err.println("数据库结果:"+userDao.addUser(Integer.parseInt(id), user, pass));
            if ("registeredUser".equals(request.getParameter("actionType"))){
                response.sendRedirect("viwe/html/Shopping.html");
            }else {
                request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    //修改
    public void modifyUser(HttpServletRequest request, HttpServletResponse response){
        try{
            System.out.println("访问了modifyUser");
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            String pass = request.getParameter("pass");
            System.out.println(userDao.upDataUser(user, pass, Integer.parseInt(id)));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //分页
    public void userPage(HttpServletRequest request, HttpServletResponse response){
        try{

            page = Integer.parseInt(request.getParameter("nowPage"));
            int pageAttr = page==0?1:page;
            request.setAttribute("nowPage2",pageAttr);
            System.err.println("访问了userPage");
            Page<User> userPage = new Page<>();
            //设置当前页数
            userPage.setNowPage(page-1);
            if (userPage.getNowPage()<0){
                userPage.setNowPage(0);
            }
            //获取总条数
            Integer sumSize = Integer.parseInt(userDao.getSumUser()+"");
            request.setAttribute("sumSize",sumSize);

            //计算总页数

            //计算剩余页数

            //设置当前条的开始与结束
            Integer nowStrip = userPage.getNowPage() * 17;
            Integer nowStripEnd = userPage.getNowPage() * 17 + 17;

            //获取数据
            List<User> pageUserList = userDao.getPageUser(nowStrip,nowStripEnd);
            //把数据上传到jsp
            request.setAttribute("pageUserList",pageUserList);
            request.getRequestDispatcher("viwe/html/jsp/UserManagement.jsp").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

~~~

##### 1. 实现用户的添加功能

~~~java
    public void addUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            if (id==null){
                id= String.valueOf(100+userDao.getListUser().size());
            }
            String pass = request.getParameter("pass");
            System.err.println("访问了addUser");
            System.err.println("数据库结果:"+userDao.addUser(Integer.parseInt(id), user, pass));
            if ("registeredUser".equals(request.getParameter("actionType"))){
                response.sendRedirect("viwe/html/Shopping.html");
            }else {
                request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
~~~

##### 2.实现用户的删除功能

~~~java
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        System.err.println("访问了DelSerlet");
        try {
            String id = request.getParameter("id");
            userDao.delUser(Integer.parseInt(id));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
~~~

##### 3.实现用户的修改功能

~~~~java
    public void modifyUser(HttpServletRequest request, HttpServletResponse response){
        try{
            System.out.println("访问了modifyUser");
            String user = request.getParameter("user");
            String id = request.getParameter("id");
            String pass = request.getParameter("pass");
            System.out.println(userDao.upDataUser(user, pass, Integer.parseInt(id)));
            request.getRequestDispatcher("/UserServlet?action=userPage&nowPage="+page).forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
~~~~

##### 4.实现分页功能

~~~java
    public void userPage(HttpServletRequest request, HttpServletResponse response){
        try{

            page = Integer.parseInt(request.getParameter("nowPage"));
            int pageAttr = page==0?1:page;
            request.setAttribute("nowPage2",pageAttr);
            System.err.println("访问了userPage");
            Page<User> userPage = new Page<>();
            //设置当前页数
            userPage.setNowPage(page-1);
            if (userPage.getNowPage()<0){
                userPage.setNowPage(0);
            }
            //获取总条数
            Integer sumSize = Integer.parseInt(userDao.getSumUser()+"");
            request.setAttribute("sumSize",sumSize);

            //计算总页数

            //计算剩余页数

            //设置当前条的开始与结束
            Integer nowStrip = userPage.getNowPage() * 17;
            Integer nowStripEnd = userPage.getNowPage() * 17 + 17;

            //获取数据
            List<User> pageUserList = userDao.getPageUser(nowStrip,nowStripEnd);
            //把数据上传到jsp
            request.setAttribute("pageUserList",pageUserList);
            request.getRequestDispatcher("viwe/html/jsp/UserManagement.jsp").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
~~~

##### 5.jsp代码,table-data代码

~~~jsp
<table id="data-table" class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
            <tr id="xx">
                <th style="text-align: center">id</th>
                <th style="text-align: center">name</th>
                <th style="text-align: center">password</th>
                <th style="text-align: center">操作</th>
            </tr>
            <%--表格主体--%>
            <%for (User user : pageUserList) {%>
            <tr>
                <td>
                    <%=user.getId()%>
                </td>
                <td>
                    <%=user.getName()%>
                </td>
                <td>
                    <%=user.getPassword()%>
                </td>
                <td style="text-align: center">
                    <input class="button is-info" style="width: 50px;height:25px;font-size: 8px;font-family:icomoon;" type="button" id="JieMian<%=user.getId()%>" onclick="modifyUser(<%=user.getId()%>)" value="修改">
                    <input class="button is-danger" style="width: 50px;height: 25px;font-size: 8px;margin-left: 20px; font-family:icomoon;" type="button" onclick="delUser(this,<%=user.getId()%>)" value="删除">
                </td>
            </tr>
            <%}%>
            <%for (int i = 0; i < 17 - pageUserList.size(); i++) {%>
                <tr class="additional">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <%}%>
</table>
~~~

##### 6.js代码

~~~~JavaScript
function del() {
    /* 显示 */
    document.getElementById("delete").style.display = "block";
}

function delesc() {
    /* 隐藏 */
    document.getElementById("delete").style.display = "none";
}

//添加一个用户
function addUser() {
    $("#alertCreat").text("添加一个用户")
    document.getElementById("addId").type = "text"
    document.getElementById("addId").value = ""
    document.getElementById("addName").value = ""
    document.getElementById("addPass").value = ""
    document.getElementById("addTian").style.display = "block";

    //分页方面
    //获取当前页面的值
    var nowPage = $("#nowPageHTML").val()
    var sumPageHTML = $("#nowPageHTML2").val()
    document.getElementById("addQue").addEventListener("click",function (){
        let id = document.getElementById("addId").value
        let name = document.getElementById("addName").value
        let pass = document.getElementById("addPass").value
        document.getElementById("addTian").style.display = "none";
        if (nowPage>=sumPageHTML){

        }
        if (id != "" && name != "" && pass != "") {
            window.location.href = "UserServlet?action=addUser&id=" + id + "&user=" + name + "&pass=" + pass
        } else {
            alert("请输入完整的用户信息！")
        }
    })
    document.getElementById("addQu").addEventListener("click",function (){
        let id = document.getElementById("addId").value
        let name = document.getElementById("addName").value
        let pass = document.getElementById("addPass").value
        document.getElementById("addTian").style.display = "none";
    })
}

//删除一个用户
function delUser(obj, id) {
    document.getElementById("delete").style.display = "block";
    document.getElementById("butQue").addEventListener("click",function (){
        document.getElementById("delete").style.display = "block";
        var pareObj = obj.parentNode.parentNode;
        var dataTable = obj.parentNode.parentNode.parentNode;
        dataTable.removeChild(pareObj);
        window.location.href = "UserServlet?action=delUser&&id=" + id;
    })
    document.getElementById("butQu").addEventListener("click",function (){
        document.getElementById("delete").style.display = "none";
    })
}

//修改一个用户
function modifyUser(pjo){
    $("#alertCreat").text("确定要修改？")
    var duixian = $("#JieMian"+pjo)
    var pass = duixian.parent().prev().text()
    var user = duixian.parent().prev().prev().text()
    var id = duixian.parent().prev().prev().prev().text()
    document.getElementById("addTian").style.display = "block";
    //设置界面的值
    document.getElementById("addId").type = "button"
    document.getElementById("addId").value = $.trim(id)
    document.getElementById("addName").value = $.trim(user)
    document.getElementById("addPass").value = $.trim(pass)

    //确定按钮
    document.getElementById("addQue").addEventListener("click",function (){
        //隐藏该显示窗
        document.getElementById("addTian").style.display = "none";
        //获取界面的值
        let name = document.getElementById("addName").value
        let pass = document.getElementById("addPass").value
        //传参到服务器
        let GeneticId = $.trim(id)
        let GeneticName = $.trim(name)
        let GeneticPass = $.trim(pass)
        window.location.href = "UserServlet?action=modifyUser&id="+GeneticId+"&user="+GeneticName+"&pass="+GeneticPass;
    })

    //取消按钮
    document.getElementById("addQu").addEventListener("click",function (){
        document.getElementById("addTian").style.display = "none";
    })
}
~~~~

#### DAO-层(在登入时候写过了,这里不重复)

~~~java
package com.cyh.dao.impl;

import java.util.List;

import com.cyh.dao.BaseDao;
import com.cyh.pjo.User;

public class UserDao extends BaseDao{
	public User getUser(int id) {
		return queryOneData(User.class, "select * from `user_table` where `id` = ?", id);
	}
	public List<User> getListUser() {
		return queryForList(User.class, "select * from `user_table`");
	}
	public int delUser(int id){
		return queryUpData("DELETE FROM `user_table` WHERE id = ?",id);
	}
	public int addUser(int id,String name,String password){
	    return queryUpData("INSERT INTO `user_table` VALUES(?,?,?)",id,name,password);
	}
	public int upDataUser(String name,String password,int id){
		return queryUpData("UPDATE `user_table` SET `name`=?,`password`=? WHERE `id`=?",name,password,id);
	}
	public List<User> getPageUser(int start,int end) {
		return queryForList(User.class, "SELECT * FROM `user_table` LIMIT ?,?",start,end);
	}
	public Number getSumUser(){
		return (Number) queryForSingleValue("SELECT COUNT(*) FROM `user_table`");
	}

}

~~~



