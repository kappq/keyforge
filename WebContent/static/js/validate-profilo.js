document.addEventListener("DOMContentLoaded", () => {
	const nome = document.getElementById("nome");
	const nomeError = document.getElementById("nome-error");
	nome.onblur = () => validateNome(nome, nomeError);

	const cognome = document.getElementById("cognome");
	const cognomeError = document.getElementById("cognome-error");
	cognome.onblur = () => validateCognome(cognome, cognomeError);

	const email = document.getElementById("email");
	const emailError = document.getElementById("email-error");
	email.onblur = () => validateEmail(email, emailError);

	const dataNascita = document.getElementById("data-nascita");
	const dataNascitaError = document.getElementById("data-nascita-error");
	dataNascita.onblur = () => validateDataNascita(dataNascita, dataNascitaError);

	const telefono = document.getElementById("telefono");
	const telefonoError = document.getElementById("telefono-error");
	telefono.onblur = () => validateTelefono(telefono, telefonoError);
	
	const form = document.getElementsByTagName("form")[0];
	form.onsubmit = async (event) => {
		event.preventDefault();
		let isValid = true;
		isValid = isValid && validateNome(nome, nomeError);
		isValid = isValid && validateCognome(cognome, cognomeError);
		isValid = isValid && await validateEmail(email, emailError);
		isValid = isValid && validateDataNascita(dataNascita, dataNascitaError);
		isValid = isValid && validateTelefono(telefono, telefonoError);
		if (isValid) {
			form.submit();
		}
	};
});

function validateNome(nome, nomeError) {
	if (nome.value.trim().length === 0) {
		nomeError.textContent = "Inserire il nome";
		return false;
	} else {
		nomeError.textContent = "";
		return true;
	}
}

function validateCognome(cognome, cognomeError) {
	if (cognome.value.trim().length === 0) {
		cognomeError.textContent = "Inserire il cognome";
		return false;
	} else {
		cognomeError.textContent = "";
		return true;
	}
}

async function validateEmail(email, emailError) {
	const rules = [{ regex: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, errorMessage: "Email invalida" }];
	for (const rule of rules) {
		if (!rule.regex.test(email.value)) {
			emailError.textContent = rule.errorMessage;
			return false;
		}
	}
	if (email.value === userEmail) {
		emailError.textContent = "";
		return true;
	}
	const params = new URLSearchParams();
	params.set("email", email.value);
	try {
		const response = await fetch(contextPath + "/common/CheckEmailServlet?" + params.toString());
		if (!response.ok) {
			throw new Error("Response status: " + response.status);
		}
		const data = await response.json();
		if (data.exists) {
			emailError.textContent = "Email non disponibile";
			return false;
		}
		emailError.textContent = "";
		return true;
	} catch (error) {
		console.log(error.message);
		emailError.textContent = "";
		return true;
	}
}

function validateDataNascita(dataNascita, dataNascitaError) {
	if (dataNascita.value.trim().length === 0) {
		dataNascitaError.textContent = "Inserire la data di nascita";
		return false;
	} else {
		dataNascitaError.textContent = "";
		return true;
	}
}

function validateTelefono(telefono, telefonoError) {
	const rules = [{ regex: /^([0-9]{10})$/, message: "Il numero di telefono deve essere una stringa di 10 cifre" }];
	for (const rule of rules) {
		if (!rule.regex.test(telefono.value)) {
			telefonoError.textContent = rule.message;
			return false;
		}
	}
	telefonoError.textContent = "";
	return true;
}
