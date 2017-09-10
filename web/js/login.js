/**
 * Created by Administrator on 2017/9/5.
 */
var login_url = "/webstu/login/login.do";
function login() {
    var name = $("#name").val();
    var password = $("#password").val();
    $.ajax({
            type: "POST",
            url: login_url,
            data: "name=" + name + "&password=" + password,
        success: function(data, status, xhr) {
                var url = xhr.getResponseHeader("login-success");
                if(url!=undefined)
                    location.href = url;
                else{
                    var JSON = $.parseJSON(data);
                    location.href = JSON.url;
                }
        }
        }
    );
}