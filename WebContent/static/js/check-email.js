document.addEventListener("DOMContentLoaded", function () {
    const email = document.getElementById("email");
    email.addEventListener("blur", function () {
        fetch(contextPath + "/common/CheckEmailServlet?email=" + encodeURIComponent(email.value))
            .then(response => response.json())
            .then(data => {
                const msg = document.getElementById("emailMsg");
                if (data.exists) {
					msg.textContent = "Email already registered.";
                } else {
                    msg.textContent = "Email does not exist yet.";
                }
            })
            .catch(error => console.error(error));
    });
});
