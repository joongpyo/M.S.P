
document.addEventListener('DOMContentLoaded', function () {
    let editorElement = document.querySelector('#editor');

    if (editorElement) {
        ClassicEditor
            .create(editorElement,{
                removePlugins: [ 'Heading','SimpleUploadAdapter' ],
                language: "ko"
            })
            .then(editor => {
                console.log('Editor was initialized', editor);
            })
            .catch(error => {
                console.error('Error initializing CKEditor', error);
            });
    } else {
        console.error('Editor element not found');
    }
});


//
//ClassicEditor.create(document.querySelector('#editor'),{
//            removePlugins: [ 'Heading' ],
//            language: "ko"
//        });
