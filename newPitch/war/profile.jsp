<%@page import="BaseClasses.Message"%>
<%@page import="BaseClasses.User"%>
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
							<%
							String userName = (String) session.getAttribute("userName");
							String userView = (String) request.getParameter("user");
							Boolean update = false;
							if(userView==null){
								update = true;
							}else{
								if(userView.equals(userName)){
									update = true;
								}
							}
							User user = (BaseClasses.User)DB.DBManager.getInstance().getUserByID(userName);
							if(!update){
								user = (BaseClasses.User)DB.DBManager.getInstance().getUserByID(userView);
							}
							if(user.getImage() == null) {
							%>
							<h4 class="mb">
								<i class="fa fa-angle-right"></i> <%=user.getFirstName()%>'s Profile
							</h4>
							
							<form class="form-horizontal style-form" method="get">
								<div class="form-group">
									<div class="col-sm-10">
										<img alt="avatar" src="assets/img/ui-zac.jpg" />
										<%if(update){%>
										<input type="file" name="pic" accept="image/*" /> 
										<input type="submit" />
										<%}%>
									</div>
								</div>
							</form>
							<% 	
							}else {
							%>
							<form class="form-horizontal style-form" method="get">
								<div class="form-group">
									<div class="col-sm-10">
										<img alt="avatar" src="assets/img/ui-zac.jpg" />
										<%if(update){%>
										<input type="file" name="pic" accept="image/*" /> 
										<input type="submit" />
										<%}%>
									</div>
								</div>
							</form>
							<%
							}
							%>

							<form class="form-horizontal style-form" action="/update" method="post">
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">First Name</label>
									<div class="col-sm-10">
										<%if(update){%>
										<input type="text" class="form-control" name="firstName" value="<%=user.getFirstName()%>">
										<%}else{%>
										<p class="form-control-static"><%=user.getFirstName()%></p>
										<%}%>
									</div>
									<label class="col-sm-2 col-sm-2 control-label">Last Name</label>
									<div class="col-sm-10">
										<%if(update){%>
										<input type="text" class="form-control" name="lastName" value="<%=user.getLastName()%>">
										<%}else{%>
										<p class="form-control-static"><%=user.getLastName()%></p>
										<%}%>
									</div>
									<label class="col-lg-2 col-sm-2 control-label">Email</label>
									<div class="col-lg-10">
										<p class="form-control-static"><%=userName%></p>
									</div>
								</div>
								
								<!-- Description -->
								<div class="form-group">
								  <label class="col-sm-2 col-sm-2 control-label">About Me</label>
								  <div class="col-sm-10">
									<%if(update){%>
									<textarea name="aboutMe" rows="10" style="width:80%"><%=user.about()%></textarea>
									<%}else{%>
									<p><%=user.about()%></p>
									<%}%>
								   </div>
								</div>		
								
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"> Categories</label>
									<div class="col-sm-10">       	
									<%
									for(String category: user.getCategoryTags()){
									%>
									  <%=category%>&nbsp;  
									<%
									}
									%>
								  </div>
								</div>	
								<%if(update){%>
								<div class="form-group">
									<div class="col-sm-10">
										<h4 class="mb"><i class="fa fa-angle-right"></i> My Attributes</h4>
										<%
										String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
												,"conceptDesign","event","teaching","cause","diy","art","music"};
										for(String category: whatIsIts){
											if(user.getCategoryTags().contains(category)){
											%>
										<div class="checkbox">
										  <label><input type="checkbox" name="<%=category%>" value="1" checked><%=category%></label>
										</div>
											<%
											}else{
											%>
										<div class="checkbox">
										  <label><input type="checkbox" name="<%=category%>" value="1"><%=category%></label>
										</div>
											<%
											}
										}
										%>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<button type="submit" class="btn btn-theme">Update Profile</button>
									</div>
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
								
							</form>
							<%}%>
							
								<div>
									<button><a href="conversation.jsp?convID=<%=user.getEmail()%>">
									Message Me!</a></button>
								</div>

						</div>
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

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
