<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<#import "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>

<@c.page>

    <h1>Управление серверами</h1>

    <div>
        <div id="info"></div>
        <form action="/start" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="version" value="mc20" />
        </form>
    </div>
    <script src="/static/js/main.js"/>
</@c.page>

