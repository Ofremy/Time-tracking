<%@include file="header.jsp" %>
<div class="wrapper">
    <div class="sidebar" data-color="blue" data-image="../img/sidebar-1.jpg">
        <div class="sidebar-wrapper">
            <ul class="nav">
                <li>
                    <a href="http://localhost:8888/jsp/userActivity.jsp">
                        <i class="material-icons">content_paste</i>
                        <p><fmt:message bundle="${common}" key="activity.list"/></p>
                    </a>
                </li>
                <li class="active">
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
                    <a class="navbar-brand" href="#"> <fmt:message bundle="${common}" key="user.profile"/> </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <%@include file="locale.jsp" %>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
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
                                <h4 class="title"><fmt:message bundle="${common}" key="edit.profile"/></h4>
                            </div>
                            <div class="card-content">
                                <form method="post" action="/app?command=update_profile">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group label-floating">
                                                <label class="control-label"><c:out value="${user.login}"/></label>
                                                <input type="text" class="form-control" name="login">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group label-floating">
                                                <label class="control-label"><c:out value="${user.firstName}"/></label>
                                                <input type="text" class="form-control" name="firstName">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group label-floating">
                                                <label class="control-label"><c:out value="${user.lastName}"/></label>
                                                <input type="text" class="form-control" name="lastName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group label-floating">
                                                <label class="control-label"><fmt:message bundle="${common}"
                                                                                          key="password"/></label>
                                                <input type="password" class="form-control" name="password">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group label-floating">
                                                <label class="control-label"><fmt:message bundle="${common}"
                                                                                          key="repeat.password"/></label>
                                                <input type="password" class="form-control" name="repeatPassword">
                                            </div>
                                        </div>
                                    </div>
                                    <font color="red"><c:out value="${error}"/></font>
                                    <button type="submit" class="btn btn-primary pull-right"><fmt:message
                                            bundle="${common}" key="update.profile"/></button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="jsScripts.jsp"%>