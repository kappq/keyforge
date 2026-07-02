<%
String successMessage = (String) session.getAttribute("successMessage");
String errorMessage = (String) session.getAttribute("errorMessage");

if (successMessage != null) {
    session.removeAttribute("successMessage");
}
if (errorMessage != null) {
    session.removeAttribute("errorMessage");
}
%>
<% if (successMessage != null) { %>
    <div id="successPopup" class="popup-success">
        <p><%= successMessage %></p>
		 <% session.removeAttribute("successMessage"); %>
    </div>
	 <script>setTimeout(() => document.getElementById('successPopup').remove(), 2000)</script>
    
<% } %>

<% if (errorMessage != null) { %>
    <div id="errorPopup" class="popup-error">
        <p><%= errorMessage %></p>
		 <% session.removeAttribute("errorMessage"); %>
    </div>
	 <script>setTimeout(() => document.getElementById('errorPopup').remove(), 2000)</script>
    
<% } %>