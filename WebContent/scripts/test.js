function validateFields() {
    var userPassword = document.getElementById("passwordsignup").value;
    var confirmPassword = document.getElementById("passwordsignup_confirm").value;
    var transactionPassword = document.getElementById("transactionpassword").value;
    
    if(userPassword != confirmPassword) {
        alert("Passwords do not match");
        return false;
    }
    
   if(userPassword == transactionPassword) {
       alert("Transaction password should be different from user password");
       return false;
   }
    return true;
}

function alertLogin(){
	alert("Successfully logged in !");
	return true;
	} 

function alertLoginF(){
	alert("login failed!");
	return true;
	} 