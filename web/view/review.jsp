<%@ page import="entity.Product" %>
<%@ page import="entity.Review" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
    </head>

    <body>

        <%
        Product product = (Product)request.getAttribute("product");
        List<Review> reviews = (List<Review>)request.getAttribute("reviews");
        double average = (Double)request.getAttribute("average");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        %>

        <h3>Reviews of <%=product.getName()%></h3>

        <a href="category.do?categoryid=<%=product.getCategory().getId()%>">Back to <%=product.getCategory().getName()%></a>

        <br><br>

    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <tr> <font size="2" face="Verdana">

        <td width="25%" align="middle">
            <img src="img/products/<%=product.getName()%>.png" alt="<%=product.getName()%>">
        </td>
        <td width="25%" align="middle">
            <b><%=product.getName()%></b>
            <br>
            <%=product.getDescription()%>
        </td>
        <td width="25%" align="middle"><%=String.format("%.2f", product.getPrice())%> &euro;</td>
        <td width="25%" align="middle">
            <% if (average > 0) { %>
                <%=String.format("%.1f", average)%>/5 &#9733;
                <br>
                (<%=reviews.size()%> reviews)
            <% } else { %>
                no reviews yet
            <% } %>
        </td>

        </font> </tr>

    </table>

        <h3>What customers say</h3>

    <% if (reviews.isEmpty()) { %>
        Be the first to review this product.
    <% } else { %>
    <table width="50%" border="1" cellpadding="5" cellspacing="0">
        <% for(Review review : reviews){
               String comment = review.getComment();
               if (comment == null) {
                   comment = "";
               } %>
            <tr>
                <td width="20%" align="middle">
                    <%=review.getRating()%>/5 &#9733;
                </td>
                <td width="60%"><%=comment%></td>
                <td width="20%" align="middle"><%=dateFormat.format(review.getDateCreated())%></td>
            </tr>
        <% } %>
    </table>
    <% } %>

        <h3>Write a review</h3>

        <form name="reviewForm" action="addreview.do" method="POST">
            <input type="hidden" name="productId" value="<%=product.getId()%>">
            <% for(int i = 1; i <= 5; i++){ %>
                <input type="radio" name="rating" value="<%=i%>" <%=(i == 5) ? "checked" : ""%>> <%=i%>
            <% } %>
            Stars
            <br>
            Comment:
            <br>
            <textarea name="comment" rows="4" cols="50" maxlength="255"></textarea>
            <br>
            <a href="javascript:document.reviewForm.submit()">submit review</a>
        </form>

    </body>