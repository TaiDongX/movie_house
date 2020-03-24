$(function () {

    $("#inputFile").change(function () {
        var url = getObjectURL(this.files[0]);
        $("#fileImg").attr('src', url).show();
        $("#choose")
            .css('top',"180px");
    });
});

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

function checkSubmit() {
    var formData = new FormData();
    formData.append('file', $('#inputFile').get(0).files[0]);
    $.ajax({
        async: false,
        type: 'POST',
        url: "/user/changeHeader",
        data: formData,
        contentType: false,//ajax上传图片需要添加
        processData: false,//ajax上传图片需要添加
        success: function (data) {
            alert(data);
            window.location.reload();
        }
    })
}

