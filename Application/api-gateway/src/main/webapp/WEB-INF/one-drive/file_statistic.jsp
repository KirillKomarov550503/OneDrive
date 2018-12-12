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
            <li><a href="/one-drive/statistics/files">Статистика по файлам</a></li>
        </ul>
    </aside>
</div>
<div id="table_list">
    <table>
        <tr class="custom_title">
            <th>Общий объем файлов для всех пользователей</th>
            <th>Средний объем файлов для всех пользователей</th>
        </tr>
        <tr>
            <td><c:out value="${generalFileStatistic}"/> </td>
            <td><c:out value="${averageFileStatistic}"/> </td>
        </tr>
    </table>
    <p>



    </p>
    <table>
        <tr class="custom_title">
            <th>Email</th>
            <th>Общий объем файлов, МБ</th>
            <th>Средний объем файлов, МБ</th>
        </tr>
        <c:forEach var="statistic" items="${userFileStatistic}">
            <tr>
                <td><c:out value="${statistic.email}"/></td>
                <td><c:out value="${statistic.generalSize}"/></td>
                <td><c:out value="${statistic.averageSize}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<footer>
    <div class="copyright">
        <p>Собственность 2018, One Drive. На этот ужас лучше не смотреть</p>
    </div>
</footer>
</body>
</html>