<%-- 
    Document   : category
    Created on : Oct 27, 2026, 11:30:03 AM
    Author     : entel
--%>

<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@ page import="cart.ShoppingCart" %>
<%@ page import="java.util.List" %>

    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>

    <body>

        <%
        List<Category> categories = (List<Category>)request.getAttribute("categories");
        List<Product> products = (List<Product>)request.getAttribute("products");
        Category selectedCategory = (Category)request.getAttribute("selectedCategory");

        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        int numberOfItems = 0;
        if (cart != null) {
            numberOfItems = cart.getNumberOfItems();
        }
        %>

        <h3>Products of <%=selectedCategory.getName()%></h3>

        <img src="img/cart.gif" alt="cart"> <%=numberOfItems%> items
        <% if (numberOfItems > 0) { %>
            <a href="viewcart.do">View Cart</a>            
        <% } %>

        <br>

    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <tr> <font size="2" face="Verdana">

        <td width="20%" valign="center" align="middle">
            <table width="100%" border="1" cellpadding="3" cellspacing="0">
            <% for(Category category : categories){ %>
                <tr>
                    <td align="middle">
                        <a href="category.do?categoryid=<%=category.getId()%>"><%=category.getName()%></a>
                    </td>
                </tr>
            <% } %>
            </table>
        </td>

        <td width="80%">
            <table width="100%" border="1" cellpadding="5" cellspacing="0">
            <% for(Product product : products){ %>
                <tr>
                    <td width="25%" align="middle">
                        <img src="img/products/<%=product.getName()%>.png" alt="<%=product.getName()%>">
                    </td>
                    <td width="25%" align="middle">
                        <%=product.getName()%>
                        <br>
                        <%=product.getDescription()%>
                    </td>
                    <td width="25%" align="middle"><%=String.format("%.2f", product.getPrice())%> &euro;</td>
                    <td width="25%" align="middle">
                        <form action="neworder.do" method="POST" style="margin:0;">
                            <input type="hidden" name="productId" value="<%=product.getId()%>">
                            <input type="hidden" name="categoryid" value="<%=selectedCategory.getId()%>">
                            <input type="submit" value="add to cart">
                        </form>
                    </td>
                </tr>
            <% } %>
            </table>
        </td>

        </font> </tr>

    </table>

    </body>