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

import edu.java.spring.dao.ChuyenKhoaDAO;
import edu.java.spring.model.ChuyenKhoa;

@Controller
public class ChuyenKhoaController {
	
	@Autowired
	ChuyenKhoaDAO ckDAO;
	
	@RequestMapping(value="/chuyenkhoa/delete/{mack}",method=RequestMethod.GET)
	public String delChuyenKhoa(@PathVariable int mack){
		ckDAO.deleteChuyenKhoa(mack);
		return "redirect:/chuyenkhoa/list";
	}
	
	@RequestMapping(value="/chuyenkhoa/edit/{mack}",method=RequestMethod.GET)
	public ModelAndView editChuyenKhoa(@PathVariable int mack){
		
		ChuyenKhoa ck = ckDAO.loadChuyenKhoa(mack);
		ModelAndView model = new ModelAndView("../ChuyenKhoaForm","command",ck);
		model.getModelMap().put("mack", ck.getMack());
		model.getModelMap().put("tenck", ck.getTenck());
		model.getModelMap().put("lanhdaock", ck.getLanhdaock());
		return model;		
	}
	
	@RequestMapping(value="/chuyenkhoa/view/{mack}",method=RequestMethod.GET)
	public String viewChuyenKhoa(@PathVariable int mack,ModelMap model){
		
		ChuyenKhoa ck = ckDAO.loadChuyenKhoa(mack);
		model.put("mack", ck.getMack());
		model.put("tenck", ck.getTenck());
		model.put("lanhdaock", ck.getLanhdaock());
		return "ChuyenKhoaView";
	}
	
	@RequestMapping(value="/chuyenkhoa/list",method=RequestMethod.GET)
	public ModelAndView listChuyenKhoa(@RequestParam(value="q",required=false)String query){
		
		ModelAndView model = new ModelAndView();
		if(query==null){
			model.setViewName("ChuyenKhoaList");
			model.addObject("chuyenkhoas", ckDAO.listChuyenKhoa());
		}else{
			model.setViewName("ChuyenKhoaList");
			List<ChuyenKhoa> danhSach= ckDAO.selectChuyenKhoaByName(query);
			model.addObject("chuyenkhoas",danhSach);
		}
		return model;
	}
	
	@RequestMapping(value="/chuyenkhoa/add",method=RequestMethod.POST)
	public ModelAndView addChuyenKhoa(@Valid
				@ModelAttribute("command")ChuyenKhoa ck, BindingResult result){
		
		ModelAndView model = null;
		if(result.hasErrors()){
			model= new ModelAndView("ChuyenKhoaForm","command",ck);
			model.addObject("errors",result);
			return model;
		}
		if(ck.getMack()>0){
			
			ckDAO.insert(ck);
//			ckDAO.updateChuyenKhoa(ck);
//			model = new ModelAndView("redirect:/chuyenkhoa/list");
//			return model;
		}else{
			ckDAO.insert(ck);
		}
		model = new ModelAndView("redirect:/chuyenkhoa/list");
		return model;
	}
	
	@RequestMapping(value="/chuyenkhoa/form",method=RequestMethod.GET)
	public ModelAndView chuyenKhoa(){
		return new ModelAndView("ChuyenKhoaForm","command",new ChuyenKhoa());
	}
			
}
