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

import edu.java.spring.dao.GiangVienDAO;
import edu.java.spring.model.GiangVien;

@Controller
public class GiangVienController {

	@Autowired
	GiangVienDAO gvDAO;
	
	@RequestMapping(value="/giangvien/delete/{magv}",method=RequestMethod.GET)
	public String delGiangVien(@PathVariable int magv){
		gvDAO.deleteGiangVien(magv);
		return "redirect:/giangvien/list";
	}
	
	@RequestMapping(value="/giangvien/edit/{magv}",method=RequestMethod.GET)
	public ModelAndView editGiangVien(@PathVariable int magv){
		
		GiangVien gv = gvDAO.loadGiangVien(magv);
		ModelAndView model = new ModelAndView("../GiangVienForm","command",gv);
		model.getModelMap().put("magv", gv.getMagv());
		model.getModelMap().put("makhoa", gv.getMakhoa());
		model.getModelMap().put("tengv", gv.getTengv());
		model.getModelMap().put("tdhv", gv.getTdhv());
		model.getModelMap().put("ns", gv.getNs());
		model.getModelMap().put("capham",gv.getCapham());
		return model;		
	}
	
	@RequestMapping(value="/giangvien/view/{magv}",method=RequestMethod.GET)
	public String viewGiangVien(@PathVariable int magv,ModelMap model){
		
		GiangVien gv = gvDAO.loadGiangVien(magv);
		model.put("magv", gv.getMagv());
		model.put("makhoa", gv.getMakhoa());
		model.put("tengv", gv.getTengv());
		model.put("tdhv", gv.getTdhv());
		model.put("ns", gv.getNs());
		model.put("capham",gv.getCapham());
		return "GiangVienView";
	}
	
	@RequestMapping(value="/giangvien/list",method=RequestMethod.GET)
	public ModelAndView listGiangVien(@RequestParam(value="q",required=false)String query){
		
		ModelAndView model = new ModelAndView();
		if(query==null){
			model.setViewName("GiangVienList");
			model.addObject("giangviens", gvDAO.listGiangVien());
		}else{
			model.setViewName("GiangVienList");
			List<GiangVien> danhSach= gvDAO.selectGiangVienByName(query);
			model.addObject("giangviens",danhSach);
		}
		return model;
	}
	
	@RequestMapping(value="/giangvien/add",method=RequestMethod.POST)
	public ModelAndView addGiangVien(@Valid
				@ModelAttribute("command")GiangVien gv, BindingResult result){
		
		ModelAndView model = null;
		if(result.hasErrors()){
			model= new ModelAndView("GiangVienForm","command",gv);
			model.addObject("errors",result);
			return model;
		}
		if(gv.getMagv()>0){
			gvDAO.updateGiangVien(gv);
			model = new ModelAndView("redirect:/giangvien/list");
			return model;
		}else{
			gvDAO.insert(gv);
		}
		model = new ModelAndView("redirect:/giangvien/list");
		return model;
	}
	
	@RequestMapping(value="/giangvien/form",method=RequestMethod.GET)
	public ModelAndView giangVien(){
		return new ModelAndView("GiangVienForm","command",new GiangVien());
	}
}
