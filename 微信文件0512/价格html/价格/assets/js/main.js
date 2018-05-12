/**
 * Created by tuyao on 2017/7/20.
 */



$(".link-list a").click(function()
{
    $(".link-list a").removeClass("active");
    $(this).addClass("active");
});

$("#addType").click(function()
{

    $("#typeCol").append('<li><div><span>1、大类：</span><select><option>类别一</option></select></div><div class="type-row"><label>品名：</label><div class="checkbox-list"><span><input type="checkbox"><label>Cherries Peaches</label></span><span><input type="checkbox"><label>Cherries Peaches</label></span><span><input type="checkbox"><label>Cherries Peaches</label></span><span><input type="checkbox"><label>Cherries Peaches</label></span><span><input type="checkbox"><label>Cherries Peaches</label></span><input type="text" placeholder="请英文输入，要输入多个类别的请用“；”隔开"> <button class="green-btn delete-btn" type="button">删除</button></div></div></li>');

});

$("#typeCol").on("click",".delete-btn",function()
{

    $(this).parent().parent().parent().remove();

});


$("#addYear").click(function()
{

    $("#yearCol").append('<li><div><span>1、年度：</span><select class="year-col"></select></div><div class="row"><label>进口金额总额：</label><input type="text"><span>（万美元）</span></div><div class="row"><label>销售总额：</label><input type="text"><span>（万美元）</span></div><div class="row"><label>代卖总额：</label><input type="text"><span>（万美元）</span></div><button class="green-btn delete-btn" type="button">删除</button></li>');

    initYearSelect();

});

$("#yearCol").on("click",".delete-btn",function()
{

    $(this).parent().remove();

});


$("#addSupplier").click(function()
{

    $("#supplierCol").append('<div class="row"><span>4、</span><input type="text"><button class="green-btn delete-btn" type="button">删除</button></div>');
});

$("#supplierCol").on("click",".delete-btn",function()
{

    $(this).parent().remove();

});


$("#addMarket").click(function()
{
    $("#marketCol").append('<li><select><option>JACK</option></select><span>B25</span> <button type="button" class="green-btn delete-btn">删除</button></li>')

});

$(".wholesale-market").on("click",".delete-btn",function()
{

    $(this).parent().remove();

});


$(".company-edit").on("click",".zoom-in",function()
{

   $img=$(this).prev().attr("src");
    layer.open({
        type: 1,
        shade: 0.6,
        title: false, //不显示标题
        content: '<img src="'+$img+'"  style="width:300px;display: block;">',
        cancel: function(){

        }
    });
});


$("#submitCheck").click(function()
{

    $("#form").submit();

});


$("#disagreeBtn").click(function()
{
    layer.confirm("是否提交审核不通过？");
});

$("#agreeBtn").click(function() {
    layer.confirm("是否提交审核通过？");
});





