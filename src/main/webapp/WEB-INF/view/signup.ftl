<html lang="eng">
<head>
    <title>Sign Up</title>
    <link href="resources/css/signup.css" rel="stylesheet" >
    <link href="resources/css/menu.css" rel="stylesheet" >
    <link href="resources/css/main.css" rel="stylesheet" >
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
        <br/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>