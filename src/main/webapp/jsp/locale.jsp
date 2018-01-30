<form action="/app/" method="POST">
    <input type="hidden" name="command" value="change_locale">
    <input type="hidden" name="uri" value="${pageContext.request.requestURI}">
    <select class="selectpicker picker" data-size="3" data-style="btn-info" style="width: 60%" name="locale" onchange="submit()">
        <option data-content='<span class="flag-icon flag-icon-us"></span> ENGLISH' value="en-US" ${locale == 'en-US' ? 'selected' : ''}><fmt:message bundle="${common}" key="language.en"/></option>
        <option data-content='<span class="flag-icon flag-icon-ua"></span> UKRAINIAN' value="uk-UA" ${locale == 'uk-UA' ? 'selected' : ''}><fmt:message bundle="${common}" key="language.ua"/></option>
        <option data-content='<span class="flag-icon flag-icon-ru"></span> RUSSIAN' value="ru-RU" ${locale == 'ru-RU' ? 'selected' : ''}><fmt:message bundle="${common}" key="language.ru"/></option>
    </select>
</form>
