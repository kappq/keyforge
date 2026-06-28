document.addEventListener("DOMContentLoaded", () => {
	const nome = document.getElementById("nome");
	nome.onblur = () => {
		const nomeError = document.getElementById("nome-error");
		if (nome.value.trim().length === 0) {
			nomeError.textContent = "Inserire il nome";
		} else {
			nomeError.textContent = "";
		}
	};

	const cognome = document.getElementById("cognome");
	cognome.onblur = () => {
		const cognomeError = document.getElementById("cognome-error");
		if (cognome.value.trim().length === 0) {
			cognomeError.textContent = "Inserire il cognome";
		} else {
			cognomeError.textContent = "";
		}
	};

	const email = document.getElementById("email");
	email.onblur = () => {
		const emailError = document.getElementById("email-error");
		const rules = [{ regex: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, errorMessage: "Email invalida" }];
		for (const rule of rules) {
			if (!rule.regex.test(email.value)) {
				emailError.textContent = rule.errorMessage;
				return;
			}
		}
		const params = new URLSearchParams();
		params.set("email", email.value);
        fetch(contextPath + "/common/CheckEmailServlet?" + params.toString())
            .then(res => res.json())
            .then(data => {
                if (data.exists) {
					emailError.textContent = "Email non disponibile";
				} else {
					emailError.textContent = "";
				}
            })
            .catch(error => {
				console.error(error);
				emailError.textContent = "";
			});
	};

	const dataNascita = document.getElementById("data-nascita");
	dataNascita.onblur = () => {
		const dataNascitaError = document.getElementById("data-nascita-error");
		if (dataNascita.value.trim().length === 0) {
			dataNascitaError.textContent = "Inserire la data di nascita";
		} else {
			dataNascitaError.textContent = "";
		}
	};

	const telefono = document.getElementById("telefono");
	telefono.onblur = () => {
		const telefonoError = document.getElementById("telefono-error");
		const rules = [{ regex: /^([0-9]{10})$/, message: "Il numero di telefono deve essere una stringa di 10 cifre" }];
		for (const rule of rules) {
			if (!rule.regex.test(telefono.value)) {
				telefonoError.textContent = rule.message;
				return;
			}
		}
		telefonoError.textContent = "";
	};
	
	const password = document.getElementById("password");
	password.onblur = () => {
		const passwordError = document.getElementById("password-error");
		const rules = [
			{ regex: /.{8,}/, message: "La password deve contenere almeno 8 caratteri" },
			{ regex: /[a-z]/, message: "La password deve contenere almeno una lettera minuscola" },
			{ regex: /[A-Z]/, message: "La password deve contenere almeno una lettera maiuscola" },
			{ regex: /\d/, message: "La password deve contenere almeno un numero" },
			{ regex: /[^A-Za-z0-9]/, message: "La password deve contenere almeno un carattere speciale" },
		];
		for (const rule of rules) {
			if (!rule.regex.test(password.value)) {
				passwordError.textContent = rule.message;
				return;
			}
		}
		passwordError.textContent = "";
	};

	const confermaPassword = document.getElementById("conferma-password");
	confermaPassword.onblur = () => {
		const confermaPasswordError = document.getElementById("conferma-password-error");
		if (confermaPassword.value != password.value) {
			confermaPasswordError.textContent = "Le password devono corrispondere";
		} else {
			confermaPasswordError.textContent = "";
		}
	};
});
