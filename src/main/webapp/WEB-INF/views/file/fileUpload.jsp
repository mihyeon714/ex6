<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fileUpload</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var count = 0;
		var name = 1;
		$("#add").click(function(){
			
			if(count<5){
				var data = '<p class="delP"><input type="file" name="f'+name+'"><span class="del">X</span></p>';
				$("#file").append(data);
				count++;
				name++;
			}else{
				alert(count+"그만 넣으세요");
			}
		});
		
		$("#file").on("click",".del",(function(){
			$(this).parent(".delP").remove(); //눌린 본인의 부모 P를 삭제하다
			count--;
			alert(count);
		}));
		
	});
</script>
</head>
<body>

	<h3>파일업로드</h3>
	<form action="fileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<button>UPLOAD</button>
	</form>
	
	<h3>다중파일업로드 - 이름 다르고 갯수 알때</h3>
	<form action="multiFileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<input type="file" name="f2">
		<button>UPLOAD</button>
	</form>
	
	<h3>다중파일업로드 - 이름이 같을 때</h3>
	<!-- name 이 같고 여러개 파일 넘어감 -->
	<form action="sameMultiFileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<input type="file" name="f1">
		<input type="file" name="f1">
		<button>UPLOAD</button>
	</form>
	
	
	<h3>다중파일업로드 - 이름은 같고 갯수를 모를 때</h3>
	<form action="upload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<div id="file">
		
		</div>
		<input type="button" id="add" value="FILE ADD">
		
		<button>UPLOAD</button>
	</form>

</body>
</html>