/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.92
 * Generated at: 2019-03-12 03:16:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>添加学生</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form method=\"post\" action=\"AddStudentServlect\">\r\n");
      out.write("\t<table border=\"1\" width=\"600\">\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>姓名</td>\r\n");
      out.write("<td><input type=\"text\" name=\"sname\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>性别</td>\r\n");
      out.write("<td>\r\n");
      out.write("男<input type=\"radio\" name=\"gender\" value=\"男\">\r\n");
      out.write("女<input type=\"radio\" name=\"gender\" value=\"女\">\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>电话</td>\r\n");
      out.write("<td><input type=\"text\" name=\"phone\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>生日</td>\r\n");
      out.write("<td><input type=\"text\" name=\"birthday\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>爱好</td>\r\n");
      out.write("<td>\r\n");
      out.write("<input type=\"checkbox\" value=\"游泳\" name=\"hobby\">游泳\r\n");
      out.write("<input type=\"checkbox\" value=\"篮球\" name=\"hobby\">篮球\r\n");
      out.write("<input type=\"checkbox\" value=\"足球\" name=\"hobby\">足球\r\n");
      out.write("<input type=\"checkbox\" value=\"看书\" name=\"hobby\">看书\r\n");
      out.write("<input type=\"checkbox\" value=\"写字\" name=\"hobby\">写字\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>简介</td>\r\n");
      out.write("<td><textarea name=\"info\" rows=\"3\" cols=\"20\"></textarea></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td colspan=\"2\"><input type=\"submit\" value=\"添加\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\t\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
