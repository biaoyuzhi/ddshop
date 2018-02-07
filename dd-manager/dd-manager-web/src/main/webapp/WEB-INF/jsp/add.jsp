<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceName" name="priceName"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="itemDesc" type="text/plain">商品描述</script>
                </td>
            </tr>
            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>

<script>
    //保存按钮
    function submitForm() {
        $('#itemAddForm').form('submit', {
            //提交后台的谁进行处理
            url: 'item',
            //提交前需要做的处理操作，返回false终止提交
            onSubmit: function () {
                //将前台的元转换为后台的分，设置给隐藏域
                $('#price').val($.trim($('#priceName').val()) * 100);
                //当所有控件校验通过才会返回true，否则返回false
                return $(this).form('validate');
            },
            success: function (data) {
                if (data>0) {
                    /*myttshop.addTab('查询商品','item-list');*/
                    $.messager.confirm('确认', '新增商品成功，是否需要关闭该选项卡？', function (r) {
                        if (r) {
                            $('#tt').tabs('close', '商品添加');
                            $(function () {
                                var title = '产品规格添加';
                                var href = 'item-param-add';
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
                            });
                        }
                    });
                }
            }
        })
    }

    UE.delEditor('container');
    //实例化富文本编辑器
    UE.getEditor('container',{
        serverUrl:'upload'
    });
    $(function () {
        //加载商品类别
        $('#cid').combotree({
            url: 'itemCat/0',
            required: true,
            onBeforeSelect: function (node) {
                var isLeaf = $('#cid').tree('isLeaf', node.target);
                if (!isLeaf) {
                    $.messager.alert('警告', '请选择最终分类！', 'info');
                    return false;
                }else {//从后台取出JSON字符串，转换成JSON对象显示在前端页面
                    var $td = $('#itemAddForm .paramsShow td').eq(1);
                    var $ul = $('<ul>');
                    $td.empty().append($ul);
                    $.post(
                        'itemParam',
                        {'catId':node.id},
                        function (data) {//注意，此处的data为TBItemParam对象，如果只是返回单纯的String的paramData会是乱码
                            var paramData = data.paramData;
                            paramData = JSON.parse(paramData);
                            $.each(paramData,function (i,e) {
                                var $li = $('<li>');
                                var $table = $('<table>');
                                var $tr = $('<tr>');
                                var $td2 = $('<td colspan="2" class="group">'+ e.group +'</td>');
                                $tr.append($td2);
                                $table.append($tr);
                                $li.append($table);
                                $ul.append($li);
                                //遍历分组中的参数
                                if (e.params){
                                    $.each(e.params,function (_i,_e) {
                                        var $tr2 = $('<tr><td class="param">'+ _e +'</td><td><input></td></tr>');
                                        $table.append($tr2);
                                    });
                                }
                            })
                            $('#itemAddForm .paramsShow').show();
                        },
                        'json'
                    );
                }
            },
            onBeforeExpand: function (node) {
                //获取树控件的属性
                var options = $('#cid').combotree('tree').tree('options');
                options.url = 'itemCat/' + node.id;
            }
        });

    });
</script>