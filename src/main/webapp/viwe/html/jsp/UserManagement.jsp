<%@ page import="com.cyh.dao.impl.UserDao" %>
<%@ page import="com.cyh.pjo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cyh.pjo.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>管理员页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/viwe/css/bulma.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/viwe/css/UserManagement.css">
    <script src="${pageContext.request.contextPath }/viwe/js/UserManagement.js"></script>
    <script src="${pageContext.request.contextPath }/viwe/js/jquery-3.6.0.js"></script>
    <style>
        @font-face {  /* 声明字体 */
            font-family: 'icomoon';
            src:  url('../../fonts/icomoon.eot?8qhwk5');
            src:  url('../../fonts/icomoon.eot?8qhwk5#iefix') format('embedded-opentype'),
            url('../../fonts/icomoon.ttf?8qhwk5') format('truetype'),
            url('../../fonts/icomoon.woff?8qhwk5') format('woff'),
            url('../../fonts/icomoon.svg?8qhwk5#icomoon') format('svg');
            font-weight: normal;
            font-style: normal;
        }
        .aoteman{
            font-family:icomoon;
        }
    </style>
</head>
<body>
<%--获取数据库里面的数据--%>
<%
    List<User> pageUserList = (List<User>) request.getAttribute("pageUserList");
    Integer nowPage2 = (Integer)request.getAttribute("nowPage2");
%>
<%--获取数据结束--%>
<div class="top-hear">
    <a href="http://localhost:8080/caiyuhao2/index.html"><-返回主页</a>
    <input class="add-user" type="button" onclick="addUser()" value="添加">
</div>
<%--数据表单开始--%>
<div class="body">
    <div class="body-left">
        <ul>
            <li>用户账户管理</li>
            <li>书籍信息管理</li>
            <li>网站管理</li>
        </ul>
    </div>
    <div class="table-container data-user">
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
    </div>
</div>

<%--数据表单结束--%>

<%--尾部开始--%>


<div class="end">
    <a id="par-page" href="UserServlet?action=userPage&&nowPage=<%=nowPage2-1%>" class="button">上一页</a>
    <a href="UserServlet?action=userPage&&nowPage=1" class="button">1</a>
    <a href="UserServlet?action=userPage&&nowPage=2" class="button">2</a>
    <a href="UserServlet?action=userPage&&nowPage=3" class="button">3</a>
    <a href="UserServlet?action=userPage&&nowPage=4" class="button">4</a>
    <a href="UserServlet?action=userPage&&nowPage=5" class="button">5</a>
    <input id="nowPageHTML" type="hidden" name="nowPageHTML" value="<%=nowPage2%>">
    <input id="nowPageHTML2" type="hidden" name="nowPageHTML" value="<%=request.getAttribute("sumSize")%>">
    <input type="text" class="button is-text page-text" placeholder="当前：<%=request.getAttribute("nowPage2")%>" >
    <a id="next-page" href="UserServlet?action=userPage&&nowPage=<%=nowPage2+1%>" class="button">下一页</a>
</div>
<script>
    var sumSize = <%=request.getAttribute("sumSize")%>;
    var sumSizeQuZhen = Math.ceil(sumSize/17);
    var nowPageJs = <%=nowPage2%>;
    if ( nowPageJs >= sumSizeQuZhen){
        $("#next-page").attr("href","#");
    }else {
        $("#next-page").attr("href","UserServlet?action=userPage&&nowPage=<%=nowPage2+1%>");
    }
</script>
<%--尾部结束--%>

<%--自定义提示框--%>
<!-- 删除确认框 -->
<div id="delete" style="border: 1px solid;width: 100%;margin: auto;height: 100%;position: fixed;left: 0;top: 0;background: rgb(0,0,0,0.6);overflow: auto;text-align: center;display: none;">

    <div style="background: white;width: 250px;height: 150px;margin: auto;margin-top: 50px;border-radius: 5px;">

        <div style="height: 110px;border-bottom: 1px solid #CCCCCC;">
            <!-- 框内内容 -->
            <div style="font-size: 0.9rem;padding-top: 30px;">您确定要删除该用户吗？</div>
            <div style="font-size: 0.8rem;margin-top: 15px;">一旦删除将不可恢复。</div>
        </div>

        <div style="height: 39px;">
            <div id="butQue"
                 style="float: left;width: 49%;height: 39px;border-right: 1px solid #CCCCCC;line-height: 39px;font-size: 1rem;">
                确认
            </div>
            <div id="butQu" style="float: right;width: 50%;height: 39px;line-height: 39px;font-size: 1rem">
                取消
            </div>
        </div>

    </div>
</div>

<%--创建和修改提示框--%>
<div id="addTian" class="addTian-div" style="">
    <div class="prompt-box">
        <div class="alert-top">
            <!-- 框内内容 -->
            <div id="alertCreat" class="alert-creat tag is-primary is-light"></div>
            <br>
            <span class="tag is-light">id:</span><input class="add-id input is-white" id="addId" type="text"><br>
            <span class="tag is-light">name:</span><input class="input is-focused" id="addName" type="text"><br>
            <span class="tag is-light">password</span><input class="input is-focused" id="addPass" type="password"><br>
        </div>
        <div style="height: 39px;">
            <div class="determine" id="addQue">
                确认
            </div>
            <div class="cancel" id="addQu">
                取消
            </div>
        </div>

    </div>
</div>

<%--自定义对话框结束--%>
</body>
</html>
