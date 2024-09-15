$(document).ready(function () {
   var pageCtg = $('#memberManageChooseTitle').text();

    // MapViewGymEach hover effect
    $(document).on('mouseenter', '.memberManageNameEach', function () {
        $(this).css({
            "background-color": "#f2f2f2"
            
        });
    });

    $(document).on('mouseleave', '.memberManageNameEach', function () {
        $(this).css({
            "background-color": "white"
            
        });
    });

    // 메뉴 항목의 스타일 초기화 및 변경
    $(document).on('click', '.memberManageNameEach', function () {
        $(".memberManageNameEach").css({
            "font-weight": "normal",
            "font-size": "16px",
            "text-decoration": "none"
        });

        $(this).css({
            "font-weight": "700",
            "font-size": "16px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
    });
    
    let rotationAngle = 0;  // 초기 회전 각도
    let isGuardianInfo = false; // 상태 변수 추가

    $('#memberManageChangeImage').click(function() {
        rotationAngle += -180;
        $('#memberManageChangeImage').css({
            'transform': 'rotate(' + rotationAngle + 'deg)',
        });
        
        // 상태에 따라 텍스트와 내용 전환
        if (isGuardianInfo) {
            // 원래 상태로 복원
            $('#memberManageChooseTitle').text('회원 정보');
            $('#memberManageMemberContentsBox1').show(); // 원래대로 보여주기
            $('#memberManageMemberContentsBox2').hide(); // 숨기기
        } else {
            // 보호자 정보 상태로 변경
            $('#memberManageChooseTitle').text('보호자 정보');
            $('#memberManageMemberContentsBox1').hide(); // 숨기기
            $('#memberManageMemberContentsBox2').css('display', 'flex'); // 보여주기
        }

        // 상태 반전
        isGuardianInfo = !isGuardianInfo;
    });
    
    $('.memberManageNameEach').first().click();
    
    //납기일 남은 날짜에 따라 css 변화
    textColorShowDday();
    function textColorShowDday(){
       $('.memberManageNameEach').each(function(){
          let chk = $(this).find('span').attr("data-day");
          
          if(chk === "soon") $(this).css("color", "orange"); 
          else if(chk === "ended") $(this).css("color", "red");
       });
    }
    
    if(pageCtg === '회원 정보' || pageCtg === '납부관리'){
       //로드시 첫번째 정보 띄우기
       loadFirstChildInfo();
       
       function loadFirstChildInfo(){
          let firstChild = $('.memberManageNameEach span').first();
          let childNo = firstChild.attr("data-value");
          
          if (childNo) {
              // 첫 번째 요소로 AJAX 요청 실행
              executeAjax(childNo);
          }
       }
       
       // 클릭 이벤트 설정
       $(document).on('click', '.memberManageNameEach',function() {
           let childNo = $(this).find('span').attr("data-value");
           executeAjax(childNo);
       });
    }
    
    //이름 클릭시 아이 정보 출력
    var getChildNo = "";
    function executeAjax(childNo) {
       $.ajax({
          type:"post",
          url:"/member/getMemDateByChildNo",
          data:{"childNo":childNo},
          success:function(result){
             $('#memberManageChildNameText').text(result.childName);
             $('#memberManageAgeText').text(result.childAge);
             $('#memberManageGenderText').text(result.childSex);
             $('#memberManageChildNumberText').text(result.childHp);
             $('#memberManageBeltText').val(result.childBelt);
             $('#memberManageDateText').text(result.deadLine);
             $('#memberManageMemNameText').text(result.memName);
             $('#memberManageNumberText').text(result.memHp);
             $('#memberManageAddressText').text(result.memAddress1 + " " + result.memAddress2);
             
             getChildNo = result.childNo;
          }
       });
    }
    
    //납부관리
    if(pageCtg === '납부관리'){
    	let eventNo = $('.memberManageSelectPlaceholder').attr("data-value");   	    	
        
        getPayChildInfo(eventNo);
        
    	$(document).on('click', '.memberManageNameEach',function() {
            let childNo = $(this).find('span').attr("data-value");
            executeAjax(childNo);
        });
    }
    
    //납부관리 아이 정보 함수
    function getPayChildInfo(eventNo) {      
       let nameClass = $('.memberManageNameEach');
       console.log(eventNo);
       
       $('.memberManageNameEach').each(function(){
           $(this).css("color", "red");            
       });
       
       $.ajax({
          type:"post",
          url:"/member/getChildNoJoinEvent",
          data:{"eventNo":eventNo},
          success:function(result){
          	 $.each(result,function(index, childNo){          
	             nameClass.each(function(){
	             	let no = $(this).find('span').attr("data-value");	
	             	if(childNo == no) $(this).css("color", "#21b605");          	
	             });                 
             });         
          }
       });
    }
    
    
    //회원 검색
   $('#memberManageNameSearch').on('keyup', function(e){
         let word = $(this).val();
         
         if(e.keyCode >= 0 && word.length > 0){
            $.ajax({
               type:"post",
               url:"/member/childNameSearch",
               data:{"word":word},
               success:function(result){
                  $('#memberManageNameBox1').hide();
                  $('#memberManageNameBox2').css('display', 'flex');
                  
                  $('#memberManageGymSearchBox').empty();
                  
                  $.each(result, function(index, child){
                     $('#memberManageGymSearchBox').append(`
                        <div class="memberManageNameEach">
                              <span data-value="${child.childNo }" data-day="${child.leftChk }">${child.childName }</span>
                           </div>
                        `)
                  });

               
                  textColorShowDday();
               }
            });
         }else{
            $('#memberManageNameBox1').show();
            $('#memberManageNameBox2').hide();
         }
   });
   
    
   
   //수정 기능
   $(document).on('click', '#memberManageModifySuccessBtn', function(){
         let belt = $('#memberManageBeltText').val();
         
         $.ajax({
            type:"post",
            url:"/member/childBeltUpdate",
            data:{"childNo":getChildNo, "childBelt":belt}
         });
   });
   
   
    // URL의 쿼리 파라미터에서 'ctg' 값 가져오기
    function getParameterByName(name) {
        var url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    var ctg = getParameterByName('ctg'); // 'ctg' 파라미터 값 가져오기
    if (!ctg) {
        window.location.href = window.location.pathname + "?ctg=회원정보";
    }

    // 'ctg' 값에 따라 해당하는 메뉴에 스타일 적용
    if (ctg === '회원정보') {
        $('a[href="?ctg=회원정보"]').parent().css({
            "font-weight": "600",
            "font-size": "21px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
    } else if (ctg === '수상내역') {
        $('a[href="?ctg=수상내역"]').parent().css({
            "font-weight": "600",
            "font-size": "21px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
    } else if (ctg === '납부관리') {
        $('a[href="?ctg=납부관리"]').parent().css({
            "font-weight": "600",
            "font-size": "21px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
        $('.memberManageSelectPlaceholder').css("display", "flex");
    }
   
      $('#memberManageModifyBtn').click(function(event) {
           $('#memberManageMemberInfo #memberManageBeltText')
            .prop('readonly', false).css({
            'color': '#a8a8a8',
            'border-bottom': '1px solid #e8e8e8',
            'cursor': 'auto'
        });

        $('#memberManageModifySuccessBtn').css('display', 'flex');
        $('#memberManageModifyBtn').hide();
    });
    
       $('#memberManageModifySuccessBtn').click(function(event) {
           $('#memberManageMemberInfo #memberManageBeltText')
            .prop('readonly', true).css({
            'color': 'black',
            'border-bottom': '0',
            'cursor': 'auto'
        });

        $('#memberManageModifyBtn').css('display', 'flex');
        $('#memberManageModifySuccessBtn').hide();
    });
    
    var totalPage = 0;
    if(pageCtg ==='수상 내역'){
       //로드시 첫번째 정보 띄우기
       loadFirstChildAwardInfo();
       
       function loadFirstChildAwardInfo(){
          let firstChild = $('.memberManageNameEach span').first();
          getChildNo = firstChild.attr("data-value");
          
          if (getChildNo) {
              // 첫 번째 요소로 AJAX 요청 실행
              loadAwardContents(getChildNo, 0);
              displayArrow(0);
          }
       }
       
       // 클릭 이벤트 설정
       $(document).on('click', '.memberManageNameEach',function() {
           let childNo = $(this).find('span').attr("data-value");
           btnFlag = false;
           addCompetitionSuccessBtn();
           
           if(getChildNo !== childNo){
              getChildNo = childNo;
              loadAwardContents(getChildNo, 0);
              
              $('#memberManageRightArrow').attr("data-num",0);
              $('#memberManageLeftArrow').attr("data-num",0);
              //$('#memberManageLeftArrow').css("visibility", "hidden");
              //$('#memberManageRightArrow').css("visibility", "visible");
              displayArrow(0);
           }
       });
    
    }
    
    function displayArrow(page){
       if(totalPage == 0){
          $('#memberManageRightArrow').css("visibility", "hidden");
            $('#memberManageLeftArrow').css("visibility", "hidden");
       }else{
          if(page == 0){
             $('#memberManageRightArrow').css("visibility", "visible");
               $('#memberManageLeftArrow').css("visibility", "hidden");
          }else if(page == totalPage){
             $('#memberManageRightArrow').css("visibility", "hidden");
               $('#memberManageLeftArrow').css("visibility", "visible");
          }else{
             $('#memberManageRightArrow').css("visibility", "visible");
               $('#memberManageLeftArrow').css("visibility", "visible");
          }
       }
    }
    
    var awardNo = 0;
    function loadAwardContents(no, page){
       let pageNum = parseInt(page) + 1;
       
       $.ajax({
            type:"post",
            url:"/member/getAwardContents",
            data:{"childNo":no, "page":pageNum},
            success:function(result){
               if(result){
                  $('#memberManageCompetitionText').val(result.awardTitle);
                  $('#memberManageCompetitionDateText').val(result.dateFmt);
                  $('#memberManageCompetitionTrophyText').val(result.ranking);
                  
                  awardNo = result.awardNo;
                  totalPage = result.totalPage - 1;
                  $('#memberManageCompetitionContentsBox').css('display', 'flex');
                  $('#memberManageCompetitionNullBox').css('display', 'none');
                  $('#memberManageArrowBox').css("display", "flex");
                  $('#deleteCompetitionBtn').css("display", "flex");
                  
                  displayArrow(page);
               }else{
                  $('#memberManageCompetitionContentsBox').css('display', 'none');
                  $('#memberManageCompetitionNullBox').css('display', 'flex');
                  $('#memberManageArrowBox').css("display", "none");
                  $('#deleteCompetitionBtn').css("display", "none");
               }
            }
         });
    }
   
   $('#memberManageRightArrow').on('click',function(){
         let page = parseInt($(this).attr("data-num")) + 1;
         
         if(page >= totalPage){           
            page = totalPage;
         }
         
        loadAwardContents(getChildNo, page);
         
        $(this).attr("data-num", page);
        $('#memberManageLeftArrow').attr("data-num", page);
   });
   
   $('#memberManageLeftArrow').on('click',function(){
         let page = parseInt($(this).attr("data-num")) - 1;
         
         if(page <= 0){          
            page = 0;
         }
         
        loadAwardContents(getChildNo, page);
         
        $(this).attr("data-num", page);
        $('#memberManageRightArrow').attr("data-num", page);
   });
   
    
    var btnFlag = false;
    var savePage = 0;
    function addCompetition(){
         $('#memberManageCompetitionInfo input[type=text]').val('');
         //화살표 숨김
         $('#memberManageRightArrow').css("visibility", "hidden");
          $('#memberManageLeftArrow').css("visibility", "hidden");
         savePage = $('#memberManageRightArrow').attr("data-num");
         
         $('#memberManageCompetitionInfo input[type=text]')
               .prop('readonly', false).css({
               'color': '#a8a8a8',
               'border-bottom': '1px solid #e8e8e8',
               'cursor': 'auto'
         });
   
   
         $('#memberManageCompetitionContentsBox').css('display', 'flex');
         $('#memberManageCompetitionNullBox').css('display', 'none');
         $('#addCompetitionSuccessBtn').css('display', 'flex');
         $('#addCompetitionBtn').hide();
         $('#addCompetitionCancleBtn').css('display', 'flex');
         $('#deleteCompetitionBtn').hide();
         btnFlag = true;
    }
    
    function addCompetitionSuccessBtn(){
       $('#memberManageCompetitionInfo input[type=text]')
            .prop('readonly', true).css({
            'color': 'black',
            'border-bottom': '0',
            'cursor': 'auto'
      });
               
      $('#addCompetitionBtn').css('display', 'flex');
      $('#addCompetitionSuccessBtn').hide();
      $('#deleteCompetitionBtn').css('display', 'flex');
        $('#addCompetitionCancleBtn').hide();
    }
    
    function addCompetitionSuccess(){
       
        //db에 추가
        let title = $('#memberManageCompetitionText').val();
        let date = $('#memberManageCompetitionDateText').val();
        let ranking = $('#memberManageCompetitionTrophyText').val();
        let childNo = getChildNo;
        let datePattern = /^[0-9]{8}$/;        
        
        if(btnFlag){
           if(datePattern.test(date)){
              date = date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
              
             $.ajax({
                 type:"post",
                 url:"/member/addAwardContents",
                 data:{"childNo":childNo,"awardTitle":title, "dateFmt":date, "ranking":ranking},
                 success:function(){
                    addCompetitionSuccessBtn();
                    loadAwardContents(getChildNo, 0);
                    $('#memberManageRightArrow').attr("data-num",0);
                       $('#memberManageLeftArrow').attr("data-num",0);
                 }
             });
             console.log(date);
           }else{
              alert('숫자 8자리 입력해 주세요. ex) 20240910');
           }
        }
    }
    
    $('#addCompetitionBtn').click(function(event) {
         addCompetition();
    });
    
    $('#addCompetitionSuccessBtn').click(function(event) {
         addCompetitionSuccess();
    });
    
    $('#addCompetitionCancleBtn').on('click',function(){
        loadAwardContents(getChildNo, savePage);
        displayArrow(savePage);    
        addCompetitionSuccessBtn();
    });
    
     $('#deleteCompetitionBtn').on('click', function(){
         let page = $('#memberManageRightArrow').attr("data-num");
         
         if(page - 1 <= 0){
            page = 0;
            $('#memberManageRightArrow').attr("data-num",page);
            $('#memberManageLeftArrow').attr("data-num",page);
         }else{
            page--;
            $('#memberManageRightArrow').attr("data-num",page);
            $('#memberManageLeftArrow').attr("data-num",page);
         }
                  
         if(confirm('삭제하시겠습니까?')){
            $.ajax({
               type:"post",
               url:"/member/awardDelete",
               data:{"awardNo":awardNo},
               success:function(){
                  loadAwardContents(getChildNo, page);
               }
            })
         }
   });
   
   
   $(".memberManageSelectPlaceholder").on('click', function() {
		var currentDisplay = $(this).siblings(".memberManageSubSelect").css("display");
		
		if (currentDisplay === "none" || currentDisplay === "") {
		       $(this).siblings(".memberManageSubSelect").css("display", "flex");
		} else {
		       $(this).siblings(".memberManageSubSelect").css("display", "none");
		}
		      
	});

    // memberManageSubSelectEach 클릭 시 Placeholder 값 변경
    $(".memberManageSubSelectEach").on('click', function() {
        var selectedValue = $(this).text();  // 선택된 텍스트를 가져옴
        var selectedNo = $(this).attr("data-value");
        var placeholder = $(this).closest(".memberManageSelect").find(".memberManageSelectPlaceholder");  // Placeholder를 찾음
        var selectedCtgValue = $(this).closest(".memberManageSelect").find(".memberManageSubSelectEach");
        placeholder.html(selectedValue + ' <div class="memberManageDown"></div>');  // Placeholder 값을 업데이트하고 memberManageDown div를 유지
        placeholder.attr("data-value", selectedNo);
        $(this).parent().css("display", "none");  // 드롭다운을 숨김
        
        //참가 이벤트 변경
        getPayChildInfo(selectedNo);
    });

    
    // 클릭 외부 클릭 시 숨기기
    $(document).on('click', function(event) {
        if (!$(event.target).closest(".memberManageSelectPlaceholder, .memberManageSubSelect").length) {
            $(".memberManageSubSelect").css("display", "none");
        }
    });
    
    $(".memberManageSelectPlaceholder, .memberManageSubSelectEach").on('mouseenter', function() {
            $(this).css({
                "background-color": "#dddddd",
                "color": "gray"
            });
        })
        .on('mouseleave', function(){
            $(this).css({
                "background-color": "#f5f5f5",
                "color": "#424949"
            });
        }
    );
    
        $(".memberManageSubSelectEach").on('mouseenter', function() {
            $(this).css({
                "background-color": "#f3f3f3",
                "color": "gray"
            });
        })
        .on('mouseleave', function(){
            $(this).css({
                "background-color": "white",
                "color": "#424949"
            });
        }
    );
});
