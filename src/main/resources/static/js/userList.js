function deleteCheck(uId,userId){
    if(confirm(userId + "을 삭제 하시겠습니까?" )){
        location.href="/admin/userDelete?uId="+uId+"&userId="+userId;
    }
}