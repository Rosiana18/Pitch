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

<title>Pitch</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/js/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="assets/js/bootstrap-daterangepicker/daterangepicker.css" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

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
					<li class="sub-menu"><a class="active" href="profile.jsp">
							<i class="fa fa-cogs"></i> <span>My Profile</span>
					</a></li>
					<li class="sub-menu"><a href="search.jsp"> <i
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
		<section id="main-content">
			<section class="wrapper">
				<h3>
					<i class="fa fa-angle-right"></i> Profile
				</h3>

				<!-- BASIC FORM ELELEMNTS -->
				<div class="row mt">
					<div class="col-lg-12">
						<div class="form-panel">
							<h4 class="mb">
								<i class="fa fa-angle-right"></i> Form Elements
							</h4>
							<%if(((BaseClasses.User) session.getAttribute("user")).getImage() == null) {%>
							<form class="form-horizontal style-form" method="get">
							<img alt="avatar" src="assets/img/ui-zac.jpg">
								<input type="file" name="pic" accept="image/*"> <input
									type="submit">
							</form>
							<% }else {%>
							<form class="form-horizontal style-form" method="get">
							<img alt="avatar" src="assets/img/ui-zac.jpg">
								<input type="file" name="pic" accept="image/*"> <input
									type="submit">
							</form>
							<%} %>
							<form class="form-horizontal style-form" action="/update" method="post">
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">First
										Name</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="firstName"
											value="<%=((BaseClasses.User) session.getAttribute("user"))
					.getFirstName()%>">
									</div>
									<label class="col-sm-2 col-sm-2 control-label">Last
										Name</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="lastName"
											value="<%=((BaseClasses.User) session.getAttribute("user"))
					.getLastName()%>">
									</div>
									<label class="col-lg-2 col-sm-2 control-label">Email</label>
									<div class="col-lg-10">
										<p class="form-control-static"><%=((BaseClasses.User) session.getAttribute("user"))
					.getEmail()%></p>
									</div>
								</div>	
								<div class="form-group">
									<div class="col-sm-10">
									<h4 class="mb"><i class="fa fa-angle-right"></i> My Attributes</h4>
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
								<div>
									<button type="submit" class="btn btn-theme">Update
										Profile</button>
								</div>
							</form>
							<form class="form-horizontal style-form" action="/update" method="post">
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">New
										Password</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" placeholder="">
									</div>
									<label class="col-sm-2 col-sm-2 control-label">Re-enter
										New Password</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" placeholder="">
									</div>
								</div>
								<div>
									<button type="submit" class="btn btn-theme">Change
											Password</button>
								</div>
								</div>
							</form>
						</div>
					</div>
					<!-- col-lg-12-->
				</div>
				<!-- /row -->

			</section>
			<! --/wrapper -->
		</section>
		<!-- /MAIN CONTENT -->

		<!--main content end-->
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 - Project Pitch <a href="profile.jsp#" class="go-top"> <i
					class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
		<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<c:url value="assets/js/jquery.scrollTo.min.js"/>"></script>
<script src="<c:url value="assets/js/jquery.nicescroll.js"/>"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!--script for this page-->
	<script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>

	<!--custom switch-->
	<script src="assets/js/bootstrap-switch.js"></script>

	<!--custom tagsinput-->
	<script src="assets/js/jquery.tagsinput.js"></script>

	<!--custom checkbox & radio-->

	<script type="text/javascript"
		src="assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>

	<script type="text/javascript"
		src="assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>


	<script src="assets/js/form-component.js"></script>


	<script>
		//custom select box

		$(function() {
			$('select.styled').customSelect();
		});
	</script>

</body>
</html>
