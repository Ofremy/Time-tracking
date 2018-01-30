<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="submit.accept"/></h2>
        <input type="hidden" name="command" value="accept">
        <input readonly type="hidden" class="form-control" name="id"
               value="<c:out value="${id}"/>"/>
        <label><fmt:message bundle="${common}" key="activity.name"/></label>
        <input readonly type="text" class="form-control" name="activityName"
               value="<c:out value="${activityName}"/>"/>
        <label><fmt:message bundle="${common}" key="login"/></label>
        <input readonly type="text" class="form-control" name="login"
               value="<c:out value="${login}"/>"/>
        <label><fmt:message bundle="${common}" key="th.description"/></label>
        <input readonly type="text" class="form-control" name="description"
               value="<c:out value="${description}"/>"/>
        <label><fmt:message bundle="${common}" key="th.status"/></label>
        <input readonly type="text" class="form-control" name="status"
               value="<c:out value="${status}"/>"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="submit"/></button>
        <a href="http://localhost:8888/jsp/admin.jsp" class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message
                bundle="${common}" key="cancel"/></a>
    </form>
</div>
</body>
</html>