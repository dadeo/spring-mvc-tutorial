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

</body>

</html>