/**
 * 
 */
 $(document).ready(function(){
 	$('#insertEventBtn').on('click',function(){
 		if(confirm('수정하시겠습니까?')){
 			let eventNo = $('.insertEventFormInputBox').attr("data-no");
 			let eventTitle = $('#EventName').val();
	    	let eventImg = $('#EventMain').attr("data-Img");
	    	let eventTitleContents = $('#EventMain').html();
	    	let eventPrice = $('#EventMoney').val();
	    	let deadLine = $('#EventDate').val();
	    	let eventContents = $('#EventDetail').html();
	    	
	    	console.log(eventNo);
	    	console.log(eventTitle);
	    	console.log(eventImg);
	    	console.log(eventTitleContents);
	    	console.log(eventPrice);
	    	console.log(deadLine);
	    	console.log(eventContents);
	    	
	    	$.ajax({
	    		type:"post",
	    		url:"/gallery/updateEventContent",
	    		data:{
	    			"eventNo":eventNo,
	    			"eventTitle":eventTitle,
	    			"eventImg":eventImg,
	    			"eventTitleContents":eventTitleContents,
	    			"eventPrice":eventPrice,
	    			"eventContents":eventContents,
	    			"deadLine":deadLine
	    		},
	    		success:function(){
	    			location.href = "/gallery/eventBoardContentPage?eventNo=" + eventNo;
	    		}
	    	});
 		}else return false;
 	});
 });