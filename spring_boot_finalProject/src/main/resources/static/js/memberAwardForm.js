$(document).ready(function() {
   $('#memberAwardImageBox').on('click', function() {
        $('#awardFileInput').click();
    });
    
    $(".memberAwardMenuEach").hover(
        function() {
            $(this).css({
                "background-color": "#f8f9f9",
                "color": "gray"
            });
        },
        function() {
            $(this).css({
                "background-color": "white",
                "color": "#424949"
            });
        }
    );
    
    $('#awardProfile').click(function() {
       window.location.href = '/member/myPageForm'; // 이동할 URL을 지정
    });
    
    $('#awardManage').click(function() {
       window.location.href = '/member/myPageChildInfo'; // 이동할 URL을 지정/member/memberManageForm
    });        
    
    $("#awardPlus").on('click', function() {
        const newUserEach = $("<div>", { class: "memberAwardRegisterUserEach" });
        newUserEach.html("<p>등록회원</p>");

        const image = $("<img>", {
            src: "/image/myPageDown.jpg",
            alt: "Down Image",
            class: "memberAwardDown"
        });

        const subMenu = $("<div>", { class: "memberAwardRegisterUserSub" });
        subMenu.html(`
            <div class="memberAwardRegisterSubBox">
                  <div id="memberAwardCompetitionInfo">
                     <div id="memberAwardChooseBox">
                        <div id="memberAwardArrowBox">
                           <img id="memberAwardLeftArrow" src="/image/leftArrow.png" class="arrowBtn" data-num="0">
                           <img id="memberAwardRightArrow" src="/image/RightArrow.png" class="arrowBtn" data-num="0">
                        </div>
                     </div>
                     <div id="memberAwardCompetitionNullBox">수상 내역이 없습니다.</div>
                     <div id="memberAwardCompetitionContentsBox">
                        <div class="memberAwardMemberContents">
                           <img id="memberAwardCompetition" src="/image/competition.jfif" class="awardImage">
                           <input id="memberAwardCompetitionText" name="memberAwardCompetitionDateText" type="text" value="" readonly required>
                        </div>
                        <div class="memberAwardMemberContents">
                           <img id="memberAwardCompetitionDate" src="/image/myPageDate.png" class="awardImage">
                           <input id="memberAwardCompetitionDateText" name="memberAwardCompetitionDateText" type="text" value="" readonly required>
                        </div>
                        <div class="memberAwardMemberContents">
                           <img id="memberAwardTrophy" src="/image/Trophy.png" class="awardImage">
                           <input id="memberAwardCompetitionTrophyText" name="memberAwardCompetitionTrophyText" type="text" value="" readonly required>
                        </div>
                     </div>
                  </div>
            </div>
        `);

        newUserEach.append(image);
        $("#memberAwardRegisterUserData").append(newUserEach);
        $("#memberAwardRegisterUserData").append(subMenu);

        // Toggle submenu visibility when the image is clicked
        image.on('click', function() {
            const isOpen = subMenu.css('display') === 'flex';
            $(".memberAwardRegisterUserSub").hide();
            $(".memberAwardDown").css('transform', 'rotate(0deg)');
            $(".memberAwardRegisterUserEach").css('border', '1px solid #a3a3a3');

            if (!isOpen) {
                subMenu.css('display', 'flex');
                image.css('transform', 'rotate(180deg)');
                newUserEach.css('border', '2px solid black');
            }
        });
    });    
});

