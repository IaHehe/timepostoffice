<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 写一封信</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/email.css">
    <script src="js/jquery.js"></script>
    <script src="js/laydate.js"></script>
    <script src="js/jquery-ui-1.7.2.custom.min.js"></script>
    <link rel="Stylesheet" href="css/jquery-ui-1.7.2.custom.css" />
    <script src="js/jHtmlArea-0.8.js"></script>
    <link rel="Stylesheet" href="css/jHtmlArea.css" />

</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<!--email-->
<div class="main">
    <div class="w">
        <div class="email">
            <form action="${clientBase }/timeLetter/email" method="POST">
                <label>标题</label><input name="title" type="text" placeholder="请输入标题"><br>
                <label>寄出时间</label><input name="sendtime" type="text" placeholder="请输入寄出时间" class="laydate-icon sendtime" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"><br>
                <label>添加图片</label><img class="imgs">
                <input name="imgs" type="file" style="display: none" class="imgFile" accept="image/*">
                <input type="button" class="img-email" value="选择" ><br>
                <textarea name="content" id="txtDefaultHtmlArea"></textarea><br>
                <label>收件人邮箱</label><input name="recipientemail" type="text" placeholder="请输入收件人邮箱"><br>
                <label>您的邮箱</label><input name="senderemail" type="text" placeholder="请输入您的邮箱"><br>
                <input name="timelisopen" type="checkbox" class="check">在您设定的寄出时间后，其他用户可以看到这封信（推荐）<br>
                <input type="submit" value="封笺" class="send-btn">
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<script>

    //邮件图片显示及上传
    $(".img-email").click(function () {
        $(".imgFile").click();
    })
    $(".imgFile").change(function () {
        var file=this.files[0]?this.files[0]:null;
        if(!file){
            return false;
        }
        var file_reader=new FileReader();
        file_reader.onload=function(){
            var img_url=this.result;
            $(".imgs").show();
            $(".imgs").attr("src",img_url);
        }
        file_reader.readAsDataURL(file);
    })
    /* $(".send-btn").click(function () {
        AjaxImgFileUpload();
    })

    function AjaxImgFileUpload() {
        $.ajaxImgFileUpload(
                {
                    url: "/System/imgFileUpload",//用于上传文件方法的地址
                    secureuri: false,
                    fileElementId: "imgFile",//上传控件的Name值
                    dataType: "Json",//返回数据类型
                    success: function (data, status)//服务器响应成功处理函数
                    {
                        alert("成功");
                    }
                })
    }
 */

    //富文本格式化
    // You can do this to perform a global override of any of the "default" options
    // jHtmlArea.fn.defaultOptions.css = "jHtmlArea.Editor.css";
    $(function() {
        //$("textarea").htmlarea(); // Initialize all TextArea's as jHtmlArea's with default values

        $("#txtDefaultHtmlArea").htmlarea(); // Initialize jHtmlArea's with all default values
        $("#txtCustomHtmlArea").htmlarea({
            // Override/Specify the Toolbar buttons to show
            toolbar: [
                ["bold", "italic", "underline", "|", "forecolor"],
                ["p", "h1", "h2", "h3", "h4", "h5", "h6"],
                ["link", "unlink", "|", "image"],
                [{
                    // This is how to add a completely custom Toolbar Button
                    css: "custom_disk_button",
                    text: "Save",
                    action: function(btn) {
                        // 'this' = jHtmlArea object
                        // 'btn' = jQuery object that represents the <A> "anchor" tag for the Toolbar Button
                        alert('SAVE!\n\n' + this.toHtmlString());
                    }
                }]
            ],

            // Override any of the toolbarText values - these are the Alt Text / Tooltips shown
            // when the user hovers the mouse over the Toolbar Buttons
            // Here are a couple translated to German, thanks to Google Translate.
            toolbarText: $.extend({}, jHtmlArea.defaultOptions.toolbarText, {
                "bold": "fett",
                "italic": "kursiv",
                "underline": "unterstreichen"
            }),

            // Specify a specific CSS file to use for the Editor
            css: "style//jHtmlArea.Editor.css",

            // Do something once the editor has finished loading
            loaded: function() {
                //// 'this' is equal to the jHtmlArea object
                //alert("jHtmlArea has loaded!");
                //this.showHTMLView(); // show the HTML view once the editor has finished loading
            }
        });
    });
</script>
    