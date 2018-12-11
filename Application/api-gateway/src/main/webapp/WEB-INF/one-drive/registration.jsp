<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/resources/css/header_and_footer.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/registration.css" type="text/css">
</head>
<body>
<header>
    <img src="/resources/images/emblem.png" width="223" height="140" class="emblem">
    <h1>Онлайн-диск</h1>
    <nav>
        <ul>
            <li><a href="/one-drive/registration">Регистрация</a></li>
            <li><a href="/one-drive/users/files">Авторизация</a></li>
        </ul>
    </nav>
</header>


<div id="form">
    <h1>Регистрация пользователя</h1>
    <form:form class="registration" method="post" action="/one-drive/registration" modelAttribute="personDTO">
        <h2><c:out value="${requestScope.error}"/></h2>
        <h2><c:out value="${requestScope.event}"/></h2>
        <p>
            <form:label class="label" name="name" path="name">Введите имя</form:label>
            <form:input class="none" type="text" name="name" path="name"/>
        </p>
        <p>
            <form:label class="label" name="surName" path="surname">Введите фамилию</form:label>
            <form:input class="none" type="text" name="surname" path="surname"/>
        </p>
        <p>
            <form:label class="label" name="email" path="email">Введите email</form:label>
            <form:input class="none" type="text" name="email" path="email"/>
        </p>
        <p>
            <form:label class="label" name="password" path="password">Введите пароль</form:label>
            <form:input class="none" type="password" name="password" path="password"/>
        </p>
        <P>
            <input type="submit" value="Отправить">
        </P>

    </form:form>
</div>


<footer>
    <div class="copyright">
        <p>Собственность 2017, Онлайн-Диск. На этот ужас лучше не смотреть</p>
    </div>
</footer>

</body>
</html>
