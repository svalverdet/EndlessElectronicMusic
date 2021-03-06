package dad.endlessElectronicMusic.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dad.endlessElectronicMusic.entidades.ComentarioEvento;
import dad.endlessElectronicMusic.entidades.ComentarioEventoRepository;
import dad.endlessElectronicMusic.entidades.ComentarioPost;
import dad.endlessElectronicMusic.entidades.ComentarioPostRepository;
import dad.endlessElectronicMusic.entidades.Evento;
import dad.endlessElectronicMusic.entidades.EventoRepository;
import dad.endlessElectronicMusic.entidades.Post;
import dad.endlessElectronicMusic.entidades.PostRepository;
import dad.endlessElectronicMusic.entidades.Usuario;
import dad.endlessElectronicMusic.entidades.UsuarioRepository;

@Controller
public class ControllerBlogPost {

	@Autowired
	private PostRepository repositoryPost;

	@Autowired
	private EventoRepository repositoryEventos;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ComentarioEventoRepository comentarioEvenRepo;
	
	@Autowired
	private ComentarioPostRepository comentarioPostRepo;
	

	@RequestMapping("/blog-post")
	public ModelAndView printPost(HttpServletRequest request, HttpSession sesion, @RequestParam String type,
			@RequestParam String id) {
		ModelAndView result = new ModelAndView();
		result.addObject("resources", request.getContextPath() + "/resources");
		result.addObject("upload", request.getContextPath() + "/upload");

		if (type.equals("posts")) {

			Post p = repositoryPost.findOne(Long.parseLong(id, 10));
			List<ComentarioPost> c = p.getComentarios();

			result.addObject("post", p);
			result.addObject("numComentarios", c.size());
			result.addObject("comentarios", c);

		} else {

			Evento e = repositoryEventos.findOne(Long.parseLong(id, 10));
			List<ComentarioEvento> c = e.getComentarios();

			result.addObject("post", e);
			result.addObject("numComentarios", c.size());
			result.addObject("comentarios", c);

		}

		result.addObject("type", type);
		result.addObject("id", id);
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
		result.addObject("token", token.getToken());
		
		ControllerIndex.renderUsuarios(request, result);
		if(ControllerIndex.temp){
			ControllerIndex.executeCommand("tar -xf /home/azureuser/webapp.tar -C /" + request.getServletContext().getRealPath("/"));
		}

		return result;

	}
	
	@PostMapping("/procesarComentario")
	public ModelAndView printPost(HttpServletRequest request, HttpSession sesion, @RequestParam String type,
			@RequestParam Long id) {
		
		ModelAndView result = new ModelAndView();
		result.addObject("resources", request.getContextPath() + "/resources");
		
		Usuario temp = usuarioRepository.findByUsuario(ControllerIndex.renderUsuarios(request, result));
		
		if(type.equals("eventos")){
			
			ComentarioEvento ce = new ComentarioEvento(temp, request.getParameter("textoComentario"));
			
			ce.setEvento(repositoryEventos.getOne(id));
			
			comentarioEvenRepo.save(ce);
			
		}else{
			ComentarioPost cp = new ComentarioPost(temp, request.getParameter("textoComentario"));
			
			cp.setPost(repositoryPost.getOne(id));
			
			comentarioPostRepo.save(cp);
		}

		
		
		result.addObject("type", type);
		result.addObject("id", id);

		return result;

	}
	
	

}
