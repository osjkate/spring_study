<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 그냥 사용 가능 -> service 로직 그대로 실행
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
    //이 표시 없으면 System.out.println()으로 다 찍어버림
    // servlet은 자바코드 중심 -> HTML을 동적으로 출력
    // JSP는 HTML 중심 -> 중간에 자바코드를 동적으로
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body> 성공
<ul>
       <li>id=<%=member.getId()%></li>
       <li>username=<%=member.getUsername()%></li>
       <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>