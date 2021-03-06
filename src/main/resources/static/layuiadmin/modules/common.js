/**

 @Name： 公共业务
 @Author：neo

 */
 
layui.define(function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  
  //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  //……
  
  
  
  //退出
  admin.events.logout = function(){
    $.post('/logout',{},function(result){
      if(result.code==0){
        window.location.href='/login';
      }else{
        layer.alert("退出异常");
      }
    });
  };
  /*admin.events.logout = function(){
    $.ajax({
      url:'/logout',
      // data:obj.field,
      type:'post',
      dataType:'json',
      success: function(result){
        if(result.code == 0){
          window.location.href='/login';
        }else{

          layer.alert('退出异常');
        }
      }
    });
  };*/
  
  //对外暴露的接口
  exports('common', {});
});