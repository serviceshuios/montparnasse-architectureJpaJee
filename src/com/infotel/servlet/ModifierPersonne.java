package com.infotel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotel.metier.Personne;
import com.infotel.service.Iservice;
import com.infotel.service.ServiceImpl;

/**
 * Servlet implementation class ModifierPersonne
 */
@WebServlet("/ModifierPersonne")
public class ModifierPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Iservice service = new ServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierPersonne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Personne p = service.affichagePersonne(Integer.parseInt(request.getParameter("idPersonne")));
		
		request.setAttribute("id", p.getId());
		request.setAttribute("lastname", p.getNom());
		request.setAttribute("firstname", p.getPrenom());
		request.setAttribute("age", p.getAge());
		/*request.setAttribute("login", p.getConnexion().getLogin());
		request.setAttribute("age", p.getConnexion().getMdp());*/
		
		request.setAttribute("personnes", service.findAllPersonnes());
		request.setAttribute("adresses", service.findAllAdresses());
		
		// 4- appel de la jsp
		request.getRequestDispatcher("personne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
