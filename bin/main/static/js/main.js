var main = {
	init : function() {
		var _this = this;
		$('#btn-send').on('click', function() {
			_this.save();
		});
	},
	save : function() {
		var data = {
			name : $('#name').val(),
			author : $('#author').val(),
			content : $('#content').val()
		};
		if(!data.name=="" && !data.author == ""){
		$.ajax({
			type : 'POST',
			url : 'posts',
			datatype : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data)
		}).done(function() {
			alert('게시글이 작성되었습니다.');
			location.reload(true);
		}).fail(function(error) {
			alert(error);
		});
		}else{
			alert('게시글 작성에 실패')
		}
	}

};
main.init();


