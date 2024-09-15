/**
 * 
 */
 $(document).ready(function(){
 	$('#galleryContentPageDeleteButton a').on('click', function(){
 		let no = $('#galleryContentPageContentTitle').attr("data-no");
 		console.log(no);
 		
 		if(confirm('삭제하시겠습니까?')){
 			$.ajax({
 				type:"get",
 				url:"/gallery/deleteContent?galleryNo=" + no,
 				success:function(){
 					location.href = "/gallery/gallery";
 				}
 			})
 		}else{
 			return false;
 		}
 	});
 });