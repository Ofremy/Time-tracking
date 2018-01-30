<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="fill.the.form"/></h2>
        <input type="hidden" name="command" value="create_exist_activity">
        <label for="activity"><fmt:message bundle="${common}" key="please.select.activity"/></label>
        <select class="selectpicker" name="id" id="activity" data-size="3" data-style="btn-info">
            <c:forEach var="activity" items="${activity}">
                <option value="<c:out value="${activity.id}"/>"><c:out value="${activity.name}"/></option>
            </c:forEach>
        </select>
        <label for="user"><fmt:message bundle="${common}" key="please.select.user"/></label>
        <select class="selectpicker" name="login" id="user" data-size="3" data-style="btn-info">
            <c:forEach var="user" items="${user}">
                <option><c:out value="${user.login}"/></option>
            </c:forEach>
        </select>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="create.activity"/></button>
        <a href="http://localhost:8888/jsp/admin.jsp" class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message
                bundle="${common}" key="cancel"/></a>
    </form>
</div>
<%@include file="../jsScripts.jsp"%>
