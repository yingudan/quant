<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
<style type="text/css">
#errorbox{background:url(${pageContext.request.contextPath}/images/web/error.png) no-repeat center;cursor:pointer;}
</style>
<div id="errorbox" onclick="javascript:parent.location.reload();"></div>
<script type="text/javascript">
autodivheight();
function autodivheight(){
    var winHeight=0;
    if(window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;
    document.getElementById("errorbox").style.height= winHeight+"px";
}
window.onresize=autodivheight;
</script>
</body>
</html>