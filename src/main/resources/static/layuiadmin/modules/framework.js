/**
 @Name：neo 框架js,基础的方法
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
 */
var formatRefrence = function(filed,value){
  try {
    return tableJson.args[filed][value];
  } catch (e) {
    return value;
  }
}

/**
 * 新增
 */
var create=function(opts){
  doCreate(opts.url,opts.title,opts.width,
      opts.height,opts.addButton);
}

/**
 * 执行添加
 */
var doCreate=function(url,title,width,height,addButton){
  layer.open({
    type: 2
    ,title: title?title:'添加'
    ,content: url
    ,maxmin: true
    ,area: [width?width:'500px',height?height:'450px']
    ,btn: [addButton?addButton:'保存', '取消']
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


/**
 * 修改
 */
var edit=function(opts){
  doEdit(opts.url,opts.title,opts.width,
      opts.height,opts.addButton);
}

/**
 * 执行修改
 */
var doEdit=function(url,title,width,height,addButton){
  layer.open({
    type: 2
    ,title: title?title:'添加'
    ,content: url
    ,maxmin: true
    ,area: [width?width:'500px',height?height:'450px']
    ,btn: [addButton?addButton:'保存', '取消']
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