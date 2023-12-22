

//ck에디터 가져오기
document.addEventListener('DOMContentLoaded', function () {
    let editorElement = document.querySelector('#editor');

     if (editorElement) {
            ClassicEditor.create(editorElement, {

                })
                .then(editor => {
                // CKEditor 인스턴스가 생성되었을 때의 콜백 함수
                console.log('에디터가 초기화', editor);

                let btn = document.querySelector(".submit");
                let subject = document.querySelector("input[name=subject]");

                //(수정)무조건 관리자
                let writer = document.querySelector("input[name=writer]");
                let id = document.querySelector("input[name=id]");

                let content = "------------ [ 원본 글 ] ------------ </br>"+ document.querySelector("#content").value+"<br/>------------ [ 답변 글 ] ------------</br></br>" ;


                console.log(content);


                editor.setData(content);

                btn.addEventListener('click', (e) => {
                    e.preventDefault();
                    let formData = new FormData();
                    let uploadData = document.querySelector("#upload-form input[name='files']").files;

                    if(uploadData != null && uploadData.length > 0){
                        for (let i = 0; i < uploadData.length; i++) {
                            formData.append("files", uploadData[i]);
                            console.log('i =', i + 1);
                        }
                    }else{
                        formData.append("files", "");
                        console.log("첨부파일 X")
                    }

                    formData.append('subject', subject.value);
                    formData.append('content', editor.getData());
                    formData.append('writer', writer.value);
                    formData.append('boardId', id.value);
                    //(수정-관리자 u_id)
                    formData.append('uIdFk', 500);

                    $.ajax({
                        type: "POST",
                        url: "/board/boardReply",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (result) {
                            if (result.msg=="success"){
                                location.href = "/board/boardQnA";
                            }
                        }
                    });
                });
            })
            .catch(error => {
                console.error('에디터가 초기화실패', error);
            });
    } else {
        console.error('에디터요소 매칭 실패');
    }
});
