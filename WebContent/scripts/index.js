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


function validateDates() {
    var startDate = new Date(document.getElementById("startDate").value);
    var endDate = new Date(document.getElementById("endDate").value);
    var todayDate = new Date();
                        
    var tDay = todayDate.getDate();
    if(tDay < 10) {
        tDay = "0" + tDay;
    }
    var tMonth = todayDate.getMonth() + 1;
    if(tMonth < 10) {
        tMonth = "0" + tMonth;
    }
    var tYear = todayDate.getYear() + 1900;
                
    var tDate = tYear + "-" + tMonth + "-" + tDay;
                
    document.getElementById("startDate").setAttribute("max",tDate);
    document.getElementById("endDate").setAttribute("max",tDate);
                        
}
        
function validateDateForm() {
                
    var startDate = new Date(document.getElementById("startDate").value);
    var endDate = new Date(document.getElementById("endDate").value);
                
    alert(startDate);
    alert(endDate);
                
    var sDay = startDate.getDate();
    var sMonth = startDate.getMonth() + 1;
    var sYear = startDate.getYear() + 1900;
                
                
    var eDay = endDate.getDate();
    var eMonth = endDate.getMonth() + 1;
    var eYear = endDate.getYear() + 1900;
                
                
    if(eDay < sDay && eMonth <= sMonth && eYear <= sYear) {
        alert("Starting date should be lesser than ending date");
        return false;
    }      
    return false;
}