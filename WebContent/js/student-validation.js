function validateForm() {
  let emptyField = [];

  const studentForm = document.forms["studentForm"];

  const firstName = studentForm["firstName"].value.trim();

  const lastName = studentForm["lastName"].value.trim();

  const email = studentForm["email"].value.trim();

  if (firstName === "") {
    emptyField.push("First name");
  }
  if (lastName === "") {
    emptyField.push("Last name");
  }
  if (email === "") {
    emptyField.push("Email");
  }

  if (emptyField.length > 0) {
    alert(
      "Form validation failed. Please add data for following fields: " +
        emptyField
    );
    return false;
  }
}
