/**
 * 
 */
 
 $(document).ready(function(){ 
 	var mainImageName = '';
    var detailImageName = '';
 
 	$('#EventMainImageInputButton').on('click', function() {
        $('#fileInputMain').click();
    });

    $('#EventDetailImageInputButton').on('click', function() {
        $('#fileInputDetail').click();
    });
     
 	// 파일 선택 시 이미지 미리보기
    $('#fileInputMain').on('change', function() {
        const input = this;
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            mainImageName = input.files[0].name;
            reader.readAsDataURL(input.files[0]);

            let formData = new FormData();
            formData.append('uploadFile', input.files[0]);

            $.ajax({
                type: "post",
                url: "/member/imageFileUploadOnly",
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                data: formData,
                success: function() {
                    $('#EventMainImageBox').append(
                        '<img class="eventMainImage" src="/images/' + mainImageName + '" width="100" height="100" data-filename="' + mainImageName + '">'
                    );
                }
            });
        }
    });
    
    $('#EventMainImageBox').on('click', '.eventMainImage', function() {
        let fileName = $(this).data('filename');
        if ($('#EventMain img').length > 0) {
            alert('대표 이미지는 하나만 업로드할 수 있습니다.');
            return;
        }
        $('#EventMain').append(
            '<img class="eventMainImage" src="/images/' + fileName + '" data-filename="' + fileName + '">'
        );
    });
    
    $('#fileInputDetail').on('change', function() {
        const input = this;
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            detailImageName = input.files[0].name;
            reader.readAsDataURL(input.files[0]);

            let formData = new FormData();
            formData.append('uploadFile', input.files[0]);

            $.ajax({
                type: "post",
                url: "/member/imageFileUploadOnly",
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                data: formData,
                success: function() {
                    $('#EventDetailImageBox').append(
                        '<img class="eventDetailImage" src="/images/' + detailImageName + '" width="100" height="100" data-filename="' + detailImageName + '">'
                    );
                }
            });
        }
    });

    $('#EventDetailImageBox').on('click', '.eventDetailImage', function() {
        let fileName = $(this).data('filename');
        $('#EventDetail').append(
            '<img class="eventDetailImage" src="/images/' + fileName + '" data-filename="' + fileName + '">'
        );
    });
    
    $('#insertEventBtn').on('click', function(){
    	let eventTitle = $('#EventName').val();
    	let eventImg = mainImageName;
    	let eventTitleContents = $('#EventMain').html();
    	let eventPrice = $('#EventMoney').val();
    	let deadLine = $('#EventDate').val();
    	let eventContents = $('#EventDetail').html();
    	
    	console.log(eventTitle);
    	console.log(eventImg);
    	console.log(eventTitleContents);
    	console.log(eventPrice);
    	console.log(deadLine);
    	console.log(eventContents);
    	
    	$.ajax({
    		type:"post",
    		url:"/gallery/insertEvent",
    		data:{
    			"eventTitle":eventTitle,
    			"eventImg":eventImg,
    			"eventTitleContents":eventTitleContents,
    			"eventPrice":eventPrice,
    			"eventContents":eventContents,
    			"deadLine":deadLine
    		},
    		success:function(){
    			location.href = "/gallery/eventBoardForm";
    		}
    	});
    });
 });