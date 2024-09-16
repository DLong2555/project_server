/**
 * chatbot.js
 */ 
 
$(function() { 

   // 시작하자마다 웰컴 메시지 요청 
   callChatbot();
   
    $('#chatForm').on('submit', function() {
       event.preventDefault();
       //let formData = new FormData($('#chatForm')[0]); // 이렇게 해도 되고   
       
       // (1) chatBox에 보낸 메시지 추가
       $('#chatBox').append('<div class="message user"><div class="bubble">' +
                                  $('#message').val() + '</div></div><br>');   
              
      callChatbot();
          
   });   // chatForm 끝   
   
   function callChatbot() {
      $.ajax({
                type:"post",
                url:"/ai/chatbot",                
               data:{"message": $('#message').val()}, 
                success:function(result){
                   //$('#chatBox').text(result); 
                   // (2) 수신한 메시지를 chatBox에 추가 
                   $('#chatBox').append('<div class="message bot"><div class="bubble">' +
                                  result + '</div></div><br>');
                   
                   // (3) 스크롤 올리기
                   $('#chatBox').scrollTop($('#chatBox').prop("scrollHeight")); 
                   
                   // 입력값 지우고 포커스
                   $('#message').val("").focus();
               },
                error:function(){
                   alert("실패");
                }
       }); // ajax 끝
      }    
});