package in.adcast.schedular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;



@Service
@EnableAsync
public class SendSMSService {


	@SuppressWarnings("deprecation")
	@Async
	public void sendSMS(String mobileNo,String message) { 		
		String username = "BUKLED";
    //Your authentication key
    String authkey = "7d0a263c75XX";
    //Multiple mobiles numbers separated by comma (max 200)
    String mobiles = "+91"+mobileNo;
    //Sender ID,While using route4 sender id should be 6 characters long.
    String senderId = "BUKLED";
    //Your message to send, Add URL encoding here.    
    //define route
    String accusage="1";

    //Prepare Url
    URLConnection myURLConnection=null;
    URL myURL=null;
    BufferedReader reader=null;

    //encoding message
    String encoded_message=URLEncoder.encode(message);

    //Send SMS API
    String mainUrl="http://admin.upplextech.com/submitsms.jsp?";

    //Prepare parameter string
    StringBuilder sbPostData= new StringBuilder(mainUrl);
    sbPostData.append("user="+username);
    sbPostData.append("&key="+authkey);
    sbPostData.append("&mobile="+mobiles);
    sbPostData.append("&message="+encoded_message);
    sbPostData.append("&accusage="+accusage);
    sbPostData.append("&senderid="+senderId);

    //final string
    mainUrl = sbPostData.toString();
    try
    {
    //prepare connection
    myURL = new URL(mainUrl);
    myURLConnection = myURL.openConnection();
    myURLConnection.connect();
    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
    //reading response
    String response;
    while ((response = reader.readLine()) != null)
    //print response
    System.out.println(response);
    System.out.println("SMS sent successfully");
    //finally close connection
    reader.close();
    }
    catch (IOException e)
    {
    e.printStackTrace();
    } 
		}
}
