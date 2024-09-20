/**
 * 
 */
 
 $(document).ready(function(){
 	
 	const currentUrl = window.location.href;  // 현재 페이지 URL 가져오기
 	const savedUrl = localStorage.getItem('savedUrl');  // 이전에 저장된 URL 가져오기
 	localStorage.setItem('savedUrl', currentUrl); 
 	
 	$('.productUniformViewListRow').on('click', function(){
 		let link = $(this).closest('.productUniformViewListRow').find('.productUniformViewTitleCell a');
 		
 		if(link.length){
 			location.href = link.attr('href');
 		}
 	});
 	
 	//숫자 포멧
 	function formatPrice(value) {
    	return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
	}
 	    
 	$('.productUniformViewPriceCell').each(function(){ 		
 		let price = $(this).text();
 		
 		$(this).text(formatPrice(parseInt(price)));
 	});
 	
 	function getParameterByName(name) {
        var url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
 	
 		var ctg = getParameterByName('ctg');
 		console.log(ctg);
 	    if (ctg === '도복') {
        $('a[href="?ctg=도복"]').parent().css({
            "font-weight": "600",
            "font-size": "21px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
    } else if (ctg === '아우터') {
        $('a[href="?ctg=아우터"]').parent().css({
            "font-weight": "600",
            "font-size": "21px",
            "text-decoration": "underline",
            "text-underline-offset": "15px",
            "text-decoration-thickness": "2px"
        });
    } 
    $('.memberManageSelectPlaceholder').css("display", "flex");
    
 })