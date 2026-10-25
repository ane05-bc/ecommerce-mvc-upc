<%@ page import="cart.ShoppingCart" %>
<%@ page import="cart.ShoppingCartItem" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h2>Your Shopping Cart</h2>
    <%
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null && cart.getNumberOfItems() > 0) {
    %>
        <table border="1" cellpadding="5" cellspacing="0" width="60%">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Update</th>
            </tr>
            <% for (ShoppingCartItem item : cart.getItems()) { %>
            <tr>
                <td align="center"><%= item.getProduct().getName() %></td>
                <td align="center">$<%= item.getProduct().getPrice() %></td>
                
                <form action="updatecart.do" method="POST">
                    <td align="center">
                        <input type="text" name="quantity" value="<%= item.getQuantity() %>" size="2">
                        <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                    </td>
                    <td align="center">$<%= item.getTotal() %></td>
                    <td align="center"><input type="submit" value="Update"></td>
                </form>
            </tr>
            <% } %>
        </table>
      <%-- <h3>Subtotal: $<%= cart.getSubtotal() %></h3> --%>

        <form action="clearcart.do" method="POST" style="display:inline;">
            <input type="submit" value="Clear Cart">
        </form>

        <form action="https://www.paypal.com/cgi-bin/webscr" method="post" style="display:inline; margin-left: 20px;">
            <input type="hidden" name="cmd" value="_cart">
            <input type="hidden" name="upload" value="1">
            <input type="hidden" name="business" value="seller@business.example.com">
            <%
                int count = 1;
                for (ShoppingCartItem item : cart.getItems()) {
            %>
                <input type="hidden" name="item_name_<%=count%>" value="<%= item.getProduct().getName() %>">
                <input type="hidden" name="amount_<%=count%>" value="<%= item.getProduct().getPrice() %>">
                <input type="hidden" name="quantity_<%=count%>" value="<%= item.getQuantity() %>">
            <%
                count++;
                }
            %>
            <input type="submit" value="Proceed to Checkout" style="background-color: #ffc439; font-weight: bold; cursor: pointer;">
        </form>

    <% } else { %>
        <p>Your cart is empty.</p>
    <% } %>
    <br><br>
    <a href="init.do">Continue Shopping</a>
</body>
</html>