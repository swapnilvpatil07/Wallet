<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Merchant Check Out Page</title>
</head>
<body>
	<center>
		<h1>Please do not refresh this page...</h1>
	</center>
	<form method="post"
		action="${payload.serverUrl}"
		name="f1">
		<table border="1">
			<tbody>
				<input type="hidden" name="mId" value="${payload.mId}">
				<input type="hidden" name="reqType" value="${payload.reqType}">
				<input type="hidden" name="ssoToken" value="${payload.ssoToken}">
				<input type="hidden" name="orderId" value="${payload.orderId}">
				<input type="hidden" name="custId" value="${payload.custId}">
				<input type="hidden" name="mobileNo" value="${payload.mobileNo}">
				<input type="hidden" name="email" value="${payload.email}">
				<input type="hidden" name="channelId" value="${payload.channelId}">
				<input type="hidden" name="txnAmount" value="${payload.txnAmount}">
				<input type="hidden" name="callBckUrl" value="${payload.callBckUrl}">
				<input type="hidden" name="serverUrl" value="${payload.serverUrl}">
				<input type="hidden" name="bnk" value="${bnk}">
				
			</tbody>
		</table>
		<script type="text/javascript">
			document.f1.submit();
		</script>
	</form>
</body>
</html>