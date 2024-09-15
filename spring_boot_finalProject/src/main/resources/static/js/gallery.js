/**
 * 
 */
 $(document).ready(function() {
    // galleryViewListRow 요소 클릭 시 이벤트 처리
    $(document).on('click', '.galleryViewListRow' ,function() {
 	  let no = $(this).attr("data-no");
 	  console.log(no);
      // 하이퍼링크로 이동
      var url = '/gallery/galleryContentPage?galleryNo=' + no;
      window.location.href = url;
    });
 });