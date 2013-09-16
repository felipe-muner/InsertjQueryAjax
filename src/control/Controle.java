package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.PessoaDao;
import entity.Pessoa;

public class Controle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controle() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if(cmd.equalsIgnoreCase("listag")){
			listag(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if(cmd.equalsIgnoreCase("inserir")){
			inserir(request,response);
		}else if(cmd.equalsIgnoreCase("alterar")){
			alterar(request,response);
		}
	}
	protected void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = new Integer(request.getParameter("id"));
		String nome = request.getParameter("nome");
		Integer idade = new Integer(request.getParameter("idade"));
		String cidade = request.getParameter("cidade");
		try {
			new PessoaDao().alterar(new Pessoa(id,nome,idade,cidade));
			listag(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	protected void listag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PessoaDao pd = new PessoaDao();
		try {
			JSONArray  lista = new JSONArray(pd.findAll());
			JSONObject json = new JSONObject();
			json.put("Pessoa", lista);
			System.out.println(json);
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}						
	}
	protected void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String cidade = request.getParameter("cidade");
		Pessoa person = new Pessoa(null, nome, new Integer(idade), cidade);
		PessoaDao pd = new PessoaDao();
		try {
			pd.incluir(person);			
			/*
			JSONObject objeto = new JSONObject();
			objeto.put("nome", person.getNome());
			objeto.put("idade", person.getIdade());
			objeto.put("cidade", person.getCidade());
			lista.put(objeto);
			System.out.println(json.toString());
			*/
			JSONObject json = new JSONObject();
			JSONObject objeto = new JSONObject(person);
			JSONArray  lista = new JSONArray();
			lista.put(objeto);
			json.put("Pessoa", lista);
			response.setContentType("application/json");
			response.getWriter().write(json.toString());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
