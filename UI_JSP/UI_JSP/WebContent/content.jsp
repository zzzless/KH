<%@page import="com.kh.db.UIVo"%>
<%@page import="com.kh.db.UIDao"%>
<%@page import="sun.security.jca.GetInstance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String sno = request.getParameter("SNO");
	//System.out.println(sno);
	UIDao dao = UIDao.getInstance();
	UIVo vo = dao.getContent(sno);
	System.out.println(vo);
%>

<%@ include file="include/header.jsp"%>
<title>Insert title here</title>
<script>
$(function() {
	// 수정버튼
	$("#btnModify").click(function() {
		// 비활성화 -> 활성화
		$("#snameInput").attr("readonly", false);
		$("#syearInput").attr("readonly", false);
		$("#genderMan").attr("disabled", false);
		$("#genderWoman").attr("disabled", false);
		$("#majorInput").attr("readonly", false);
		$("#scoreInput").attr("readonly", false);
		$("#btnModifyFinish").show(100);
	});
	$("#btnDelete").click(function() {
		$("#btnDeleteFinish").show(100);
	});
});
</script>

<%@ include file="include/bodystarter.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>학생 상세 정보</h2>
				<p>학생 정보 상세보기입니다.</p>
			</div>
			<form role="form" action="">
				<div class="form-group">

					<label for="snoInput"> 학번 </label> <input
						type="text" class="form-control" id="snoInput" readonly
						value="<%=vo.getSNO()%>"/>
				</div>
				<div class="form-group">

					<label for="snameInput"> 이름 </label> <input
						type="text" class="form-control" id="snameInput" readonly
						value="<%=vo.getSNAME()%>"/>
				</div>
				<div class="form-group">

					<label for="syearInput"> 학년 </label> <input
						type="number" class="form-control" id="syearInput" readonly
						value="<%=vo.getSYEAR()%>"/>
				</div>
				<div class="form-group">
					
					<label for="genderInput"> 남자 </label> <input
						type="radio" id="genderMan" name="gender" disabled
						<%if(vo.getGENDER().equals("남")){ %> checked="checked" <% } %>
						/>
					<label for="genderInput"> 여자 </label> <input
						type="radio" id="genderWoman" name="gender" disabled
						<%if(vo.getGENDER().equals("여")){ %> checked="checked" <% } %>
						/>
				</div>
				<div class="form-group">

					<label for="majorInput"> 전공 </label> <input
						type="text" class="form-control" id="majorInput" readonly
						value="<%=vo.getMAJOR()%>"/>
				</div>
				<div class="form-group">

					<label for="scoreInput"> 점수 </label> <input
						type="number" class="form-control" id="scoreInput" readonly
						value="<%=vo.getSCORE()%>"/>
				</div>
				<a class="btn btn-primary" href="index.jsp">전체보기</a>
				<button type="button" class="btn btn-success" id="btnModify">수정</button>
				<button type="button" class="btn btn-info" id="btnDelete">삭제</button>
				<button type="button" class="btn btn-warning" id="btnModifyFinish"style="display:none">수정완료</button>
				<button type="button" class="btn btn-danger" id="btnDeleteFinish"style="display:none">삭제완료</button>
			</form>
		</div>
	</div>
</div>

<%@ include file="include/footer.jsp"%>