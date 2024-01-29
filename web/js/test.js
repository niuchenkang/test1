function rec(){
    var mymessage=    confirm("你是女的吗？")     ;
    if(mymessage==true){
        document.write("你是女士!");
    }
    else{
        document.write("你是男士!");
    }
}
<body>
<input name="button" type="button" onClick="rec()" value="点击我，弹出确认对话框" />
</body>
