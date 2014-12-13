<%@page import="BaseClasses.Message"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>Search Request</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/css/zabuto_calendar.css">
<link rel="stylesheet" type="text/css"
	href="assets/js/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

<script src="assets/js/chart-master/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
    if((String)session.getAttribute("userName")==null||(BaseClasses.User)session.getAttribute("user")==null)
    {
		response.sendRedirect("login.jsp");
	}
    %>  
</head>

<body>

	<section id="container">
	  
	  <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->
			<a href="index.jsp" class="logo"><b>MY DASHBOARD</b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  notification start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					
							<!-- settings end -->
							<!-- inbox dropdown start-->
							<li id="header_inbox_bar" class="dropdown"><a
								data-toggle="dropdown" class="dropdown-toggle" href="index.jsp#">
									<i class="fa fa-envelope-o"></i> <span class="badge bg-theme"><%=((java.util.ArrayList<Message>)((BaseClasses.User)session.getAttribute("user")).getMessages()).size()%></span>
							</a>
								<ul class="dropdown-menu extended inbox">
									<div class="notify-arrow notify-arrow-green"></div>
									<li>
										<p class="green">
											You have
											<%=((java.util.ArrayList<Message>)((BaseClasses.User)session.getAttribute("user")).getMessages()).size()%>
											new messages
										</p>
									</li>
									<%
										for(Message msg: (java.util.ArrayList<Message>)((BaseClasses.User)session.getAttribute("user")).getMessages()){
									%>
									<li><a href="index.jsp#"> <span class="photo"><img
												alt="avatar" src="assets/img/ui-zac.jpg"></span> <span
											class="subject"> <span class="from"><%=msg.getFrom()%></span>
												<span class="time"><%=msg.getDate().toGMTString()%></span>
										</span> <span class="message"> <%=msg.getBody()%>
										</span>
									</a></li>
									<%
										}
									%>
									<li><a href="index.jsp#">See all messages</a></li>
								</ul></li>
							<!-- inbox dropdown end -->
						</ul> <!--  notification end -->
			</div>
			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="/logout">Logout</a></li>
				</ul>
			</div>
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu" id="nav-accordion">

					<p class="centered">
						<a href="profile.jsp"><img src="assets/img/ui-sam.jpg"
							class="img-circle" width="60"></a>
					</p>
					<h5 class="centered">
						<%=((BaseClasses.User) session.getAttribute("user"))
					.getName()%></h5>

					<li class="mt"><a href="index.jsp"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>
					<li class="sub-menu"><a href="mypitches.jsp"> <i
							class="fa fa-book"></i> <span>My Pitches</span>
					</a></li>
					<li class="sub-menu"><a href="createPitch.jsp"> <i
							class="fa fa-book"></i> <span>Create a Pitch</span>
					</a></li>
					<li class="sub-menu"><a href="profile.jsp">
							<i class="fa fa-cogs"></i> <span>My Profile</span>
					</a></li>
					<li class="sub-menu"><a class="active" href="search.jsp"> <i
							class="fa fa-cogs"></i> <span>Search/Explore</span>
					</a></li>
					<li class="sub-menu"><a href="conversation.jsp"> <i
							class="fa fa-cogs"></i> <span>Conversations</span>
					</a></li>


				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

	<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
    <!--main content start-->
   	<form id="form" class="form-horizontal style-form" action="/search" method="get">
    <section id="main-content">
      <section class="wrapper">
      <h3><i class="fa fa-angle-right"></i> Search Form</h3>

      <!-- Keywords -->
      <div class="row mt">
      	<div class="col-lg-12">
      	  <div class="form-panel">
        	<h4 class="mb"><i class="fa fa-angle-right"></i> Word Search</h4>
         	<div class="form-group">
          	  <label class="col-sm-2 col-sm-2 control-label">Keywords</label>
              <div class="col-sm-10">
            	<input type="text" class="form-control" name="keyword">
              </div>
          	</div>
         </div>
        </div>
     
	  	<!-- Pitch Duration -->
	  	<div class="col-lg-12">
          <div class="form-panel">
         	<h4 class="mb"><i class="fa fa-angle-right"></i> Pitch Duration</h4>
			<div><input type="range" name="length" min="0" max="40" step="8" value="10" onChange="pitchLength(this.value)"/>
			  <p id="pitchLength">week(s)</p><br>
			</div>
          </div>
      	</div>
      	
      	<!-- Pitch Length -->
	  	<div class="col-lg-12">
          <div class="form-panel">
         	<h4 class="mb"><i class="fa fa-angle-right"></i> Pitch Size</h4>
			<div><input type="range" name="size" min="0" max="40" step="8" value="10" onChange="teamSize(this.value)"/>
			  <p id="pitchSize">3-5 members</p><br>
			</div>
          </div>
      	</div>
        
        <!-- Categories -->
	  	<div class="col-lg-12">
          <div class="form-panel">
			<h4 class="mb"><i class="fa fa-angle-right"></i> Tags</h4>
		    <div class="checkbox">
			  <label><input type="checkbox" name="science" value="1">science</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="engineering" value="1">engineering</label>
			</div>
			<div class="checkbox">  
			  <label><input type="checkbox" name="writing" value="1">writing</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="craft" value="1">craft</label>
			</div>
			<div class="checkbox">  
			  <label><input type="checkbox" name="fixing" value="1">fixing</label>	
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="visualDesign" value="1">visual design</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="conceptDesign" value="1">concept design</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="event" value="1">event</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="teaching" value="1">teaching</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="cause" value="1">cause</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="diy" value="1">DIY</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="art" value="1">art</label>
			</div>
			<div class="checkbox">
			  <label><input type="checkbox" name="music" value="1">music</label>
			</div>
		  </div>
	  	</div>
	  
	  	<!-- Submit Button -->
		<div class="col-lg-12">
		  <div class="col-lg-12">
 	     	<button type="submit" class="btn btn-theme">Submit</button>
      	  </div>
      	</div>
      </div>
	</section>
	</form>
	<!--main content end-->
	
	<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 - Project Pitch <a href="search.jsp#" class="go-top"> <i
					class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/jquery-1.8.3.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<c:url value="assets/js/jquery.scrollTo.min.js"/>"></script>
<script src="<c:url value="assets/js/jquery.nicescroll.js"/>"></script>
	<script src="assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<script type="text/javascript"
		src="assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="assets/js/gritter-conf.js"></script>

	<!--script for this page-->
	<script src="assets/js/sparkline-chart.js"></script>
	<script src="assets/js/zabuto_calendar.js"></script>

	<script type="application/javascript">
		
		
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    
    	function pitchLength(val){
			if(val<=8 ){
				document.getElementById("pitchLength").innerHTML = "weekend or shorter";
			}else if(val>8&&val<=16){
				document.getElementById("pitchLength").innerHTML = "short - week";
			}else if(val>16&&val<=24){
				document.getElementById("pitchLength").innerHTML = "medium - weeks";
			}else if(val>24&&val<=32){
				document.getElementById("pitchLength").innerHTML = "long - year";
			}else if(val>32){
				document.getElementById("pitchLength").innerHTML = "long haul - years and years!";
			}
		}
		function teamSize(val){
			if(val<=8){
				document.getElementById("pitchSize").innerHTML = "1-2 members";
			}else if(val>8&&val<=16){
				document.getElementById("pitchSize").innerHTML = "3-5 members";
			}else if(val>16&&val<=24){
				document.getElementById("pitchSize").innerHTML = "5-10 members";
			}else if(val>24&&val<=32){
				document.getElementById("pitchSize").innerHTML = "10-20 members";
			}else if(val>32){
				document.getElementById("pitchSize").innerHTML = ">20 members / organization";
			} 
		}
	
	</script>


</body>
</html>
