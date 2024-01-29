function skipLogin(){
    window.location.href="login.html";
}
function skipRegist() {
    window.location.href = "login.html";
}
function loginOut() {
    //sessions 清除session
    sessionStorage.clear();
    window.location.reload();
}