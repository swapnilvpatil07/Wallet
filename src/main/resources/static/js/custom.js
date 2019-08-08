$(document).ready(function() {
	$('#verify-btn').hide();
	
	$("#signupModal").on("hidden.bs.modal", function(){
		$(".err-signup").remove();	
		
		$(this).find("input,textarea,select").val('').end()
			   .find("input[type=checkbox], input[type=radio]").prop("checked", "").end();
	});
	
	$("#loginModal").on("hidden.bs.modal", function(){
		$(".err-login").remove();	
		
		$(this).find("input,textarea,select").val('').end()
			   .find("input[type=checkbox], input[type=radio]").prop("checked", "").end();
	});
	
	$("#loginbtn, #loginbtn-signup").click(function() {
		$(".err-login").remove();	
		$("#loginform").find("input,textarea,select").val('').end()
			   .find("input[type=checkbox], input[type=radio]").prop("checked", "").end();
	});
	
	$("#cls-1, #cls-2, #cls-3").click(function(code){
		activeCrd(this, code);
		var abc = code;
	});
	
	$('#other-bnk').change(function() {
		$('.crd-cust-pay').removeClass('crd-active');
		$('.crd-cust-pay').find('ul').children().css('color','');
	});
});

function activeCrd(e) {
	$('.crd-cust-pay').removeClass('crd-active');
	$('.crd-cust-pay').find('ul').children().css('color','');
	
	$(e).parent('.crd-cust-pay').addClass('crd-active');
	$(e).find('ul').children().css('color','#0044cc');
	$('#other-bnk').prop('selectedIndex',0);
	
	if ($(e).attr('id') == 'cls-1') {
		$('input[name="bnk"]').val(42);
	}
	
	if ($(e).attr('id') == 'cls-2') {
		$('input[name="bnk"]').val(24);
	}
	
	if ($(e).attr('id') == 'cls-3') {
		$('input[name="bnk"]').val(25);
	}
	
	
}

function sendOtp() {
	var mobileno = $('input[name="mobileno"]').val();
	
	$.ajax({
		type : "Get",
		url : "/wallet/otp",
		data : "mobileno=" + mobileno,
		success : function(response) {
			if (response.status == 'success') {
				$('#err-profile').show();
				$('#message').text(response.errorMsg);
				fadeAlert();
				$('#otp-btn').hide();
				$('#verify-btn').show();
			}
		},
		error : function(response) {
			if (response.status == 'error') {
				$('#err-profile').show();
				$('#message').text(response.errorMsg);
				fadeAlert();
			}else {
				$('#err-profile').show();
				$('#message').text(response.responseJSON.errorMsg);
				fadeAlert();
			}
		}
	});
	//alert("Sent");
	
}

function verifyOtp() {
	var otp = $('input[name="otp"]').val();
	
	$.ajax({
		type : "Get",
		url : "/wallet/verify-otp",
		data : "otp=" + otp,
		success : function(response) {
			if (response.status == 'success') {
				$('#err-profile').show();
				$('#message').text(response.errorMsg);
				fadeAlert();
				$('#otp-btn').show();
				$('#verify-btn').hide();
				
				$('input[name=otp]').hide();
				$('#otp-btn').text('Verified').prop('disabled', true);
				$('#otp-btn').parent().removeClass('col-lg-2').addClass('col-lg-3');
				$('input[name=mobileno]').parent().removeClass('col-lg-5').addClass(
						'col-lg-6');
				$('input[name=otp]').parent().removeClass('col-lg-2');
			}
		},
		error : function(response) {
			if (response.status == 'error') {
				$('#err-profile').show();
				$('#message').text(response.errorMsg);
				$('#otp-btn').show();
				$('#verify-btn').hide();
			} else {
				$('#err-profile').show();
				$('#message').text(response.responseJSON.errorMsg);
				$('#otp-btn').show();
				$('#verify-btn').hide();
			}
		}
	});
}
