function showLoader(){
    $.LoadingOverlay("show");
    setTimeout(function (){
        $.LoadingOverlay("hide");
    }, 3000);
}

function formUpload(){
    document.getElementsByName("uploadForm").submit();
    $.LoadingOverlay("show")
}


function onClickUploadMain(){
    let myInput = document.getElementById("uploadInput");
    myInput.click();
}