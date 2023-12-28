let btn = document.querySelector("#dis-btn");
let disName = document.querySelector("input[name=disName]");
let disSym1 = document.querySelector("#disSym1");
let disSym2 = document.querySelector("#disSym2");
let disSym3 = document.querySelector("#disSym3");
let disId = document.querySelector("input[name=disId]");

btn.addEventListener('click', (e)=>{
    e. preventDefault();
    
    if(disName.value == 0 ){
        alert("병명을 입력하세요");
        disName.focus();
        return false;
    }
    if(disSym1.value == 0 ){
        alert("증상1을 입력하세요");
        disSym1.focus();
        return false;
    }
    if(disSym2.value == 0 ){
        alert("증상2을 입력하세요");
        disSym2.focus();
        return false;
    }
    if(disSym3.value == 0 ){
        alert("증상3을 입력하세요");
        disSym3.focus();
        return false;
    }

    let obj = {
        disId : disId.value,
        disName : disName.value,
        disSym : disSym1.value + "/"+disSym2.value+"/"+disSym3.value
    };
    

    console.log(obj);
    $.ajax({
        type:"get",
        url:"/admin/checkId",
        dataType:"json",
        data:{disId : disId.value},
        success:function(res){
            
        }
    })

    $.ajax({
        type:"post",
        url : "/admin/disUpdate",
        dataType: "json",
        data: obj,
        success: function(res){
            if(res.msg == "success"){
                alert("등록 성공");
                location.href = "/admin/disList";
            }else{
                alert("등록 실패! \n 서버관리자에게 문의하세요");
                document.location.reload();               
            }
        }

    })
})