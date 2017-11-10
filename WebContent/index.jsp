<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="styles/style.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans|Nunito|Spectral+SC|Ubuntu" rel="stylesheet">
        <script src="scripts/index.js"></script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="imageLogo">
                    <a href="index.jsp">
                    <img src="images/logo.png" alt="logo" id="logo"></a>
                </div>
            </div>
            <div id="body">
                <div  id="mainBody">
                    <h1 align="center">Welcome to Vivahaka Bank</h1>
                    <div id="wrapper">
                        <input type="checkbox" id="form-switch">
                        <input type="checkbox" id="p-form-switch">
                        <input type="checkbox" id="user-sel">
                        <input type="checkbox" id="admin-sel">
                        <div id="userTypes">
                            <label for="user-sel">
                                <div id="user-login">User</div>
                            </label>
                            <label for="admin-sel">
                                <div id="admin-login">Admin</div>
                            </label>
                        </div>
                        <div id="admin-login-form">
                            <h1>Log in</h1>
                            <form action="adminlogin.htm" method="post">
                                <p>
                                    <label for="admin-username">Username</label>
                                    <input id="admin-username" type="text" name="admin-username" placeholder="Enter your username" required/>
                                </p>
                                <p>
                                    <label for="admin-password" >Password</label>
                                    <input type="password" id="admin-password" name="admin-password" placeholder="Enter your password" required/>
                                </p>
                                <p class="button">
                                    <input type="submit" value="Login"/>
                                </p>
                            </form>
                        </div>
                        <div id="login-form">
                            <h1>Log in</h1>
                            <form action="userlogin.htm" method="post">
                                <p>
                                    <label for="username" >Username</label>
                                    <input id="username" type="text" name="username" placeholder="Enter your username" required/>
                                </p>
                                <p>
                                    <label for="password" >Password</label>
                                    <input type="password" id="password" name="password" placeholder="Enter your password" required/>
                                </p>
                                <p class="button">
                                    <input type="submit" value="Login"/>
                                </p>
                                <label for="p-form-switch">
                                    <span class="link-switch">
                                        Forgot password?
                                    </span>
                                </label>
                                <label for="form-switch">
                                    <span class="link-switch"> Not a member yet?
                                    Join Us
                                    </span>
                                </label>
                            </form>
                        </div>
                        <div id="register-form">
                             <h1>Sign up</h1>
                            <form action="signup.htm" method="post" onsubmit="return validateFields();">
                                <p>
                                    <label for="usernamesignup">Your username</label>
                                    <input id="usernamesignup" name="usernamesignup" type="text" placeholder="Enter preferred username" required/>
                                </p>
                                <p> 
                                    <label for="passwordsignup">Your password </label>
                                    <input id="passwordsignup" name="passwordsignup" required type="password" placeholder="Enter your password"/>
                                </p>
                                <p> 
                                    <label for="passwordsignup_confirm"> Please confirm your password </label>
                                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required type="password" placeholder="Re enter your password"/>
                                </p>
                                <p> 
                                    <label for="transactionpassword">Please enter your transaction password </label>
                                    <input id="transactionpassword" name="transactionpassword" required type="password" placeholder="Transaction password must be different from log in password"/>
                                </p>

                                <p>
                                    <label>Please select a secret question</label>
                                    <select name="secretquestion" id="secretquestion">
                                        <option value="favourite color">What is your favourite color?</option>
                                        <option value="mothername">What is maiden name of your mother?</option>
                                        <option value="cityofschool">Your first school was in which city?</option>
                                        <option value="spousemeetingplace">Where did you meet your spouse?</option>
                                    </select>
                                </p>
                                <p>
                                    <label for="secretanswer">Your answer to secret question</label>
                                    <input type="text" id="secretanswer" name="secretanswer" required placeholder="Enter your answer">
                                </p>
                                <p class="button"> 
                                    <input type="submit" value="Sign up"/> 
                                </p>
                                <label for="form-switch">
                                    <span class="link-switch"> Already a member?
                                     Go and log in</span>
                                </label>
                            </form>
                        </div>
                        <div id="forgetpassword-form">
                            <h1>Forget Password?</h1>
                            <form action="forgetPass.htm" method="post">
                            	 <p>
                                    <label for="usernameForget">Please enter username</label>
                                    <input id="usernameForget" name="usernameForget" type="text" placeholder="Enter your username" required/>
                                </p>
                                <p>
                                    <label>Please select your secret question</label>                    
                                    <select name="secretquestion" id="secretquestion">
                                        <option value="favourite color">What is your favourite color?</option>
                                        <option value="mothername">What is maiden name of your mother?</option>
                                        <option value="cityofschool">Your first school was in which city?</option>
                                        <option value="spousemeetingplace">Where did you meet your spouse?</option>
                                    </select>
                                </p>
                                <p>
                                    <lable for="forget-passwordq">Enter your answer</lable>
                                    <input type="text" id="forget-passwordq" name="forget-passwordq" required placeholder="Enter your secret answer"/>
                                </p>
                                 <p class="button">
                                    <input type="submit" value="Change Password"/>
                                </p>
                                <label for="p-form-switch">
                                    <span class="link-switch">Go back and log in </span>
                                </label>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer">
                &copy; Vivahaka Bank
            </div>
        </div>
    </body>
</html>