package com.nekixitodev.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nekixitodev.app.model.Invitado;
import com.nekixitodev.app.repository.InvitadoRepository;

@Controller
public class InvitadoController {
	
	@Autowired
	private InvitadoRepository invitadoRepository;

	@GetMapping({"/",""})
	public String verPaginaInicio(Model model) {
		List<Invitado> invitados = invitadoRepository.findAll();
		model.addAttribute("invitados", invitados);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioRegistro(Model modelo) {
		modelo.addAttribute("invitado", new Invitado());
		return "nuevo";
	}
	
	@PostMapping("/nuevo")
	public String guardarInvitado(@Validated Invitado invitado,BindingResult bindingResult,RedirectAttributes redirect, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("invitado", invitado);
			return "nuevo";
		}
		
		ArrayList<Invitado> listaInvitados = (ArrayList<Invitado>) invitadoRepository.findAll();
		
		for (Invitado invitadoContenido : listaInvitados) {
			if (invitadoContenido.getName().equals(invitado.getName())) {
				redirect.addFlashAttribute("msgError", "El invitado ya existe :(");
				return "redirect:/";
			}
		}
		
		invitadoRepository.save(invitado);
		redirect.addFlashAttribute("msgExito", "El invitado ha sido agregado con éxito :)");
		return "redirect:/";
	}
	
	@GetMapping("/{id}/editar")
	public String mostrarFormularioDeEditarInvitado(@PathVariable Integer id,Model modelo) {
		Invitado invitado = invitadoRepository.getById(id);
		modelo.addAttribute("invitado", invitado);
		return "nuevo";
	}
	
	@PostMapping("/{id}/editar")
	public String actualizarContacto(@PathVariable Integer id,@Validated Invitado invitado,BindingResult bindingResult,RedirectAttributes redirect,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("invitado", invitado);
			return "nuevo";
		}
		
		Invitado invitadoObtenido = invitadoRepository.getById(id);
		
		invitadoObtenido.setName(invitado.getName());
		invitadoObtenido.setReference(invitado.getReference());
		/*
		invitadoObtenido.setCelphone(invitado.getCelphone());
		invitadoObtenido.setEmail(invitado.getEmail());
		invitadoObtenido.setBirthDate(invitado.getBirthDate());
		*/
		invitadoRepository.save(invitadoObtenido);
		redirect.addFlashAttribute("msgExito", "El invitado ha sido actualizado correctamente :)");
		return "redirect:/";
	}
	
	@PostMapping("/{id}/eliminar")
	public String eliminarInvitado(@PathVariable Integer id,RedirectAttributes redirect) {
		Invitado invitadoObtenido = invitadoRepository.getById(id);
		invitadoRepository.delete(invitadoObtenido);
		redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente :X");
		return "redirect:/";
	}
	
}
