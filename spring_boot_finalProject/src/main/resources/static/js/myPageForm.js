$(document).ready(function() {
    var isEditable = false;
    var isSubEditable = false;
    var OriginNick = $('#memNick').val(); 
    var nickChk = false;
    var emailChk = false;
    var text = "";
    var imgName = $('#memImg').attr("data-img");
    
    $(document).on('keydown', function (e) {
        if (e.keyCode == 13) return false; // Disable form submission on Enter key
    });
    
    // Click event for image box to open file selector
    $('#myPageImageBox').on('click', function() {
        $('#fileInput').click();
    });

   // 파일 선택 시 이미지 미리보기
    $('#fileInput').on('change', function(e) {
	    const input = this;        
	    
	    if (input.files && input.files[0]) {	        
	        
	        // 파일을 서버에 전송하기 위한 FormData 객체 생성
	        let formData = new FormData();
	        formData.append('uploadFile', input.files[0]); // 'uploadFile'은 서버의 @RequestParam과 매칭되는 이름
	        
	        // 서버에 먼저 요청을 보내고, 성공하면 이미지 업데이트
	        $.ajax({
	            type: "post",
	            url: "/member/imageFileUpload",
	            enctype: "multipart/form-data",
	            processData: false,
	            contentType: false,
	            data: formData,
	            success: function(result) {
	                if(result === 'fail'){
	                    // 실패 시 이미지 업데이트 하지 않음
	                    alert('선정적 이미지 입니다.');
	                    input.value = ''; // 파일 입력 필드 초기화
	                    return;
	                } else {
	                    const reader = new FileReader();
				        
				        let fileName = input.files[0].name;
				     
				        reader.onload = function(e) {
				            $('#memImg').attr('src', e.target.result);
				            $('#myImage').attr('src', e.target.result);
				            $('#myImageUser').attr('src', e.target.result);
				        }
				
				        reader.readAsDataURL(input.files[0]);
	                }
	            },
	            error: function() {
	                alert('서버 오류 발생');
	                input.value = ''; // 서버 오류 시 파일 입력 필드 초기화
	            }
	         });
	    }
	});

    // Menu hover effect
    $(".myPageMenuEach, #memDeleteAccount").hover(
        function() {
            $(this).css({
                "background-color": "#f8f9f9",
                "color": "gray"
            });
        },
        function() {
            $(this).css({
                "background-color": "white",
                "color": "#424949"
            });
        }
    );

    $('#memProfile').click(function() {
       window.location.href = '/member/myPageForm'; // 이동할 URL을 지정
    });
    
    var newWindow = null;
    $('#memberManage').click(function() {
        var text = $('#memberManage').text().trim(); // div 내부의 텍스트를 가져옴
        
        if (text === '등록정보') {
            window.location.href = '/member/myPageChildInfo'; // 등록정보가 있을 때 URL로 이동
        } else if (text === '회원관리') {
            // 화면의 너비와 높이를 계산하여 창의 중앙 좌표 설정
            var width = 800; // 새 창의 너비
            var height = 573; // 새 창의 높이
            var left = (window.screen.width / 2) - (width / 2); // 중앙으로 위치 계산 (가로)
            var top = (window.screen.height / 2) - (height / 2); // 중앙으로 위치 계산 (세로)

			if(newWindow && !newWindow.closed){
				newWindow.close();
			}
			console.log(1);
            newWindow = window.open('/member/memberManageForm', '_blank', `width=${width},height=${height},left=${left},top=${top}`);
        }
    });
    
    $('#memAwards').click(function() {
       window.location.href = '/member/memAwardForm'; // 이동할 URL을 지정/member/memberManageForm
    });
    
    $('#memDeleteAccount').on('click',function(){
       if(confirm("회원탈퇴 하시겠습니까?")){
               $.ajax({
                  type:"post",
                  url:"/member/memDeleteAccount",
                  success:function(){
                     location.href = "/";
                  }
               });
            }
    });

    // Edit button click event
    $('#editBtn').click(function(event) {
        isEditable = true;
        // Enable editing for input fields (excluding certain fields)
        $('input[type=text]').not('#myPageDate, #memId, #memZipcode, #memAddress1')
            .prop('readonly', false).css({
            'color': '#a8a8a8',
            'border-bottom': '1px solid #e8e8e8',
            'cursor': 'auto'
        });

        // Show save button and hide edit button
        $('#saveBtn').show();
        $('#editBtn').hide();
        $('.inBtn').show();
    });

    // Nickname duplicate check
    $('#memNick').on('keyup', function() {
        let newNick = $('#memNick').val();      
        $.ajax({
            url: "/member/myPageNickChk",
            data: {"nick": newNick, "OriginNick": OriginNick},
            success: function(result) {
                if (newNick.length <= 0) $('#memNick_chk').empty();
                if (result == "true") {
                    nickChk = false;
                    $('#memNick').focus();
                    if (newNick.length > 0) {
                        $('#memNick_chk').empty().text('사용 불가능합니다').css('color', 'red');
                    }
                } else if (result == "equal") {
                    $('#memNick_chk').empty().text('동일한 닉네임입니다').css('color', '#03C75A');
                } else {
                    nickChk = true;
                    $('#memNick_chk').empty().text('사용 가능합니다').css('color', '#03C75A');
                }
            }
        });
    });

    // Phone number formatting
    const formatPhoneNumber = (input) => {
        const cleanInput = input.replaceAll(/[^0-9]/g, "");
        let result = "";
        const length = cleanInput.length;
        if (length === 8) {
            result = cleanInput.replace(/(\d{4})(\d{4})/, '$1-$2');
        } else if (cleanInput.startsWith("02") && (length === 9 || length === 10)) {
            result = cleanInput.replace(/(\d{2})(\d{3,4})(\d{4})/, '$1-$2-$3');
        } else if (!cleanInput.startsWith("02") && (length === 10 || length === 11)) {
            result = cleanInput.replace(/(\d{3})(\d{3,4})(\d{4})/, '$1-$2-$3');
        } else {
            result = undefined;
        }
        return result;
    }

    // Format phone number on keyup and blur
    $('#memHp').on('keyup', function(e) {
        if (e.keyCode == 13) $('#memHp').val(formatPhoneNumber($('#memHp').val()));
    });
    $('#memHp').on('blur', function() {
        $('#memHp').val(formatPhoneNumber($('#memHp').val()));
    });

    // Email validation
    $('#memEmail').on('keyup', function(e) {
        let email = $('#memEmail').val();
        let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
        if (emailPattern.test(email)) {
            emailChk = true;
            $('#memEmail_chk').empty();
        } else {
            emailChk = false;
            $('#memEmail_chk').empty().text('올바른 이메일이 아닙니다').css('color', 'red');   
        }
    });

    // Address search button click event
    $('#searchZipBtn').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                let address1 = "";
                if (data.userSelectedType == 'R') {
                    address1 = data.roadAddress + "(" + data.bname + data.buildingName + ")";
                } else {
                    address1 = data.jibunAddress;
                }
                $('#memZipcode').val(data.zonecode);
                $('#memAddress1').val(address1);
                let address2 = $('#memAddress2');
                address2.val("");
                address2.focus();
            }
        }).open();
    });

    // Save button click event
    $('#saveBtn').on("submit", function() {
       isEditable = false;
        if (nickChk && emailChk) {
            $('#editBtn').show();
            $('#saveBtn').hide();
            $('.inBtn').hide();
        } else {
            if (!nickChk) {
                alert('닉네임을 다시 확인해주세요');
                $('#memNick').focus();
            } else if (!emailChk) {
                alert('이메일을 다시 확인해주세요');
                $('#memEmail').focus();
            }
            return false;
        }
    });

    // Input field focus and blur events
    $(".myPageMyContents input[type=text]").focus(function() {
        $(this).css("outline", "none");
        if (isEditable && !$(this).is('#myPageDate, #memId')) {
            $(this).css("border-bottom", "1px solid #1b2631");
        }
    });

    $(".myPageMyContents input[type=text]").blur(function() {
        if (isEditable && !$(this).is('#myPageDate, #memId')) {
            $(this).css("border-bottom", "1px solid #e8e8e8");
        }
    });

    // Button hover effects
    $('.inBtn, .modifyBtn').hover(
        function() {
            $(this).css({
                "background-color": "#333333",
                "color": "whitesmoke"
            });
        },
        function() {
            $(this).css({
                "background-color": "black",
                "color": "white"
            });
        }
    );

   
});

