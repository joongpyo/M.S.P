
    let userid = document.querySelector("input[name=userId]");
    let passwd = document.querySelector("input[name=userPasswd]");
    let username = document.querySelector("input[name=userName]");
    let re_passwd = document.querySelector("input[name=re_passwd]");
    let useremail = document.querySelector("input[name=userEmail]");
    let btn = document.querySelector("#btn");
    btn.addEventListener('click', (e)=>{
        e.preventDefault();
//    /*--- js 아이디/비밀번호/이름 체크 정규식 */
//    const expIdText = /^[a-z]+[a-z0-9]{4,20}$/g;
//    const expNameText = /^[가-힣]+$/;
//    const expPwText = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
//    const expEmailText = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//
//    if (userid.value == "") {
//        alert("아이디를 입력하세요");
//        userid.focus();
//        return false;
//    }
//
//    if (!expIdText.test(userid.value)) {
//        alert("아이디는 5자이상 20자이하의 소문자로 시작하는 영문자와 숫자의 조합이어야 합니다.");
//        userid.focus();
//        return false;
//    }
//
//    if (passwd.value == "") {
//        alert("비밀번호를 입력하세요");
//        passwd.focus();
//        return false;
//    }
//
//    if (!expPwText.test(passwd.value)) {
//        alert("비밀번호는 소문자, 대문자, 특수문자 각 1개 이상을 포함하여 8자 이상이어야 합니다.");
//        passwd.focus();
//        return false;
//    }
//
//    if (passwd.value != re_passwd.value) {
//        alert("동일한 비밀번호를 입력하세요");
//        re_passwd.focus();
//        return false;
//    }
//
//    if (username.value == "") {
//        alert("이름을 입력하세요");
//        username.focus();
//        return false;
//    }
//
//    if (!expNameText.test(username.value)) {
//        alert("이름은 한글로 입력하세요");
//        username.focus();
//        return false;
//    }
//
//    if (useremail.value == "") {
//        alert("이메일을 입력하세요");
//        useremail.focus();
//        return false;
//    }
//
//    if (!expEmailText.test(useremail.value)) {
//        alert("이메일 형식을 맞춰주세요");
//        useremail.focus();
//        return false;
//    }

    // 서버에 중복 확인 요청
    $.ajax({
        type: "get",
        url: "/user/checkUserIdAndEmail",
        dataType: "json",
        data: {
            userId: userid.value,
            userEmail: useremail.value
        },
        success: function (res) {
            if (res.checkUserId == 1) {
                alert("중복된 아이디입니다. 확인 후 다시 입력하세요");
                userid.value = "";
                userid.focus();
            }if (res.checkUserEmail == 1) {
                alert("중복된 이메일입니다. 확인 후 다시 입력하세요");
                useremail.value = "";
                useremail.focus();
            }
        }
    });

    obj = {
        userId : userid.value,
        userPasswd : passwd.value,
        userName : username.value,
        userEmail : useremail.value
    }

    $.ajax({
        type:"post",
        url:"/user/register",
        dataType:"json",
        data: obj,
        success:function(res){
            if(res.msg == "success" ){
                alert("가입성공");
                location.href = "/user/login";
            }
        }
    })
});
