<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<script>
		var firstName="TestName";
		var lastName="TestName2";
		var email="TestEmail";
		function fill(){
		}
	</script>
	<script type="text/javascript" src="http://platform.linkedin.com/in.js">
		api_key: 75mqr7t01d6v4p
		authorize: true
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<script type="text/javascript">

    function onLinkedInLoad() {
        IN.UI.Authorize().place();      
        IN.Event.on(IN, "auth", function () { onLogin(); });
        IN.Event.on(IN, "logout", function () { onLogout(); });
    }

    function onLogin() {
            IN.API.Profile("me").result(displayResult);
    }  
    function displayResult(profiles) {
        member = profiles.values[0];
        document.getElementById("firstname").value=member.firstName;
		document.getElementById("lastname").value=member.lastName;
		document.getElementById("email").value="";
    }  
    
</script>
	
	<link type="text/css" rel="stylesheet" href="bootstrap.css" />
	<title>Sign Up</title>

</head>
<body>
<div id="fb-root"></div>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '779834495411292', // App ID
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true,  // parse XFBML
      version	 : 'v2.1'
      });


    FB.Event.subscribe('auth.authResponseChange', function(response) 
    {
     if (response.status === 'connected') 
    {
        //SUCCESS
    }    
    else if (response.status === 'not_authorized') 
    {
        document.getElementById("message").innerHTML +=  "<br>Failed to Connect";

        //FAILED
    } else 
    {
        document.getElementById("message").innerHTML +=  "<br>Logged Out";

        //UNKNOWN ERROR
    }
    }); 

    };

    function LoginFB()
    {

        FB.login(function(response) {
           if (response.authResponse) 
           {
                getUserInfo();
            } else 
            {
            }
         },{scope: 'public_profile,email', return_scopes: true, auth_type: 'rerequest'});
    }

  function getUserInfo() {
      FB.api('/me', function(response) {
		document.getElementById("firstname").value=response.first_name;
		document.getElementById("lastname").value=response.last_name;
		document.getElementById("email").value=response.email;
    });
            }
  
  // Load the SDK asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
   
</script>
	<form id="form" class="form-horizontal" action="/register" method="post">
  <fieldset>
    <legend>Sign Up</legend>
    <div class="form-group">
      <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
      <div class="col-lg-10">
        <input type="text" class="form-control" id="firstname" placeholder="First Name" name="firstName">
      </div>
      <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
      <div class="col-lg-10">
        <input type="text" class="form-control" id="lastname" placeholder="Last Name" name="lastName">
      </div>
      <label for="inputEmail" class="col-lg-2 control-label">Email</label>
      <div class="col-lg-10">
        <input type="text" class="form-control" id="email" placeholder="Email" name="email">
      </div>
      <label for="inputPassword" class="col-lg-2 control-label">Password</label>
      <div class="col-lg-10">
        <input type="password" class="form-control" id="password1" placeholder="Password" name="password1">
       </div>
    <label for="inputPassword" class="col-lg-2 control-label">Re-Enter</label>
      <div class="col-lg-10">
        <input type="password" class="form-control" id="password2" placeholder="Re-Enter" name="password2">
      </div>
           </div>
      <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2">
        <button class="btn btn-default">Cancel</button>
        <button type="submit" class="btn btn-primary">Submit</button>
      	<img src="F.png" width="50" height="50"  style="cursor:pointer;" onclick="LoginFB()"/>
		<img src="http://www.iconsplace.com/download/orange-linkedin-256.png" width="50" height="50" style="cursor:pointer;" onclick="onLinkedInLoad()"/>
      </div>
    </div>
  </fieldset>
</form>
	<div id="profiles"></div>	
		<!-- Ask User for confirmation if this is them -->
</body>

</html>