<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="fill.the.form"/></h2>
        <input type="hidden" name="command" value="request_activity">
        <input type="text" class="form-control" name="name"
               placeholder="<fmt:message bundle="${common}" key="activity.name"/>" required="" autofocus=""/>
        <input type="text" class="form-control" name="description"
               placeholder="<fmt:message bundle="${common}" key="th.description"/> " required=""/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="create.activity"/></button>
        <a href="http://localhost:8888/jsp/userActivity.jsp"
           class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message bundle="${common}" key="cancel"/></a>
    </form>
</div>
</body>
</html>
