<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="set.time.in.format"/> </h2>
        <input type="hidden" name="command" value="set_time">
        <label><fmt:message bundle="${common}" key="activity.name"/></label>
        <input disabled type="text" class="form-control" name="name" value="<c:out value="${name}"/>"/>
        <label><fmt:message bundle="${common}" key="th.description"/>:</label>
        <input disabled type="text" class="form-control" name="description" value="<c:out value="${description}"/>"/>
        <input type="text" class="form-control" name="time"
               placeholder="<fmt:message bundle="${common}" key="set.time.in.format"/>"/>
        <input type="hidden" class="form-control" name="id" value="<c:out value="${id}"/>">
        <input type="hidden" class="form-control" name="activityId" value="<c:out value="${activityId}"/>">
        <input type="hidden" class="form-control" name="status" value="<c:out value="${status}"/>">
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}" key="submit"/></button>
        <a href="http://localhost:8888/jsp/userActivity.jsp"
           class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message bundle="${common}" key="cancel"/></a>
    </form>
</div>
</body>
</html>