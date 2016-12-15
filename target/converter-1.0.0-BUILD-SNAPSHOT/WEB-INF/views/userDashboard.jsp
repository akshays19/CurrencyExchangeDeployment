<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ page import= "org.json.JSONObject" %>
<%@ page import= "java.util.*" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>Dashboard</title>
	<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
  
<body>

<h4 class="text-center" style="float:left;">Base Currency: ${baseCurrency}</h4>

<div id="currentRate">
<%
     	JSONObject exchangeRate=(JSONObject)request.getAttribute("exchangeRate");
     //String[] nameList=(String[])request.getAttribute("name");
	
        out.print("<table style='margin-left: 200px; margin-top: 20px; width: 250px; font-size=15px; padding: 5px; background: white;' class='wrap' border='1'>");
        out.print("<caption style='font-size:20px;'>Current Exchange Rate</caption>");
        out.print("<tr>");
        out.print("<td style='padding: 5px;'>Currency</td>");
        out.print("<td style='padding: 5px;'>Rate</td>");
        out.print("</tr>");
        
        Iterator<?> keys = exchangeRate.keys();

        while( keys.hasNext() ) {
        	
        	String key = (String)keys.next();
        	
        	out.print("<tr>");
            out.print("<td style='padding: 5px;'>"+key.substring(3)+"</td>");
            out.print("<td style='padding: 5px;'>"+exchangeRate.get(key)+"</td>");
            out.print("</tr>");	
        }
    %>
    </div>
    
   <div id="historicalRate">
	<%
	     	List<String> historicalRate =(List<String>)request.getAttribute("historicalRates");
	     //String[] nameList=(String[])request.getAttribute("name");
			if(historicalRate!=null){
		        out.print("<table style='margin-left: 200px; margin-top: 20px; width: 450px; font-size=15px; padding: 5px; background: white;' class='wrap' border='1'>");
		        out.print("<caption style='font-size:20px;'>Historical Exchange Rate</caption>");
		        
		        Iterator<String> histKeys = historicalRate.iterator();
		
		        while( histKeys.hasNext() ) {
		        	
		        	JSONObject histData = new JSONObject(histKeys.next());
		        	keys = histData.keys();

		        	out.print("<table style='margin-left: 200px; float: left; margin-top: 20px; width: 250px; font-size=15px; padding: 5px; background: white;' class='wrap' border='1'>");
		        	out.print("<tr>");
		        	out.print("<td>"+histKeys+"</td>");
		        	out.print("</tr>");
	                out.print("<tr>");
	                out.print("<td style='padding: 5px;'>Currency</td>");
	                out.print("<td style='padding: 5px;'>Rate</td>");
	                out.print("</tr>");
		            while( keys.hasNext() ) {
		            	
		            	String key = (String)keys.next();
		            	
		            	out.print("<tr>");
		                out.print("<td style='padding: 5px;'>"+key.substring(3)+"</td>");
		                out.print("<td style='padding: 5px;'>"+histData.get(key)+"</td>");
		                out.print("</tr>");	
		            }
	
		        }
			}
	    %>
   </div>
   
   <form id="logoutForm" method="POST" action="${contextPath}/logout">
   </form>
   <h2 style="position:absolute;top:0;right:0"><a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
