<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty UID}">
    <script>
        alert('로그인이 필요한 서비스입니다.');
        location.href = "/list?cp=1";
    </script>
</c:if>

<c:import url="../header.jsp"/>
<div id="main">
    <div class="margin1050">
        <form id="ModiMemberForm">
            <input type="hidden" name="data" value="${data}">
            <div class="row mt-3">
                <div class="col-8 offset-2 mt-2">
                    <label for="userIDForModi">아이디</label>
                    <input type="text" class="form-control" id="userIDForModi" name="userid" value="${data.userid}" readonly>
                </div>
                <div class="col-8 offset-2 mt-2">
                    <label for="NickNameForModi">닉네임</label>
                    <input type="text" class="form-control" id="NickNameForModi" name="nickname" value="${data.nickname}" readonly>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-12 mt-5 text-center">
                    <button type="button" id="nickChangeBtn" class="btn btn-success">닉네임 변경하기</button>
                    <button type="button" id="passwordChangeBtn" class="btn btn-danger">비밀번호 변경하기</button>
                </div>
            </div>
        </form>
    </div>
</div>
<c:import url="../footer.jsp"/>
