<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/about.css">
  <title>About Us - TastyTasty</title>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="h1"><h1>About Us</h1></div>
    <div class="list">
      <div class="item">About Us</div>
      <div class="description">
        <h3>Segundo proyecto de Programación IV</h3><br>
        <b>Estudiantes:</b>
        <ol>
          <li>David Guevara Sánchez</li>
          <li>Mario Arguello Borge</li>
        </ol>
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
</html>

