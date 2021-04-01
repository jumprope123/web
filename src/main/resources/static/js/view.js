$('#goBackBtnFromView').on('click',function () {
    location.href = "redirect:/list?cp=1";
})

function modiFromView(boardno) {
    var password = prompt("수정하시려면 비밀번호를 입력하세요");

    $.ajax({
        url: '/modicheck',
        type: 'GET',
        data: {boardno: boardno, password: password}
    })
        .done(function (data) {
            if (data =='true') {//비번이 일치한다면
                location.href = "/modiData?boardno="+boardno;
            } else { //비번이 일치하지 않는다면
                location.href = "/list?cp=1";
                alert("비밀번호가 틀립니다.");
            }
        })

}

function modiMemberFromView(boardno) {
    location.href = "/modiData?boardno="+boardno;
}

function delFromView(boardno) {
    var password = prompt("삭제하시려면 비밀번호를 입력하세요");

    $.ajax({
        url: '/modicheck',
        type: 'GET',
        data: {boardno: boardno, password: password}
    })
        .done(function (data) {
            if (data =='true') {//비번이 일치한다면
                location.href = "/delData?boardno="+boardno;
            } else { //비번이 일치하지 않는다면
                location.href = "/list?cp=1";
                alert("비밀번호가 틀립니다.");
            }
        })
}

function delMemberFromView(boardno) {
    location.href = "/delData?boardno="+boardno;
}