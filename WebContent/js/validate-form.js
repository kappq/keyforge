document.addEventListener("DOMContentLoaded", function() {
	var form = document.getElementById("lForm");
	form.addEventListener("submit", function(event) {
		document.getElementById("errorList").innerHTML = "";
		event.preventDefault();
		var valid = true;
		var name = document.getElementsByName("nome")[0];
		if(checkNameSurname(name).length) {
			valid = false;
			name.classList.add("error");
		}else {
			name.classList.remove("error");
		}
		var surname = document.getElementsByName("cognome")[0];
		if(checkNameSurname(surname).length) {
			valid = false;
			surname.classList.add("error");
		}else {
			surname.classList.remove("error");
		}
		var email = document.getElementsByName("email")[0];
		if(checkEmail(email).length) {
			valid = false;
			email.classList.add("error");
		}
		else {
			email.classList.remove("error");
		}
		var date = document.getElementsByName("dataNascita")[0];
		if(checkDate(date).length) {
			valid = false;
			date.classList.add("error");
		}else {
			date.classList.remove("error");
		}
		var phone = document.getElementsByName("telefono")[0];
		if(checkPhone(phone).length) {
			valid = false;
			phone.classList.add("error");
		}else {
			phone.classList.remove("error");
		}
		var password = document.getElementsByName("password")[0];
		if(checkPassword(password).length) {
			valid = false;
			password.classList.add("error");
		}else {
			password.classList.remove("error");
		}
		if (!valid) {
			return;
		}
		form.submit();
	});
	
});
/*validazione input*/
function checkNameSurname(inpt) {
	const rules = [{regex: /^.+$/, message: "No name/surname inserted."}];
	let errors = [];
	for(const rule of rules) {
		if(!rule.regex.test(inpt.value)) {
			errors.push(rule.message);
		}
	}
	addErrors("Name/surname", errors);	
	return errors;
}
function checkEmail(inpt) {
    const rules = [{regex: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, message:"Invalid format."}];
	let errors = [];
	for(const rule of rules) {
		if(!rule.regex.test(inpt.value)) {
			errors.push(rule.message);
		}
	}
	addErrors("Email", errors);	
	return errors;
}
function checkPhone(inpt) {
	const rules = [{regex: /^([0-9]{10})$/, message: "10 digits."}];
	let errors = [];
	for(const rule of rules) {
		if(!rule.regex.test(inpt.value)) {
			errors.push(rule.message);
		}
	}
	addErrors("Phone", errors);	
	return errors;
}
function checkPassword(inpt) {
	const rules = [
	  { regex: /.{8,}/, message: "At least 8 characters." },
	  { regex: /[a-z]/, message: "One lowercase letter." },
	  { regex: /[A-Z]/, message: "One uppercase letter." },
	  { regex: /\d/, message: "One number." },
	  { regex: /[^A-Za-z0-9]/, message: "One special character." }
	];
	let errors = [];
	for(const rule of rules) {
		if(!rule.regex.test(inpt.value)) {
			errors.push(rule.message);
		}
	}
	addErrors("Password", errors);
	return errors;
}
function checkDate(inpt) {
    const errors = [];

    const d = new Date(inpt.value);
    const start = new Date("1920-01-01");
    const today = new Date();

    if (d < start || d > today) {
        errors.push("Date must be between 1920 and today.");
    }

    addErrors("Date", errors);
    return errors;
}
/*aggiunta errori alla UI*/
function addErrors(fieldName, errors) {
  	let errorList = document.getElementById("errorList");
	errors.forEach(msg => {
	    const li = document.createElement("li");
	    li.textContent = `${fieldName}: ${msg}`;
	    errorList.appendChild(li);
  	});
}