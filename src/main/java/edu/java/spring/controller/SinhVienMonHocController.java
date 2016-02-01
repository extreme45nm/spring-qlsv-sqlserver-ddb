package edu.java.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import edu.java.spring.model.SinhVienMonHoc;
import edu.java.spring.dao.SinhVienMonHocDAO;

@Controller
public class SinhVienMonHocController {
	
	@Autowired
	SinhVienMonHocDAO sinhVienMonHocDAO;

	@RequestMapping(value="/sinhvienmonhoc/delete/{mamon}",method=RequestMethod.GET)
	public String delSVMH(@PathVariable Integer mamon){
		
		System.out.println("Controller.delSVMH() invoked!!!\n");
		sinhVienMonHocDAO.deleteSVMH(mamon);
		return "redirect:/sinhvienmonhoc/list";
	}
	
	@RequestMapping(value="/sinhvienmonhoc/list",method=RequestMethod.GET)
	public ModelAndView listSVMH(){
		
		ModelAndView model = new ModelAndView("SinhVienMonHocList");
		model.addObject("sinhVienMonHocs", sinhVienMonHocDAO.listSVMH());
		return model;		
	}
	
	@RequestMapping(value="/sinhvienmonhoc/add",method=RequestMethod.POST)
	public ModelAndView addSVMH(@Valid
			@ModelAttribute("command")SinhVienMonHoc svmh,BindingResult rel){
		
		System.out.println("Controller.addSVMH() invoked!!!\n");
		ModelAndView model = null;
		System.out.println("Controller.addSVMH() invoked!!");
		if(rel.hasErrors()){
			model = new ModelAndView("SinhVienMonHocForm","command",svmh);
			model.addObject("errors",rel);
			return model;
		}
//		if(svmh.getMamon()>0){
//			System.out.println("\n\n edit sinhVienMonHoc "+svmh.getMasv()+"\n\n");
//			sinhVienMonHocDAO.updateSVMH(svmh);
//			model = new ModelAndView("redirect:/sinhvienmonhoc/form");
//			return model;
//		}else{
			sinhVienMonHocDAO.insert(svmh);
//		}
//		model = new ModelAndView("StudentView");
//		model.getModelMap().put("id",student.getId());
//		model.getModelMap().put("name", student.getName());
//		model.getModelMap().put("age", student.getAge());
//		model.getModelMap().put("address", student.getAddress());
//		model.getModelMap().put("point", student.getPoint());
//		model.addAttribute("id", student.getId());
//		model.addAttribute("name",student.getName());
//		model.addAttribute("age", student.getAge());
//		model.addAttribute("address", student.getAddress());
//		model.addAttribute("point", student.getPoint());
		model = new ModelAndView("redirect:/sinhvienmonhoc/list");
		return model;
	}
	
	@RequestMapping(value="/sinhvienmonhoc/form",method=RequestMethod.GET)
	public ModelAndView sinhVienMonHoc(){
		return new ModelAndView("SinhVienMonHocForm","command",new SinhVienMonHoc());
	}
}
