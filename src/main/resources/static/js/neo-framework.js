/**
 * 基础框架功能封装
 *
 * @author neo
 * @date 2019-6-15
 * @version: 1.0.0
 * @required layui
 */

(function ($) {
  $.extend({
    neo: {
      framework:{
        layer:null
      }
    }
  });
  $.extend($.neo,
      {
        framework: {
          /**
           * 新增
           */
          create: function (opts) {
            $.neo.framework.doCreate(opts.table, opts.url, opts.saveUrl, opts.title, opts.width,
                opts.height, opts.addButton);
          },

          /**
           * 执行添加
           */
          doCreate: function (table, url, saveUrl, title, width, height, addButton) {
            parent.layer.open({
              type: 2
              , title: title ? title : '添加'
              , content: url
              , maxmin: true
              , area: [width ? width : '600px', height ? height : '500px']
              , btn: [addButton ? addButton : '保存', '取消']
              , yes: function (index, layero) {
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var submitID = 'LAY-entity-front-submit';
                var submit = layero.find('iframe').contents().find('#' + submitID);
                //监听提交
                iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                  var editing_index = parent.layer.load(2, {
                    shade: [0.1,'#grad']
                  });
                  var field = data.field; //获取提交的字段
                  $.ajax({
                    url: saveUrl,
                    data: field,
                    type: 'post',
                    dataType: 'json',
                    success: function (result) {
                      parent.layer.close(editing_index);
                      if (result.success) {
                        table.reload('LAY-entity-manage'); //数据刷新
                        parent.layer.close(index); //关闭弹层
                      } else {
                        parent.layer.alert('保存异常:' + result.msg);
                      }
                    }
                  });
                });
                submit.trigger('click');
              }
            });
          },
          edit: function (opts) {
            $.neo.framework.doEdit(opts.table, opts.url, opts.updateUrl, opts.title, opts.width,
                opts.height, opts.addButton);
          },
          /**
           * 执行添加
           */
          doEdit: function (table, url, updateUrl, title, width, height, addButton) {
            parent.layer.open({
              type: 2
              , title: title ? title : '编辑'
              , content: url
              , maxmin: true
              , area: [width ? width : '500px', height ? height : '450px']
              , btn: [addButton ? addButton : '保存', '取消']
              , yes: function (index, layero) {
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                var submitID = 'LAY-entity-front-submit';
                var submit = layero.find('iframe').contents().find('#' + submitID);
                //监听提交
                iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                  var editing_index = parent.layer.load(2, {
                    shade: [0.1,'#grad']
                  });
                  var field = data.field; //获取提交的字段
                  //提交 Ajax 成功后，静态更新表格中的数据
                  //$.ajax({});
                  $.ajax({
                    url: updateUrl,
                    data: field,
                    type: 'post',
                    dataType: 'json',
                    success: function (result) {
                      parent.layer.close(editing_index);
                      if (result.success) {
                        table.reload('LAY-entity-manage'); //数据刷新
                        parent.layer.close(index); //关闭弹层
                      } else {
                        parent.layer.alert('保存异常:' + result.msg);
                      }
                    }
                  });
                });
                submit.trigger('click');
              }
            });
          },
          open: function (opts) {
            $.neo.framework.doOpen(opts.url, opts.title, opts.width, opts.height);
          },
          doOpen: function (url, title, width, height) {
            parent.layer.open({
              type: 2
              , title: title ? title : '添加'
              , content: url
              , maxmin: true
              , area: [width ? width : '500px', height ? height : '450px']
            });
          },
          serializeObject : function(form) {
            var o = {};
            $.each(form.serializeArray(), function(index) {
              if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
              } else {
                o[this['name']] = this['value'];
              }
            });
            return o;
          },
          //打开导入页面
          openImportPage : function(opts) {
            parent.layer.open({
              type : 2,
              area : [opts.width?opts.width:'800px',opts.height?opts.height:'500px'],
              title :opts.title?opts.title:'导入选课学生名单',
              shade : 0.6,
              maxmin : true, // 允许全屏最小化
              anim : -1, // 0-6的动画形式，-1不开启
              content : '/manage/system/importRecord/importPage?type='+opts.type,
              cancel: function (index, layero) {//取消事件
                console.info('取消事件')
                opts.table.reload('LAY-entity-manage'); //数据刷新
              }
            });
          },
          exports : function(url, searchForm, fileName) {
            Date.prototype.Format = function (fmt) { //author: meizz
              var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
              };
              if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
              for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
              return fmt;
            }
            var queryParams = $.neo.framework.serializeObject($('#'+ searchForm));
            $(queryParams).attr('exportFileName',fileName + '-' + (new Date()).Format("yyyyMMdd"));
            $.neo.framework.createAndSubmitForm(url, queryParams);
          },

          /**
           * 动态创建表单和提交表单
           */
          createAndSubmitForm : function(url, inputObjects) {
            var form = $('<form action="' + url + '" method="POST" target="_blank"></form>');
            for ( var key in inputObjects) {
              if (inputObjects[key] != '') {
                var inputForm = $('<input type="hidden" name="' + key + '" value="' + inputObjects[key] + '" />');
                form.append(inputForm);
              }
            }
            form.appendTo($('body')).submit();
            form.remove();
          }


        }
      });
})(jQuery);

var tableJson = new Array();
var formatRefrence = function (filed, value) {
  try {
    return tableJson.args[filed][value];
  } catch (e) {
    return value;
  }
}