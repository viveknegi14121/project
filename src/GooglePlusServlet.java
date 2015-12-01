import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

@WebServlet("/googleplus") 
public class GooglePlusServlet extends HttpServlet {
   private static final String CLIENT_ID = "486701667226-48s997ioibsfmv0vgd8tbqi30ksen650.apps.googleusercontent.com";
   private static final String CLIENT_SECRET = "X8QAy3H0vwbYYYy4wd_Flrgq";
   @Override 
   protected void doGet(HttpServletRequest req, HttpServletResponse res) 
      throws IOException, ServletException {
      //Configure 
      ServiceBuilder builder= new ServiceBuilder(); 
      OAuthService service = builder.provider(Google2Api.class) 
         .apiKey(CLIENT_ID) 
         .apiSecret(CLIENT_SECRET) 
         .callback("http://localhost:8080/j9/arsenal")
         .scope("openid profile email " + 
               "https://www.googleapis.com/auth/plus.login " + 
               "https://www.googleapis.com/auth/plus.me")
         .debug() 
         .build(); //Now build the call
      HttpSession sess = req.getSession(); 
      sess.setAttribute("oauth2Service", service);
      res.sendRedirect(service.getAuthorizationUrl(null)); 
   } 
} 