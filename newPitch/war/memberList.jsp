<%@page import="BaseClasses.Message"%>
<%@page import="BaseClasses.Pitch"%>
<%@page import="BaseClasses.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
<script>
	var pitch = "";
</script>
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
						<%=((BaseClasses.User)session.getAttribute("user")).getName()%></h5>

					<li class="mt"><a href="index.jsp"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>
					<li class="sub-menu"><a  href="mypitches.jsp">
							<i class="fa fa-book"></i> <span>My Pitches</span>
					</a></li>
					<li class="sub-menu"><a  href="createPitch.jsp"> <i
							class="fa fa-book"></i> <span>Create a Pitch</span>
					</a></li>
					<li class="sub-menu"><a href="profile.jsp"> <i
							class="fa fa-cogs"></i> <span>My Profile</span>
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
          <section class="wrapper site-min-height">
          	<h3><i class="fa fa-angle-right"></i> Discover <%=request.getParameter("pitch")%>'s Members</h3>
          	<div class="row mt">
          		<div class="col-lg-12">
					<% 
					String pitch = request.getParameter("pitch");
					Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
					String userName = session.getAttribute("userName");
					int memNum;
					if(currentPitch.getUserList() == null){
						memNum =0;
					}else{
						memNum = currentPitch.getUserList().size();
					}
					//if viewer is a member/owner
					if(memNum>0&&currentPitch.getUserList().contains(userName)){
						for(int j=0; j < memNum/3 + 1; j++){
					%>
      				<!-- ROW OF PANELS -->
					<div class="row">
						<%
							for(int k=0; k < 3; k++){
								int i = j*3+k;
								if(i<memNum){
									String member = currentPitch.getUserList().get(i);
						%>
						<!-- PROFILE 01 PANEL -->
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="content-panel pn">
								<div id="profile-01">
									<h3><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getName()%></h3>
									<h6>Sexy Foxy</h6>
								</div>
								<div class="profile-01 centered">
									<a href="conversation.jsp?convID=<%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getEmail()%>">
									<p>MESSAGE ME</p></a>
								</div>
								<div class="centered">
									<h6><i class="fa fa-envelope"></i><br/><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getEmail()%></h6>
								</div>
							</div><!--/content-panel -->
						</div><!--/col-md-4 -->
						<%
								}
							}
						%>
						
					</div>
					
					<%	
						}
					}else if(currentPitch.getOwnerId().equals(userName)){
						for(int j=0; j < memNum/3 + 1; j++){
					%>
      				<!-- ROW OF PANELS -->
					<div class="row">
						<%
							for(int k=0; k < 3; k++){
								int i = j*3+k;
								if(i<memNum){
									String member = currentPitch.getUserList().get(i);
						%>
						<!-- PROFILE 01 PANEL -->
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="content-panel pn">
								<form action="/delete" method="post">
								<div id="profile-01">
									<h3><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getName()%></h3>
									<h6>Sexy Foxy</h6>
								</div>
								<button type="submit" class="profile-01 centered" style="width:100%">REMOVE MEMBER</button>
								<div class="centered">
									<a href="conversation.jsp?convID=<%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getEmail()%>">
									<h6><i class="fa fa-envelope"></i><br/><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getEmail()%></h6>
									</a>
								</div>
								<input type="hidden" name="pitch" value="<%=pitch%>" />
								<input type="hidden" name="member" value="<%=member%>" />
								</form>
							</div><!--/content-panel -->
						</div><!--/col-md-4 -->
						<%
								}
							}
						%>
						
					</div>
						
					
					<%
						}
					}else{
						for(int j=0; j < memNum/3 + 1; j++){
					%>
      				<!-- ROW OF PANELS -->
					<div class="row">
						<%
							for(int k=0; k < 3; k++){
								int i = j*3+k;
								if(i<memNum){
									String member = currentPitch.getUserList().get(i);
						%>
						<!-- PROFILE 02 PANEL -->
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="content-panel pn">
								<div id="profile-02">
									<div class="user">
										<img src="assets/img/friends/fr-06.jpg" class="img-circle" width="80">
										<h4><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getName()%></h4>
									</div>
								</div>
								<div class="pr2-social centered">
									<a href="#"><i class="fa fa-twitter"></i></a>
									<a href="#"><i class="fa fa-facebook"></i></a>
									<a href="#"><i class="fa fa-dribbble"></i></a>
								</div>
							</div><!--/panel -->
						</div><!--/ col-md-4 -->
							<%
								}
							}
							%>
					</div>
					<%
						}
					}
					%>
					
          		</div>
          	</div>
			
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->
	
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 <a href="bidList.jsp?pitch=<%=pitch%>" class="go-top"> <i
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
