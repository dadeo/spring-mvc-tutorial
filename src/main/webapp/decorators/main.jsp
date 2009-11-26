<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>

<head>
    <title>
        My Site - <decorator:title default="Welcome!"/>
    </title>
    <decorator:head/>
</head>

<body>
<h1><decorator:title default="Welcome!"/></h1>

<p><decorator:body/></p>

<p><decorator:getProperty property="meta.author" default="dinky"/></p>

<p>
    <small>
            <%
                    StringBuffer printUrl = new StringBuffer();
                    printUrl.append(request.getRequestURI());
                    printUrl.append("?printable=true");
                    if (request.getQueryString()!=null) {
                        printUrl.append('&');
                        printUrl.append(request.getQueryString());
                    }
                %>
        <p align="right">[ <a href="<%= printUrl %>">printable version</a> ]</p>
        <small>
</p>
</body>

</html>