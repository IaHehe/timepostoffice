/**
 * Created by pc-asus on 2016/9/21.
 */
$(function () {

    //个人设置下拉菜单
    $("#user").mouseenter(function () {
        $(".user-ul").slideDown(300);
    })
    $(".user-ul").mouseleave(function () {
        $(".user-ul").slideUp(300);
    })


    //轮播图
    var banner =document.getElementById("banner");
    var ul=banner.children[0];
    var lis=ul.children;
    var num=Math.ceil(1000/lis.length);//每张图片的宽度

    // 除第一项外所有定位
    for(var i=0;i<lis.length;i++){
        lis[i].style.left=num*i+"px";
    }

    for(var i=0;i<lis.length;i++){
        lis[i].index=i;
        lis[i].onmouseover= function () {
            for(var i=0;i<lis.length;i++){
                if(i<=this.index){
                    animate(lis[i],{left:i*75}); //当前图片左侧的图片左移的距离
                }else {
                    animate(lis[i],{left:1000-(lis.length-1)*75+(i-1)*75}); //当前图片右侧的图片左移的距离
                }
            }
            $(".mask").eq(this.index).fadeIn(500);
        }
        lis[i].onmouseout=function(){
            for(var i=1;i<lis.length;i++){
                animate(lis[i],{left:num*i});
            }
            $(".mask").hide();
        }
    }


})



