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

import edu.java.spring.dao.MonHocDAO;
import edu.java.spring.model.MonHoc;

@Controller
public class MonHocController {
	
	@Autowired
	private MonHocDAO monHocDAO;
	
	@RequestMapping(value="/monhoc/list",method=RequestMethod.GET)
	public ModelAndView listMonHoc(
			@RequestParam(value="q",required=false)String query){
		System.out.println("COntroller.listMonHoc() invoked");
		ModelAndView model = new ModelAndView(); 
		if(query!=null||query==""){
			model.setViewName("MonHocList");			
			List<MonHoc> danhSachMH = monHocDAO.selectMonHocByName(query);
			model.addObject("danhSachMonHoc",danhSachMH);
		}else{
			model.setViewName("MonHocList");
			model.addObject("danhSachMonHoc",monHocDAO.listMonHoc());
		}
		return model;
	}
	
	@RequestMapping(value="/monhoc/delete/{mamon}",method=RequestMethod.GET)
	public String delMonHoc(@PathVariable Integer mamon){
		
		System.out.println("Controller.delMonHoc() invoked!!!\n");
		monHocDAO.deleteMonHoc(mamon);
		return "redirect:/monhoc/list";
	}
	
	@RequestMapping(value="/monhoc/edit/{mamon}",method=RequestMethod.GET)
	public ModelAndView editMonHoc(@PathVariable int mamon){
		
		System.out.println("Controller.editMonHoc() invoked");
		MonHoc mh = monHocDAO.loadMonHoc(mamon);
		ModelAndView model = new ModelAndView("../MonHocForm","command",mh);
		model.getModelMap().put("mamon", mh.getMamon());
		model.getModelMap().put("tenmon", mh.getTenmon());
		model.getModelMap().put("sotc",mh.getSotc());
		model.getModelMap().put("giangvienchinh", mh.getGiangvienchinh());
		return model;
	}
	
	@RequestMapping(value="/monhoc/view/{mamon}")
	public String loadMonHoc(@PathVariable Integer mamon,ModelMap model){
		
		MonHoc mh = monHocDAO.loadMonHoc(mamon);
		model.put("mamon", mh.getMamon());
		model.put("tenmon", mh.getTenmon());
		model.put("sotc", mh.getSotc());
		model.put("giangvienchinh", mh.getGiangvienchinh());
		return "MonHocView";
	}
	
//	@RequestMapping(value="/sinhVien/list",method=RequestMethod.GET)
//	public ModelAndView listStudent(){
//		
//		ModelAndView model = new ModelAndView("StudentList");
//		model.addObject("students", monHocDAO.listStudents());
//		return model;		
//	}
	
	@RequestMapping(value="/monhoc/form",method=RequestMethod.GET)
	public ModelAndView monHoc(){
		return new ModelAndView("MonHocForm","command",new MonHoc());
	}
	
	@RequestMapping(value="/monhoc/add",method=RequestMethod.POST)
	public ModelAndView addMonHoc(@Valid
			@ModelAttribute("command")MonHoc mh,BindingResult rel){
		
		System.out.println("Controller.addMonHoc() invoked!!!\n");
		ModelAndView model = null;
		System.out.println("Controller.addMonHoc() invoked!!");
		if(rel.hasErrors()){
			model = new ModelAndView("MonHocForm","command",mh);
			model.addObject("errors",rel);
			return model;
		}
		if(mh.getMamon()>0){
			System.out.println("\n\n edit sinhVien "+mh.getMamon()+"\n\n");
			monHocDAO.updateMonHoc(mh);
			model = new ModelAndView("redirect:/monhoc/form");
			return model;
		}else{
			monHocDAO.insert(mh);
		}
//		model = new ModelAndView("StudentView");
//		model.getModelMap().put("id",sinhVien.getMasv());
//		model.getModelMap().put("name", sinhVien.getName());
//		model.getModelMap().put("age", sinhVien.getAge());
//		model.getModelMap().put("address", sinhVien.getAddress());
//		model.getModelMap().put("point", sinhVien.getPoint());
//		model.addAttribute("id", sinhVien.getMasv());
//		model.addAttribute("name",sinhVien.getName());
//		model.addAttribute("age", sinhVien.getAge());
//		model.addAttribute("address", sinhVien.getAddress());
//		model.addAttribute("point", sinhVien.getPoint());
		model = new ModelAndView("redirect:/monhoc/list");
		return model;
	}
}
