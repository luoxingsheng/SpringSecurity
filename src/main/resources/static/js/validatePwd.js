function validate() {
    var pwd1 = $("#password1").val();
    var pwd2 = $("#password2").val();
    <!-- 对比两次输入的密码 -->
    if(pwd1 == pwd2)
    {
        $("#tishi").html("两次密码相同");
        $("#tishi").css("color","green");
        $("#register").removeAttr("disabled");
    }
    else {
        $("#tishi").html("两次密码不相同");
        $("#tishi").css("color","red")
        $("#register").attr("disabled","disabled");
    }
}