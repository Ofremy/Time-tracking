<%@include file="header.jsp" %>
<div class="wrapper">
    <div class="sidebar" data-color="blue" data-image="../img/sidebar-1.jpg">
        <div class="sidebar-wrapper">
            <ul class="nav">
                <li class="active">
                    <a href="http://localhost:8888/jsp/userActivity.jsp">
                        <i class="material-icons">content_paste</i>
                        <p><fmt:message bundle="${common}" key="activity.list"/></p>
                    </a>
                </li>
                <li>
                    <a href="http://localhost:8888/jsp/profile.jsp">
                        <i class="material-icons">person</i>
                        <p><fmt:message bundle="${common}" key="user.profile"/></p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <nav class="navbar navbar-transparent navbar-absolute">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <%@include file="locale.jsp" %>
                    </ul>
                    <a href="http://localhost:8888/jsp/form/requestAddActivityForm.jsp" class="btn btn-success"
                       style="margin-left: 40px">
                        <fmt:message bundle="${common}" key="request.for.create.new.activity"/>
                    </a>
                    <a href="/app?command=request_exist_activity_form" class="btn btn-success">
                        <fmt:message bundle="${common}" key="request.for.exist.activity"/>
                    </a>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                   <span><c:out value="${user.firstName}"/>
                                       <c:out value="${user.lastName}"/>
                                       <i class="material-icons">person</i>
                                   </span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="/app?command=logout"><fmt:message bundle="${common}" key="log.out"/></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title"><fmt:message bundle="${common}" key="activities.table"/> </h4>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th><fmt:message bundle="${common}" key="th.name"/></th>
                                    <th><fmt:message bundle="${common}" key="th.description"/></th>
                                    <th><fmt:message bundle="${common}" key="th.time"/></th>
                                    <th><fmt:message bundle="${common}" key="th.status"/></th>
                                    <th></th>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="list" items="${list}">
                                        <tr>
                                            <td><c:out value="${list.activity.name}"/></td>
                                            <td><c:out value="${list.activity.description}"/></td>
                                            <td><c:out value="${list.time}"/></td>
                                            <td><c:out value="${list.status}"/></td>
                                            <td>
                                                <c:if test="${list.status ne 'PENDING'}">
                                                    <c:if test="${list.status eq 'ACTIVE'}">
                                                        <form method="post" action="/app">
                                                            <input type="hidden" name="command" value="set_time_form">
                                                            <input type="hidden" name="name"
                                                                   value="<c:out value="${list.activity.name}"/>"/>
                                                            <input type="hidden" name="description"
                                                                   value="<c:out value="${list.activity.description}"/>"/>
                                                            <input type="hidden" name="time"
                                                                   value="<c:out value="${list.time}"/>"/>
                                                            <input type="hidden" name="id"
                                                                   value="<c:out value="${list.id}"/>"/>
                                                            <input type="hidden" name="activityId"
                                                                   value="<c:out value="${list.activity.id}"/>"/>
                                                            <input type="hidden" name="status"
                                                                   value="<c:out value="${list.status}"/>"/>
                                                            <button type="submit"
                                                                    class="btn btn-white btn-round btn-just-icon"
                                                                    name="Set Time"
                                                            >
                                                                <i class="material-icons">alarm</i>
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                    <form method="post" action="/app">
                                                        <input type="hidden" name="command"
                                                               value="submit_delete_form">
                                                        <input type="hidden" name="name"
                                                               value="<c:out value="${list.activity.name}"/>">
                                                        <input type="hidden" name="description"
                                                               value="<c:out value="${list.activity.description}"/>">
                                                        <input type="hidden" name="time"
                                                               value="<c:out value="${list.time}"/>">
                                                        <input type="hidden" name="status"
                                                               value="<c:out value="${list.status}"/>">
                                                        <input type="hidden" name="activityId"
                                                               value="<c:out value="${list.activity.id}"/>"/>
                                                        <input type="hidden" name="id"
                                                               value="<c:out value="${list.id}"/>">
                                                        <button type="submit"
                                                                class="btn btn-round btn-white btn-just-icon btn-delete"
                                                                name="Delete"
                                                        >
                                                            <i class="material-icons">delete_forever</i>
                                                        </button>
                                                    </form>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${pageNumber ne 1}">
                <a style="margin-left: 30px; background-color: dodgerblue; width: 20%"
                   href="/app?command=pagination&pageNumber=${pageNumber - 1}&uri=${pageContext.request.requestURI}"
                   class="btn"><fmt:message bundle="${common}" key="previous"/></a>
            </c:if>
            <c:if test="${fn:length(list) eq 5}">
                <a style="float: right; background-color: dodgerblue; margin-right: 30px; width: 20%"
                   href="/app?command=pagination&pageNumber=${pageNumber + 1}&uri=${pageContext.request.requestURI}"
                   class="btn"><fmt:message bundle="${common}" key="next"/></a>
            </c:if>
        </div>
    </div>
</div>
<%@include file="jsScripts.jsp"%>