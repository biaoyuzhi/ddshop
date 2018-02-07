<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bootstrap-table</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="bootstrap-table/dist/bootstrap-table.css">
    <%--jQuery文件。务必在bootstrap.min.js 之前引入 --%>
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">&lt;%&ndash;轮播图需要该版本&ndash;%&gt;--%>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <%--<script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="bootstrap-table/dist/bootstrap-table.js"></script>
    <script src="bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>

    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 20%;
        }
    </style>
</head>
<body>
<%--提示框功能--%>
<div class="container">
    <h3>提示框实例</h3><br>
    <a href="#" data-toggle="tooltip" data-placement="top" title="我是提示内容!">鼠标移动到我这</a><br>
    <a href="#" data-toggle="tooltip" data-placement="bottom" title="我是提示内容!">鼠标移动到我这</a>
</div>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<%--内容折叠效果的实现，注意button或a标签中data-target中的值指定折叠div的id值--%>
<button data-toggle="collapse" data-target="#demo1">折叠</button>
<div id="demo1" class="collapse">
    Lorem ipsum dolor text....
</div>
<%--轮播图的实现--%>
<div id="demo11" class="carousel slide" data-ride="carousel">
    <!-- 指示符 -->
    <ul class="carousel-indicators">
        <li data-target="#demo11" data-slide-to="0" class="active"></li>
        <li data-target="#demo11" data-slide-to="1"></li>
        <li data-target="#demo11" data-slide-to="2"></li>
    </ul>
    <!-- 轮播图片 -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="http://static.runoob.com/images/mix/img_fjords_wide.jpg">
        </div>
        <div class="carousel-item">
            <img src="http://static.runoob.com/images/mix/img_nature_wide.jpg">
        </div>
        <div class="carousel-item">
            <img src="http://static.runoob.com/images/mix/img_mountains_wide.jpg">
        </div>
    </div>
    <!-- 左右切换按钮 -->
    <a class="carousel-control-prev" href="#demo11" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo11" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>
<%--alter弹窗的美化--%>


<table id="table"></table>
<script>//可以实现页码和分类中的下拉框操作是由于jQuery引入的版本号要兼容
$('#table').bootstrapTable({
    url: 'boot-items',
    sidePagination: "server",
    pagination: true,
    searchOnEnterKey: true,
    search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    showColumns: true,     //是否显示所有的列，能让表头固定在最上面
    clickToSelect: true,    //是否启用点击选中行
    height: 630,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度,该设置可使分页按钮固定在最上面
//                detailView: true,     //是否显示父子表，每行最前面会加一个折叠按钮
    showRefresh: true,     //是否显示刷新按钮
    columns: [
        {field: 'cb', checkbox: true},
        {field: 'id', title: '商品id', heigh: 20, align: 'left'},
        {field: 'title', title: '商品标题'},
        {field: 'statusName', title: '所属名称'},
        {field: 'sellPoint', title: '商品卖点'},
        {field: 'price', title: '商品价格'},
        {field: 'num', title: '库存数量'},
        {field: 'barcode', title: '条形码'},
        {field: 'image', title: '商品图片'},
        {field: 'cid', title: '所属类目'},
        {
            field: 'status', title: '商品状态', formatter: function (v, r, i) {
            switch (v) {
                case 1:
                    return '上架';
                    break;
                case 2:
                    return '下架';
                    break;
                case 3:
                    return '删除';
                    break;
                default:
                    return '未知';
                    break;
            }
        }
        },
        {field: 'created', title: '创建时间'},
        {field: 'updated', title: '更新时间', align: 'right'}
    ],
});
</script>
</body>
</html>
