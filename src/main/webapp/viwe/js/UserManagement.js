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