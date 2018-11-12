$(document).ready(function(){
	$.validator.addMethod("nourl", 
			function(value, element) {
		return !/http\:\/\/|www\.|link\=|url\=/.test(value);
	}, 
	"No URL's"
	);});

$(document).ready(function(){
	$.validator.addMethod("wrongDate", function(value, element, params) {
		if(value === '' || $(params).val() === ''){
			return true
		}

		if (!/Invalid|NaN/.test(new Date(value))) {
			return new Date(value) > new Date($(params).val());
		}

		return isNaN(value) && isNaN($(params).val())
		|| (Number(value) > Number($(params).val()));
	}, 'La date doit être postérieur à celui de la date introduction');
});
$(document).ready(function() {
	$("#addForm").validate({
		rules : {
			computerName : {
				required : true,
				nourl : true
			},
			discontinued: { wrongDate: "#introduced"}
		},
		messages : {
			computerName : "Champ requis"

		}
	});

	$("#editForm").validate({
		rules : {
			computerName : {
				required : true
			},
			discontinued: { wrongDate: "#introduced"}
		},
		messages : {
			computerName : "Champ requis"
		}
	});

});