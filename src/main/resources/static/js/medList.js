function deleteMed(medId,medName){
            if(confirm(medName + "을 삭제 하시겠습니까?" )){
                location.href="/admin/medDelete?medId="+medId+"&medName="+medName;

            }
}
