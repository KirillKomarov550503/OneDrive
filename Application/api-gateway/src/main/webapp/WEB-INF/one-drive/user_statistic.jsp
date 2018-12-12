<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html>
<head>

    <title>Look JSP</title>
    <link rel="stylesheet" href="/resources/css/header_and_footer.css">
    <link rel="stylesheet" href="/resources/css/welcome.css">
    <link rel="stylesheet" href="/resources/css/table.css">
</head>
<body>
<header>
    <img src="/resources/images/emblem.png" width="223" height="140" class="emblem">
    <h1>Онлайн-диск</h1>
</header>
<div id="work_space">
    <aside class="menu">
        <ul>
            <li><a href="/one-drive/statistics">Статистика по пользователям</a></li>
            <li><a href="">Статистика по файлам</a></li>
        </ul>
    </aside>
</div>
<div id="table_list">
    <form method="get" action="/one-drive/statistics/users">
        <label class="label" name="early">Введите нижнюю границу</label>
        <input type="text" name="early"/>
        <label class="label" name="later">Введите верхнюю границу</label>
        <input type="text" name="later"/>
        <input type="submit" value="Получить статистику"/>
    </form>

    <c:if test="${get == 'yes'}">
        <table>
            <tr class="custom_title">
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Email</th>
            </tr>
            <c:forEach var="person" items="${peopleList}">
                <tr>
                    <td><c:out value="${person.name}"/></td>
                    <td><c:out value="${person.surname}"/></td>
                    <td><c:out value="${person.email}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<footer>
    <div class="copyright">
        <p>Собственность 2018, One Drive. На этот ужас лучше не смотреть</p>
    </div>
</footer>
</body>
</html>