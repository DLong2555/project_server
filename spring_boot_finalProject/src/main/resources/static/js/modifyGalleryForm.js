/**
 * 
 */
 
 $(document).ready(function(){
 	var mainImage = '';  
 	
 	$('#modifyGalleryBtn').on('click', function(){
 		let firstImg = $('#galleryMain img:first').data('filename');
 		let galleryName = $('#galleryName').val();
 		let registDate = $('#galleryDate').val();
 		let galleryContents = $('#galleryMain').html();
 		let galleryNo = $('.modifyGalleryPageInputBox').attr("data-no");
 		
 		console.log(firstImg);
 		console.log(galleryName);
 		console.log(registDate);
 		console.log(galleryContents);
 		
 		$.ajax({
    		type:"post",
    		url:"/gallery/updateGallery",
    		data:{"galleryTitle":galleryName, "galleryImg":firstImg, "registDate":registDate, "galleryContents":galleryContents, "galleryNo":galleryNo},
    		success:function(){
    			location.href = "/gallery/galleryContentPage?galleryNo=" + galleryNo;
    		}
    	});
 	});
 	
 	$('#fileInputMain').on('change', function() {
	        // 파일 입력 필드에서 선택된 파일들
	        var files = $(this)[0].files;
	        
	        // 파일 이름들을 저장할 변수
	        var formData = new FormData();
	
	        // files 배열을 순회하며 파일 이름을 가져옴
	        $.each(files, function(index, file) {
	            formData.append('uploadFile',file);	            
	        });;
	        
	        $.ajax({
                type: "post",
                url: "/member/imageFileUploadMulti",
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                data: formData,
                success: function(response) {
                	$.each(response, function(index, mainImageName){
                		if(mainImage.length == 0){
                			mainImage = mainImageName;
                		}
                		
	                    $('#galleryMain').append(
	                        '<img class="galleryMainImage" src="/images/' + mainImageName + '" width="410" height="410" data-filename="' + mainImageName + '">'
	                    );
                    });                 
                }
            });
	       
	});
	
	$('#galleryMainImageInputButton').on('click', function() {
        $('#fileInputMain').click();
    });  
 });