function changeFn(){
    let board = document.querySelector("#board");
    var value = (board.options[board.selectedIndex].value)
        console.log(value);
    }
    document.addEventListener('DOMContentLoaded', function (e) {
    e.preventDefault();

    let editorElement = document.querySelector('#editor');
    if (editorElement) {
        ClassicEditor.create(editorElement, {

            })
            .then(editor => {
            // CKEditor 인스턴스가 생성되었을 때의 콜백 함수
            console.log('에디터가 초기화');
        
    let btn = document.querySelector(".btn1");
    let subject = document.querySelector("input[name=subject]");
    let writer =  document.querySelector("input[name=writer]");

    btn.addEventListener('click',(e)=>{
        e.preventDefault();
        let board = document.querySelector("#board");
        var value = (board.options[board.selectedIndex].value)
           
        if(value.value == "0" ){
            alert("등록할 게시판을 선택하세요");
            return false;
        }
        if(subject.value == ""){
            alert("게시글 제목을 입력하세요");
            subject.focus();
            return false;
        }
        if(writer.value == ""){
            alert("작성자를 입력하세요");
            writer.focus();
            return false;
        }
        if(editor.getData().trim() == ""){
            alert("내용을 입력하세요");
            editor.focus();
            return false;
        }
        

        let frmData = new FormData();       
        
        frmData.append("configCode",value);
        frmData.append("subject",subject.value);
        frmData.append("writer",writer.value);
        frmData.append("content",editor.getData());
       // frmData.append("file",document.querySelectorAll("#uploadFile")[0].files[0]);
       
       let uploadFileInput = document.querySelectorAll("#uploadFile")[0];
        if (uploadFileInput && uploadFileInput.files.length > 0) {
            frmData.append("file", uploadFileInput.files[0]);
        } else {
            frmData.append("file", new Blob(), "empty-file.txt");
        }

        $.ajax({
            type:"post",
            url:"/admin/noticeInsert",
            dataType:"json",
            data:frmData,
            processData : false,
            contentType : false,
            success:function(res){
            if(res.msg == "choice1" ){
                alert("review 게시판에는 첨부파일을 사용할수 없습니다")
                document.location.reload();
            }if(res.msg == "success"){
                alert("게시글 등록 완료!")
                location.href="/admin/noticeMain";
            }else{
                  alert("등록 실패 \n 서버관리자에게 문의하세요")
                  document.location.reload();
                }
            }
        })
    })        
        })
        .catch(error => {
            console.error('에디터가 초기화실패');
        });
    } else {
        console.error('에디터 매칭 실패');
    }
})






