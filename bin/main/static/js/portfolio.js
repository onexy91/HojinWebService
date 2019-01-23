var main2 = {
		init : function() {
			var _this = this;
			$('#btn-upload').on('click', function() {
				_this.save();
			});
		},
	save : function() {
		var form = $('#uploadform')[0];
		var formData = new FormData(form);
		$.ajax({
			type : 'POST',
			enctype : 'multipart/form-data',
			url : 'uploadportfolio',
			data : formData,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				console.log("SUCCESS : ", data);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	}
};
main2.init();