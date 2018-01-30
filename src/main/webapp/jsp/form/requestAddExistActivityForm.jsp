<%@include file="headerForm.jsp" %>
<div class="wrapper">
    <form class="form-signin" method="post" action="/app">
        <h2 class="form-signin-heading"><fmt:message bundle="${common}" key="fill.the.form"/></h2>
        <input type="hidden" name="command" value="request_exist_activity">
        <label for="activity"><fmt:message bundle="${common}" key="please.select.activity"/></label>
        <select class="selectpicker" name="id" id="activity" data-size="3" data-style="btn-info">
            <c:forEach var="list" items="${list}">
                <option value="<c:out value="${list.id}"/>"><c:out value="${list.name}"/></option>
            </c:forEach>
        </select>
        <c:if test="${fn:length(list) ne 0}">
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${common}"
                                                                                        key="create.activity"/></button>
        </c:if>
        <a href="http://localhost:8888/jsp/userActivity.jsp"
           class="btn btn-lg btn-primary btn-block btn-sign-in"><fmt:message bundle="${common}" key="cancel"/></a>
    </form>
</div>
<%@include file="../jsScripts.jsp"%>

