

//ck에디터 가져오기
document.addEventListener('DOMContentLoaded', function () {
    let editorElement = document.querySelector('#editor');

     if (editorElement) {
            ClassicEditor.create(editorElement, {
                    removePlugins: ['Heading', 'SimpleUploadAdapter'],
                    language: "ko"
                })
                .then(editor => {
                // CKEditor 인스턴스가 생성되었을 때의 콜백 함수
                console.log('에디터가 초기화', editor);

                let btn = document.querySelector(".submit");
                let subject = document.querySelector("input[name=subject]");
                let qnaWriter = document.querySelector("input[name=qnaWriter]");
                let uIdFk = document.querySelector("input[name=uIdFk]");

                let qnaContent = document.querySelector(".qnaContent").value;
                editor.setData(qnaContent);

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

                    formData.append('qnaSubject', subject.value);
                    formData.append('qnaContent', editor.getData());
                    formData.append('qnaWriter', qnaWriter.value);
                    formData.append('uIdFk', uIdFk.value);


                    $.ajax({
                        type: "POST",
                        url: "/board/qnaUpdate",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (result) {
                            if (result.msg=="success"){
                                alert("");
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
