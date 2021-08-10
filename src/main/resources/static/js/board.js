let index ={
	init: function(){
	  $("#btn-save").on("click",()=>{ // function(){} 안쓰고 , ()=>{} 쓴 이유는 this를 바인딩하기 위해서
		this.save();
	});
	  $("#btn-delete").on("click",()=>{ // function(){} 안쓰고 , ()=>{} 쓴 이유는 this를 바인딩하기 위해서
		this.deleteById();
	});
	  $("#btn-update").on("click",()=>{ // function(){} 안쓰고 , ()=>{} 쓴 이유는 this를 바인딩하기 위해서
		this.update();
	});
	  $("#btn-reply-save").on("click",()=>{ // function(){} 안쓰고 , ()=>{} 쓴 이유는 this를 바인딩하기 위해서
		this.replySave();
	});
	 /*$("#btn-login").on("click",()=>{ // function(){} 안쓰고 , ()=>{} 쓴 이유는 this를 바인딩하기 위해서
		this.login();
	 });*/		
	},
	/*
	login: function(){
		//alert('user의 save함수 호출됨');
		let data ={
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		//console.log(data);
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		//ajax 호출시 default가 비동기 호출
		//ajax가 통신성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌 
		$.ajax({
			type:"POST",
			url:"/api/user/login",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			location.href ="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},*/
	
deleteById: function(){
       let id=$("#id").text();     

		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href ="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},
	save: function(){
		//alert('user의 save함수 호출됨');
		let data ={
			title: $("#title").val(),
			content: $("#content").val()
		};
		

		$.ajax({
			type:"POST",
			url:"/api/board",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href ="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},	
	update: function(){
		let id = $("#id").val();
		
		let data ={
			title: $("#title").val(),
			content: $("#content").val()
		};
		

		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href ="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},			
	replySave: function(){
		//alert('user의 save함수 호출됨');
		let data ={
			content: $("#reply-content").val()
		};
		let boardid = $("#boardid").val();

		$.ajax({
			type:"POST",
			url:`/api/board/${boardid}/reply`,
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("댓글 작성이 완료되었습니다.");
			location.href =`/board/${boardid}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},	
	
	replyDelete: function(boardid,replyid){
		$.ajax({
			type:"DELETE",
			url:`/api/board/${boardid}/reply/${replyid}`,
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경해줌
			
		}).done(function(resp){
			alert("댓글삭제 성공.");
			location.href =`/board/${boardid}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		
	},	
}

index.init();