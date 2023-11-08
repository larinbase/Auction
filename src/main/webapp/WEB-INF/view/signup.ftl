<html lang="eng">
<head>
    <title>Sign Up</title>
    <style>
        <#include "resources/css/signup.css" >
        <#include "resources/css/menu.css" >
        <#include "resources/css/main.css" >
    </style>
</head>
<body>
<div class="menu">
    <#include "menu.ftl">
</div>
<div class="container">
    <div class="form-signin-heading">Sign up</div>
    <form class="form-center-content" method="post">
        <div class="form-signin-name-heading">NAME</div>
        <input type="text" name="username" placeholder="name" required>
        <div class="form-signin-password-heading">PASSWORD</div>
        <input type="password" name="password" placeholder="password" required>
        <div class="form-signin-role-heading">SELECT ROLE<br/>
            <label>
                <select name="select_role">
                    <option>admin</option>
                    <option>user</option>
                </select>
            </label>
        </div>
        <br/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>