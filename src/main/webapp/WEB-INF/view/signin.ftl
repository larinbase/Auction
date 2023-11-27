<html lang="eng">
<head>
    <title>Sign In</title>
    <link href="resources/css/menu.css" rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet">
    <link href="resources/css/signin.css" rel="stylesheet">
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