<%
    session = request.getSession(false);
    if (session != null) session.invalidate();
    response.sendRedirect("login.jsp");
%>