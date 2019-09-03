<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="/js/custom.js"></script>
<nav
	class="navbar navbar-expand-md navbar-light shadow-sm p-3 bg-white rounded">
	<a class="navbar-brand" href="#"> <img
		src="https://img.icons8.com/cotton/64/000000/wallet--v2.png"
		width="30" height="30" alt="">
	</a> <a class="nav-brand-nm navbar-brand " href="/wallet/secure/home">Web
		Wallet</a>



	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<!-- <ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			</ul> -->

		<!-- <ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#"> <i
						class="fa fa-bell"> <span class="badge badge-info">11</span>
					</i>
				</a></li>
			</ul> -->


		<ul class="nav navbar-nav ml-auto">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li class="nav-item" style="padding-right: 10px;"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="fa fa-bell"> <span class="badge badge-primary">2</span>
					</i> Notification
				</a>
					<div class="dropdown-menu notification">
						<a class="dropdown-item" href="#">Notification</a> <a
							class="dropdown-item" href="#">Notification</a>
					</div></li>

			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#signupModal"><span
						class="fas fa-user"></span> Sign Up</a></li>
				<li class="nav-item"><a id="loginbtn" class="nav-link" href="#"
					data-toggle="modal" data-target="#loginModal"><span
						class="fas fa-sign-in-alt"></span> Login</a></li>
			</c:if>


			<%-- <c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="fas fa-user"></span>
							${pageContext.request.userPrincipal.name}
					</a></li>
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<li class="nav-item"><a class="nav-link" href="#"
						onclick="document.forms['logoutForm'].submit()"> <span
							class="fas fa-user">Logout</span></a></li>

				</c:if> --%>
		</ul>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<div class="dropdown pmd-dropdown pmd-user-info">
				<a href="javascript:void(0);"
					class="btn-user dropdown-toggle media align-items-center"
					data-toggle="dropdown" data-sidebar="true" aria-expanded="false">
					<img class="mr-2" src="/img/boy.png" width="40" height="40"
					alt="avatar">
					<div class="media-body">
						<c:if
							test="${not empty userInfo.fName and not empty userInfo.lName}">
							<c:out value="${userInfo.fName} ${userInfo.lName}"></c:out>
						</c:if>
						<c:if test="${empty userInfo.fName or empty userInfo.lName}">
							<c:out value="${pageContext.request.userPrincipal.name }"></c:out>
						</c:if>

					</div> <i class="material-icons md-light ml-2 pmd-sm"></i>
				</a>
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<ul class="dropdown-menu dropdown-menu-right" role="menu">
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#profileModal">Edit Profile</a>
					<a class="dropdown-item" href="#">Send Money</a>
					<a class="dropdown-item" href="#"
						onclick="document.forms['logoutForm'].submit()">Logout</a>
				</ul>
			</div>
		</c:if>
	</div>
</nav>

<!-- Modal -->
<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Sign Up</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<c:if test="${response.status eq 'error'}">
					<!-- Error Alert -->
					<div
						class="alert alert-danger alert-dismissible fade show err-signup">
						<strong>Error!</strong>
						<c:out value=" ${response.errorMsg }"></c:out>
						<button type="button" class="close" data-dismiss="alert">&times;</button>
					</div>
					<!-- Error Alert -->
				</c:if>

				<div class="card bg-light">
					<article class="card-body">
						<form method="post" action="/wallet/user/register" id="signup">
							<!-- form-group// -->
							<div class="form-group input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class="fa fa-envelope"></i>
									</span>
								</div>
								<input name="email" type="email" class="form-control"
									placeholder="Email address">
							</div>
							<!-- form-group// -->
							<div class="form-group input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i class="fa fa-phone"></i>
									</span>
								</div>
								<select class="custom-select" style="max-width: 70px;">
									<option value="+91">+91</option>
								</select> <input name="contact" class="form-control"
									placeholder="Phone number" type="text">
							</div>
							<!-- form-group end.// -->
							<div class="form-group input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i class="fa fa-lock"></i>
									</span>
								</div>
								<input name="password" type="password" class="form-control"
									placeholder="Create password">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block"
									name="create-account">Create Account</button>
							</div>
							<!-- form-group// -->
							<p class="text-center">
								Have an account? <a href="#" data-dismiss="modal"
									data-toggle="modal" data-target="#loginModal"
									id="loginbtn-signup">Log In</a>
							</p>
						</form>
					</article>
				</div>
				<!-- card.// -->

			</div>
		</div>

	</div>
</div>
<!-- Modal -->


<!-- Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Sign In to Web
					Wallet</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<c:if test="${param.error ne null}">
					<!-- Error Alert -->
					<div id="loginModalForm"
						class="alert alert-danger alert-dismissible fade show err-login">
						<strong>Error!</strong>
						<c:out value="${errorMessge}"></c:out>

						<button type="button" class="close" data-dismiss="alert">&times;</button>
					</div>
					<!-- Error Alert -->
				</c:if>
				<div class="card bg-light">
					<article class="card-body" style="max-width: 500px;">
						<form action="/wallet/secure/postReq" method="post" id="loginform">
							<div class="form-group input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i class="fa fa-user"></i>
									</span>
								</div>
								<input type="text" name="email" class="form-control"
									placeholder="Email Address/Mobile No">
							</div>
							<div class="form-group input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i class="fa fa-lock"></i>
									</span>
								</div>
								<input name="password" class="form-control"
									placeholder="Enter password" type="password">
							</div>
							<div class="form-group input-group">
								<div>
									<input type="checkbox" name="remember-me" /> <span>
										Remember Me</span>
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block"
									name="login-btn">Login Securely</button>
							</div>
							<div class="mt-4">
								<div class="d-flex justify-content-center links">
									Don't have an account? <a href="#" class="ml-2"
										data-dismiss="modal" data-toggle="modal"
										data-target="#signupModal">Sign Up</a>
								</div>
								<div class="d-flex justify-content-center links">
									<a href="#">Forgot your password?</a>
								</div>
							</div>
							<%-- <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> --%>
							<!-- <input type="hidden" name="remember-me" /> -->
						</form>
					</article>
				</div>
				<!-- card.// -->
			</div>
		</div>
	</div>
</div>
<!-- Modal -->

<!-- Modal Profile -->
<div class="modal fade" id="profileModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Profile</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<c:if test="${param.error ne null}">
					<!-- Error Alert -->
					<div id="profileModalForm"
						class="alert alert-danger alert-dismissible fade show err-profile">
						<strong>Error!</strong>
						<c:out value="${errorMessge}"></c:out>

						<button type="button" class="close" data-dismiss="alert">&times;</button>
					</div>
					<!-- Error Alert -->
				</c:if>
				<div class="card bg-light">
					<article class="card-body">

						<div class="row m-y-2">

							<div class="col-lg-12" id="err-profile">
								<div class="alert alert-info alert-dismissable">
									<a class="panel-close close" data-dismiss="alert">×</a> <span
										id="message"></span>
								</div>
							</div>

							<div class="col-lg-8 push-lg-4 personal-info">
								<form role="form">
									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label">First
											name</label>
										<div class="col-lg-9">
											<input name="fname" class="form-control" type="text"
												value="${userInfo.fName}" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label">Last
											name</label>
										<div class="col-lg-9">
											<input name="lname" class="form-control" type="text"
												value="${userInfo.lName}" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label">Email</label>
										<div class="col-lg-7">
											<input name="emailid" class="form-control" type="email"
												value="${userInfo.emailId}" />
										</div>
										<div class="col-lg-2  pl-0">
											<button name="verify-otp-btn" type="button" class="btn btn-primary">Verify</button>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label">Mobile
											No</label>
										<div class="col-lg-5">
											<input name="mobileno" class="form-control" type="text"
												value="${userInfo.contactNo}" placeholder="Mobile No" />
										</div>
										<div class="col-lg-2  pl-0">
											<input name="otp" class="form-control" type="text"
												placeholder="OTP" />
										</div>
										<div class="col-lg-2  pl-0">
											<button id="otp-btn" type="button" class="btn btn-primary"
												onclick="return sendOtp();">Send</button>
											<button id="verify-btn" type="button" class="btn btn-primary"
												onclick="return verifyOtp();">Verify</button>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label">DOB</label>
										<div class="col-lg-6">
											<input name="dob" id="datepicker" class="form-control"
												type="text" value="${userInfo.dob}" placeholder="01/01/1950" />
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-3 col-form-label form-control-label"></label>
										<div class="col-lg-9">
											<input type="reset" class="btn btn-secondary" value="Cancel" />
											<input type="button" class="btn btn-primary"
												value="Save Changes" onclick="doAjaxPost();" />
										</div>
									</div>
								</form>
							</div>
							<div
								class="col-lg-4 pull-lg-8 text-xs-center text-center shadow p-2">
								<%-- <img src="//placehold.it/150"
									class="m-x-auto img-fluid rounded-circle" alt="avatar" />
								<h6 style="margin-top: 10px;">Upload a different photo</h6>
								<form action="/action_page.php">
									<!-- <p>Custom file:</p> -->
									<div class="custom-file mb-5 mt-2">
										<label class="btn btn-primary" for="customFile">Choose
											file</label><input type="file" class="custom-file-input"
											id="customFile" name="filename">
									</div>
								</form> --%>
								<span class="font-weight-bold"> My QR Code</span> <img
									class="mt-2 mb-2"
									src="${pageContext.request.contextPath }/wallet/qrcode/${userInfo.custId}">
								<span> <c:out
										value="${userInfo.fName}  ${userInfo.lName}"></c:out> <%-- <c:out value="${userInfo.contactNo}"></c:out>,
								<c:out value="${userInfo.emailId}"></c:out> --%>
								</span>
							</div>
						</div>

					</article>
				</div>
				<!-- card.// -->
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>


<script>
	$('#err-profile').hide();

	function doAjaxPost() {
		var fname = $('input[name="fname"]').val();
		var lname = $('input[name="lname"]').val();
		var dob = $('input[name="dob"]').val();
		var mobileno = $('input[name="mobileno"]').val();
		var emailid = $('input[name="emailid"]').val();

		$.ajax({
			type : "Get",
			url : "/wallet/update-profile",
			data : "fname=" + fname + "&lname=" + lname + "&emailid=" + emailid
					+ "&mobileno=" + mobileno + "&dob=" + dob,
			success : function(response) {
				var userInfo = response.data;
				if (response.status == 'success') {
					$('#err-profile').show();
					$('#message').text(response.errorMsg);
					fadeAlert();
				}
			},
			error : function(e) {
				if (response.status == 'error') {
					$('#err-profile').show();
					$('#message').text(response.errorMsg);
				}
			}
		});
	}

	function fadeAlert() {
		$("#err-profile").fadeTo(10000, 500).slideUp(1000, function() {
			$("#err-profile").slideUp(1000);
		});
	}

	function disableButton() {
		var cnt = '${userInfo.cntVerified}';
		if ((cnt == '1')) {
			$('input[name=otp]').hide();
			$('#otp-btn').text('Verified').prop('disabled', true);
			$('#otp-btn').parent().removeClass('col-lg-2').addClass('col-lg-3');
			$('input[name=mobileno]').parent().removeClass('col-lg-5')
					.addClass('col-lg-6');
			$('input[name=otp]').parent().removeClass('col-lg-2');
		}
	}

	$(document).ready(function() {
		disableButton();
		$('button[name="login-btn"]').click(function() {
			$('input[name="remember-me"]').prop('checked', true);
		});
	});
</script>


