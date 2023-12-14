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
                let formData = new FormData();
                let uploadData = document.querySelector("#upload-form")[0].files;
                let subject = document.querySelector("input[name=subject]");

                if(uploadData != null){
                    for (let i = 0; i < uploadData.length; i++) {
                        formData.append("files", uploadData);
                    }
                }

                btn.addEventListener('click', (e) => {
                    e.preventDefault();

                    // 이벤트 핸들러 내에서 CKEditor의 데이터를 textarea의 value로 설정
                    editorElement.value = editor.getData();

                    formData.append('qSubject', subject.value);
                    formData.append('qContent', editorElement.value);

                    $.ajax({
                        type: "post",
                        url: "/board/boardWrite",
                        dataType: "json",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (result) {

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

//게시물 ajax방식

/////////////////////////////////////////////////////////////////////////////////
/*
let uploadData = document.querySelectorAll("#upload-form input[type='file']");
let formData = new FormData();
let subject = document.querySelector("input[name=subject]");

uploadData.forEach(fileInput => {
    if (fileInput.files && fileInput.files.length > 0) {
        Array.from(fileInput.files).forEach(file => {
            formData.append("files", file);
        });
    }
});

btn.addEventListener('click', (e) => {
    e.preventDefault();

    // 이벤트 핸들러 내에서 CKEditor의 데이터를 textarea의 value로 설정
    editorElement.value = editor.getData();

    formData.append('qSubject', subject.value);
    formData.append('qContent', editorElement.value);

    $.ajax({
        type: "post",
        url: "/board/boardWrite",
        dataType: "json",
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {
            // 성공했을 때의 동작 추가
        }
    });
});
*/
