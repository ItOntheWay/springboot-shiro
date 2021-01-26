/**
 @Name：neo 框架js,基础的方法
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
 */
var tableJson = new Array();

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
      ,table = layui.table
      ,form = layui.form;

  //监听工具条
  table.on('tool(LAY-entity-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.prompt({
        formType: 1
        ,title: '敏感操作，请验证口令'
      }, function(value, index){
        layer.close(index);

        layer.confirm('真的删除行么', function(index){
          obj.del();
          layer.close(index);
        }) ;
      });
    } else if(obj.event === 'edit'){
      var sta = formatRefrence('allStatuss','1')
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑用户'
        ,maxmin: true
        ,content:'/manage/system/user/edit?id='+data.id
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
              ,submitID = 'LAY-user-front-submit'
              ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段

            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            table.reload('LAY-user-front-submit'); //数据刷新
            layer.close(index); //关闭弹层
          });

          submit.trigger('click');
        }
        ,success: function(layero, index){

        }
      });
    }
  });

  //监听搜索
  form.on('submit(LAY-entity-front-search)', function(data){
    var field = data.field;
    //执行重载
    table.reload('LAY-entity-manage', {
      where: field
    });
  });

  //事件
  var active = {
    batchdel: function(){
      var checkStatus = table.checkStatus('LAY-entity-manage');
      var checkData = checkStatus.data; //得到选中的数据
      if(checkData.length === 0){
        return layer.msg('请选择数据');
      }
      layer.confirm('确定删除吗？', function(index) {
        //执行 Ajax 后重载
        /*
        admin.req({
          url: 'xxx'
          //,……
        });
        */
        table.reload('LAY-entity-manage');
        layer.msg('已删除');
      });
    }
    ,add: function(){
      layer.open({
        type: 2
        ,title: '添加用户'
        ,content: '/manage/system/user/create'
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
              ,submitID = 'LAY-entity-front-submit'
              ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段

            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            table.reload('LAY-entity-front-submit'); //数据刷新
            layer.close(index); //关闭弹层
          });

          submit.trigger('click');
        }
      });
    }
  };

  $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });

  exports('framework', {})
});

var formatRefrence = function(filed,value){
  try {
    return tableJson.args[filed][value];
  } catch (e) {
    return value;
  }
}

function entityAdd(title,url){
  layer.open({
    type: 2
    ,title: '添加用户'
    ,content: '/manage/system/user/create'
    ,maxmin: true
    ,area: ['500px', '450px']
    ,btn: ['确定', '取消']
    ,yes: function(index, layero){
      var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-entity-front-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

      //监听提交
      iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
        var field = data.field; //获取提交的字段

        //提交 Ajax 成功后，静态更新表格中的数据
        //$.ajax({});
        table.reload('LAY-entity-front-submit'); //数据刷新
        layer.close(index); //关闭弹层
      });

      submit.trigger('click');
    }
  });
}