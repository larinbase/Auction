<html lang="eng">
<head>
    <title>Sign In</title>
    <style>
        <#include "resources/css/signin.css" >
        <#include "resources/css/menu.css" >
        <#include "resources/css/main.css" >
    </style>
</head>
<body>
<div class="menu">
    <#include "menu.ftl">
</div>
<div class="container">
    <div class="form-signin-heading">Sign in</div>
    <form class="form-center-content" method="post">
        <div class="form-signin-name-heading">NAME</div>
        <label>
            <input type="text" name="username" placeholder="name" required>
        </label>
        <div class="form-signin-password-heading">PASSWORD</div>
        <label>
            <input type="password" name="password" placeholder="password" required>
        </label>
        <br/>
        <input type="submit">
    </form>
</div>
</body>
</html>