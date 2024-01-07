
let btn = document.querySelector(".quit-btn");
let uId = document.querySelector(".uId");

    btn.addEventListener('click',function(e){
        e.preventDefault();

        let pwdCheck = document.querySelector("input[name=pwdCheck]");
        let userPasswd = document.querySelector("input[name=userPasswd]");


        if(pwdCheck.value === userPasswd.value){

          if(confirm("회원 탈퇴를 진행하시겠습니까?\n다시 복구 할 수 없습니다.")){

                let obj = {
                    uId: uId.value,
                    userPasswd: userPasswd.value
                }

                 $.ajax({
                        type: "POST",
                        url: "/mypage/myQuit",
                        data: obj,
                        success: function(result) {
                           if(result.msg == "success"){
                                alert("회원 탙퇴가 완료되었습니다.");
                                location.href = "/logout";
                           }
                        }
                    });

            }

        }else{
            alert("비밀번호가 일치하지 않습니다.");
            pwdCheck.focus();
            return false;
        }
         return false;
    });

