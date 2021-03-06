<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// request = JSP 안에 만들어져있는 내장 객체
	request.setCharacterEncoding("utf-8");
	String st_num = request.getParameter("st_num");
	String st_name = request.getParameter("st_name");
	String st_major = request.getParameter("st_major");
	String st_year = request.getParameter("st_year");
	String st_score = request.getParameter("st_score");
	String st_etc = request.getParameter("st_etc");
	
	System.out.println("st_num =" + st_num);
	System.out.println("st_name =" + st_name);
	System.out.println("st_major =" + st_major);
	System.out.println("st_year =" + st_year);
	System.out.println("st_score =" + st_score);
	System.out.println("st_etc =" + st_etc);
%>
<%@ include file="include/header.jsp" %>
<title>학생 등록 처리 결과</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h2>등록 성공</h2>
					<p>학생 목록으로 이동하시려면 아래의 목록 버튼을 클릭해주세요.</p>
					<p>
						<a class="btn btn-primary btn-large" href="student_list.jsp">목록</a>
					</p>
				</div>
			</div>
		</div>
<%@ include file="include/footer.jsp" %>