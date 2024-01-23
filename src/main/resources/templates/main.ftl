<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<#import "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <h1>Main</h1>
    <div>${some}</div>
    <div>
        <form action="/start" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="version" value="mc20" />
            <button class="btn btn-primary" type="submit">Start</button>
        </form>
    </div>
</@c.page>

