$('#loginLoginBtn').on('click',function () {
    $('#loginForm').attr('method','post');
    $('#loginForm').attr('action','/login/loginProcess');
    $('#loginForm').submit();
});

$('#loginCancelBtn').on('click',function () {
    if (confirm('취소하시겠습니까?')){
        location.href = "redirect:/list?cp=1";
    }
});