<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
                  
                  
                  <div class="row" style="padding-right: 13px">
                  <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-4 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            Pending Requests</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>          
                            
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-4 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2">
                                                    <div class="progress-bar bg-info" role="progressbar"
                                                        style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                        aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-4 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2">
                                                    <div class="progress-bar bg-info" role="progressbar"
                                                        style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                        aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-4 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Earnings (Annual)</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                  </div>                   
                      <!-- Scroll Top, Login Modal import -->
   <c:import url="/temp/layout_top_logoutModal.jsp"></c:import>
<script>
    // ?????? ?????? ????????? ????????? ???
    $('.delete_btn').click(function() {
        console.log($(this).val()); // ?????? ??? ????????? value???(reserveNum)??? ????????????.
        
        let reserve = $(this).val(); // value???(reserveNum)??? reserve??? ????????????.
        let result = confirm("????????? ????????????????????????? \n?????? ????????? ????????? ??? ????????????.");

        if (result) { // ?????? ?????? ???
            $.get("/goods/car/carReserveDelete?reserveNum=" + reserve, function(result) { // controller??? get????????? ?????????
                console.log(reserve);
                location.reload();
            });
        }
    });
</script>
<!-- calendar script-->
<script>
    var loadCalendar = function() {
        // calendar element ??????
        var calendarEl = $('#calendar')[0];
        console.log("rr");
        var params = {
                  room : $("#room").is(':checked') ,
                  car : $("#car").is(':checked'),
                  vacation : $("#vacation").is(':checked'),
          depNum : $("#depNum").val()
              }
        
  
        var request = $.ajax({
            url: "/goods/calendar", // ????????????
            method: "POST",
              data : params,
            dataType: "json"
        });
        
        
        
        request.done(function (data) {
            console.log(data); // log ??? ????????? ????????????.
  
            var calendarEl = document.getElementById('calendar');
  
            var calendar = new FullCalendar.Calendar(calendarEl, {
                height: '550px', // calendar ?????? ??????
                expandRows: true, // ????????? ?????? ?????? ?????????
                slotMinTime: '08:00', // Day ??????????????? ?????? ??????
                slotMaxTime: '20:00', // Day ??????????????? ?????? ??????
                // ????????? ????????? ??????
                headerToolbar: {
                  left: 'prev,next today',
                  center: 'title',
                  right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
                },
                initialView: 'dayGridMonth', // ?????? ?????? ?????? ????????? ????????? ??????(?????? ??????: ???)
                // initialDate: '2021-12-15', // ?????? ?????? ?????? (???????????? ????????? ?????? ????????? ?????????.)
                navLinks: true, // ????????? ???????????? Day ???????????? Week ???????????? ??????
                editable: true, // ?????? ???????
                selectable: true, // ?????? ?????? ????????? ????????????
                nowIndicator: true, // ?????? ?????? ??????
                dayMaxEvents: true, // ???????????? ???????????? ?????? ?????? (+ ??? ???????????? ??????)
                locale: 'ko', // ????????? ??????
                eventAdd: function(obj) { // ???????????? ???????????? ???????????? ?????????
                  console.log(obj);
                },
                eventChange: function(obj) { // ???????????? ???????????? ???????????? ?????????
                  console.log(obj);
                },
                eventRemove: function(obj){ // ???????????? ???????????? ???????????? ?????????
                  console.log(obj);
                },
                
                /**
                 * data ??? ?????? ????????????. log ??? ??????.
                 */
                 events: data
                
                 ,eventClick:function(data) {
                  
                    if(data) {
                      $('#calendarModal').modal("show");    
                        var startTimeCheck = new Date(data.event.start);
                        var endTimeCheck = new Date(data.event.end);
                      console.log(data.event)
                      console.log(data.event.extendedProps.content);
                        
  
                        $('#calendar_content').val(data.event.title);
                                $('#calendar_start_date').val(startTimeCheck.toLocaleDateString('ko-kr')+startTimeCheck.toLocaleTimeString('ko-kr'));
                                $('#calendar_end_date').val(endTimeCheck.toLocaleDateString('ko-kr')+endTimeCheck.toLocaleTimeString('ko-kr'));
                      $('#calendar_user_roleName').val(data.event.extendedProps.test1);
                                $('#calendar_user_name').val(data.event.extendedProps.test2);
                      
                      
                    }
                }
                
                //  [{
                //     title: 'All Day Event',
                //     start: '2022-12-01 12:30:00',
                //     end: '2022-12-03',
                //     content : '??????'
                //   }] 
                
            });
  
            calendar.render();
            
        });
  
        request.fail(function( jqXHR, textStatus ) {
            alert( "Request failed: " + textStatus );
        });
  
       
     }; 
     
     loadCalendar();
     
     $('.filter').click(function(){
         
         loadCalendar();
     });
     
  
  </script>