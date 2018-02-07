<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="paramListToolbar">
    <div>
        <button type="button" onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button type="button" onclick="del()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
    </div>
</div>
<table id="dgParamList"></table>

<script>
    $(function(){

        //列表
        $('#dgParamList').datagrid({
            method: 'get',
            url:'itemParams',
            fit: true,
            pagination: true,
            pageSize:20,
            toolbar: '#paramListToolbar',
            columns:[[
                {field:'ck', checkbox: true},
                {field:'id',title:'ID', sortable: true},
                {field:'itemCatName',title:'商品类目'},
                {field:'paramData',title:'规格',formatter:function (v) {
                    var obj = JSON.parse(v);
                    var array = [];
                    $.each(obj,function (i,e) {
                        array.push(e.group);
                    });
                    return array;
                }},
                {field:'created',title:'创建日期', formatter:function(value,row,index){
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }},
                {field:'updated',title:'更新日期', formatter:function(value,row,index){
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }}
            ]]

        });
    });
    function add(){
        myttshop.addTab('新增规格参数', 'item-param-add');
    }
    function edit(){

    }
    function del(){

    }
</script>