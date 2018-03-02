package in.adcast.common;

import in.adcast.common.utils.SMEUtils;
import in.adcast.dao.UserDao;
import in.adcast.exception.AuthenticationFailedException;
import in.adcast.model.ApplicationUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.log4j.spi.LoggerFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


public class ShowAdAuthServerFilter implements Filter{
	
	@Autowired
	private static UserDao userDao;

	
	

	private static List<String> excludeSecuredResourcePaths=null;

	


	  /**
	   * When this attribute is specified, there is no need to use OAuth.
	   *
	   * <p>Use this when the client has already been authenticated by other means.
	   */

	  /**
	   * Do nothing.
	   *
	   * {@inheritDoc}
	   */
	  public void init(FilterConfig config) throws ServletException {
	  }

	  /**
	   * Do nothing.
	   *
	   * {@inheritDoc}
	   */
	  public void destroy() {
	  }

	  /**
	   * Does the filtering.
	   *
	   * {@inheritDoc}
	   */
	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	     throws IOException, ServletException {
	    // Use HTTP specific requests.
		  try {
			if(doHttpFilter((HttpServletRequest)req, (HttpServletResponse)res, chain)){
				 chain.doFilter(req, res);
			}else{
				((HttpServletResponse) res).setStatus(401);
			}
			  
		} catch (AuthenticationFailedException e) {
		} catch (JSONException e) {
		}
	  }

	  /**
	   * Does the filtering.
	   *
	   * @param req   the HTTP servlet request
	   * @param res   the HTTP servlet response
	   * @param chain The filter chain
	   *
	   * @throws IOException      If thrown by any of the underlying filters or servlets.
	   * @throws ServletException If thrown by any of the underlying filters or servlets.
	 * @throws JSONException 
	 * @throws AuthenticationFailedException 
	   */
	  private boolean doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	     throws IOException, ServletException, JSONException, AuthenticationFailedException {
		 try {
	   	boolean IsRestSecurityRequired=Boolean.valueOf(ApplicationProperties.getProperty(ApplicationConstants.ISRESTSECURITYREQ));
	    	
	   	boolean isExclude=false;
	    	
	    	if(IsRestSecurityRequired){

	    		excludeSecuredResourcePaths=new ArrayList<String>(Arrays.asList(ApplicationProperties.getProperty(ApplicationConstants.SECURED_REST_SERVICE_FILTERS)
		    				.toString().split(",")));

	    		
	    		
	    		
	    		
	    		  
	   		

		    		String requestedURL=req.getRequestURI();
		    		
	    			System.out.println("*******requestedURL*********"+requestedURL + " "+excludeSecuredResourcePaths.size());
	    		    			
	    			for (String exclude : excludeSecuredResourcePaths) {
	    				
	    				System.out.println("*******excluding url *********"+exclude);
	    				
	    				if(requestedURL.contains(exclude)) {
	    					isExclude=true;
	    				    break;
	    				}
						
					}
	    			
	    		
	    	
	    			    		
	    			/*		    		
		    		
		    		System.out.println("*******requestedURL*********"+requestedURL);
		            
		            String pathData[]=requestedURL.split("/");
		            
		            System.out.println("*******pathData 0*********"+pathData[0]);
		            System.out.println("*******pathData 1*********"+pathData[1]);
		            System.out.println("*******pathData 2*********"+pathData[2]);
		            System.out.println("*******pathData 3*********"+pathData[3]);
		            
		            for(String path : pathData){
		            	System.out.println(path);
		            }
		            
		            requestedURL = "/"+pathData[1].concat("/").concat(pathData[2]).concat("/").concat(pathData[3]).concat("/");
		    		
		    		if(IsRestSecurityRequired && excludeSecuredResourcePaths!=null && 
		    				(excludeSecuredResourcePaths.contains(pathData[3]))){
		    			IsPatternMatch=true;
		    		}*/
	    			
	    			
		    	if(!isExclude){
		    		
		    		checkOAuthRequest(req, res);
		    		
		    	}
		    	
		       return true;
		       
	    	}else{
	    		
	    		return true;
	    	}
	        
	      } catch (Exception ex) {
	    	  ex.printStackTrace();
	    	 	return false;
	      }
	  }
	  

	  
	  

	  // ==============================================================================================

	  /**
	   * Checks that the request has access to the underlying resources.
	   *
	   * @param req The HTTP servlet request
	   * @param res The HTTP servlet response.
	   *
	   * @throws OAuthException Should any OAuth related problem occur.
	   * @throws IOException Should an error occur while writing the output stream.
	   */
	  
private static final void checkOAuthRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String oauth_token=req.getHeader("oauth_token")!=null?req.getHeader("oauth_token"):req.getParameter("oauth_token");
		String userId = req.getHeader("userId");
		ApplicationUser user = userDao.findByUUID(userId);
		user.setLastLogin(SMEUtils.todaysDate(Constants.USDAEFORMAT));
		userDao.update(user);
		System.out.println(userId+"-------------------");
		String oauth_token_secret=req.getHeader("oauth_token_secret")!=null?req.getHeader("oauth_token_secret"):req.getParameter("oauth_token_secret");
		URL url = new URL(Constants.OAUTH_VERIFY_TOKEN_URL);
	    URLConnection urlConnection = url.openConnection();
	    HttpURLConnection connection = null;
	    connection = (HttpURLConnection) urlConnection;
	    connection.addRequestProperty("oauth_token",oauth_token);
	    connection.addRequestProperty("oauth_token_secret",oauth_token_secret);
	    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    in.close();
	  }


}
