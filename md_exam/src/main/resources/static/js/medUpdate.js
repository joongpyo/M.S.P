function frmCheck(){
    let medName = document.querySelector("input[name=medName]");
    if(medName.value == ""){
        alert("약품명을 입력하세요");
        medName.focus();
        return false;
    }
}