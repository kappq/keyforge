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
	
	const password = document.getElementById("password");
	const passwordError = document.getElementById("password-error");
	password.onblur = () => validatePassword(password, passwordError);

	const confermaPassword = document.getElementById("conferma-password");
	const confermaPasswordError = document.getElementById("conferma-password-error");
	confermaPassword.onblur = () => validateConfermaPassword(confermaPassword, confermaPasswordError, password);
	
	const form = document.getElementsByTagName("form")[0];
	form.onsubmit = async (event) => {
		event.preventDefault();
		let isValid = true;
		isValid = isValid && validateNome(nome, nomeError);
		isValid = isValid && validateCognome(cognome, cognomeError);
		isValid = isValid && await validateEmail(email, emailError);
		isValid = isValid && validateDataNascita(dataNascita, dataNascitaError);
		isValid = isValid && validateTelefono(telefono, telefonoError);
		isValid = isValid && validatePassword(password, passwordError);
		isValid = isValid && validateConfermaPassword(confermaPassword, confermaPasswordError, password);
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
	const date = new Date();
	const todayISO = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay(); 
	if (dataNascita.value.trim().length === 0) {
		dataNascitaError.textContent = "Inserire la data di nascita";
		return false;
	} else if(dataNascita.value.trim() <= "1920-01-01") {
		dataNascitaError.textContent = "Inserire una data superiore al 1920";
		return false;
	}else if(dataNascita.value.trim() >= todayISO)  {
		dataNascitaError.textContent = "Inserire una data inferiore a quella odierna";
		return false;
	}else {
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

function validatePassword(password, passwordError) {
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
			return false;
		}
	}
	passwordError.textContent = "";
	return true;
}

function validateConfermaPassword(confermaPassword, confermaPasswordError, password) {
	if (confermaPassword.value != password.value) {
		confermaPasswordError.textContent = "Le password devono corrispondere";
		return false;
	} else {
		confermaPasswordError.textContent = "";
		return true;
	}
}