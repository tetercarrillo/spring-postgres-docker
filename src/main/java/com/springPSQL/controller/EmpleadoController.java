/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springPSQL.controller;


import com.springPSQL.model.Empleado;
import com.springPSQL.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Teter
 */

@Controller

public class EmpleadoController {
    
    @Autowired
    EmpleadoRepository empleadoRepository;
    
    @RequestMapping(value = "/empleados", method = RequestMethod.GET)
    public String showAllPersonas(Model model) {
        
        
        model.addAttribute("empleados",empleadoRepository.findAll());
        
        
        return "empleados/list";
        
    
    }
    
    @RequestMapping("/new")
    public String newEmpleado(Model model) {
  
        Empleado empleado = new Empleado();
        
        model.addAttribute("empleadoForm",empleado);

        return "empleados/form";

      
        
   
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmpleado(Empleado empleadoForm, BindingResult bindingResult,
                    Model model, RedirectAttributes redirectAttributes) {
  
        Empleado empleado = new Empleado();
        
        empleado.setNombre(empleadoForm.getNombre().trim());
        empleado.setEmail(empleadoForm.getEmail().trim());
        
        empleadoRepository.save(empleado);
        

        return "redirect:/show/" + empleado.getId_empleado();
    }
    
    @RequestMapping(value = "/show/{id_empleado}", method = RequestMethod.GET)
    public String showEmplado(@PathVariable("id_empleado") long id_empleado, Model model) {

        Empleado empleado = empleadoRepository.findOne(id_empleado);
        
        model.addAttribute("empleadoShow", empleado);
        
        return "empleados/show"; 
    }
   
    @RequestMapping(value = "/edit/{id_empleado}", method = RequestMethod.GET)
    public String showUpdateEmpleadoForm(@PathVariable("id_empleado") long id_empleado, Model model) {
        
        Empleado empleado = empleadoRepository.findOne(id_empleado);
        
        model.addAttribute("empleadoForm", empleado);
        
        return "empleados/updateForm";
        
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEmpleado(Empleado empleadoForm, BindingResult bindingResult,
                    Model model, RedirectAttributes redirectAttributes) {
  
        Empleado empleado = empleadoRepository.findOne(empleadoForm.getId_empleado());
        
        empleado.setNombre(empleadoForm.getNombre().trim());
        empleado.setEmail(empleadoForm.getEmail().trim());
        
        empleadoRepository.save(empleado);
        

        return "redirect:/show/" + empleado.getId_empleado();
    }
}
