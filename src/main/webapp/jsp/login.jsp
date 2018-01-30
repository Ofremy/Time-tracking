<%@include file="headerHome.jsp" %>
<%--
  Created by IntelliJ IDEA.
  PkType: Orik
  Date: 14.11.2017
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="log.in"/></h2>
        <input type="hidden" name="command" value="login">
        <input type="text" class="form-control" name="login"
               placeholder="<fmt:message bundle="${common}" key="login"/>"
               required="" autofocus=""/>
        <input type="password" class="form-control" name="password"
               placeholder="<fmt:message bundle="${common}" key="password"/>" required=""/>
        <font color="red"><c:out value="${error}"/></font>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message bundle="${common}" key="log.in"/>
        </button>
        <a href="http://localhost:8888/jsp/register.jsp" class="btn btn-lg btn-primary btn-block btn-sign-in">
            <fmt:message bundle="${common}" key="sign.up"/>
        </a>
    </form>
</div>
</body>
</html>

