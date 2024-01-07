 let btn = document.querySelector(".update-btn");
 let uId = document.querySelector(".uId");

    btn.addEventListener('click', function(e){
        e.preventDefault();

        let pwdCheck = document.querySelector("input[name=pwdCheck]");
        let userPasswd = document.querySelector("input[name=userPasswd]");
        let newPasswd = document.querySelector("input[name=newPasswd]");
        let newPasswdChk = document.querySelector("input[name=newPasswdChk]");

        if(pwdCheck.value != userPasswd.value){
            alert("현재 비밀번호를 다시 입력하세요.");
            pwdCheck.focus();
            return false;
        }else if(newPasswdChk.value != newPasswd.value){
            alert("변경할 비밀번호를 다시 입력하세요.");
            newPasswdChk.focus();
            return false;
        }else{
            if(confirm("회원 정보를 수정하시겠습니까?")){
                let obj = {
                    uId: uId.value,
                    userPasswd: newPasswd.value
                }

                $.ajax({
                        type: "POST",
                        url: "/mypage/update",
                        data: obj,
                        success: function(result) {
                           if(result.msg == "success"){
                                alert("회원 정보 수정이 완료되었습니다.");
                                location.href = "/index";
                           }
                        }
                    });


            }
        }

        return false;
    });