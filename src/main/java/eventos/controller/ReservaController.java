package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Reserva;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservaController {
	
	@Autowired
	private ReservaDao rDao;
	
	
	//DE MOMENTO DEJO MOSTRAR TODAS LAS RESERVAS, PERO HAY QUE SACARLAS POR EL USUARIO LOGEADO
	@GetMapping("/misReservas")
	public String verMisReservas(Model model, Authentication aut, HttpSession misesion) {
		List<Reserva> reservas = rDao.verReservas();
		model.addAttribute("reservas", reservas);
		return "/misReservas";
	}
	
	@GetMapping("/misReservas/eliminar/{id}")
	public String eliminarReserva(@PathVariable ("id") int idReserva, Model model) {
		if(rDao.eliminarReserva(idReserva) == 1) {
		model.addAttribute("mensaje", "Se ha eliminado la reserva");
		return "redirect:/misReservas";
		}else
			model.addAttribute("mensaje", "No ha podido eliminar la reserva");
		return "redirect:/misReservas";
	}
	
	
	

}