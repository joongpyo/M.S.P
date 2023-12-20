//ck에디터 가져오기
document.addEventListener('DOMContentLoaded', function () {
    let editorElement = document.querySelector('#editor');

    if (editorElement) {
        ClassicEditor.create(editorElement, {

            })
            .then(editor => {
            // CKEditor 인스턴스가 생성되었을 때의 콜백 함수
            console.log('에디터가 초기화');

            let btn = document.querySelector(".submit");
            let subject = document.querySelector("input[name=subject]");
            let writer = document.querySelector("input[name=writer]");
            let uIdFk = document.querySelector("input[name=uIdFk]");

            btn.addEventListener('click', (e) => {
                e.preventDefault();
                if(subject.value == ""){
                    alert("게시물 제목을 입력하세요");
                    subject.focus();
                    return false;
                }
                if(editor.getData().trim() == ""){
                    alert("내용을 입력하세요")
                    editor.focus();
                    return false;
                }


                let formData = new FormData();
                let uploadData = document.querySelector("#upload-form input[name='files']").files;

                if(uploadData != null && uploadData.length > 0){
                    for (let i = 0; i < uploadData.length; i++) {
                        formData.append("files", uploadData[i]);
                    }
                }else{
                    formData.append("files", "");
                    console.log("첨부파일 X")
                }

                formData.append('subject', subject.value);
                formData.append('content', editor.getData());
                formData.append('writer', writer.value);
                formData.append('uIdFk', uIdFk.value);

                $.ajax({
                    type: "POST",
                    url: "/board/boardWrite",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (result) {
                        if (result.msg=="success"){
                            alert("등록되었습니다.");
                            location.href = "/board/boardQnA";
                        }
                    }
                });
            });
        })
        .catch(error => {
            console.error('에디터가 초기화실패');
        });
    } else {
        console.error('에디터 매칭 실패');
    }



});
