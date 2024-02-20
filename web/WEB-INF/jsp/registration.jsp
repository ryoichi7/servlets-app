<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/images/users/qwe.jpg" alt="User image">

    <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
      <label for="username"> username:
        <input type="text" name="username" id="username">
      </label>
      <br>
      <label for="birthday"> birthday:
        <input type="date" name="birthday" id="birthday">
      </label>
      <br>
      <label for="image"> image:
        <input type="file" name="image" id="image">
      </label>
      <br>
      <label for="email"> email:
        <input type="text" name="email" id="email">
      </label>
      <br>
      <label for="password"> password:
        <input type="password" name="password" id="password">
      </label>
      <br>
      <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
          <option value="${role}">${fn:toUpperCase(role)}</option>
        </c:forEach>
      </select>
      <br>
      <c:forEach var="gender" items="${requestScope.genders}">
        <label for="gender"> ${fn:toUpperCase(gender)}
          <input type="radio" id="gender" name="gender" value="${gender}">
        </label>
        <br>
      </c:forEach>
      <button type="submit">Send</button>
      <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
          <c:forEach var="error" items="${requestScope.errors}}">
            <span>${error}</span>
            <br>
          </c:forEach>
        </div>
      </c:if>
    </form>
</body>
</html>
