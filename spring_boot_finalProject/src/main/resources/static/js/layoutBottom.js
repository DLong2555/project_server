$(document).ready(function() {
    // 처음에는 X 이미지를 숨김
    $("#xImage").hide();
    $("#bottomChatBotChatBox").hide();
    
    // 챗봇 버튼 또는 닫기 버튼을 눌렀을 때 동작
    $("#bottomChatBotBtn, #btnClose").click(function() {
        toggleChatBox();
    });

    // 문서의 아무 곳이나 클릭했을 때 이벤트
    $(document).click(function(event) {
        // 클릭한 대상이 #bottomChatBotChatBox나 #bottomChatBotBtn, 그 자식이 아니면
        if (!$(event.target).closest('#bottomChatBotChatBox, #bottomChatBotBtn').length) {
            // 챗봇 창이 열려 있으면 닫기
            if ($("#bottomChatBotChatBox").is(":visible")) {
                $("#bottomChatBotChatBox").fadeOut(300);  // 챗봇 창 닫기
                $("#xImage").hide();  // X 이미지 숨기기
                $("#ChatBotImage").show();  // 챗봇 이미지 보이기
            }
        }
    });

    // 채팅 박스를 열고 닫는 함수
    function toggleChatBox() {
        if ($("#bottomChatBotChatBox").is(":visible")) {
            // 챗봇 창이 열려있으면 닫고 챗봇 이미지로 변경
            $("#bottomChatBotChatBox").fadeOut(300);
            $("#xImage").hide();
            $("#ChatBotImage").show();
        } else {
            // 챗봇 창이 닫혀있으면 열고 X 이미지로 변경
            $("#bottomChatBotChatBox").fadeIn(300);
            $("#ChatBotImage").hide();
            $("#xImage").show();
        }
    }
});
