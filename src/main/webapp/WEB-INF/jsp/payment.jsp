<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Web Wallet</title>
<link rel="shortcut icon"
	href="https://img.icons8.com/cotton/64/000000/wallet--v2.png" />
<link href="/css/custom.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="/js/custom.js"></script>
<style type="text/css">
/* .nav-link-cust.active {
	border-top-left-radius: 0rem;
	border-top-right-radius: 0rem;
	background-color: #f2f2f2;
	font-weight: 500;
	color:;
	background-color:;
	border-color:;
}

.nav-link-cust:focus {
	border-top-left-radius: 0rem;
	border-top-right-radius: 0rem;
	background-color: #f2f2f2;
	font-weight: 500;
	color:;
	background-color:;
	border-color:;
} */
</style>
</head>
<body style="background-color: #f2f2f2;" class="pay-body">

	<nav
		class="navbar navbar-expand-md navbar-light shadow-sm p-3 bg-white rounded">
		<a class="navbar-brand" href="#"> <img
			src="https://img.icons8.com/cotton/64/000000/wallet--v2.png"
			width="30" height="30" alt="">
		</a> <a class="navbar-brand" href="/wallet/home">Web Wallet</a>



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


		</div>
	</nav>
	<div class="container-fluid">
		<div class="pay-title text-center">
			<h4 class="text-primary">Select Payment Method</h4>
		</div>
		<c:if test="${response.status eq 'success'}">
			<!-- Alert -->
			<div class="alert alert-success alert-dismissible shadow alert-gbl"
				style="margin-top: 10px;">
				<strong>Success!</strong> ${response.errorMsg}
			</div>
			<!-- Alert -->
		</c:if>

		<div class="card shadow-sm payment-crd" style="margin: 10px 200px;">
			<div class="card-body p-0">
				<ul class="list-group list-group-horizontal pay-details-ul">
					<li
						class="list-group-item  border-0 border-left-0 pay-details-left w-50">
						<span class="stack-span-horz">Total payment to be made: </span> <span
						class="stack-span-horz">Transaction ID: <span><c:out
									value="${payload.orderId}"></c:out></span></span>
					</li>
					<li
						class="list-group-item border-0 li-cust-2 pay-details-right w-50">
						<div style="float: right;">
							<i class="fa fa-inr" style="padding-right: 5px; font-size: 15px;"></i>
							<c:out value="${payload.txnAmount}"></c:out>
						</div>
					</li>

				</ul>
			</div>
		</div>
		<div class="card shadow-sm add-money-crd"
			style="margin: 10px 200px; height: auto;">
			<div class="card-body">
				<div>
					<div class="row">
						<div class="col-md-3 mb-3">
							<ul class="nav nav-tabs flex-column nav-tab-cust" id="myTab"
								role="tablist">
								<li class="nav-item"><a
									class="nav-link active nav-link-cust" id="saved-cards-tab"
									data-toggle="tab" href="#saved-cards" role="tab"
									aria-controls="home" aria-selected="true">Saved Details</a></li>
								<li class="nav-item"><a class="nav-link nav-link-cust"
									id="bhim-upi-tab" data-toggle="tab" href="#bhim-upi" role="tab"
									aria-controls="profile" aria-selected="false">BHIM UPI</a></li>
								<li class="nav-item"><a class="nav-link nav-link-cust"
									id="debit-card-tab" data-toggle="tab" href="#debit-card"
									role="tab" aria-controls="contact" aria-selected="false">Debit
										Card</a></li>
								<li class="nav-item"><a class="nav-link nav-link-cust"
									id="credit-card-tab" data-toggle="tab" href="#credit-card"
									role="tab" aria-controls="contact" aria-selected="false">Credit
										Card</a></li>
								<li class="nav-item"><a class="nav-link nav-link-cust"
									id="net-banking-tab" data-toggle="tab" href="#net-banking"
									role="tab" aria-controls="contact" aria-selected="false">Net
										Banking</a></li>
							</ul>
						</div>
						<!-- /.col-md-4 -->
						<div class="col-md-9 pl-0 cust-col-md">
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="saved-cards"
									role="tabpanel" aria-labelledby="saved-cards-tab">
									<div class="card">
										<div class="card-body">
											<h4>Saved Details</h4>
											<p>No Data</p>
										</div>
									</div>

								</div>
								<div class="tab-pane fade" id="bhim-upi" role="tabpanel"
									aria-labelledby="bhim-upi-tab">
									<h4>BHIM UPI</h4>
									<p></p>
								</div>
								<div class="tab-pane fade" id="debit-card" role="tabpanel"
									aria-labelledby="debit-card-tab">
									<div class="card">
										<div class="card-header bg-primary">
											<h4 class="mb-0" style="color: white;">Debit Card</h4>
										</div>
										<div class="card-body p-3">
											<div class="form-group">
												<label for="cardNumber">Card number</label>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text"><i
															class="fa fa-credit-card"></i></span>
													</div>
													<input type="text" class="form-control" name="cardNumber"
														placeholder="">
												</div>
												<!-- input-group.// -->
											</div>
											<!-- form-group.// -->

											<div class="row">
												<div class="col-sm-8">
													<div class="form-group">
														<label><span class="hidden-xs">Expiration</span> </label>
														<div class="form-inline">
															<select class="form-control" style="width: 45%">
																<option>MM</option>
																<option>01 - Janiary</option>
																<option>02 - February</option>
																<option>03 - February</option>
															</select> <span style="width: 10%; text-align: center"> / </span>
															<select class="form-control" style="width: 45%">
																<option>YY</option>
																<option>2018</option>
																<option>2019</option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label data-toggle="tooltip" title=""
															data-original-title="3 digits code on back side of the card">CVV
															<i class="fa fa-question-circle"></i>
														</label> <input class="form-control" required="" type="text">
													</div>
													<!-- form-group.// -->
												</div>
											</div>
											<button class="subscribe btn btn-primary btn-block"
												type="button">Confirm</button>
											<!-- row.// -->
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="credit-card" role="tabpanel"
									aria-labelledby="credit-card-tab">
									<h4>Credit Card</h4>
									<p></p>

								</div>
								<div class="tab-pane fade" id="net-banking" role="tabpanel"
									aria-labelledby="net-banking-tab">

									<form action="${payload.serverUrl}" method="post" class="payment-form">
										<div class="card card-net-bnk">
											<div class="card-header">
												<h4 class="card-header-title">Net Banking</h4>
											</div>

											<div class="card-body">
												<ul class="list-group">
													<li class="list-group-item border-0"><div
															class="card-columns">
															<div class="card crd-cust-pay">
																<a href="#" onclick="addActive();" id="cls-1">
																	<ul class="list-group">
																		<li class="list-group-item border-0 pb-1 pl-2"><img
																			src="/img/fid-bnk-1.png" width="auto" height="24"></li>
																		<li class="list-group-item border-0 pt-1">Fidel
																			Bank</li>
																	</ul>
																</a>
															</div>
															<div class="card crd-cust-pay">
																<a href="#" onclick="addActive();" id="cls-2">
																	<ul class="list-group crd-cust-pay">
																		<li class="list-group-item border-0 pb-1"><img
																			src="/img/ICICI.png" width="24" height="24"></li>
																		<li class="list-group-item border-0 pt-1">ICICI
																			Bank</li>
																	</ul>
																</a>
															</div>
															<div class="card crd-cust-pay">
																<a href="#" onclick="addActive();" id="cls-3">
																	<ul class="list-group crd-cust-pay">
																		<li class="list-group-item border-0 pb-1"><img
																			src="/img/HDFC.png" width="24" height="24"></li>
																		<li class="list-group-item border-0 pt-1">HDFC
																			Bank</li>

																	</ul>
																</a>
															</div>
														</div></li>
													<li class="list-group-item border-0">
														<div class="form-group row">
															<div class="col-lg-9">
																<select class="form-control" id="other-bnk"
																	name="othr-bnk">
																	<option selected="selected" hidden="hidden">Select
																		from other banks</option>
																	<option value="1">State Bank of India</option>
																	<option value="2">State Bank of Mysore</option>
																	<option value="3">State Bank of Hyderabad</option>
																	<option value="4">State Bank of Travancore</option>
																	<option value="5">State Bank of Bikaner and
																		jaipur</option>
																	<option value="6">Union Bank</option>
																	<option value="7">Bank of India</option>
																	<option value="8">Canara Bank</option>
																	<option value="9">Corporation Bank</option>
																	<option value="10">City Union Bank</option>
																	<option value="11">Indian Bank</option>
																	<option value="12">Indian Overseas Bank</option>
																	<option value="13">CITIBANK</option>
																	<option value="14">Indusind Bank</option>
																	<option value="15">Kotak Mahindra Bank</option>
																	<option value="16">IDBI Bank</option>
																	<option value="17">Fedral Bank</option>
																	<option value="18">Deutsche Bank</option>
																	<option value="19">Yes Bank</option>
																	<option value="20">Bank of Maharashtra</option>
																	<option value="21">Catholic Syrian Bank</option>
																	<option value="22">Karnataka Bank</option>
																</select>
															</div>
															<input type="hidden" name="bnk" value="">
															<div class="col-lg-3">
																<button type="submit"
																	class="btn btn-primary w-100 cust-pay-btn"
																	onclick="return validate();">
																	PAY <span class="fa fa-inr fa-lg"></span>
																	<c:out value="${payload.txnAmount}"></c:out>
																</button>
															</div>
														</div>
													</li>
												</ul>
											</div>
										</div>

										<input type="hidden" name="txnAmount"
											value="${payload.txnAmount}"> <input type="hidden"
											name="orderId" value="${payload.orderId}"> <input
											type="hidden" name="mobileNo" value="${payload.mobileNo}">
										<input type="hidden" name="email" value="${payload.email}">
										<input type="hidden" name="callBckUrl"
											value="${payload.callBckUrl}"> <input type="hidden"
											name="mId" value="${payload.mId}"> <input
											type="hidden" name="reqType" value="${payload.reqType}">
									</form>
								</div>
							</div>
						</div>
						<!-- /.col-md-8 -->
					</div>

				</div>

			</div>
		</div>

	</div>

	<script type="text/javascript">
		if ('${response.status}' == 'error') {
			$("#signupModal").modal();
		}

		$(".alert-success").fadeTo(2000, 500).slideUp(1000, function() {
			$("#success-alert").slideUp(1000);
			$("#loginModal").modal();
		});

		var session = '${session}';

		function validate() {
			var bkCd = $('input[name="bnk"]').val();
			if (bkCd == '') {
				alert("Select Fidel Bank");
				$(".payment-form").submit(function(e) {
					e.preventDefault();
				});
			}else{
				$('.payment-form').unbind('submit');
			}
			return true;
		}
	</script>
</body>
</html>