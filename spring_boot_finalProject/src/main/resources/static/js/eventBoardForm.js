/**
 * 
 */
 $(document).ready(function() {
 	$('.eventBoardFormListRow').each(function(){
 		let dateElement = $(this).find('.eventBoardFormDescription');
 		let dateChk = dateElement.attr('data-value');
 		console.log(dateChk);
 		if(dateChk === 'true'){ 			
 			$(this).find('.eventBoardFormDate').empty();
 			$(this).find('.eventBoardFormDate').text('마감');
 		}
 	});   
 });