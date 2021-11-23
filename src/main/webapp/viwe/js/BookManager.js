var yema = 1
var sumPage
$(function () {
    xuanran()
    $("#show").click(function () {
        $(".ytqwe").toggle()
    })
})

function submit() {
    const inputs = $("#formData3 input");
    const action = inputs.eq(0).val();
    console.log(action)
    let id = inputs.eq(1).val()
    console.log(action)
    let name = inputs.eq(2).val()
    let author = inputs.eq(3).val()
    let category = inputs.eq(4).val()
    let ajaxUrl = "BooksServlet?action=" + action + "&id=" + id + "&name=" + name + "&author=" + author + "&category=" + category
    $.post(ajaxUrl, function () {
        returnTozero()
        xuanran()
    })
}

function returnTozero() {
    const inputs = $("#formData3 input");
    const id = inputs.eq(1).val("")
    const name = inputs.eq(2).val("")
    const author = inputs.eq(3).val("")
    const category = inputs.eq(4).val("")
}

function xuanran() {
    $("#formData3 input").eq(0).val("add")
    $.post("BooksServlet?action=pageList&yema="+yema, function (data) {
        $("#formData").find(".dataUl").remove()
        console.log(yema)
        sumPage = data.sumPage
        console.log(data)
        console.log(sumPage)
        console.log(data.list)
        data.list.forEach(function (book, index) {
            $("#formData").append(` <tr class="dataUl">
                <td style="width: 150px;height: 50px;"> ${book.id} </td>
                <td style="width: 150px;height: 50px;"> ${book.name}</td>
                <td style="width: 150px;height: 50px;"> ${book.author}</td>
                <td style="width: 150px;height: 50px;"> ${book.category}</td>
                <td>
                <button class="button yt-bt is-danger" onclick="del(${book.id})">删除</button>
                <button class="button yt-bt" onclick="getOneBooks(${book.id})">修改</button>
                </td>
            </tr>`)
        })
    }, "json")
}

function del(id) {
    $(".modal").addClass("is-active")
    $("#card-del").click(function (){
        $(".modal").removeClass("is-active")
        $.post("BooksServlet?action=del&id=" + id, function (data) {
            xuanran()
        })
    })
}
function Return() {
    $("#formData3 input").eq(0).val("add")
    returnTozero()
}

function getOneBooks(id) {
    $.post("BooksServlet?action=getOneBooks&id=" + id, function (data) {
        var inputs = $("#formData3 input")
        inputs.eq(0).val("xiugai")
        inputs.eq(1).val(data.id)
        inputs.eq(2).val(data.name)
        inputs.eq(3).val(data.author)
        inputs.eq(4).val(data.category)
    }, "json")
}

function delDiv() {
    $(".modal").removeClass("is-active")
}
function nextPage(){
    yema++
    if (yema>sumPage){
        alert("不能再翻了")
        yema = sumPage
    }
    xuanran()
}
function prePage(){
    yema--
    if (yema<=0){
        alert("想啥呢？")
        yema = 1
    }
    xuanran()
}
function indexPage(index){
    yema = index
    xuanran()
}