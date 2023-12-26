function frmCheck(){
    const userid = document.querySelector("input[name=userId]");
    const passwd = document.querySelector("input[name=userPasswd]");
    const username = document.querySelector("input[name=userName]");
    const re_passwd = document.querySelector("input[name=re_passwd]");
      const useremail = document.querySelector("input[name=userEmail]");
    /*--- js 아이디/비밀번호/이름 체크 정규식 */
    const expIdText = /^[a-z]+[a-z0-9]{4,20}$/g;;
    const expNameText= /^[가-힣]+$/;
    const expPwText = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    const expEmailText = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    if(userid.value==""){
        alert("아이디를 입력하세요")
        userid.focus();
        return false;
    }
    if(!expIdText.test(userid.value)){
        alert("아이디는 5자이상 20자이하의 대소문자로 입력하세요")
        userid.focus();
        return false;
    }
    if(passwd.value==""){
        alert("비밀번호를 입력하세요")
        passwd.focus();
        return false;
    }
    if(!expPwText.test(passwd.value)){
        alert("비밀번호 형식을 확인해주세요 \n 소문자,대문자,특수문자 1개씩은 포함하세요")
        passwd.focus();
        return false;
    }
    if(passwd.value != re_passwd.value){
        alert("동일한 비밀번호를 입력하세요");
        re_passwd.focus();
        return false;
    }
    if(username.value ==""){
        alert("이름을 입력하세요");
        username.focus();
        return false;
    }
    if(!expNameText.test(username.value)){
        alert("이름은 한글로 입력하세요");
        username.focus();
        return false;
    }
    if(useremail.value==""){
            alert("이메일을 입력하세요")
            useremail.focus();
            return false;
    }
    if(!expEmailText.test(useremail.value)){
            alert("이메일 형식을 맞춰주세요")
            useremail.focus();
            return false;
        }
    else{
        alert("회원가입이 완료되었습니다.");
    }
    

     
}
