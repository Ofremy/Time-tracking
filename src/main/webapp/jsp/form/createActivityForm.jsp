<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="fill.the.form"/></h2>
        <input type="hidden" name="command" value="create_activity">
        <label for="user"><fmt:message bundle="${common}" key="please.select.user"/></label>
        <select class="selectpicker" name="login" id="user" data-size="4" data-style="btn-info">
            <c:forEach var="list" items="${list}">
                <option><c:out value="${list.login}"/></option>
            </c:forEach>
        </select>
        <input type="text" class="form-control" name="name"
               placeholder="<fmt:message bundle="${common}" key="activity.name"/>"/>
        <input type="text" class="form-control" name="description"
               placeholder="<fmt:message bundle="${common}" key="th.description"/>" required=""/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                    key="create.activity"/></button>
        <a href="http://localhost:8888/jsp/admin.jsp" class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message
                bundle="${common}" key="cancel"/></a>
    </form>
</div>
<%@include file="../jsScripts.jsp"%>
