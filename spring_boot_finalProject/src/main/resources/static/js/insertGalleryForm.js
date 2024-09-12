/**
 * 
 */
 $(document).ready(function() {
    var mainImageName = '';
    var detailImageName = '';
    
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
                    $('#galleryMainImageBox').append(
                        '<img class="galleryMainImage" src="/images/' + mainImageName + '" width="100" height="100" data-filename="' + mainImageName + '">'
                    );
                }
            });
        }
    });

    $('#galleryMainImageBox').on('click', '.galleryMainImage', function() {
        let fileName = $(this).data('filename');
        if ($('#galleryMain img').length > 0) {
            alert('대표 이미지는 하나만 업로드할 수 있습니다.');
            return;
        }
        $('#galleryMain').append(
            '<img class="galleryMainImg" src="/images/' + fileName + '" data-filename="' + fileName + '">'
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
                    $('#galleryDetailImageBox').append(
                        '<img class="galleryDetailImage" src="/images/' + detailImageName + '" width="100" height="100" data-filename="' + detailImageName + '">'
                    );
                }
            });
        }
    });

    $('#galleryDetailImageBox').on('click', '.galleryDetailImage', function() {
        let fileName = $(this).data('filename');
        $('#galleryDetail').append(
            '<img class="galleryDetailImg" src="/images/' + fileName + '" data-filename="' + fileName + '">'
        );
    });


    $('#galleryDetailImageInputButton').on('click', function() {
        $('#fileInputDetail').click();
    });

    $('#galleryMainImageInputButton').on('click', function() {
        $('#fileInputMain').click();
    });
    
        $(".insertGalleryPageSelectPlaceholder").on('click', function() {
        var currentDisplay = $(this).siblings(".insertGalleryPageSubSelect").css("display");

        if (currentDisplay === "none" || currentDisplay === "") {
            $(this).siblings(".insertGalleryPageSubSelect").css("display", "flex");
        } else {
            $(this).siblings(".insertGalleryPageSubSelect").css("display", "none");
        }
      
    });

    // insertGalleryPageSubSelectEach 클릭 시 Placeholder 값 변경
    $(".insertGalleryPageSubSelectEach").on('click', function() {
        var selectedValue = $(this).text();  // 선택된 텍스트를 가져옴
        var placeholder = $(this).closest(".insertGalleryPageSelect").find(".insertGalleryPageSelectPlaceholder");  // Placeholder를 찾음
        var selectedCtgValue = $(this).closest(".insertGalleryPageSelect").find(".insertGalleryPageSubSelectEach");
        placeholder.html(selectedValue + ' <div class="insertGalleryPageDown"></div>');  // Placeholder 값을 업데이트하고 insertGalleryPageDown div를 유지
        selectedCtgValue.attr("data-value", selectedValue);
        $(this).parent().css("display", "none");  // 드롭다운을 숨김
    });

    
    // 클릭 외부 클릭 시 숨기기
    $(document).on('click', function(event) {
        if (!$(event.target).closest(".insertGalleryPageSelectPlaceholder, .insertGalleryPageSubSelect").length) {
            $(".insertGalleryPageSubSelect").css("display", "none");
        }
    });
    
    $(".insertGalleryPageSelectPlaceholder, .insertGalleryPageSubSelectEach").on('mouseenter', function() {
            $(this).css({
                "background-color": "#dddddd",
                "color": "gray"
            });
        })
        .on('mouseleave', function(){
            $(this).css({
                "background-color": "#f8f8f8",
                "color": "#424949"
            });
        }
    );
    
        $(".insertGalleryPageSubSelectEach").on('mouseenter', function() {
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
 