$('.adminbtn').on('click', function () {
    $('#imageInput').click();
});

// 파일이 선택되었을 때 처리
$('#imageInput').on('change', function () {
    var input = this;
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#adminImage').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
});