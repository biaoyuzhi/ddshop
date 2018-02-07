<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a id="import" class="easyui-linkbutton">一键导入商品数据到索引库</a>
<span id="importmsg"></span>
<script>
    $(function () {
        $('#import').click(function () {
            $('#import').linkbutton('disable');
            $('#importmsg').html('拼命导入中，请稍等...');
            $.post(
                'item/index',
                function (data) {
                    if (data.success){
                        $('#import').linkbutton('enable');
                        $('#importmsg').html('');
                        $.messager.alert('温馨提示',data.message);
                    }else{
                        $('#import').linkbutton('enable');
                        $('#importmsg').html('');
                        $.messager.alert('温馨提示','导入失败！请刷新后再尝试！');
                    }
                }
            );
        });
    });
</script>
