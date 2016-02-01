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

import edu.java.spring.dao.LopHocDAO;
import edu.java.spring.model.LopHoc;

@Controller
public class LopHocController {

	@Autowired
	LopHocDAO lopHocDAO;
	
	@RequestMapping(value="/lophoc/delete/{malop}",method=RequestMethod.GET)
	public String delLopHoc(@PathVariable int malop){
		lopHocDAO.deleteLopHoc(malop);
		return "redirect:/lophoc/list";
	}
	
	@RequestMapping(value="/lophoc/edit/{malop}",method=RequestMethod.GET)
	public ModelAndView editLopHoc(@PathVariable int malop){
		
		LopHoc lh = lopHocDAO.loadLopHoc(malop);
		ModelAndView model = new ModelAndView("../LopHocForm","command",lh);
		model.getModelMap().put("malh", lh.getMalop());
		model.getModelMap().put("mack", lh.getMack());
		model.getModelMap().put("gvcn", lh.getGvcn());
		model.getModelMap().put("maloptruong", lh.getMaloptruong());
		return model;		
	}
	
	@RequestMapping(value="/lophoc/view/{malh}",method=RequestMethod.GET)
	public String viewLopHoc(@PathVariable int malop,ModelMap model){
		
		LopHoc lh = lopHocDAO.loadLopHoc(malop);
		model.put("malh", lh.getMalop());
		model.put("mack", lh.getMack());
		model.put("gvcn", lh.getGvcn());
		model.put("maloptruong", lh.getMaloptruong());
		return "LopHocView";
	}
	
	@RequestMapping(value="/lophoc/list",method=RequestMethod.GET)
	public ModelAndView listLopHoc(@RequestParam(value="q",required=false)String query){
		
		ModelAndView model = new ModelAndView();
		if(query==null){
			model.setViewName("LopHocList");
			model.addObject("lophocs", lopHocDAO.listLopHoc());
		}else{
			model.setViewName("LopHocList");
			List<LopHoc> danhSach= lopHocDAO.selectLopHocByName(query);
			model.addObject("lophocs",danhSach);
		}
		return model;
	}
	
	@RequestMapping(value="/lophoc/add",method=RequestMethod.POST)
	public ModelAndView addLopHoc(@Valid
				@ModelAttribute("command")LopHoc lh, BindingResult result){
		
		ModelAndView model = null;
		if(result.hasErrors()){
			model= new ModelAndView("LopHocForm","command",lh);
			model.addObject("errors",result);
			return model;
		}
//		if(lh.getMalop()>0){
//			lopHocDAO.updateLopHoc(lh);
//			model = new ModelAndView("redirect:/lophoc/form");
//			return model;
//		}else{
			lopHocDAO.insert(lh);
//		}
		model = new ModelAndView("redirect:/lophoc/list");
		return model;
	}
	
	@RequestMapping(value="/lophoc/form",method=RequestMethod.GET)
	public ModelAndView lopHoc(){
		return new ModelAndView("LopHocForm","command",new LopHoc());
	}
}
