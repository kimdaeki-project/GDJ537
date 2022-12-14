package com.app.home.report;


import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.home.report.pay.ReportPayVO;
import com.app.home.report.pay.RepriceVO;
import com.app.home.report.sorry.ReportSorryVO;
import com.app.home.report.util.ReportPager;
import com.app.home.report.vaca.ReportVacaVO;
import com.app.home.report.work.ReportWorkVO;
import com.app.home.user.DepartmentVO;
import com.app.home.user.UserMapper;
import com.app.home.user.UserService;
import com.app.home.user.UserVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private UserService userService;
	
	
	//=======================κΉλμ===================
	@GetMapping("/kdy/reportAdd")
	public ModelAndView reportAdd(ReportApplyVO reportApplyVO, Principal principal)throws Exception{
		ModelAndView mv = new ModelAndView();
		
//		if(principal == null) {
//			mv.setViewName("user/login");
//			return mv;
//		}
		
		mv.setViewName("kdy/reportAdd");
		
		return mv;
	}
	//ν΄κ°μ μ²­μ
	@GetMapping("/kdy/vacationApplication")
	public ModelAndView vacationApplication(Principal principal)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		
		log.info("principal :: {} " , principal);
		int id = Integer.parseInt(principal.getName());
		
		
		//νμ¬μκ° κ΅¬νκΈ°========================
		LocalDate now = LocalDate.now();
								
		int year = now.getYear();
		int month = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", dayOfMonth);
		//===================================
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO = userMapper.getMypage(userVO);
		mv.addObject("vo", userVO);
		mv.setViewName("/kdy/vacationApplication");
		
		return mv;
	}
	//μλ¬΄λ³΄κ³ μ
	@GetMapping("/kdy/workReport")
	public ModelAndView workReport(Principal principal)throws Exception{
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(principal.getName());
		
		//νμ¬μκ° κ΅¬νκΈ°========================
		LocalDate now = LocalDate.now();
								
		int year = now.getYear();
		int month = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", dayOfMonth);
		//===================================
		
		
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO = userMapper.getMypage(userVO);
		mv.addObject("vo", userVO);
		mv.setViewName("/kdy/workReport");
		
		
		return mv;

	}
	
	//μ§μΆ κ²°μμ
	@GetMapping("/kdy/cashDisbursementVoucher")
	public ModelAndView cashDisbursementVoucher(Principal principal)throws Exception{
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(principal.getName());
		
		//νμ¬μκ° κ΅¬νκΈ°========================
		LocalDate now = LocalDate.now();
						
		int year = now.getYear();
		int month = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
						
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", dayOfMonth);
		//===================================
		
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO = userMapper.getMypage(userVO);
		mv.addObject("vo", userVO);
		mv.setViewName("/kdy/cashDisbursementVoucher");
		
		return mv;
	}
	//μλ§μ
	@GetMapping("/kdy/writtenApology")
	public ModelAndView writtenApology(Principal principal)throws Exception{
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(principal.getName());
		
		//νμ¬μκ° κ΅¬νκΈ°========================
		LocalDate now = LocalDate.now();
								
		int year = now.getYear();
		int month = now.getMonthValue();
		int dayOfMonth = now.getDayOfMonth();
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", dayOfMonth);
		//===================================
		
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO = userMapper.getMypage(userVO);
		mv.addObject("vo", userVO);
		mv.setViewName("/kdy/writtenApology");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	//================================================
	
	
	//=======================νμ’μ===================
	
	//λΆμ, μ§κΈμ κ³ λ₯΄λ©΄ μ‘°νλλ λ¦¬μ€νΈ
	@RequestMapping(method = RequestMethod.GET, value = "/report/insa")
	public ModelAndView getReportList(UserVO userVO) throws Exception{
//		UserVO userVO = new UserVO();
		ModelAndView mv = new ModelAndView();
		

		
		mv.setViewName("report/insa");
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/report/insa1")
	@ResponseBody
	public List<UserVO> getReportList1(UserVO userVO, Model model) throws Exception{
//		UserVO userVO = new UserVO();
		
		log.info("userVO :::: {} " ,userVO);
		ModelAndView mv = new ModelAndView();

		
		
		List<UserVO> ar = reportService.getReportList(userVO);
//		model.addAttribute("reportPager", userVO);
//		log.info("dddd ====>>>> {}", reportPager.getStartNum());
		
		log.info("lstatus ==== > {} " , ar.get(0));
		
//		log.info("depNum :: {} " , ar.get(0).getDepartmentVO().getDepName());

		
		return ar;
	}
	
	
	//κΆνμμ  νμ΄μ§ GET
//	@RequestMapping(method = RequestMethod.GET, value="/report/insa")
//	public ModelAndView setLstatusUpdate(UserVO userVO, ReportVO reportVO, ModelAndView mv) throws Exception{
//		
//		
//		
//		
//		userVO.setId(2209);						//λ‘κ·ΈμΈμ κ°μ ν΄μ μμ΄λλ₯Ό μΈνν΄μ€
//		userVO.setDepnum(userVO.getDepnum());	//jsp λλ‘­λ€μ΄μμ λΆμλ₯Ό κ³ λ₯΄λ©΄ κ·Έμ ν΄λΉνλ depnumμ΄ μΈνλ¨
//		userVO.setRolenum(userVO.getRolenum());	//jsp λλ‘­λ€μ΄μμ μ§κΈμ κ³ λ₯΄λ©΄ κ·Έμ ν΄λΉνλ rolenumμ΄ μΈνλ¨
//		reportVO.setLstatus(2);	
//		
//		mv.addObject("userVO", userVO);
//		mv.setViewName("report/insa");
//		
//		
//		return mv;
//	}
//	
	//κΆνμμ  νμ΄μ§ POST
	@RequestMapping(value = "/report/insa", method = RequestMethod.POST)
	@ResponseBody
	public int setLstatusUpdate(String depNum, ReportVO reportVO, UserVO userVO, Model model,HttpServletRequest request , RedirectAttributes redirectAttributes) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		

//		int result1 = reportService.setLicenserAdd(userVO);

		int count = reportService.getGrantorCount(reportVO);
		int result = 0;
		log.info("count :: {} ",count);
		
		if(count == 0) {				//λΆμ¬λ₯Ό λλ₯΄λ €λ λΆμμ μΉμΈμκ° μ΄λ―Έ μλ€λ©΄ 
			result = reportService.setLstatusUpdate(reportVO, userVO);
		}else{						//λΆμ¬λ₯Ό λλ₯΄λ €λ λΆμμ μΉμΈμκ° μλ€λ©΄
			log.info("μ΄μ§μλΌμ§");
			
//			model.addAttribute("msg", "μ΄λ―Έ μΉμΈμκ° μμ΅λλ€.");
//			model.addAttribute("url", "/");
			
			//request.setAttribute("msg", "μ΄λ―Έ μΉμΈμκ° μμ΅λλ€");
			
			mv.addObject("msg", "μ΄λ―Έ μΉμΈμκ° μμ΅λλ€");
			mv.addObject("url", "/");
			mv.setViewName("/report/alert");
			
			log.info("μ΄μ§μκΏκΏ");
			
			//mv.setViewName("/report/alert");
			return count;
			
		}
		
		
		
//		model.addAttribute("result", result);
		mv.addObject("count", count);
//		mv.addObject("result", result);
//		mv.addObject("result1", result1);
//		mv.addObject("UserVO", userVO);
//		mv.addObject("ReportVO", reportVO);
//		
		mv.setViewName("report/insa");
		
		return count;
	}
	
	
	
	//μΉμΈκΆνμ κ°μ§μ¬λ λͺ¨λ λ³΄λ λ¦¬μ€νΈ λͺ©λ‘
	@RequestMapping(value = "/report/licenserList", method = RequestMethod.GET)
	public ModelAndView getLicenserList(ReportVO reportVO, UserVO userVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<ReportVO> ar = reportService.getLicenserList(reportVO);
//		
//		log.info("roleName :: {} ", ar.get(ar.).getRoleVO().getRoleName());
		
//		String str = ar.get(ar.size()).getRoleVO().getRoleName();
		
		// μλ₯Όλ€μ΄ ROLE_ADMIN => ROLE_μ νμ±ν΄μ ADMINλ§ μΆμΆ
//		for(ReportVO str: ar) {
//			str.getRoleVO().setRoleName(str.getRoleVO().getRoleName().substring(5));
//			log.info("auth {} " ,str.getRoleVO().getRoleName().substring(5));
//		}
		
		
		
		log.info("λ΄ μΉμΈμ νμ΄λΈ μμ΄λ :: {} " , reportVO.getId());
//		log.info("λ΄ μΉμΈμ νμ΄λΈ μ΄λ¦ :: {} " , reportVO.getUserVO().getName());
		
		mv.addObject("list", ar);
		mv.setViewName("/report/licenserList");
		
		return mv;
	}
	
	
	//μΉμΈμ νμ΄λΈμμ κΆνμ λ€μ νμνκΈ° μν΄ lstatus == 0μΌλ‘ λ§λ¬
	@RequestMapping(value = "/report/deleteLicenser", method = RequestMethod.POST)
	@ResponseBody
	public int setLicenserUpdate(String depNum, String id, UserVO userVO, ReportVO reportVO, Model model) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		ReportVO reportVO2 = new ReportVO();
		
		int count = reportService.getGrantorCount(reportVO);
		int result = 0;
		log.info("count :: {} ",count);
		
		reportVO2 = reportService.getLicenserId(reportVO);
		if(reportVO2 != null) {	//μΉμΈμλΌλ©΄
			
			result = reportService.setLicenserUpdate(userVO);
		}else{						//λΆμ¬λ₯Ό λλ₯΄λ €λ λΆμμ μΉμΈμκ° μλ€λ©΄
			
			}
		
//		if(count >= 1) {				//λΆμ¬λ₯Ό λλ₯΄λ €λ λΆμμ μΉμΈμκ° μ΄λ―Έ μλ€λ©΄ μ νν μ¬λμ lstatus == 0μΌλ‘ μλ°μ΄νΈ
//		}else if(count == 0){						//λΆμ¬λ₯Ό λλ₯΄λ €λ λΆμμ μΉμΈμκ° μλ€λ©΄
//			log.info("μ΄μ§μλΌμ§");
//			
//			log.info("μ΄μ§μκΏκΏ");
//			
//			//mv.setViewName("/report/alert");
//			return count;
//		}
		
		model.addAttribute("result", result);
		mv.addObject("reportVO2", reportVO2);
//		mv.addObject("count", count);
		
		return result;
	}
	
	
	//μ μ²­μ μμ₯μμ κ²°μ¬λ°μ μ¬λ λμ€κ² νκΈ°
//	@RequestMapping(method = RequestMethod.GET, value = "/report/reportMyPage")
//	@ResponseBody
//	public ModelAndView getReportMyPage(UserVO userVO, Principal principal, HttpSession session, ReportVO reportVO) throws Exception{
//		
//		ModelAndView mv = new ModelAndView();
//		
//		log.info("λ§΄λ² λ§μ΄νμ΄μ§ μ λ³΄ =====>>>> {} ", userVO);
//		log.info("νλ¦°μν ::: {} " ,principal);
//		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
//	    Authentication authentication = context.getAuthentication();
//	    userVO  =(UserVO)authentication.getPrincipal();
//		
//	    int id = Integer.parseInt(principal.getName());
//	    
//	    
//	    reportVO.setId(id);
//	    reportVO.setDepNum(userVO.getDepNum());
//	    
//	    
//	    ReportVO reportVO2 = new ReportVO();
//	    ReportVO reportVO3 = new ReportVO();
//	    reportVO = reportMapper.getFirstList(reportVO);
//	    
//	    
//	    reportVO2 = reportMapper.getlastlist(reportVO2);
//	    
//	    log.info("prpr :: {} " ,id);
//	    log.info("λλλλ ::: {} " ,userVO.getDepNum());
////	    log.info("λλ€μ ::: {} " , userVO.getDepartmentVO().getDepName());
//	    
//		UserVO userVO2= new UserVO();
//		userVO2 = userService.getMypage(userVO);
//		log.info("λ‘κ·ΈμΈν μμ μ λ§μ΄νμ΄μ§ :: {} " , userVO2);
////		log.info("λ΄ λ‘€λ€μ ::: {} " ,userVO2.getRoleVO().getRoleName());
////		log.info("1111111 :: {}" ,reportVO.getDepName());
////		log.info("222222::: {}" , reportVO2.getDepName());
////		log.info("333333:: {} ", reportVO2.getDepartmentVO().getDepName());
////		log.info("44444 :: {} " ,reportVO.getDepartmentVO().getDepName());
//		
////		log.info(reportVO.getDepName()); 
//
//		if(userVO2.getDepNum() == reportVO.getUserVO().getDepNum() && userVO.getRoleNum() == reportVO.getRoleNum()) {
//			log.info("μ‘°κ±΄λ§μ‘±");
//			reportMapper.getFirstList(reportVO3);
//			reportMapper.getlastlist(reportVO3);
//		}
//		
//		
//
//		mv.addObject("reportVO", reportVO);
//		mv.addObject("reportVO2", reportVO2);
//		mv.addObject("reportVO3", reportVO3);
//		mv.setViewName("/report/reportMyPage");
//		
//		return mv;
//	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/report/reportMyPage")
	   public ModelAndView getFirstList(String depNum, Principal principal, UserVO userVO, ReportVO reportVO, HttpSession session) throws Exception{
	      
	      ModelAndView mv = new ModelAndView();
	      SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	       Authentication authentication = context.getAuthentication();
	       userVO  = (UserVO)authentication.getPrincipal();
	      
	       int id = Integer.parseInt(principal.getName());
	       userVO.setId(id);
	       
	       log.info("λ°μ‘λ ::: {} " , userVO.getDepNum());
	       UserVO userVO2 = new UserVO();
	       UserVO userVO3 = new UserVO();
	       
	       
	      userVO = reportMapper.getFirstList(userVO);   //λμ μ²«λ²μ§Έ μΉμΈμ λ¦¬μ€νΈλ₯Ό λμ
	      userVO2 = reportMapper.getlastlist(userVO);   //λμ μ΅μ’μΉμΈμ λ¦¬μ€νΈλ₯Ό λμ
	      userVO3 = userService.getMypage(userVO3);   // λ§μ΄νμ΄μ§λ₯Ό λΆλ¬μ νμμ λ³΄μ λͺ¨λ κ²μ userVO3μ κ°μ Έμ΄
	      
//	      log.info("ID ::::: {} " , userVO.getId());
//	      log.info("depNum :::::: {} " , userVO.getDepNum());
	      
	      
	      mv.addObject("userVO", userVO);
	      mv.addObject("userVO2", userVO2);
	      mv.addObject("userVO3", userVO3);
	      
	      
	      return mv;
	   }
	
	
	
	
	
	
	
	
	
	//================================================
	
	
	//=======================κ²°μ¬μ μ²­ insert===================
	
	
	@PostMapping("/report/addvaca")
	public String setAddVaca(String depNum, ReportVacaVO reportVacaVO, Principal principal) throws Exception{
		
		int result = reportService.setAddVaca(reportVacaVO);
		
		return "redirect:/report/mylist?cat=2";
	} 
	
	@PostMapping("/report/addwork")
	public String setAddWork(ReportWorkVO reportWorkVO, Principal principal) throws Exception{
		
		int result = reportService.setAddWork(reportWorkVO);
		
		return "redirect:/report/mylist?cat=3";
	}
	
	@PostMapping("/report/addpay")
	public String setAddPay(ReportPayVO reportPayVO, Principal principal) throws Exception{
		
		int result = reportService.setAddPay(reportPayVO);
		
		return "redirect:/report/mylist?cat=1";
	}
	
	@PostMapping("/report/addsorry")
	public String setAddSorry(ReportSorryVO reportSorryVO, Principal principal) throws Exception{

		
		int result = reportService.setAddSorry(reportSorryVO);
		
		return "redirect:/report/mylist?cat=4";
	}
	
	
	//================================================
	
	
	//=======================μ΅κ·ΌνΈ===================

	@PostMapping("/report/updateapply")
	@ResponseBody
	public int setUpdateApply(ReportApplyVO reportApplyVO) throws Exception{
		log.info("mmmmmmmmmmmmmmmmmmmmmmmmmmmmm:{}",reportApplyVO.getApplyNum());
		log.info("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn:{}",reportApplyVO.getLstatus());
		int result = reportService.setUpdateApply(reportApplyVO);
		return result;
	}
	
	@PostMapping("/report/updatecancelapply")
	@ResponseBody
	public int setUpdateCancelApply(ReportApplyVO reportApplyVO) throws Exception{
		int result = reportService.setUpdateCancelApply(reportApplyVO);
		return result;
	}
	
	@GetMapping("/report/vacadetail")
	public ModelAndView getLicenseVacaReportDetail(ReportVacaVO reportVacaVO,String lstatus) throws Exception{
		ModelAndView mv = new ModelAndView();
		reportVacaVO = reportService.getLicenseVacaReportDetail(reportVacaVO);
		Date date = reportVacaVO.getDate();
		String a = date.toString();
		String[] b = a.split("-");
		String year = b[0];
		String month = b[1];
		String day = b[2];
		
		
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("reportVacaVO", reportVacaVO);
		mv.addObject("result", lstatus);
		return mv;
	}
	
	@GetMapping("/report/workdetail")
	public ModelAndView getLicenseWorkReportDetail(ReportWorkVO reportWorkVO,String lstatus) throws Exception{
		ModelAndView mv = new ModelAndView();
		reportWorkVO = reportService.getLicenseWorkReportDetail(reportWorkVO);
		Date date = reportWorkVO.getDate();
		String a = date.toString();
		String[] b = a.split("-");
		String year = b[0];
		String month = b[1];
		String day = b[2];
		
		
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("reportWorkVO", reportWorkVO);
		mv.addObject("result", lstatus);
		return mv;
	}
	
	@GetMapping("/report/paydetail")
	public ModelAndView getLicensePayReportDetail(ReportPayVO reportPayVO,String lstatus) throws Exception{
		ModelAndView mv = new ModelAndView();
		reportPayVO = reportService.getLicensePayReportDetail(reportPayVO);
		List<RepriceVO> rePriceVOs = reportPayVO.getRepriceVOs();
		Date date = reportPayVO.getDate();
		String a = date.toString();
		String[] b = a.split("-");
		String year = b[0];
		String month = b[1];
		String day = b[2];
		
		
		mv.addObject("rePriceVOs", rePriceVOs);	
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("reportPayVO", reportPayVO);
		mv.addObject("result", lstatus);
		return mv;
	}
	
	@GetMapping("/report/sorrydetail")
	public ModelAndView getLicenseSorryReportDetail(ReportSorryVO reportSorryVO,String lstatus) throws Exception{
		ModelAndView mv = new ModelAndView();
		reportSorryVO = reportService.getLicenseSorryReportDetail(reportSorryVO);
		Date date = reportSorryVO.getDate();
		String a = date.toString();
		String[] b = a.split("-");
		String year = b[0];
		String month = b[1];
		String day = b[2];
		
		
								
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("reportSorryVO", reportSorryVO);
		mv.addObject("result", lstatus);
		return mv;
	}
	
	
	@GetMapping("/report/doreport")
	public ModelAndView getDoReport(Principal principal,String cat,ReportPager reportPager) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(principal == null) {
			mv.setViewName("/user/login");
			return mv;
		}
		String id = principal.getName();
		int num = Integer.parseInt(id);
		int category = 1;
		if(cat != null) {
			category = Integer.parseInt(cat);
		}
		if(reportPager.getKind() == "") {
			reportPager.setKind("1"); 
		}
		
		ReportVO reportVO = new ReportVO();
		reportPager.setId(num);
		reportVO.setId(num);
		reportPager.setReportNum(category);
		mv.addObject("cat", category);
		Integer check = reportService.getLicenseCheck(reportVO);
		log.info("ffffffffffffffffffffffffffffffff:{}",check);
		log.info("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn:{}",reportPager.getKind());
		if(check == 0) {
			String message = "μΉμΈμλ§ λ³Όμ μμ΅λλ€.";
			String url = "/";
			mv.addObject("message", message);
			mv.addObject("url", url);
			mv.setViewName("report/redirect");
			return mv;
	}
	else if (check == 2){
			if(reportPager.getKind().equals("2")) {
				log.info("dddddddddddddddddddddddd");
				log.info("ppppppppppppppppppppppppppppppppp:{}",reportPager.getReportNum());
				reportVO = reportService.getFinishReport(reportPager);
				if(reportVO != null) {
					List<ReportApplyVO> reportApplyVOs = reportVO.getReportApplyVOs();
					mv.addObject("reportApplyVOs", reportApplyVOs);		
				}
				mv.addObject("pager", reportPager);
				mv.setViewName("report/doreport");
				return mv;
			}
			else if(reportPager.getKind().equals("3")) {						
				reportVO = reportService.getReturnReport(reportPager);
				if(reportVO != null) {
					List<ReportApplyVO> reportApplyVOs = reportVO.getReportApplyVOs();
					mv.addObject("reportApplyVOs", reportApplyVOs);					
				}
				mv.addObject("pager", reportPager);
				mv.setViewName("report/doreport");
				return mv;
			}
			else {
				log.info("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
				reportVO = reportService.getDoFirstReport(reportPager);
				if(reportVO != null) {

					List<ReportApplyVO> reportApplyVOs = reportVO.getReportApplyVOs();
					mv.addObject("reportApplyVOs", reportApplyVOs);					
				}
				mv.addObject("reportVO",reportVO);
				mv.addObject("pager", reportPager);
				mv.setViewName("report/doreport");
				return mv;

				

			}
	}
		else {
			log.info("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
			if(reportPager.getKind().equals("2")) {
				List<ReportApplyVO> reportApplyVOs = reportService.getAdminFinishReport(reportPager);
				mv.addObject("reportApplyVOs", reportApplyVOs);					
				mv.addObject("pager", reportPager);
				mv.setViewName("report/adminreport");
				return mv;
			}
			else if(reportPager.getKind().equals("3")) {						
				List<ReportApplyVO> reportApplyVOs = reportService.getAdminReturnReport(reportPager);
				mv.addObject("reportApplyVOs", reportApplyVOs);					
				mv.addObject("pager", reportPager);
				mv.setViewName("report/adminreport");
				return mv;
			}
			else {
				log.info("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"); 
				List<ReportApplyVO> reportApplyVOs = reportService.getDoFinalReport(reportPager);
				mv.addObject("reportApplyVOs", reportApplyVOs);			
				mv.addObject("pager", reportPager);
				mv.addObject("check", check);
				mv.setViewName("report/adminreport");
				return mv;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	//================================================
	
	//=======================λ₯νλ―Ό===================
	
	@GetMapping("/report/mylist")
	public ModelAndView getMyReportList(ModelAndView mv,String cat,ReportPager pager, Principal principal,HttpSession session) throws Exception{
		
		if(principal == null) {
			mv.setViewName("user/login");
			return mv;
		}
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    UserVO userVO  = (UserVO)authentication.getPrincipal();
	      
	    int id = Integer.parseInt(principal.getName());
	    userVO.setId(id);
	    
	    userVO = reportMapper.getFirstList(userVO);
	    mv.addObject("first", userVO);
		userVO = reportMapper.getlastlist(userVO);
	    mv.addObject("second", userVO);
		
		pager.setId(Integer.parseInt(principal.getName()));
		
		if(cat.equals("1")){
			List<ReportPayVO> list = reportService.getMyPayList(pager);
			mv.addObject("list", list);
		}else if(cat.equals("2")){
			List<ReportVacaVO> list = reportService.getMyVacaList(pager);
			
			for(int i = 0; i < list.size(); i++) {
				String startDate = list.get(i).getStartDate();
				startDate = startDate.substring(0, startDate.lastIndexOf("T"));
				list.get(i).setStartDate(startDate);
				
				String endDate = list.get(i).getEndDate();
				endDate = endDate.substring(0, endDate.lastIndexOf("T"));
				list.get(i).setEndDate(endDate);
			}
			
			mv.addObject("list", list);
		}else if(cat.equals("3")) {
			List<ReportWorkVO> list = reportService.getMyWorkList(pager);
			mv.addObject("list", list);
		}else if(cat.equals("4")) {
			List<ReportSorryVO> list = reportService.getMySorryList(pager);
			mv.addObject("list", list);
		}else {
			List<ReportVacaVO> list = reportService.getMyVacaList(pager);
			mv.addObject("list", list);
		}
		
		
		mv.addObject("pager", pager);
		mv.addObject("cat", cat);
		mv.setViewName("report/mylist");
		
		return mv;
		
	}
	
	@GetMapping("/report/detail")
	public ModelAndView getMyReportDetail(ModelAndView mv,ReportApplyVO reportApplyVO, Principal principal) throws Exception{
		
		if(principal == null) {
			mv.setViewName("user/login");
			return mv;
		}
		
		reportApplyVO.setId(Integer.parseInt(principal.getName()));
		
		int result = reportApplyVO.getReportNum();
		
		if(result == 1) {
			ReportVacaVO reportVacaVO = reportService.getMyVacaDetail(reportApplyVO);
			Date date = reportVacaVO.getDate();
			String a = date.toString();
            String[] b = a.split("-");
            String year = b[0];
            String month = b[1];
            String day = b[2];         
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("day", day);
			
			mv.addObject("vo", reportVacaVO);
		}else if(result == 2) {
			ReportWorkVO reportWorkVO = reportService.getMyWorkDetail(reportApplyVO);
			Date date = reportWorkVO.getDate();
			String a = date.toString();
            String[] b = a.split("-");
            String year = b[0];
            String month = b[1];
            String day = b[2];         
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("day", day);
			
			mv.addObject("vo", reportWorkVO);
		}else if(result == 3) {
			ReportPayVO reportPayVO = reportService.getMyPayDetail(reportApplyVO);
			log.info("λͺ©λ‘ : {}", reportPayVO.getRepriceVOs());
			Date date = reportPayVO.getDate();
			String a = date.toString();
            String[] b = a.split("-");
            String year = b[0];
            String month = b[1];
            String day = b[2];         
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("day", day);
			
			
			mv.addObject("vo", reportPayVO);
		}else if(result == 4) {
			ReportSorryVO reportSorryVO = reportService.getMySorryDetail(reportApplyVO);
			Date date = reportSorryVO.getDate();
			String a = date.toString();
            String[] b = a.split("-");
            String year = b[0];
            String month = b[1];
            String day = b[2];         
            mv.addObject("year", year);
            mv.addObject("month", month);
            mv.addObject("day", day);
			
			
			mv.addObject("vo", reportSorryVO);
		}
		
		mv.setViewName("report/mydetail");
		
		return mv;
		
	}
	
	
	//================================================
	
}
