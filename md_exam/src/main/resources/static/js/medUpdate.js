let btn = document.querySelector(".btn");
    btn.addEventListener('click',(e)=>{
        e.preventDefault();
        let chkData= [];
        let chkBox = document.querySelectorAll("input[name=medDis]");
        for(let i = 0; i < chkBox.length; i++){
            if(chkBox[i].checked){
                chkData.push(chkBox[i].value);
            }
        }
        if(chkData.length == 0){
            alert("유요한 질병을 체크해주세요");
            chkBox.focus();
            return false;
        }
        if(chkData.length > 3 ){
            alert("유요한 질병을 3개 이하로 체크해주세요");
            chkBox.focus();
            return false;
        } 
        console.log(chkData);
        
        let chkMedPrd = document.querySelectorAll("input[type=radio][name=medPregnant]");
        let radMedPrd = "";
        for(let i = 0; i<chkMedPrd.length; i++){
            if(chkMedPrd[i].checked){
                radMedPrd=chkMedPrd[i].value;
            }
        }
        if(radMedPrd.value == 0){
            alert("임산부 복용가능 여부를 확인해주세요");
            chkMedPrd.focus();
            return false;   
        }
        console.log(radMedPrd);
        

        let chkMedStore = document.querySelectorAll("input[type=radio][name=medStore]");
        let radMedStore = "";
        for (let i = 0; i < chkMedStore.length; i++) {
            if (chkMedStore[i].checked) {
                radMedStore=chkMedStore[i].value;
            }
        }
        if(radMedStore.value ==0){
            alert("편의점 판매 여부를 확인해주세요");
            chkMedStore.focus();
            return false;
        }

        let frmData = new FormData();
        frmData.append("medName", document.querySelector("input[name=medName]").value);
        frmData.append("medDis",chkData);
        frmData.append("medEff",document.querySelector("input[name=medEff]").value);
        frmData.append("medType",document.querySelector("input[name=medType]").value);
        frmData.append("medCom",document.querySelector("input[name=medCom]").value);
        frmData.append("medAge",document.querySelector("input[name=medAge]").value);
        frmData.append("medPregnant",radMedPrd);
        frmData.append("medStore",radMedStore);
        frmData.append("file", document.querySelectorAll("#uploadFile")[0].files[0]);


        $.ajax({
            type: "post",
            url: "/admin/medUpdate",
            dataType: "json",
            data: frmData,
            processData : false,
            contentType : false,
            success: function(res) {
                if(res.msg == "success"){
                    alert("약품 등록이 완료되었습니다.");
                }else{
                    alert("등록 실패 \n 관리자에게 문의하세요");
                };
            }
        });
    })