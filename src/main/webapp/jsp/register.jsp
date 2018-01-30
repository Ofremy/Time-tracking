<%@ include file="headerHome.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Orik
  Date: 04.01.2018
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="register"/></h2>
        <input type="hidden" name="command" value="register">
        <input type="text" class="form-control" name="login" placeholder="<fmt:message bundle="${common}" key="login"/>"
               required="" autofocus=""/>
        <input type="text" class="form-control" name="firstName"
               placeholder="<fmt:message bundle="${common}" key="first.name"/>"/>
        <input type="text" class="form-control" name="lastName"
               placeholder="<fmt:message bundle="${common}" key="last.name"/>"/>
        <input type="password" class="form-control" name="password"
               placeholder="<fmt:message bundle="${common}" key="password"/>" required=""/>
        <input type="password" class="form-control" name="repeatPassword"
               placeholder="<fmt:message bundle="${common}" key="repeat.password"/>" required=""/>
        <font color="red"><c:out value="${error}"/></font>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="sign.in"/></button>
        <a href="http://localhost:8888/jsp/login.jsp" class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message
                bundle="${common}" key="log.in"/></a>
    </form>
</div>
</body>
</html>