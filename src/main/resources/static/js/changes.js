function changes(lans){
$.ajax({
    async : false,
    type : 'POST',
    url : "/logins/i18n",
    data : {lan:lans},
    dataType : 'json',
    success : function(data) {
        console.log("修改语言成功");
    },
    error : function(result) {
        alert('服务器异常');
    }
});
}