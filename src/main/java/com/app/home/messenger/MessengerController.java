package com.app.home.messenger;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.home.user.DepartmentVO;
import com.app.home.user.UserService;
import com.app.home.user.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("messenger")
@Slf4j
public class MessengerController extends Socket {
	
	@Autowired
	private NoteService noteService;

	@Autowired
	private MessengerService messengerService;
	
	@Autowired
	private PickService pickService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("chat")
	public ModelAndView getMyChat(HttpSession session, UserVO userVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  = (UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		Integer id = userVO.getId();
		ModelAndView mv = new ModelAndView();
		List<DepartmentVO> dl = messengerService.getDepList();
		List<UserVO> el = messengerService.getEmpList();
		List<UserVO> pl = pickService.getPickList(id.toString());
		
		mv.addObject("user", userVO);
		
		// ------------------ ???????????? ------------------
		List<RoomVO> roomVOs = new ArrayList<>();
		RoomVO roomVO = new RoomVO();
		UserVO userVO2 = new UserVO();
		userVO.setId(userVO.getId());
		roomVO.setUserVO(userVO);
		
		roomVOs = messengerService.getRoomList(roomVO);
		
		mv.addObject("roomList", roomVOs);
		mv.setViewName("messenger/chat");
		// ------------------ ???????????? ------------------
		
		mv.addObject("myId", id);
		mv.addObject("depList", dl);
		mv.addObject("empList", el);
		mv.addObject("url", "chat");
		mv.addObject("pickList", pl);
		return mv;
	}
	
	@PostMapping("searchEmp")
	public ModelAndView getSearchResult(HttpSession session, UserVO userVO, String kind, String keyword, String url) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		Integer id = userVO.getId();
		Map<String, String> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("kind", kind);
		List<UserVO> el = messengerService.getSearchResult(map);
		List<UserVO> pl = pickService.getPickList(id.toString());
		ModelAndView mv = new ModelAndView();
		
		// ------------------ ???????????? ------------------
			List<RoomVO> roomVOs = new ArrayList<>();
			RoomVO roomVO = new RoomVO();
			UserVO userVO2 = new UserVO();
			userVO.setId(userVO.getId());
			roomVO.setUserVO(userVO);
			
			roomVOs = messengerService.getRoomList(roomVO);
			
			mv.addObject("roomList", roomVOs);
		// ------------------ ???????????? ------------------
		
		mv.addObject("user", userVO);
		mv.addObject("myId", id);
		mv.addObject("empList", el);
		mv.addObject("pickList", pl);
		mv.addObject("url", "chat");
		mv.setViewName("/messenger/chat");
		return mv;
	}
	
	@GetMapping("pickCheck")
	@ResponseBody
	public int setPickCheck (String myId, String yourId) throws Exception{
		int result = pickService.createPick(myId, yourId);
		log.info("result:{}",result);
		return result;
	}
	
	@PostMapping("pickCancel")
	@ResponseBody
	public int pickCancel (String myId, String yourId) throws Exception{
		int result = pickService.pickCancel(myId, yourId);
		return result;
	}
	
	// --------------------- ?????? ------------------------------
	
	//?????????
	@GetMapping("note")
	public ModelAndView getReceiveNoteList(HttpSession session, UserVO userVO, NotePager notePager)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		Integer id = userVO.getId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userVO);
		mv.addObject("url", "note");
		
		//?????? id
		userVO.setId(id);

		List<NoteVO> ar = noteService.getReceiveNoteList(userVO, notePager);
		
		
		Long getNotReadCount = noteService.getNotReadCount(userVO);
		mv.addObject("list", ar);
//		log.info("?????????AR {}",ar);
		mv.addObject("pager", notePager);
		
		if(getNotReadCount==0L) {
			mv.addObject("getNotReadCount", "");
		} else {
			mv.addObject("getNotReadCount", getNotReadCount);
		}
		
		
		if(ar.size()==0) {
//			log.info("=============================?????????");
			mv.addObject("message5", "????????? ????????????.");
		} else {
			mv.addObject("message5", "");
		}
		
		
		
		List<DepartmentVO> dl = messengerService.getDepList();
		List<UserVO> el = messengerService.getEmpList();
		List<UserVO> pl = pickService.getPickList(id.toString());
		
		mv.addObject("myId", id);
		mv.addObject("depList", dl);
		mv.addObject("empList", el);
		mv.addObject("pickList", pl);
		
		return mv;
	}
	
	//?????????
	@GetMapping("note/sent")
	@ResponseBody
	public ModelAndView getSendNoteList(HttpSession session, UserVO userVO, NotePager notePager)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		
		ModelAndView mv = new ModelAndView("jsonView");
		//?????? id
		userVO.setId(userVO.getId());
		List<NoteVO> ar = noteService.getSendNoteList(userVO, notePager);
		mv.addObject("list", ar);
		mv.addObject("pager", notePager);
		
		if(ar.size()==0) {
//			log.info("=============================???????????????");
			mv.addObject("message5", "????????? ????????????.");
		} else {
			mv.addObject("message5", "");
		}
		return mv;
	}
	
	//?????? ??????
	@GetMapping("note/detail")
	public ModelAndView getNoteDetail(HttpSession session, NoteVO noteVO, UserVO userVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		
		ModelAndView mv = new ModelAndView();
		noteVO = noteService.getNoteDetail(noteVO);
		
		
		UserVO sendUser = new UserVO();
		sendUser.setId(noteVO.getSendId());
		sendUser = userService.getMypage(sendUser);
		
		UserVO receiveUser = new UserVO();
		receiveUser.setId(noteVO.getReceiveId());
		receiveUser = userService.getMypage(receiveUser);
		
		mv.addObject("detail", noteVO);
//		log.info("???????????? {}, {}", userVO.getId(), noteVO.getSendId());
		mv.addObject("sendUser", sendUser);
		mv.addObject("receiveUser", receiveUser);
		mv.addObject("session", userVO);
		
		userVO.setId(userVO.getId());
		
		noteVO.setReceiveId(userVO.getId());
		
		
		NoteVO checkMinus = new NoteVO();
		checkMinus.setNoteNum(noteVO.getNoteNum()-1);
		checkMinus.setReceiveId(noteVO.getReceiveId());
		
		
		if(noteVO.getReadCheck()==1) {
			noteService.updateCheck(noteVO);
			int result = noteService.updateCheck(checkMinus);
		} else {
			
		}
		
		mv.setViewName("messenger/note/detail");
		return mv;
	}
	
	//????????????
	@GetMapping("note/send")
	public ModelAndView setSendNote(HttpSession session, UserVO userVO, NoteVO noteVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
	    
	    UserVO receiveUser = new UserVO();
	    receiveUser.setId(noteVO.getReceiveId());
	    receiveUser = userService.getMypage(receiveUser);
	    
		ModelAndView mv = new ModelAndView();
		userVO.setId(userVO.getId());
		mv.addObject("member", userVO);
		mv.addObject("receiveUser", receiveUser);
		return mv;
	}
	
	@PostMapping("note/send")
	public ModelAndView setSendNote(HttpSession session, NoteVO noteVO)throws Exception{
		
		
		ModelAndView mv = new ModelAndView();
		String message = "";
		int result = noteService.setSendNote(noteVO);
		if(result==1) {
			message = "????????? ?????????????????????.";
		} else {
			message = "?????? ????????? ??????????????????.";
		}
		mv.addObject("message", message);
		mv.setViewName("messenger/note/sendAfter");
		return mv;
	}
	
//	@GetMapping("note/check")
//	@ResponseBody
//	public int updateCheck(NoteVO noteVO)throws Exception{
//		return noteService.updateCheck(noteVO);
//	}
	
	@GetMapping("note/delete")
	@ResponseBody
	public int setDeleteNote(NoteVO noteVO)throws Exception{
		return noteService.setDeleteNote(noteVO);
	}
	
	private ArrayList<Integer> arrr = new ArrayList<>();
	
	@PostMapping("note/group1")
	@ResponseBody
	public ModelAndView setGroup1(HttpSession session, UserVO userVO, int [] arr)throws Exception{
		arrr = new ArrayList<>();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  = (UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		
		ModelAndView mv = new ModelAndView("jsonView");
//		log.info("??????????????????????????? {}",arr);
		
		for(int arrrdd : arr) {
			arrr.add(arrrdd);
		}
		
		mv.addObject("member", userVO);
		mv.addObject("yourId", arr);
		mv.setViewName("messenger/note/group");
		
		return mv;
	}
	
	@GetMapping("note/group")
	@ResponseBody
	public ModelAndView setGroup(HttpSession session, UserVO userVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  = (UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		mv.addObject("member", userVO);
		mv.addObject("yourId", arrr);
		
		UserVO daepyo = new UserVO();
		daepyo.setId(arrr.get(0).intValue());
		daepyo = userService.getMypage(daepyo);
		
		mv.addObject("daepyo", daepyo);
		mv.addObject("count", arrr.size()-1);
//		log.info("??????????????????????????? {}",arrr);
		
		mv.setViewName("messenger/note/group");
		
		return mv;
	}
	
	@PostMapping("note/group")
	public ModelAndView setGroup(HttpSession session, UserVO userVO, NoteVO noteVO, ModelAndView mv)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
	    
//		log.info("??????????????? {}",arrr);

	    
	    int result = 0;
		String message = "";

	    for(int arrrdd : arrr) {
	    	noteVO.setReceiveId(arrrdd);
	    	result = noteService.setSendNoteGroup(noteVO);
//	    	log.info("???????????? {}",noteVO);
		}
	    
	    if(result==1) {
			message = "????????? ?????????????????????.";
		} else {
			message = "?????? ????????? ??????????????????.";
		}
	    
		mv.addObject("message", message);
		mv.setViewName("messenger/note/sendAfter");
	    
	    
		return mv;
	}
	
	
	
	// --------------------- ?????? ???------------------------------
	
	// --------------------- ?????? ------------------------------	
	// ????????? ??????
	@PostMapping("addRoom")
	public ModelAndView setAddRoom(HttpSession session, UserVO userVO, RoomVO roomVO)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  = (UserVO)authentication.getPrincipal();
	    userVO = userService.getMypage(userVO);
	    
	    // ????????? ????????? ????????????
		roomVO.setHostId(userVO.getId());
		
		// ????????? ??????
		UserVO hostVO = new UserVO();
		hostVO.setId(userVO.getId());
		roomVO.setUserVO(hostVO);
		
		int result = messengerService.setAddRoom(roomVO);
		String message="????????? ????????? ?????? ????????????..";
		// ?????? ????????? /member/login.iu
		String url = "./chat";
		
		if(result > 0) {
			log.info("===========????????? ?????? ??????===========");
			message="????????? ????????? ?????? ????????????!!";
			url="../messenger/chat";
		}
		
		mv.addObject("message", message);
		mv.addObject("url", url);
		mv.setViewName("messenger/result");
		
		return mv;
	}
	
	// ?????? ??????
	@GetMapping("userCount")
	@ResponseBody
	public ModelAndView getUserCount(RoomVO roomVO)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		int userCount = messengerService.getUserCount(roomVO);
		
		log.info("Count =========> {} ", userCount);
		
		mv.addObject("userCount", userCount);
		mv.setViewName("messenger/chat");
		
		return mv;
	}
	
	@PostMapping("roomPw")
	@ResponseBody
	public int getRoomPw(RoomVO roomVO, String pw)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		log.info("?????? ?????????????!!! ======> {} ",roomVO.getRoomNum());
		log.info("????????????????! =====> {} ", pw);
		
		// ????????? ????????? ????????? ????????? ????????? ??????????????? ???
		// String => Integer??? ??????
		int roomPw = Integer.parseInt(pw);
		
		// roomVO??? ?????? ?????????
		roomVO.setPw(roomPw);
		
		// ?????? ??????????????? ????????? ??????
		roomVO = messengerService.getRoomPw(roomVO);
		
		int result = 0;
		
//		log.info("??????????????? ?????????? {} ", roomVO.getRoomName());
		
		if(roomVO!=null) {
			result = 1;
		}
		
		
//		mv.addObject("roomPw", roomVO.getPw());
		
		return result;
	}
	
	@PostMapping("pwCheck")
	@ResponseBody
	public int getPwCheck(RoomVO roomVO)throws Exception{
		
		int result = messengerService.getPwCheck(roomVO);
		
		return result;
	}
	
	// --------------------- ?????? ??? ------------------------------
	

	// --------------------- ?????? ------------------------------

	@GetMapping("oneChat/{roomNum}")
	public ModelAndView chatroom(HttpSession session, UserVO userVO, RoomVO roomVO,@PathVariable String roomNum)throws Exception{
		ModelAndView mv= new ModelAndView();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  =(UserVO)authentication.getPrincipal();	
	    
	    System.out.println(roomNum);
	    userVO = userService.getMypage(userVO);
	    mv.addObject("userVO", userVO);  
	    
	    String rn = messengerService.getChatName(roomVO);
		mv.addObject("rn", rn);
		
		mv.addObject("roomNum", roomNum);

	    mv.setViewName("messenger/oneChat");
		
		return mv;
	}

	
	//--------------------- ?????? ------------------------------
	// ?????? ?????????
	@GetMapping("chatroom/{roomNum}")
	public ModelAndView chat3(HttpSession session, UserVO userVO, RoomVO roomVO, @PathVariable String roomNum)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    userVO  = (UserVO)authentication.getPrincipal();
		
		ModelAndView mv = new ModelAndView();
		
		//?????? ???
		int count = messengerService.getUserCount(roomVO);
		mv.addObject("count", count);
		
		//?????? ??????
		userVO = userService.getMypage(userVO);
		mv.addObject("user", userVO);
		
		//????????? ??????
		String rn = messengerService.getChatName(roomVO);
		mv.addObject("rn", rn);

		mv.addObject("roomNum", roomNum);
		mv.setViewName("messenger/chatroom");
		return mv;
	}

	
}



