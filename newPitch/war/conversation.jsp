<%@page import="BaseClasses.User"%>
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
<script>
	var convID = "";
</script>
<title>Conversations</title>

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
			<a href="index.jsp" class="logo"><b>Pitch</b></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">
				<!--  notification start -->
				<ul class="nav top-menu">
					<!-- settings start -->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="index.jsp#"> <i
							class="fa fa-tasks"></i> <span class="badge bg-theme">4</span>
					</a>
						<ul class="dropdown-menu extended tasks-bar">
							<div class="notify-arrow notify-arrow-green"></div>
							<li>
								<p class="green">You have pending tasks</p>
							</li>
							<li><a href="index.jsp#">
									<div class="task-info">
										<div class="desc">DashGum Admin Panel</div>
										<div class="percent">40%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 40%">
											<span class="sr-only">40% Complete (success)</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.jsp#">
									<div class="task-info">
										<div class="desc">Database Update</div>
										<div class="percent">60%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100" style="width: 60%">
											<span class="sr-only">60% Complete (warning)</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.jsp#">
									<div class="task-info">
										<div class="desc">Product Development</div>
										<div class="percent">80%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
											style="width: 80%">
											<span class="sr-only">80% Complete</span>
										</div>
									</div>
							</a></li>
							<li><a href="index.jsp#">
									<div class="task-info">
										<div class="desc">Payments Sent</div>
										<div class="percent">70%</div>
									</div>
									<div class="progress progress-striped">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="70" aria-valuemin="0"
											aria-valuemax="100" style="width: 70%">
											<span class="sr-only">70% Complete (Important)</span>
										</div>
									</div>
							</a></li>
							<li class="external"><a href="#">See All Tasks</a></li>
						</ul></li>
					<!-- settings end -->
					<!-- inbox dropdown start-->
					<li id="header_inbox_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="index.jsp#">
							<i class="fa fa-envelope-o"></i> <%
 	if(((java.util.ArrayList<Message>)((BaseClasses.User) session.getAttribute("user")).getMessages()) != null){
 %> <span class="badge bg-theme"> <%=((java.util.ArrayList<Message>)((BaseClasses.User) session.getAttribute("user")).getMessages()).size()%>
						</span> <%
 	}
 %>
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-green"></div>
							<li>
								<p class="green">
									You have
									<%
									if(((java.util.ArrayList<Message>)((BaseClasses.User) session.getAttribute("user")).getMessages()) == null){
								%>
									no
									<%
									}else{
																																																			out.print(((java.util.ArrayList<Message>)((BaseClasses.User)session.getAttribute("user")).getMessages()).size());
																																																			}
								%>
									new messages
								</p>
							</li>
							<%
								if(((java.util.ArrayList<Message>)((BaseClasses.User) session.getAttribute("user")).getMessages()) != null){
																																											for(Message msg: (java.util.ArrayList<Message>)((BaseClasses.User)session.getAttribute("user")).getMessages()){
							%>
							<li><a href="index.jsp#"> <span class="photo"><img
										alt="avatar" src="assets/img/ui-zac.jpg"></span> <span
									class="time"><%=msg.getDate().toGMTString()%></span> <span
									class="subject"> <span class="from"><%=msg.getFrom()%></span>
								</span> <span class="message"> <%=msg.getBody()%>
								</span>
									<button type="button" class="btn btn-info btn-primary btn-xs"
										data-toggle="modal" data-target="#myModal">reply</button>
									<button type="button"
										class="btn btn-warning btn-primary btn-xs">dismiss</button>

							</a></li>
							<%
								}
																																										}
							%>
							<li><a href="index.jsp#">See all messages</a></li>
						</ul></li>
					<!-- inbox dropdown end -->
				</ul>
				<!--  notification end -->
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
					<li class="sub-menu"><a href="profile.jsp"> <i
							class="fa fa-cogs"></i> <span>My Profile</span>
					</a></li>
					<li class="sub-menu"><a href="search.jsp"> <i
							class="fa fa-cogs"></i> <span>Search/Explore</span>
					</a></li>
					<li class="sub-menu"><a class="active" href="conversation.jsp"> <i
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
		<section id="main-content">
			<section class="wrapper">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<! -- ALERTS EXAMPLES --> <%
 	String name=request.getParameter("convID");
  					if(name == null){
 %>
					<div class="row mt">
						<div class="col-lg-12">
							<div class="form-panel">
							<h4 class="mb">
									<i class="fa fa-angle-right"></i> Send Message
								</h4>
								<form class="form-horizontal style-form" action="/sendmessage"
									method="post">
									<div class="form-group">
										<div class="col-sm-10">
											<label class="col-sm-2 col-sm-2 control-label">To:</label> <input
												type="text" name="to" class="form-control round-form">
											<label class="col-sm-2 col-sm-2 control-label">Message:</label>
											<input type="text" name="Message"
												class="form-control round-form">
										</div>

									</div>
									<div>
										<button type="submit" class="btn btn-round btn-default">Send</button>
									</div>
								</form>
							</div>
						</div>
						<!-- col-lg-12-->
					</div>
					<%
						}else{
					%>
					<div class="row mt">
						<div class="col-lg-12">
							<div class="form-panel">
								<h4 class="mb">
									<i class="fa fa-angle-right"></i> Conversation with <b>
									<%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(name)).getName()%></b>
								</h4>
								<form class="form-horizontal style-form" action="/sendmessage"
									method="post">
									<div class="form-group">
										<div class="col-sm-10">
											<input type="text" name="Message"
												class="form-control round-form"> <input
												type="hidden" name="to" class="form-control round-form"
												value="<%=name%>">
										</div>
										<button type="submit" class="btn btn-round btn-default">Send</button>
									</div>
								</form>
								<div class="chatbox" style="overflow-y:scroll; height:400px;">
								<%
									User user = (User)session.getAttribute("user");
								System.out.println(user.getMessages());
								System.out.println(name);
								
								
									//if(user.getMessages() != null){
								//if(user.getMessages().size() > 1){
									int counter = 0;
										for(Message msg: user.getMessages()){ 
											System.out.println(msg.getFrom());
											System.out.println(msg.getTo());
											if((msg.getFrom().equals(name)) || (msg.getTo().equals(name))){
												System.out.println(counter);
											counter++;
											}
										}
									if(counter > 0){
										for(Message msg: user.getMessages()){ 
											if((msg.getFrom().equals(name)) || (msg.getTo().equals(name))){
										%>
										<div class="alert alert-info">
										<%
										String from;
										if(msg.getFrom().equals(user.getId())){
											from = "me";
											}else{
												from = ((User)DB.DBManager.getInstance().getUserByID(msg.getFrom())).getName(); 
												}%>
										<b><%=from %>:</b>
										
										<%=msg.getBody()%>
									</div>
									<%
											}
										}
									}else{
								%>
								<div class="alert alert-warning">There are no messages to
									display.</div>
								<%
									}
								%>
								<%
									}
								%>
								</div>
							</div>
						</div>
						<!-- col-lg-12-->
					</div>
					<!-- /row -->
				</div>
				<!-- /col-lg-6 -->




				<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->

				<div class="col-lg-3 ds">
					<!--COMPLETED ACTIONS DONUTS CHART-->

					<!-- USERS ONLINE SECTION -->
					<h3>FRIENDS</h3>
					<%
						if(((java.util.ArrayList<String>)((BaseClasses.User) session.getAttribute("user")).getFriends()) != null){
																												for(String friend: (java.util.ArrayList<String>)((BaseClasses.User)session.getAttribute("user")).getFriends()){
					%>
					<div class="desc">
						<div class="thumb">
							<img class="img-circle" src="assets/img/ui-divya.jpg"
								width="35px" height="35px" align="">
						</div>
						<div class="details">
							<p>
								<a href="conversation.jsp?convID=<%=friend %>"><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(friend)).getName()%></a><br />
								<muted>Available</muted>
							</p>
						</div>
					</div>
					<%
						}
																											}
					%>
					<!-- CALENDAR-->
					<div id="calendar" class="mb">
						<div class="panel green-panel no-margin">
							<div class="panel-body">
								<div id="date-popover" class="popover top"
									style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
									<div class="arrow"></div>
									<h3 class="popover-title" style="disadding: none;"></h3>
									<div id="date-popover-content" class="popover-content"></div>
								</div>
								<div id="my-calendar"></div>
							</div>
						</div>
					</div>
					<!-- / calendar -->

				</div>
				<!-- /col-lg-3 -->
				</div>
				<! --/row -->
			</section>
		</section>

		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 - Project Pitch<a href="conversations.jsp#" class="go-top"> <i
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
    
        var $cont = $('.chatbox');
        $cont[0].scrollTop = $cont[0].scrollHeight;

        $('.form-control round-form').keyup(function(e) {
            if (e.keyCode == 13) {
                $cont.append('<p>' + $(this).val() + '</p>');
                $cont[0].scrollTop = $cont[0].scrollHeight;
                $(this).val('');
            }
        })
        .focus();

	</script>


</body>
</html>
