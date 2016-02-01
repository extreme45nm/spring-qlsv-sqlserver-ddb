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

import edu.java.spring.dao.SinhVienDAO;
import edu.java.spring.model.SinhVien;

@Controller
public class SinhVienController {
	
	@Autowired
	SinhVienDAO sinhVienDAO ;
	
	@RequestMapping(value="/sinhvien/list",method=RequestMethod.GET)
	public ModelAndView listSinhVien(
			@RequestParam(value="q",required=false)String query){
		
		ModelAndView model = new ModelAndView(); 
		if(query!=null||query==""){
			model.setViewName("SinhVienList");			
			List<SinhVien> students = sinhVienDAO.selectSinhVienByName(query);
			model.addObject("students",students);
		}else{
			model.setViewName("SinhVienList");
			model.addObject("sinhviens",sinhVienDAO.listSinhVien());
		}
		return model;
	}
	
	@RequestMapping(value="/sinhvien/delete/{masv}",method=RequestMethod.GET)
	public String delSinhVien(@PathVariable Integer masv){
		
		System.out.println("Controller.delSinhVien() invoked!!!\n");
		sinhVienDAO.deleteSinhVien(masv);
		return "redirect:/sinhvien/list";
	}
	
	@RequestMapping(value="/sinhvien/edit/{masv}",method=RequestMethod.GET)
	public ModelAndView editSinhVien(@PathVariable int masv){
		
		System.out.println("Controller.editStudent() invoked");
		SinhVien sinhVien = sinhVienDAO.loadSinhVien(masv);
		ModelAndView model = new ModelAndView("../SinhVienForm","command",sinhVien);
		model.getModelMap().put("masv", sinhVien.getMasv());
		model.getModelMap().put("chuyenkhoa", sinhVien.getChuyenkhoa());
		model.getModelMap().put("hodem",sinhVien.getHodem());
		model.getModelMap().put("ten",sinhVien.getTen());
		model.getModelMap().put("tuoi",sinhVien.getTuoi());
		model.getModelMap().put("diachi", sinhVien.getDiachi());
		model.getModelMap().put("quequan", sinhVien.getQuequan());
		model.getModelMap().put("chucvu",sinhVien.getChucvu());
		
		return model;
	}
	
	@RequestMapping(value="/sinhvien/view/{masv}")
	public String loadSinhVien(@PathVariable Integer masv,ModelMap model){
		
		SinhVien sinhVien = sinhVienDAO.loadSinhVien(masv);
		model.put("id", sinhVien.getMasv());
		model.put("chuyenkhoa", sinhVien.getChuyenkhoa());
		model.put("hodem", sinhVien.getHodem());
		model.put("ten", sinhVien.getTen());
		model.put("tuoi", sinhVien.getTuoi());
		model.put("diachi", sinhVien.getDiachi());
		model.put("quequan", sinhVien.getQuequan());
		model.put("chucvu", sinhVien.getChucvu());
		return "SinhVienView";
	}
	
//	@RequestMapping(value="/sinhVien/list",method=RequestMethod.GET)
//	public ModelAndView listStudent(){
//		
//		ModelAndView model = new ModelAndView("StudentList");
//		model.addObject("students", sinhVienDAO.listStudents());
//		return model;		
//	}
	
	@RequestMapping(value="/sinhvien/form",method=RequestMethod.GET)
	public ModelAndView sinhVien(){
		return new ModelAndView("SinhVienForm","command",new SinhVien());
	}
	
	@RequestMapping(value="/sinhvien/add",method=RequestMethod.POST)
	public ModelAndView addSinhVien(@Valid
			@ModelAttribute("command")SinhVien sv,BindingResult rel){
		
		System.out.println("Controller.addStudent() invoked!!!\n");
		ModelAndView model = null;
		System.out.println("Controller.addStudent() invoked!!");
		if(rel.hasErrors()){
			model = new ModelAndView("SinhVienForm","command",sv);
			model.addObject("errors",rel);
			return model;
		}
		if(sv.getMasv()>0){
			System.out.println("\n\n edit sinhVien "+sv.getMasv()+"\n\n");
			sinhVienDAO.updateSinhVien(sv);
			model = new ModelAndView("redirect:/sinhvien/form");
			return model;
		}else{
			sinhVienDAO.insert(sv);
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
		model = new ModelAndView("redirect:/sinhvien/list");
		return model;
	}
}
