<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css"/>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;" align="center"><h1>天天电器小商城
    <input type="button" onclick="fn()" value="测试JSONP"/></h1></div>
<div data-options="region:'west',title:'West',split:true" style="width:120px;">
        <div id="aa" class="easyui-accordion" style="width:120px;height:90%;">
            <div title="Title1" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
                <ul class="easyui-tree">
                    <li data-options="attributes:{'href':'add'}">商品添加</li>
                    <li data-options="attributes:{'href':'list'}">商品列表</li>
                    <li data-options="attributes:{'href':'item-param-add'}">产品规格添加</li>
                    <li data-options="attributes:{'href':'index-item'}">导入索引库</li>
                </ul>
            </div>
            <div title="Title2" data-options="iconCls:'icon-reload'" style="padding:10px;">
                content2
            </div>
            <div title="Title3" style="padding:10px;">
                content3
            </div>
        </div>
</div>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
    <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
        <div title="Tab1" data-options="closable:true" style="padding:20px;display:none;">
            <h1 style="color: #00F7DE;">欢迎来到天天电器商城，这里是展示页面，操作请点左边下拉框</h1>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment-with-locales.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utf8-jsp/ueditor.all.js"></script><%--该行js引入影响了添加页面的样式，不能缺少--%>

<script>
    $('#aa .easyui-tree').tree({
        onClick: function(node){
            //从节点中获取href属性
            var href = node.attributes.href;
            var title = node.text;
            var exist = $('#tt').tabs('exists',title);
            if(!exist && href != undefined){
                //添加新的选项卡
                $('#tt').tabs('add',{
                    title: title,
                    closable: true,
                    href: href
                });
            }
            else {
                $('#tt').tabs('select',title);
            }
        }
    });
</script>
<script>
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage') {
            return 'http://localhost:8080/shop/upload';
        }else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
<script>
    function fn() {
        $.ajax({
            url:'https://api.douban.com/v2/book/search?q=javascript&count=1',
            dataType:'jsonp',
            success:function (data) {
                console.log(data);
                alert(data.total)
            },
            async:true,
            type:'GET',
            jsonpCallback:'my'
        })
    }
</script>
</body>
</html>
