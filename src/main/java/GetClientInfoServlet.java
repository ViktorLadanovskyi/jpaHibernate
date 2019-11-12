import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetClientInfoServlet extends HttpServlet {
    private String name;
    private String email;
    private String phoneNumber;

    static EntityManagerFactory emf;
    static EntityManager em;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        name = request.getParameter("name");
        email = request.getParameter("email");
        phoneNumber = request.getParameter("phoneNumber");

        if(name.equals("") || email.equals("") || phoneNumber.equals("")) {
            response.sendRedirect("index.html");
            return;
        }

        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            Client client = new Client(name, email, phoneNumber);
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }

        getClients(request, response);
    }

    private void getClients(HttpServletRequest request, HttpServletResponse response){
        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();

        List<Client> clients;
        try {
            Query query = em.createQuery("SELECT c FROM Client c", Client.class);
            clients = (List<Client>) query.getResultList();
        }finally {
            em.close();
            emf.close();
        }

        request.setAttribute("list", clients);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
