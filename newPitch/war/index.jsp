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

					<li class="mt"><a class="active" href="index.jsp"> <i
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
					<i class="fa fa-angle-right"></i>   Today's Recommendations
				</h3>
			<! -- MODALS -->
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Send Message</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal style-form" method="get">
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
								<button type="button" class="btn btn-primary">Send</button>
							</form>
						</div>
					</div>
				</div>
			</div>

			<section class="wrapper">

				<div class="row">
					<div class="col-lg-9 main-chart">
						<div class="row mt">
							<!-- SERVER STATUS PANELS -->
							<div class="col-md-4 col-sm-4 mb">
								<div class="white-panel pn donut-chart">
									<div class="white-header">
										<h5>SERVER LOAD</h5>
									</div>
									<div class="row">
										<div class="col-sm-6 col-xs-6 goleft">
											<p>
												<i class="fa fa-database"></i> 70%
											</p>
										</div>
									</div>
									<canvas id="serverstatus01" height="120" width="120"></canvas>
									<script>
										var doughnutData = [ {
											value : 70,
											color : "#68dff0"
										}, {
											value : 30,
											color : "#fdfdfd"
										} ];
										var myDoughnut = new Chart(document
												.getElementById(
														"serverstatus01")
												.getContext("2d"))
												.Doughnut(doughnutData);
									</script>
								</div>
								<! --/grey-panel -->
							</div>
							<!-- /col-md-4-->


							<div class="col-md-4 col-sm-4 mb">
								<div class="white-panel pn">
									<div class="white-header">
										<h5>TOP PRODUCT</h5>
									</div>
									<div class="row">
										<div class="col-sm-6 col-xs-6 goleft">
											<p>
												<i class="fa fa-heart"></i> 122
											</p>
										</div>
										<div class="col-sm-6 col-xs-6"></div>
									</div>
									<div class="centered">
										<img src="assets/img/product.png" width="120">
									</div>
								</div>
							</div>
							<!-- /col-md-4 -->

							<div class="col-md-4 mb">
								<!-- WHITE PANEL - TOP USER -->
								<div class="white-panel pn">
									<div class="white-header">
										<h5>TOP USER</h5>
									</div>
									<p>
										<img src="assets/img/ui-zac.jpg" class="img-circle" width="80">
									</p>
									<p>
										<b>Zac Snider</b>
									</p>
									<div class="row">
										<div class="col-md-6">
											<p class="small mt">MEMBER SINCE</p>
											<p>2012</p>
										</div>
										<div class="col-md-6">
											<p class="small mt">TOTAL SPEND</p>
											<p>$ 47,60</p>
										</div>
									</div>
								</div>
							</div>
							<!-- /col-md-4 -->


						</div>
						<!-- /row -->


						<div class="row">
							<!-- TWITTER PANEL -->
							<div class="col-md-4 mb">
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>DROPBOX STATICS</h5>
									</div>
									<canvas id="serverstatus02" height="120" width="120"></canvas>
									<script>
										var doughnutData = [ {
											value : 60,
											color : "#68dff0"
										}, {
											value : 40,
											color : "#444c57"
										} ];
										var myDoughnut = new Chart(document
												.getElementById(
														"serverstatus02")
												.getContext("2d"))
												.Doughnut(doughnutData);
									</script>
									<p>April 17, 2014</p>
									<footer>
										<div class="pull-left">
											<h5>
												<i class="fa fa-hdd-o"></i> 17 GB
											</h5>
										</div>
										<div class="pull-right">
											<h5>60% Used</h5>
										</div>
									</footer>
								</div>
								<! -- /darkblue panel -->
							</div>
							<!-- /col-md-4 -->


							<div class="col-md-4 mb">
								<!-- INSTAGRAM PANEL -->
								<div class="instagram-panel pn">
									<i class="fa fa-instagram fa-4x"></i>
									<p>
										@THISISYOU<br /> 5 min. ago
									</p>
									<p>
										<i class="fa fa-comment"></i> 18 | <i class="fa fa-heart"></i>
										49
									</p>
								</div>
							</div>
							<!-- /col-md-4 -->

							<div class="col-md-4 col-sm-4 mb">
								<!-- REVENUE PANEL -->
								<div class="darkblue-panel pn">
									<div class="darkblue-header">
										<h5>REVENUE</h5>
									</div>
									<div class="chart mt">
										<div class="sparkline" data-type="line" data-resize="true"
											data-height="75" data-width="90%" data-line-width="1"
											data-line-color="#fff" data-spot-color="#fff"
											data-fill-color="" data-highlight-line-color="#fff"
											data-spot-radius="4"
											data-data="[200,135,667,333,526,996,564,123,890,464,655]"></div>
									</div>
									<p class="mt">
										<b>$ 17,980</b><br />Month Income
									</p>
								</div>
							</div>
							<!-- /col-md-4 -->

						</div>
						<!-- /row -->

						<div class="row mt">
							<!--CUSTOM CHART START -->
						</div>
						<!-- /row -->

					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->


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
									<a href="#"><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(friend)).getName()%></a><br />
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
				2014 - Project Pitch<a href="index.jsp#" class="go-top"> <i
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
    
	
	
	</script>


</body>
</html>
