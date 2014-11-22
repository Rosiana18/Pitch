<html>
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

    function Login()
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

      var str="<b>Name</b> : "+response.name+"<br>";
          str +="<b>id: </b>"+response.id+"<br>";
          str +="<b>Email:</b> "+response.email+"<br>";
           document.getElementById("status").innerHTML=str;

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
</body>
<div id="status">
<img src="http://www.kilnandco.com/gallery/var/albums/facebook-login-button.png" style="cursor:pointer;" onclick="Login()"/>
</div>
<div id="message">
</div>

</html>
