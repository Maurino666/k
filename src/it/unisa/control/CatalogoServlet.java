package it.unisa.control;

import java.io.IOException; 
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoDao;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProdottoDao prodDao = new ProdottoDao();
		ProdottoBean bean = new ProdottoBean();
		String sort = request.getParameter("sort");
		String action = request.getParameter("action");
		String redirectedPage = request.getParameter("page");;
	
		try {
			
			
			if(action!=null) {
				//tolti i caratteri speciali da tutti i parametri (grazie caro gpt per aver suggerito il metodo)
				if(action.equalsIgnoreCase("add")) {
					bean.setNome(request.getParameter("nome").replaceAll("[<>]", ""));
					bean.setDescrizione(request.getParameter("descrizione").replaceAll("[<>]", ""));
					bean.setIva(request.getParameter("iva").replaceAll("[<>]", ""));
					bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo").replaceAll("[<>]", "")));
					bean.setQuantità(Integer.parseInt(request.getParameter("quantità").replaceAll("[<>]", "")));
					bean.setPiattaforma(request.getParameter("piattaforma").replaceAll("[<>]", ""));
					bean.setGenere(request.getParameter("genere").replaceAll("[<>]", ""));
					bean.setImmagine(request.getParameter("img").replaceAll("[<>]", ""));
					bean.setDataUscita(request.getParameter("dataUscita").replaceAll("[<>]", ""));
					bean.setDescrizioneDettagliata(request.getParameter("descDett").replaceAll("[<>]", ""));
					bean.setInVendita(true);
					prodDao.doSave(bean);
				}
				
				else if(action.equalsIgnoreCase("modifica")) {
					
					bean.setIdProdotto(Integer.parseInt(request.getParameter("id").replaceAll("[<>]", "")));
					bean.setNome(request.getParameter("nome").replaceAll("[<>]", ""));
					bean.setDescrizione(request.getParameter("descrizione").replaceAll("[<>]", ""));
					bean.setIva(request.getParameter("iva").replaceAll("[<>]", ""));
					bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo").replaceAll("[<>]", "")));
					bean.setQuantità(Integer.parseInt(request.getParameter("quantità").replaceAll("[<>]", "")));
					bean.setPiattaforma(request.getParameter("piattaforma").replaceAll("[<>]", ""));
					bean.setGenere(request.getParameter("genere").replaceAll("[<>]", ""));
					bean.setImmagine(request.getParameter("img").replaceAll("[<>]", ""));
					bean.setDataUscita(request.getParameter("dataUscita").replaceAll("[<>]", ""));
					bean.setDescrizioneDettagliata(request.getParameter("descDett").replaceAll("[<>]", ""));
					bean.setInVendita(true);
					prodDao.doUpdate(bean);	
				}

				request.getSession().removeAttribute("categorie");

			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {
			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products", prodDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
			
			response.sendRedirect(request.getContextPath() + "/" +redirectedPage);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
