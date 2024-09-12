/**
 * 
 */
 $(document).ready(function() {
    // galleryViewListRow 요소 클릭 시 이벤트 처리
    $('.galleryViewListRow').on('click', function() {
 
      // 하이퍼링크로 이동
      var url = '/gallery/galleryContentPage';
      window.location.href = url;
    });
 });