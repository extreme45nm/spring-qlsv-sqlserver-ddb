package edu.java.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.java.spring.dao.KhoaDAO;
import edu.java.spring.model.ChuyenKhoa;
import edu.java.spring.model.Khoa;

@Controller
public class KhoaController {
		
		@Autowired
		KhoaDAO khoaDao;
		
		@RequestMapping(value="/khoa/delete/{makhoa}",method=RequestMethod.GET)
		public String delKhoa(@PathVariable String makhoa){
			khoaDao.deleteKhoa(makhoa);
			return "redirect:/khoa/list";
		}
		
		@RequestMapping(value="/khoa/edit/{makhoa}",method=RequestMethod.GET)
		public ModelAndView editKhoa(@PathVariable String makhoa){
			
			Khoa khoa = khoaDao.loadKhoa(makhoa);
			ModelAndView model = new ModelAndView("../KhoaForm","command",khoa);
			model.getModelMap().put("mack", khoa.getMakhoa());
			model.getModelMap().put("tenck", khoa.getTenkhoa());
			model.getModelMap().put("malanhdaokhoa", khoa.getMalanhdaokhoa());
			return model;		
		}
		
		@RequestMapping(value="/khoa/view/{makhoa}",method=RequestMethod.GET)
		public String viewKhoa(@PathVariable String makhoa,ModelMap model){
			
			Khoa khoa = khoaDao.loadKhoa(makhoa);
			model.put("mack", khoa.getMakhoa());
			model.put("tenck", khoa.getTenkhoa());
			model.put("malanhdaokhoa", khoa.getMalanhdaokhoa());
			return "../KhoaView";
		}
		
		@RequestMapping(value="/khoa/list",method=RequestMethod.GET)
		public ModelAndView listKhoa(@RequestParam(value="q",required=false)String query){
			
			ModelAndView model = new ModelAndView();
			if(query==null){
				model.setViewName("KhoaList");
				model.addObject("khoas", khoaDao.listKhoa());
			}else{
				model.setViewName("KhoaList");
				List<Khoa> danhSach= khoaDao.selectKhoaByName(query);
				model.addObject("khoas",danhSach);
			}
			return model;
		}
		
		@RequestMapping(value="/khoa/add",method=RequestMethod.POST)
		public ModelAndView addKhoa(@Valid
					@ModelAttribute("command")Khoa khoa,BindingResult result){
			
			ModelAndView model = null;
			if(result.hasErrors()){
				model = new ModelAndView("KhoaForm","command",khoa);
				model.addObject("errors", result);
				return model;
			}
//			if(khoa.getMakhoa()!=null){
//				khoaDao.updateKhoa(khoa);
//				model = new ModelAndView("redirect:/khoa/form");
//				return model;
//			}else{
				khoaDao.insert(khoa);	
//			}
			model = new ModelAndView("redirect:/khoa/list");
			return model;
		}
		
		@RequestMapping(value="/khoa/form",method=RequestMethod.GET)
		public ModelAndView khoa(){
			return new ModelAndView("KhoaForm","command",new Khoa());
		}
}
