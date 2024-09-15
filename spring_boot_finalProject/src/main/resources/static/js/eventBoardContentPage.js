/**
 * 
 */
 
 $(document).ready(function(){
 
 	//숫자 포멧
    function formatPrice(value) {
       return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    
 	var eventPrice = formatPrice(parseInt($('#eventPrice').text()));
 	$('#eventPrice').text(eventPrice);
 	
 	//특수활동 공고 삭제
 	$('#eventBoardContentPageDeleteButton a').on('click',function(){
 		let no = parseInt($('#eventBoardContentPageContentTitle').attr("data-value"));
 		console.log(no);
 		
 		if(confirm('삭제하시겠습니까?')){
	 		$.ajax({
	 			type:"post",
	 			url:"/gallery/deleteGallContent",
	 			data:{"eventNo":no},
	 			success:function(){
	 				location.href = "/gallery/eventBoardForm"
	 			}
	 		});
	 	}else return false;
 	});
 	
 });