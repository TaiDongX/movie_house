
function login() {
    var s ="loginName="+$("#username").val();
    s +="&passWord="+$("#password").val();
    s += "&remember="+$("#remember").is(':checked');
    $.ajax({
        type: "GET",
        url: "/user/login",
        data: s,
        success: function(msg){
            alert(msg);
            if(msg === '登录成功'){
                window.location.reload();
            }
        }
    });
}

function exit(){
    $.ajax({
        type: "GET",
        url: "/user/userExit",
        success: function(msg){
            alert(msg);
            window.location.reload();
        }
    });
}

var isOk = false;
var repOk = false;
$(function() {

    checkUser();

    $("#username").blur(function () {
       checkUser() ;
    });


    $("#username-2").blur(function() {
        //获取用户名
        var name = $("#username-2").val();
        if(name !== ""){
            $.ajax({
                type : "get",
                url : "/user/userNameIsExist",
                data : "loginName=" + name,
                success : function(msg) {
                    if (msg !== "false") {
                        var reg = /^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$/;
                        if(!reg.test(name)){
                            $("#msg").html("格式不正确").css("color","red");
                            isOk = false;
                        }
                        else{
                            //名称可用
                            $("#msg").html("名称可用").css("color","green");
                            isOk = true;
                        }
                    }
                    else {
                        $("#msg").html("名称已存在").css("color","red");
                        isOk = false;
                    }
                }
            });
        }
        else{
            $("#msg").html("");
            isOk = false;
        }
    });
    $("#password-2").blur(function(){
        var pwd = $("#password-2").val();
        var reg1=/^[a-zA-Z]\w{5,17}$/;
        if(!reg1.test(pwd)){
            $("#msg1").css("color","red").html("密码格式不正确");
            repOk = false;
        }
        else{
            $("#msg1").html("");
        }
    });
    //确认密码验证
    $("#repassword-2").blur(function(){

        var pwd = $("#password-2").val();
        var repwd = $("#repassword-2").val();
        if(pwd === ""){
            repOk = false;
            return;
        }
        if (pwd !== repwd) {
            $("#msg1").css("color","red").html("两次密码不一致");
            isOk = false;
            repOk = false;
        }
        else {
            $("#msg1").css("color","green").html("密码验证成功");
            isOk = true;
            repOk = true;
        }
    });
    $("#code").blur(function(){
        var code =$("#code").val();
        if(code !== ""){
            $("#msg2").html("");
        }
    });

});

function register(){
    var s = '';
    var code = $("#code").val();
    var name = $("#username-2").val();
    s+='loginName=' + name;
    s+='&password=' + $("#password-2").val();
    s+='&mobile=' + $("#phoneNum").val();
    s+='&code=' + code;
    if(name === ""){
        alert("用户名不能为空!");
    }
    else if(!repOk||!isOk){
        alert("验证未通过，请按照要求填写信息");
    }
    else if(code === ""){
        $("#msg2").css("color","red").html("验证码不能为空");
    }
    else{

        $.ajax({
            type : "GET",
            url : "/user/register",
            data : s,
            success : function(msg) {
                alert(msg);
                if("注册成功！"=== msg){
                    window.location.reload();
                }
            }
        });
    }
}

function checkUser(){
   var s = $("#username").val();
    $.ajax({
        type : "GET",
        url : "/user/checkUser",
        data : 'loginName='+s,
        dataType:'json',
        success : function(user) {
            if(user.loginName != null){
                $("#username").val(user.loginName)
            }
            else{
                $("#remember").removeAttr('checked','checked');
            }
            if(user.password != null){
                $("#password").val(user.password);
                $("#remember").attr('checked','checked');
            }
            else{
                $("#password").val('');
            }
        }
    });
}