$(document).ready(function() {
   $('#memberAwardImageBox').on('click', function() {
        $('#awardFileInput').click();
    });
    
    $(".memberAwardMenuEach").hover(
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
    
    $('#awardProfile').click(function() {
       window.location.href = '/member/myPageForm'; // 이동할 URL을 지정
    });
    
    $('#awardManage').click(function() {
       window.location.href = '/member/myPageChildInfo'; // 이동할 URL을 지정/member/memberManageForm
    });        
    
    $(document).on('click', '.memberAwardRegisterUserEach', function(){
    	let subElement = $(this).closest('#memberAwardRegisterUserData').find('.memberAwardRegisterUserSub');
    	let no = $(this).attr('data-no');   	
    	let position = $(this);
    	let page = 0;
    	
    	if(subElement.css('display') === 'none'){
    		subElement.css('display', 'flex');
    		
    		loadAwardContents(no, page, position);    		
    	}else{
    		subElement.css('display', 'none');
    		position.closest('#memberAwardRegisterUserData').find('#memberAwardLeftArrow').attr("data-num", 0);
    		position.closest('#memberAwardRegisterUserData').find('#memberAwardRightArrow').attr("data-num", 0);
    	}
    	
    	position.closest('#memberAwardRegisterUserData').find('#memberAwardRightArrow').off('click').on('click',function(){
	        let page = parseInt($(this).attr("data-num")) + 1;
	         
	        if(page >= totalPage){           
	           page = totalPage;
	        }
	         
	        loadAwardContents(no, page, position);
	         
	        $(this).attr("data-num", page);
	        position.closest('#memberAwardRegisterUserData').find('#memberAwardLeftArrow').attr("data-num", page);
	    });
   
	    position.closest('#memberAwardRegisterUserData').find('#memberAwardLeftArrow').off('click').on('click',function(){
	         let page = parseInt($(this).attr("data-num")) - 1;
	         
	         if(page <= 0){          
	            page = 0;
	         }
	         
	         loadAwardContents(no, page, position);
	         
	         $(this).attr("data-num", page);
	         position.closest('#memberAwardRegisterUserData').find('#memberAwardRightArrow').attr("data-num", page);
	    });
    });
    
    var totalPage = 0;
    var awardNo = 0;
    function loadAwardContents(no, page, position){
       let pageNum = parseInt(page) + 1;
       let $this = position.closest('#memberAwardRegisterUserData');
       
       $.ajax({
            type:"post",
            url:"/member/getAwardContents",
            data:{"childNo":no, "page":pageNum},
            success:function(result){
               if(result){
                  $this.find('#memberAwardCompetitionText').val(result.awardTitle);
                  $this.find('#memberAwardCompetitionDateText').val(result.dateFmt);
                  $this.find('#memberAwardCompetitionTrophyText').val(result.ranking);
                  
                  awardNo = result.awardNo;
                  totalPage = result.totalPage - 1;
                  $this.find('#memberAwardCompetitionContentsBox').css('display', 'flex');
                  $this.find('#memberAwardCompetitionNullBox').css('display', 'none');
                  $this.find('#memberAwardArrowBox').css("display", "flex");
                 
                  displayArrow(page, position);
               }else{
                  $this.find('#memberAwardCompetitionContentsBox').css('display', 'none');
                  $this.find('#memberAwardCompetitionNullBox').css('display', 'flex');
                  $this.find('#memberAwardArrowBox').css("display", "none");
                  
               }
            }
         });
    }    
   
   function displayArrow(page, position){
   	   let $this = position.closest('#memberAwardRegisterUserData');
       if(totalPage == 0){
          	$this.find('#memberAwardRightArrow').css("visibility", "hidden");
            $this.find('#memberAwardLeftArrow').css("visibility", "hidden");
       }else{
          if(page == 0){
               $this.find('#memberAwardRightArrow').css("visibility", "visible");
               $this.find('#memberAwardLeftArrow').css("visibility", "hidden");
          }else if(page == totalPage){
               $this.find('#memberAwardRightArrow').css("visibility", "hidden");
               $this.find('#memberAwardLeftArrow').css("visibility", "visible");
          }else{
               $this.find('#memberAwardRightArrow').css("visibility", "visible");
               $this.find('#memberAwardLeftArrow').css("visibility", "visible");
          }
       }
    }

});

