/**
 * 
 */
 $(document).ready(function() {
    var mainImage = '';    
    
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
	    
    $('#galleryDetailImageInputButton').on('click', function() {
        $('#fileInputDetail').click();
    });

    $('#galleryMainImageInputButton').on('click', function() {
        $('#fileInputMain').click();
    });    
    
    $('#insertGalleryBtn').on('click',function(){
    	let title = $('#galleryName').val();
    	let date = $('#galleryDate').val();
    	let contents = $('#galleryMain').html();
    	
    	console.log(title);
    	console.log(date);
    	console.log(contents);
    	console.log(mainImage);
    	
    	$.ajax({
    		type:"post",
    		url:"/gallery/insertGallery",
    		data:{"galleryTitle":title, "galleryImg":mainImage, "registDate":date, "galleryContents":contents},
    		success:function(){
    			location.href = "/gallery/gallery";
    		}
    	});
    });
    
});
 