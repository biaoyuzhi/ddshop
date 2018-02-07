<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title" name="title">
        <label>商品状态：</label>
        <select id="status" name="status" class="easyui-combobox" data-options="panelHeight:'auto'" style="width: 60px">
            <option value="0">全部</option>
            <option value="1">上架</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="rem()" class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">上架</button>
    </div>
</div>
<table id="mytt"></table>
<script>
    function searchForm() {
        $('#mytt').datagrid('load',{
            title: $.trim($('#title').val()),
            status: $('#status').combobox('getValue')
        });
    }
    function add() {
        var title = '商品添加';
        var href = 'add';
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
    function edit() {
        console.log("编辑")
    }
    function rem() {
        var selections = $('#mytt').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('提醒','请先选择需要删除的数据','info');
        }else {
            $.messager.confirm('确认','您确定要删除这些数据吗？',function (r) {
                if (r){
                    var ids = [];
                    for(var i=0;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }
                    $.post(
                        'item/beach/3',
                        {'ids[]':ids},
                        function (data) {
                            if(data>0){
                                $('#mytt').datagrid('reload');
                            }
                        },
                        'json'
                    );
                }
            })
        }
    }
    function down() {
        var selections = $('#mytt').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('提醒','请先选择需要下架的数据','info');
        }else {
            var ids = [];
            for(var i=0;i<selections.length;i++){
                ids.push(selections[i].id);
            }
            $.post(
                'item/beach/2',
                {'ids[]':ids},
                function (data) {
                    if(data>0){
                        $('#mytt').datagrid('reload');
                    }
                },
                'json'
            );
        }
    }
    function up() {
        var selections = $('#mytt').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('提醒','请先选择需要上架的数据','info');
        }else {
            var ids = [];
            for(var i=0;i<selections.length;i++){
                ids.push(selections[i].id);
            }
            $.post(
                'item/beach/1',
                {'ids[]':ids},
                function (data) {
                    if(data>0){
                        $('#mytt').datagrid('reload');
                    }
                },
                'json'
            );
        }
    }
</script>
<script>
    $('#mytt').datagrid({
        toolbar:'#toolbar',
        url: 'items',
        pagination: true, /*开启分页控件*/
        fit: true, /*让分页控件和表头部分浮动在最上层*/
        multiSort:true,/*允许多列排序*/
        columns: [[
            {field:'cb',checkbox:true},
            {field: 'id', title: '商品id', width: 100, align: 'left',sortable:true},
            {field: 'title', title: '商品标题', width: 100,sortable:true},
            {field: 'statusName', title: '所属名称', width: 100},
            {field: 'sellPoint', title: '商品卖点', width: 100},
            {field: 'priceName', title: '商品价格', width: 100},
            {field: 'num', title: '库存数量', width: 100},
            {field: 'barcode', title: '条形码', width: 100},
            {field: 'image', title: '商品图片', width: 100},
            {field: 'cid', title: '所属类目', width: 100},
            {
                field: 'status', title: '商品状态', width: 100, formatter: function (v, r, i) {
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
            {field: 'created', title: '创建时间', width: 100,formatter:function (v,r,i) {
                return moment(v).format('L')
            }},
            {field: 'updated', title: '更新时间', width: 100, align: 'right',formatter:function (v,r,i) {
                return moment(v).format('L')}}
        ]]
    });
</script>