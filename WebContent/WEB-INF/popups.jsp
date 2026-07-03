<style>
/* Popup Notifications */
.popup-success,
.popup-error {
	position: fixed;
	top: 24px;
	right: 24px;
	z-index: 1000;
	display: flex;
	align-items: center;
	gap: 10px;
	padding: 14px 20px;
	border-radius: 8px;
	border: 1px solid;
	border-left: 4px solid;
	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
	animation: popup-in 0.25s ease-out, popup-out 0.25s ease-in 1.75s;
	max-width: 360px;
}

.popup-success {
	background: rgb(223, 237, 223); 
	border-color: #82C09A;
	border-left-color: #82C09A;
}

.popup-error {
	background: rgb(238, 221, 221);
	border-color: #C96E73;
	border-left-color: #C96E73;
}

.popup-success p,
.popup-error p {
	margin: 0;
	font-size: 14px;
	color: var(--text);
	line-height: 1.4;
}

@keyframes popup-in {
	from {
		opacity: 0;
		transform: translateX(20px);
	}
	to {
		opacity: 1;
		transform: translateX(0);
	}
}

@keyframes popup-out {
	from {
		opacity: 1;
		transform: translateX(0);
	}
	to {
		opacity: 0;
		transform: translateX(20px);
	}
}
</style>

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