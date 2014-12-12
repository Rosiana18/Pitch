<%@page import="BaseClasses.Message"%>
<%@page import="BaseClasses.User"%>
<%@page import="BaseClasses.Pitch"%>
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
   
      <section class="wrapper">
      <!-- Title -->
      <h3><i class="fa fa-angle-right"></i> 
      	<%=request.getParameter("pitch")%>
      </h3>

	<div class="row">
		<div class="col-lg-9 main-chart">					
		<div class="row mt">
			<div class="form-panel">
				<form class="form-horizontal style-form" action="/bid" method="post">
					
					<!-- Description -->
					<% 
					String pitch = request.getParameter("pitch");
					Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
					User currentUser = (User)session.getAttribute("user");
					ArrayList<String> titles = currentPitch.getAllTitles();
					ArrayList<String> descriptions = currentPitch.getAllDescriptions();
					for( int i = 0; i < titles.size(); i++){ 
					%>
					<h4 class="mb"><i class="fa fa-angle-right"></i> <%=titles.get(i)%></h4>	
					<div class="form-group">
					  <div class="col-sm-10">
						<p><%=descriptions.get(i)%></p>
					  </div>
					</div>
					
					<!--Project Duration and Team Size-->
					<%
						}
						int length = currentPitch.getDuration();
						String duration = "";
						if(length<=8 ){
							duration = "day(s)";
						}else if(length>8&&length<=16){
							duration = "week(s)";
						}else if(length>16&&length<=24){
							duration = "month(s)";
						}else if(length>24&&length<=32){
							duration = "1 year";
						}else if(length>32){
							duration = "year(s)";
						}
						int big = currentPitch.getSize();
						String size = "";
						if(big<=8){
							size = "1-2 members";
						}else if(big>8&&big<=16){
							size = "3-5 members";
						}else if(big>16&&big<=24){
							size = "5-10 members";
						}else if(big>24&&big<=32){
							size = "10-20 members";
						}else if(big>32){
							size = "> 20 members (organization)";
						} 
					%>
					<div class="form-group">
					  <label class="col-sm-2 col-sm-2 control-label"> Duration</label>
					  <div class="col-sm-10">
						<%=duration%>
					   </div>
					</div>
					<div class="form-group">
					  <label class="col-sm-2 col-sm-2 control-label"> Size</label>
					  <div class="col-sm-10">
						<%=size%>
					   </div>
					</div>
			
					<!--Categories-->
					<div class="form-group">
					  <label class="col-sm-2 col-sm-2 control-label"> Categories</label>
					  <div class="col-sm-10">       	
					  <%
						for(String categories: currentPitch.getCategoryTags()){
						%>
						  <%=categories%>&nbsp;  
						<%
						}
					  %>
					  </div>
					</div>
					<div>
					<input type="hidden" name="pitch" value="<%=pitch%>" />
					<%
					if(!currentPitch.getOwnerId().equals(currentUser.getEmail())
						&&!currentPitch.getUserList().contains(currentUser.getEmail())){
					%>
					<button type="submit" name="button" ="btn btn-round btn-success" value="add">Bid</button>
					<%
					}else if(currentPitch.getBidderList().contains(currentUser.getEmail())){
					%>
					<button type="submit" name="button" class="btn btn-round btn-success" value="remove">Unbid</button>
					<%
					}
					%>
				</div>
				</form>        		
			</div>
			
		 
			<!--Feedback Form-->			
			<div class="form-panel">
				<form id="form" class="form-horizontal style-form" action="/feed" method="post">
					<input type="hidden" name="pitch" value="<%=pitch%>" />
					<h4 class="mb"><i class="fa fa-angle-right"></i> Leave a Feedback</h4>
					<div class="form-group">
						<div class="col-sm-10">
							<label class="col-sm-2 col-sm-2 control-label">Subject: </label> 
							<input type="text" name="subject" class="form-control round-form">
							<label class="col-sm-2 col-sm-2 control-label">Feedback:</label>
							<input type="text" name="body" class="form-control round-form">
						</div>
					</div>
					<div>
						<button type="submit" class="btn btn-round btn-warning">Submit</button>
					</div>
				</form>	
			</div>
			
			
			<!--List of Feedbacks-->	   
       	  <div class="form-panel">
       	  	<form class="form-horizontal style-form">
       	  	<h4 class="mb"><i class="fa fa-angle-right"></i> Feedbacks</h4>	
      	  	<%
      	  	if(currentPitch.getFeedbacks()!= null){
      	  	  for(Message feedback: currentPitch.getFeedbacks()){
	      	    %>
	      		<div class="form-group">
	       	  	  <label class="col-sm-2 col-sm-2 control-label"> <%=feedback.getSubject()%></label>
	          	  <div class="col-sm-10">
					<%=feedback.getFrom()%>, on&nbsp;<%=feedback.getDate()%>,
					<%=feedback.getBody()%>
	          	  </div>
	          	</div>
	          	<%
          	  }
          	}else{
          	  	%>
          	  	<div><b>There is no feedback</b></div>
          	  
          	  	<%
          	}
          	%>
       	  	</form>
       	  
       	  </div>
       	</div>
		</div>
		
       	<!--RIGHT SIDE BAR-->
       	<div class="col-lg-3 ds">
		<!--COMPLETED ACTIONS DONUTS CHART-->

			<!-- Owner SECTION -->
			<h3>OWNER</h3>
			
			<div class="desc">
				<div class="thumb">
					<img class="img-circle" src="assets/img/ui-divya.jpg"
						width="35px" height="35px" align="">
				</div>
				<div class="details">
					<p>
						<a href="#"><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(currentPitch.getOwnerId())).getName()%></a><br />
						<muted>Available</muted>
					</p>
				</div>
			</div>
			<!--END OF MEMBER LIST SECTION-->

			<!-- MEMBER LIST SECTION -->
			<a href="memberList.jsp?pitch=<%=pitch%>">
			<h3>TEAM MEMBERS</h3></a>
			<%
				if(currentPitch.getUserList() != null){
				for(String member: currentPitch.getUserList()){
			%>
			<div class="desc">
				<div class="thumb">
					<img class="img-circle" src="assets/img/ui-divya.jpg"
						width="35px" height="35px" align="">
				</div>
				<div class="details">
					<p>
						<a href="#"><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(member)).getName()%></a><br />
						<muted>Available</muted>
					</p>
				</div>
			</div>
			<%
				}
			}
			//*******END OF MEMBER LIST SECTION**********
			
			//**** BIDDER LIST SECTION ***********
			int bidNum;
			if(currentPitch.getBidderList() == null){
				bidNum =0;
			}else{
				bidNum = currentPitch.getBidderList().size();
			}
			%>
			<a href="bidList.jsp?pitch=<%=pitch%>">
			<h3><%=bidNum%> BIDDERS</h3></a>
			<%
				if(bidNum>0){
				for(int i = 0; i < 5 ; i++){
					if(i<bidNum){
						String bidder= currentPitch.getBidderList().get(i);
			%>
			<div class="desc">
				<div class="thumb">
					<img class="img-circle" src="assets/img/ui-divya.jpg"
						width="35px" height="35px" align="">
				</div>
				<div class="details">
					<p>
						<a href="#"><%=((BaseClasses.User)DB.DBManager.getInstance().getUserByID(bidder)).getName()%></a><br />
						<muted>Available</muted>
					</p>
				</div>
			</div>
			<%
					}
				}
			}
			%>
			<!--end of bidders-->
			

		</div>
       	
       	
     </div>
	  <!-- end of feedback -->        
              
	</section>
	<!--main content end-->
	
		<!--footer start-->
		<footer class="site-footer">
			<div class="text-center">
				2014 <a href="pitch.jsp?pitch=<%=pitch%>" class="go-top"> <i
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
