<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>

    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>
    
    <body>

        <h2>Welcome to the online home of our virtual grocery</h2>

        <h3> Our unique home delivery service brings you fresh organic produce,
        dairy, meats, breads and other delicious and healthy items direct
        to your doorstep. </h3>


    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <tr> <font size="2" face="Verdana">

        <%
        List<Category> categories = (List<Category>)request.getAttribute("categories");

        for(Category category : categories){

        %>

        <td width="14%" valign="center" align="middle">
            <a href="category.do?categoryid=<%=category.getId()%>">
                <img src="img/categories/<%=category.getName()%>.jpg"
                     alt="<%=category.getName()%>" >
                <%=category.getName()%>
            </a>
        </td>
 
       <% } %>

        </font> </tr>

    </table>
       <%
    List<Product> products = (List<Product>)request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
    %>
        <br>
        <h3>Available Products:</h3>
        <table width="60%" border="1" cellpadding="5" cellspacing="0">
            <% for(Product product : products) { %>
            <tr>
                <td width="20%"><b><%=product.getName()%></b></td>
                <td width="15%">$<%=product.getPrice()%></td>
                <td width="50%"><%=product.getDescription()%></td>
                <td width="15%" align="center">
                    <form action="neworder.do" method="POST" style="margin:0;">
                        <input type="hidden" name="productId" value="<%=product.getId()%>">
                        <input type="hidden" name="categoryid" value="<%=request.getParameter("categoryid")%>">
                        <input type="submit" value="Add to Cart">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    <% } %>

    </body>
    
    <div style="text-align: right; margin-bottom: 20px;">
    <a href="viewcart.do" style="font-size: 18px; font-weight: bold; background-color: #eee; padding: 5px 10px; border: 1px solid #ccc; text-decoration: none;">View Cart</a>
    </div> 
    