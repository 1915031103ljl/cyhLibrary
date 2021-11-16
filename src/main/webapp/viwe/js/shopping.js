//热度栏
function heatBarDisplay(){
    const title = $(".heat-introduction h2");//这个是标题
    const p = $(".heat-introduction .introduction");//这个是内容
    //使用ajax请求获取数据
    $.post("http://localhost:8080/caiyuhao2/ShoppingServlet?action=heatBarDisplay",function (datas){
        title.text(datas.title)
        p.text(datas.list)
    },"json")
}

//书籍实体
function bookEntity(){
    //使用ajax请求获取服务器数据
    $.post("http://localhost:8080/caiyuhao2/ShoppingServlet?action=bookEntity",function (datas){
        //将数据放在bookShelfLi里面
       datas.forEach(function(value,i){
           //每一个书架里面的元素
           const bookShelf = `<img src="../img/龙族封面.jpg" width="100px" height="auto" alt="">
                    <br><a class="book-name">${value.name}</a>
                    <br><span style="color:#e74c3c;font-size:20px ;" class="book-title">${value.finalDescriptor}</span>
                    <br><a href="" class="book-author">${value.author}</a><span style="color: #00d1b2">著</span>
                    <br><span class="book-zjxx">最近信息：${value.descriptor}</span>
                    <br><i><span class="book-date">时间：${new Date().getDay()}</span></i>`
           //获取到书籍li每个对象
           var bookShelfLi = $(".xuanHuan li")
           //将bookShelf元素放到bookShelfLi里面
           bookShelfLi.eq(i+1).html(bookShelf)
       })
    },"json")
    //获取每一个值对应的对象
    const bookName = $(".book-name")
    const bookTitle = $(".book-title")
    const bookAuthor = $(".book-author")
    const bookZjxx = $(".book-zjxx")
    const bookDate = $(".book-date")
}
$(function (){
    //热度栏显示
    heatBarDisplay();
    //书架数据
    bookEntity()
})
