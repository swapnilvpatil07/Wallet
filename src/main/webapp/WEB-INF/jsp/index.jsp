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
<!-- <script src="/js/bootstrap-filestyle.min.js"></script> -->
<script src="/js/custom.js"></script>
</head>
<body style="background-color: #f2f2f2;">

	<jsp:include page="/WEB-INF/jsp/menu/gmenu.jsp"></jsp:include>
	<div class="container-fluid">

		<c:if test="${response.status eq 'success'}">
			<!-- Alert -->
			<div class="alert alert-success alert-dismissible shadow alert-gbl"
				style="margin-top: 10px;">
				<strong>Success!</strong> ${response.errorMsg}
			</div>
			<!-- Alert -->
		</c:if>
		<c:if test="${status eq 'error'}">
			<!-- Alert -->
			<div class="alert alert-danger alert-dismissible shadow alert-gbl"
				style="margin-top: 10px;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errorMsg}
			</div>
			<!-- Alert -->
		</c:if>

		<div class="card shadow-sm add-money-crd" style="margin: 10px 0px;">
			<div class="card-body p-0">
				<form action="/wallet/secure/init-txn" method="post" id="init-txn">
					<ul class="list-group list-group-horizontal add-money-ul">
						<li class="list-group-item border-0 border-left-0 li-cust-1">

							<div class="left-sec">
								<i class="fas fa-wallet addmoney-fa-wallet"></i>
							</div>
							<div class="right-sec p-0">
								<span class="stack-span-horz span-rs p-0 m-0">Rs. <c:if
										test="${pageContext.request.userPrincipal.name != null}">${balance}</c:if></span>
								<span class="span-msg p-0 m-0 stack-span-horz">Your
									Wallet Balance</span>
							</div>
						</li>
						<li class="list-group-item border-0 w-100 li-cust-2">
							<div class="form-group">
								<input name="txnAmt" type="text" class="form-control rounded-0"
									id="amount" placeholder="Enter Amount to be Added in Wallet"
									required="required">
							</div>
						</li>
						<li class="list-group-item border-0 w-25 li-cust-3"><button
								type="submit" class="btn btn-primary" id="add-money-btn"
								style="width: 100%;">Add Money</button></li>
					</ul>
				</form>
			</div>
		</div>

		<div class="card shadow-sm add-money-crd"
			style="margin: 10px 0px; height: auto;">
			<div class="card-body p-0">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<div class="text-left"
						style="margin: 0px 0px 5px 0px; background-color: #007bff; color: white; padding: 5px;">
						<b>Transaction Details</b>
					</div>
					<table class="table tbl-txn-details">
						<thead class="thead-light">
							<tr>
								<th class="th-cust">Merchant Name</th>
								<th class="text-center th-cust">Withdrawal</th>
								<th class="text-center th-cust">Deposit</th>
								<th class="text-center th-cust">Status</th>
								<th class="text-center th-cust">Remark</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty orderDetails}">
								<c:forEach items="${orderDetails}" var="order">
									<tr>
										<td>
											<ul class="ul-txn">
												<li><c:if test="${order.txnSts eq '1'}">
														<b class="success">Added to wallet</b>
													</c:if> <c:if test="${order.txnSts eq '0'}">
														<b class="fail">Failed to add in wallet</b>
													</c:if></li>
												<!-- <li>From:</li> -->
												<li>Transaction Id: ${order.orderId}</li>
												<li>On: ${order.txnDate}</li>
											</ul>

										</td>
										<td align="center"><c:if
												test="${order.txnType eq 'WHTDRW' }">
												<span class="amt"> ${order.txnAmt}</span>
											</c:if> <c:if test="${order.txnType ne 'WHTDRW' }">-</c:if></td>
										<td align="center"><c:if
												test="${order.txnType eq 'ADMNY' }">
												<i class="fa fa-inr"
													style="padding-right: 5px; font-size: 10px;"></i>
												<span class="amt">${order.txnAmt}</span>
											</c:if> <c:if test="${order.txnType ne 'ADMNY' }">-</c:if></td>
										<td align="center"><c:if test="${order.txnSts eq '0'}">
												<span class="fail">FAILED</span>
											</c:if> <c:if test="${order.txnSts eq '1'}">
												<span class="success">SUCCESS</span>
											</c:if></td>
										<td align="center"></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty orderDetails}">
								<tr>
									<td align="center" colspan="5"><b>No Transactions
											Found.</b></td>
							</c:if>
						</tbody>
					</table>
				</c:if>

			</div>
			
			<%-- <c:out value="${loginflg}"></c:out> --%>
		</div>

	</div>
	<div class="card shadow-sm add-money-crd"
		style="margin: 10px 0px; height: auto;">
		<div class="card-body p-0">
			<footer>
				<div class="footer-copyright text-center py-3">
					© 2019 Copyright: <a href="#"> FidelSoftech Pvt. Ltd.</a>
				</div>
				<!-- Copyright -->

			</footer>
		</div>
	</div>
	<script type="text/javascript">
		$('#datepicker').datepicker({
			uiLibrary : 'bootstrap4'
		});

		if ('${response.status}' == 'error') {
			$("#signupModal").modal();
		}

		if ('${loginflg}' == 'true') {
			if ('${pageContext.request.userPrincipal.name}' == '') {
				$("#loginModal").modal();
			}
		}

		$(".alert-success").fadeTo(5000, 5000).slideUp(1000, function() {
			$("#success-alert").slideUp(1000);
			$("#loginModal").modal();
		});
		
		$(".alert-danger").fadeTo(5000, 5000).slideUp(1000, function() {
			$("#success-alert").slideUp(1000);
			
		});

		var session = '${session}';
	</script>
</body>
</html>