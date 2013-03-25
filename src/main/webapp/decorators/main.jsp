<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA- Compatible" content="IE=EmulateIE7"/>
    <title>Demo</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/cx_style.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/yj_style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-validation-1.9.0/jquery.validate.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-validation-1.9.0/localization/messages_cn.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/yj_main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cx_main.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/wbox/wbox/wbox-min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/wbox/wbox-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/top-menu.js"></script>
    <decorator:head/>
</head>

<body>
<decorator:body/>
</body>
</html>
