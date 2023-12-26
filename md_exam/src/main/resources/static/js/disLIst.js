function deleteDis(disId,disName){
    if(confirm(disName + "을 삭제 하시겠습니까?" )){
        location.href="/admin/disDelete?disId="+disId+"&disName="+disName;
    }
}