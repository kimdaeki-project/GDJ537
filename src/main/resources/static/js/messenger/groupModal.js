
// 채팅방 생성 모달창 닫기
// const room = document.getElementById("room")
// const rmCloseBtn = room.querySelector(".close-area")
// rmCloseBtn.addEventListener("click", e => {
//     room.style.display = "none"
// });

// //  채팅방 생성 모달창 열기
// $("#roomBtn").click(function(){
//     console.log("모달창 클릭됨?")
//     $(".room-overlay").css('display','flex').hide().fadeIn();
// });

// 채팅방추가 아이콘 누르면 체크박스 생성
$("#userBtn").click(function(){

    $(".check-overlay").css('display','flex').hide().fadeIn();

    // 다음버튼 클릭
    $("#roomBtn").click(function(){
        // 유저 체크창 닫기
        $(".check-overlay").css('display','none').hide().fadeIn();
        // 채팅방 생성창 열기
        $(".room-overlay").css('display','flex').hide().fadeIn();

        // ------------- Ajax -------------
        $.ajax({
            type:"POST",
            url:"addRoomUser",
            data: id,
            success:function(result){
                console.log("result : ", result);
            }
        });

    });

});