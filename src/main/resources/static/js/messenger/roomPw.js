
$(".chatRoomArea").on("click", ".roomLink", function(){
    
    // 채팅방 번호
    let roomNum = $(this).attr("data-room-num");

    // console.log("방번호 : ", roomNum);

    // console.log("룸넘이 나오니!", roomNum);

    // 비밀번호가 있는지 확인
    // ----------- Ajax -----------
    $.ajax({
        type:"POST",
        url:"pwCheck",
        data:{
            roomNum:roomNum
        },
        success:function(result){
            // let result = JSON.parse(obj);
            // console.log("결과는?!", result);

            // 비밀번호가 있음
            if(result==1){

                // console.log("성공!!");
                // 모달창 띄우기!
                $(".pwModal-overlay").css('display','flex').hide().fadeIn();

            }else{

                // 비밀번호가 없음


            }

        }
    });

    $("#pwCheckBtn").click(function(){

        let pw = $("#roomPwCheck").val();

        // console.log("비밀번호 맞았을때 방 번호 : ", roomNum);

        // console.log("입력한 비밀번호가? ", pw);
        
        $.ajax({
            type:"POST",
            url:"roomPw",
            data:{
                roomNum : roomNum,
                pw : pw
            },
            success:function(roomVO){

                // console.log("룸브이오", roomVO);

                
                if(roomVO==1){
                    // console.log("비밀번호 맞음")

                    $(".close-area").click();
                    
                    chatPop(roomNum);

                }else{
                    // console.log("틀림")
                    alert("비밀번호가 맞지 않습니다.")
                }
            }
        })

    });

});

// 채팅방 팝업창
let popupWidth = "500";
let popupHeight = "750";
let popUp = "";
    
// 듀얼 모니터 고려한 윈도우 띄우기
let curX = window.screenLeft;
let curWidth = document.body.clientWidth;
let curHeight = document.body.clientHeight;
    
let nLeft = (screen.availWidth/ 2) - (popupWidth / 2);
let nTop = ((screen.availHeight-popupHeight)/2)-10;

let strOption = "";
strOption += "left=" + nLeft + "px,";
strOption += "top=" + nTop + "px,";
strOption += "width=" + popupWidth + "px,";
strOption += "height=" + popupHeight + "px,";
strOption += "toolbar=no,menubar=no,location=no,";
strOption += "resizable=yes,status=yes";

function chatPop(roomNum){
    window.open('/messenger/chatroom?roomNum='+roomNum, '단체 채팅방', strOption);
}
