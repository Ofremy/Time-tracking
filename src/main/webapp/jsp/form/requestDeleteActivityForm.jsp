<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="submit.delete"/></h2>
        <input type="hidden" name="command" value="submit_delete">
        <label><fmt:message bundle="${common}" key="activity.name"/></label>
        <input readonly type="text" class="form-control" name="name" value="<c:out value="${name}"/>"/>
        <label><fmt:message bundle="${common}" key="th.description"/></label>
        <input readonly type="text" class="form-control" name="description" value="<c:out value="${description}"/>"/>
        <label><fmt:message bundle="${common}" key="th.time"/></label>
        <input readonly type="text" class="form-control" name="time" value="<c:out value="${time}"/>"/>
        <input type="hidden" class="form-control" name="status" value="<c:out value="${status}"/>"/>
        <input type="hidden" class="form-control" name="id" value="<c:out value="${id}"/>"/>
        <input type="hidden" class="form-control" name="activityId" value="<c:out value="${activityId}"/>"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="submit"/></button>
        <a href="http://localhost:8888/jsp/userActivity.jsp"
           class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message bundle="${common}" key="cancel"/></a>
    </form>
</div>
</body>
</html>