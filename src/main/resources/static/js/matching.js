let btn = document.querySelector(".btn").addEventListener('click',(e)=>{
        e.preventDefault();

    var pregnant = document.getElementsByName("pregnant");
    var pregnantValue = "";
    var drugtype = document.getElementsByName("drugtype");
    var drugtypeValue = "";

    //셀렉트박스 값
    var disease = document.getElementById("disease").value;
    var age = document.getElementById("age").value;

    //라디오 버튼 값
       for(var i=0; i < pregnant.length; i++) {
        if(pregnant[i].checked == true) {
            pregnantValue = pregnant[i].value
      }
    }
       for(var i=0; i < drugtype.length; i++) {
        if(drugtype[i].checked == true) {
            drugtypeValue = drugtype[i].value
      }
    }

    /*유효성 검사*/

    if(pregnantValue == ""){
        alert("임신 여부를 선택하세요.");
        pregnantValue.focus();
        return false;
    }

    if(age == ""){
        alert("연령대를 선택하세요.");
        age.focus();
        return false;
    }

    if(disease == ""){
        alert("병명을 선택하세요.")
        disease.focus();
        return false;
    }

    if(drugtypeValue == ""){
        alert("약의 제형을 선택하세요.");
        drugtypeValue.focus();
        return false;
    }

    /*/유효성 검사*/

<!--       alert("임신 여부: " + pregnantValue + "\n연령대: " + age + "\n병명: " + disease + "\n약의 제형: " + drugtypeValue);-->

         let obj = {
                    medPregnant: pregnantValue,
                    medAge: age,
                    medDis: disease,
                    medType: drugtypeValue
                }
        $.ajax({
                type: "post",
                url: "/matching/matching",
                dataType: "json",
                data: obj,
                success: function(res) {
                    if(res.msg == "success"){
                        location.href = "/matching/match_result?";
                    }
                }
         });



});