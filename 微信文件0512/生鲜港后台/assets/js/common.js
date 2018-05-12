/**
 * Created by tuyao on 2017/7/13.
 */

function initYearSelect()
{
$(".year-col").each(function()
{
    if($(this).find("option").length==0)
    {
        var yearStr=new Date().getFullYear();
        for(var i=yearStr;i>=1970;i--)
        {
            $(this).append("<option>"+i+"</option>");

        }
    }

})
}
initYearSelect();


var $loading={
    show:function()
    {
        $("body").append('<div class="loading-col"><div class="loading-img"></div></div>');
    },
    hide:function()
    {

        $('.loading-col').remove();
    }

}

