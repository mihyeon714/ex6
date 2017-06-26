/**
 * 
 */

/* 
	//AJAX 이용하기 4가지 예시
	
	$.get("URL?name=value",function(data){		});
	
	$.post("URL",{name:value, name2=value2},function(data){		});
	
	$.ajax({
		url:"./memoList",
		type: "POST",
		data: {},
		success:function(data){}
	});
	
	$("선택자").load(); // 선택자에 바로 받아온것 뿌려주세요
 */


function memoView(num){
	
	//rest Service 방식 사용해보기
	$.get("memoView/"+num,function(data){
		alert("writer = "+data.writer); //JSON 형태일 것
	});
	
	
	/*
	$.get("memoView?num="+num,function(data){
		alert("writer = "+data.writer); //JSON 형태일 것
	});
	*/
}




function memoWrite(writer,contents){

	$.ajax({
		url:"memoWrite",
		type:"POST",
		data:{
			writer:writer,
			contents:contents
		},
		success:function(data){
			/* 
				//1번 :message 받기
				alert(data.trim());
				getList(1,'','');
			 */

			/*
			//2번 : 입력된 것 맨위에 한줄 추가해주기
			$("#result").prepend(data); //이렇게하면 제목 아래에 result를 세팅해야함
			*/
			
			/*
			//3번 : 아예 list를 받아온다
			$("#result").html(data);
			*/
			
			//jackson api 사용하기
			
			var result="<table>";
			$(data).each(function(){
				result = result + "<tr>";
				result = result + "<td>"+ this.num + "</td>";
				result = result + "<td>"+ this.contents + "</td>";
				result = result + "<td>"+ this.writer + "</td>";
				result = result + "<td>"+ this.reg_date + "</td>";
				result = result + "</tr>";
			});
			result = result + "</table>";

			
			
			$("#result").html(result);
		}

	});
}


function getList(curPage, search, find){
	//alert("test");

	$.ajax({
		
		//rest 방식 사용하기 
		url: "getMemoList/"+curPage+"/"+search+"/"+find,
		type:"GET",
		
		/*
		url:"getMemoList",
		type: "GET",
		data: {
			curPage: curPage,
			search: search,
			find: find
		},*/
		success:function(data){
			//alert(data=data.trim());
			//$("#result").html(data); // DTO 로 받은결과
			
			//JSON 으로 받았을때
			//data = data.trim();
			//alert("data= "+data);
			//data = JSON.parse(data);
			//alert("JSONdata= "+data);
			
			//jackson 바인딩 사용하면 바로 여기서 data 에 json 형태로 날아온다
			//parse 가 필요없어짐
			
			
			var result="<table>";
			$(data).each(function(){
				result = result + "<tr>";
				result = result + "<td>"+ this.num + "</td>";
				result = result + "<td>"+ this.contents + "</td>";
				result = result + "<td>"+ this.writer + "</td>";
				result = result + "<td>"+ this.reg_date + "</td>";
				result = result + "</tr>";
			});
			result = result + "</table>";

			
			
			$("#result").html(result);
		}
	});
}