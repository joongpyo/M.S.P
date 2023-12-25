 function loginCheck(action,boardId,e){
        e.preventDefault();
        let configCode = document.querySelector("input[name=configCode]").value;

        $.ajax({
            type:'GET',
            url :'/checkLogin',
            success: function(result){
                if(!result.isLogin) {
                    alert("로그인이 필요합니다");
                }else{
                    if (action == 'view') {
                        location.href = "/board/boardView?configCode=" + configCode + "&id=" + boardId;
                    } else if (action == 'write') {
                        location.href = "/board/boardWrite?configCode=" + configCode;
                    }
                }
            }
        });
        return false;
}