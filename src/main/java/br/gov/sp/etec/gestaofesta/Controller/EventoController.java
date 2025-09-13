package br.gov.sp.etec.gestaofesta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.etec.gestaofesta.model.Convidado;
import br.gov.sp.etec.gestaofesta.model.Evento;
import br.gov.sp.etec.gestaofesta.repository.ConvidadoRepository;
import br.gov.sp.etec.gestaofesta.repository.EventoRepository;

@Controller
@RequestMapping("evento")
public class EventoController {
 
	@Autowired
	EventoRepository eventoRepository;
	
	@Autowired
	ConvidadoRepository convidadoRepository;
	
	
	@GetMapping ("cadastrar")
	public String formEvento() {
		return "form-evento";
	}//formEvento
	
	@PostMapping("salvar")
	public String salvarEvento(Evento evento) {
		eventoRepository.save(evento);
		return "form-evento";
	}
	
	@GetMapping("/listar/{id}")
	  public ModelAndView listaConvidado(@PathVariable Long id) {
		  
		 List<Convidado> convidados = convidadoRepository.findByEventoId(id);
		  
		  ModelAndView view= new ModelAndView("lista-convidado");
		  
	      view.addObject("convidados", convidados);

		  return view;
	  }
	
	@GetMapping("/editar/{id}")
	  public ModelAndView editarEvento(@PathVariable Long id) {
		
		Evento evento = eventoRepository.findById(id).get(); 		  
		 		  
		  ModelAndView view= new ModelAndView("editar-evento");
		  
	      view.addObject("evento", evento);

		  return view;
	  }
	
	@GetMapping("/excluir/{id}")
	  public ModelAndView excluirEvento(@PathVariable Long id) {
		
		eventoRepository.deleteById(id);
		  
		 List<Evento> eventos = eventoRepository.findAll();
		  
		  ModelAndView view= new ModelAndView("lista-evento");
		  
	      view.addObject("eventos", eventos);

		  return view;
	  }
	
}
