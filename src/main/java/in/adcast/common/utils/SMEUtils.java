package in.adcast.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import in.adcast.common.Constants;
import in.adcast.dto.StaffScheduleDto;


@EnableAsync
public class SMEUtils {

	
		/*public static boolean isUserShowAdAdmin(ApplicationUser user){
			
			Set<Role> roles = user.getRoles();
			for (Role role : roles) {
				if(role.getId() == AccountRole.ADMIN_SHOWAD.getValue()){
					return true;
				}
			}
			return false;
		}
		*/
	
		@Async
		public static void SendMail(String userMailID,String ConfirmationLinkMessage,String title) {
		
			final String username = "admin@bookmyled.com";
			final String password = "showad";
	
			Properties props = new Properties();
			/*props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			*/
			
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			props.put("mail.smtp.host", "smtpauth.net4india.com");
			//props.put("mail.smtp.host", "smtpauth.bookmyled.com");
			props.put("mail.smtp.port", "25");
			
			/*props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			props.put("mail.smtp.host", "smtpout.secureserver.net");
			props.put("mail.smtp.port", "25");
			
			*/
			
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(username, password);
					
				}
				
			  });
	
			session.setDebug(true);
			
			try {
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userMailID));
				message.setSubject(title);
				
				//String ConfirmationLinkMessage = "<html><body><b>ShowAD account</b><h1 style=\"color:BLUE\">Verify your email address</h1><p>To finish setting up this ShowAD account, we just need to make sure this email address is yours.</p><table ><th><h2>Verify mahendra.navaghane@gmail.com</h2></th></table><p>If you didn't make this request, <a href=\"\">click here</a> to cancel.</p><p>Thanks,<br/>The ShowAD account team</p></body></html>";
				//message.setText(ConfirmationLinkMessage);
				message.setContent(ConfirmationLinkMessage,"text/html");
				System.out.println("sending mail---------------");
				Transport.send(message);
	
				System.out.println("mail sent to client..............");
	
			} catch (MessagingException e) {
				
				System.out.println(" MessagingException :: Exception occurred while sending email to client ..............");
				e.printStackTrace();
				throw new RuntimeException(e);
				
			}
			catch(Exception e){
				
				System.out.println(" MessagingException :: Exception occurred while sending email to client ..............");
				e.printStackTrace();
				
			}
		}
	
		public static String generateHexToken(){
			
			String HEX_TOKEN="";
			Random r = new Random();
			StringBuffer sb = new StringBuffer();
	        int numchars=10;
	        
			while(sb.length() < numchars){
				
	          sb.append(Integer.toHexString(r.nextInt()));
	        }

	        HEX_TOKEN = sb.toString().substring(0, numchars);
	        
	        return HEX_TOKEN;
	        
		}
		
		public static long getNumberofDays(Date sdate,Date edate){
			
			LocalDate start = null;
			LocalDate end = null;
			
			if(sdate instanceof java.sql.Date)
			{
				start = ((java.sql.Date) sdate).toLocalDate();
			}
			else
			{
				start = sdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
			}
			
			if(edate instanceof java.sql.Date)
			{
				end = ((java.sql.Date) edate).toLocalDate();
			}
			else
			{
				end = edate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
			}
			
			return java.time.temporal.ChronoUnit.DAYS.between(start, end)+1;
		
		}

		
		public static String generatePassword() {
			
		      String chars = "abcdefghijklmnopqrstuvwxyz"
		                   + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		                   + "0123456789!@%$%&^?|~'\"#+="
		                   + "\\*/.,:;[]()-_<>";
		
		      final int PW_LENGTH = 8;
		      
		      Random rnd = new SecureRandom();
		      
		      StringBuilder pass = new StringBuilder();
		      
		      for (int i = 0; i < PW_LENGTH; i++)
		          pass.append(chars.charAt(rnd.nextInt(chars.length())));
		      
		      return pass.toString();
		      
		}

		
		/*static public boolean isUserShowAdAdmin(ApplicationUser appUser){
			Set<Role> roles = appUser.getRoles();
			LinkedList<Role> roleList = new LinkedList<>();
			roleList.addAll(roles);
			int i = roles.size();
			Role role = new Role();
			role.setId(1);
			role.setRole("Admin");
			role.setType("ShowAd");
			if(roles.contains(role)){
				return true;
			}
			return false;
			
		}*/

		
		public String getTemplateFile(String fileName){
				
			String fileData = null;
			
	        ClassLoader classLoader = getClass().getClassLoader();
	        
	    	File file = new File(classLoader.getResource(fileName).getFile());
	    	
	    	FileReader fileReader;
	    	
	    	StringBuffer stringBuffer = new StringBuffer();
	    	
				try {
					
					fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						stringBuffer.append(line);
						
					}
					fileReader.close();
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
				
				System.out.println("Contents of file:");
				System.out.println(stringBuffer.toString());
				
				fileData = stringBuffer.toString();
				
				return fileData;
				
		}
		
		private static Date getCurrentDate() {
			
			Date date = new Date();
			
			return date;
		
		}
		
		public static String todaysDateString(String format){		
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			//Date date = new Date();/
			//Date date = dateAfterTodaysDate(5);
			Date date = getCurrentDate();
			
			String todaysDateString = sdf.format(date);	
			
			return todaysDateString;
		}
		
		public static String dateAsString(String format,Date date){
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			
			return sdf.format(date);
			
		}
		
		
		/*	public static LocalDateTime todaysDate(){						
			LocalDateTime currentTime = LocalDateTime.now();
			//Date date = new Date();/
			//Date date = dateAfterTodaysDate(5);
			Date date = getCurrentDate();
			return currentTime;
		}*/
		
		
		public static Date todaysDate(String format){
			
			Date todaysDate = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			//Date date = new Date();/
			//Date date = dateAfterTodaysDate(5);
			Date date = getCurrentDate();
			
			String todaysDateString = sdf.format(date);
			
			try {
				
				todaysDate = sdf.parse(todaysDateString);
				
			} catch (ParseException e) {	
				
				e.printStackTrace();
				
			}
			
			return todaysDate;
			
		}
		
		public static Date dateAfterInputDate(int numberOfDays,Date date){
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, numberOfDays); //minus number would decrement the days
	        
	        return cal.getTime();
			
		}
		
		public byte[] getFile(int orderId)
		{
			
			byte[] bytes = null;
			
			try
			{
				OutputStream file = new FileOutputStream(new File("HTMLtoPDF.pdf"));
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, file);
				
				String template = getTemplateFile("invoicetemplate/invoice.html");
				
				/*StringBuilder htmlString = new StringBuilder();
				htmlString.append(new String("<html><body>"));*/
				/*base64-data-string here*/
				
				String base_64_image = getImageAsBase();
				String imageSrc = new String("<img src=\"data:image/png;base64,"+base_64_image+"\"/>");
				template = template.replace("IMAGEHERE", imageSrc);
				//template = template.replaceAll("",campaignOrder.getOrderTotalCostAllScreens().toString());
				template = template.replace("OTHER_RATE", "");
				
				template = template.replace("BILLDATE",todaysDateString("dd-MM-YYYY"));
				//htmlString.append(new String("<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAc4AAABQCAYAAACQ/ZU3AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MjRGMzU1Qjk5RjFFMTFFNEE2NzA4QzlBNERCRTcxRTUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MjRGMzU1QkE5RjFFMTFFNEE2NzA4QzlBNERCRTcxRTUiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDoyNEYzNTVCNzlGMUUxMUU0QTY3MDhDOUE0REJFNzFFNSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoyNEYzNTVCODlGMUUxMUU0QTY3MDhDOUE0REJFNzFFNSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PkQbS2MAABpBSURBVHja7F0JuBTV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\" />"));
				/*htmlString.append(new String("<img src=\"data:image/png;base64,"+base_64_image+"\"/>"));
				htmlString.append(new String(" This is HMTL to PDF conversion Example<table border='2' align='center'> "));
				htmlString.append(new String("<tr><td><font color=\"RED\">JavaCodeGeeks</font></td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));				
				htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='www.google.com'>Google</a> </td> </tr></table></body></html>"));
				*/
				document.open();
				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
				
				HtmlPipelineContext htmlPipelineContext = new HtmlPipelineContext(null);
				htmlPipelineContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
				htmlPipelineContext.setImageProvider(new Base64ImageProvider());
				
				PdfWriterPipeline pdf = new PdfWriterPipeline(document,writer);
				HtmlPipeline htmlPipeline = new HtmlPipeline(htmlPipelineContext, pdf);
				CssResolverPipeline css = new CssResolverPipeline(cssResolver, htmlPipeline);
				
				XMLWorker worker = new XMLWorker(css, true);
				XMLParser parser = new XMLParser(worker);
				
				InputStream is = new ByteArrayInputStream(template.getBytes());
				parser.parse(is);
				//File filehtmlTemplate = new File("d:\\invoice.html");
                //File length
                //int size = (int) filehtmlTemplate.length();
                //if (size > Integer.MAX_VALUE) {
                //  System.out.println("File is to larger");
                //}
				//InputStream hfis = new FileInputStream(filehtmlTemplate);
				//XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
				//XMLWorkerHelper.getInstance().parseXHtml(writer, document, hfis);
				document.close();
				file.close();
				
				
				try {
				        File filepdf = new File("HTMLtoPDF.pdf");
				        //File length
				        int sizepdf = (int) filepdf.length();
				        if (sizepdf > Integer.MAX_VALUE) {
				            System.out.println("File is to larger");
				        }
				        bytes = new byte[sizepdf];
				        DataInputStream dis = new DataInputStream(new FileInputStream(filepdf));
				        int read = 0;
				        int numRead = 0;
				        while (read < bytes.length && (numRead = dis.read(bytes, read,
				                bytes.length - read)) >= 0) {
				            read = read + numRead;
				        }
				        dis.close();
			
				        DataOutputStream out = new DataOutputStream(new FileOutputStream(new File("d:\\out.pdf")));
				        out.write(bytes);
				        out.close();
				        System.out.println("File size: " + read);
			
				        // Ensure all the bytes have been read in
				        if (read < bytes.length) {
				        	
				            System.out.println("Could not completely read: " + filepdf.getName());
				            
				        }
				  } catch (Exception e) {
					  
				        e.getMessage();
				        
				  }
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
				return bytes;

		}

		public String getImageAsBase(){
			
			byte[] bytes = null;
			String base_64_image = null;
			try {
			        //File imageFile = new File("d:\\logo-blacknwhite.png");
			        //File length
			        String fileName = "invoicetemplate/logo-blacknwhite.png";
			        ClassLoader classLoader = getClass().getClassLoader();
			      	File imageFile = new File(classLoader.getResource(fileName).getFile());
			
			      	
			        int size = (int) imageFile.length();
			        if (size > Integer.MAX_VALUE) {
			            System.out.println("File is to larger");
			        }
			        bytes = new byte[size];
			        DataInputStream dis = new DataInputStream(new FileInputStream(imageFile));
			        int read = 0;
			        int numRead = 0;
			        while (read < bytes.length && (numRead = dis.read(bytes, read,
			                bytes.length - read)) >= 0) {
			            read = read + numRead;
			        }
			        dis.close();
			
			        System.out.println("File size: " + read);
			
			        // Ensure all the bytes have been read in
			        if (read < bytes.length) {
			            System.out.println("Could not completely read: " + imageFile.getName());
			        }
			        
			        base_64_image = Base64.encodeBase64String(bytes);
		    } catch (Exception e) {
		        e.getMessage();
		    }
			return base_64_image;
		}

		public static Date[] getFinancialYearDates() {			
			
			Date financialYear[] = new Date[2];
			
			Calendar calDate = Calendar.getInstance();
			
			int month = calDate.get(Calendar.MONTH);
			
			if(month == 0 || month == 1){
				
				int year = calDate.get(Calendar.YEAR);  // 2012			
				int prevYear = year - 1;
				String startDate = prevYear + "-03-01";
				//year++;
				String endDate = null ;
				
					if(year % 4 == 0){
						endDate = year + "-02-29";
					}
					else{
						endDate = year + "-02-28";
					}
					
				SimpleDateFormat sdf = new SimpleDateFormat(Constants.USDAEFORMAT);	
					
					try {
						
						financialYear[0] = sdf.parse(startDate);
						financialYear[1] = sdf.parse(endDate);
						
					} catch (ParseException e) {
						
						e.printStackTrace();
						
					}	
					
			}else{
				
				int year = calDate.get(Calendar.YEAR);  // 2012			
				String startDate = year + "-03-01";
				year++;
				String endDate = null ;
				
					if(year % 4 == 0){
						endDate = year + "-02-29";
					}
					else{
						endDate = year + "-02-28";
					}		
					
				SimpleDateFormat sdf = new SimpleDateFormat(Constants.USDAEFORMAT);	
				
				try {
					
					financialYear[0] = sdf.parse(startDate);
					financialYear[1] = sdf.parse(endDate);
					
				} catch (ParseException e) {
				
					e.printStackTrace();
					
				}
				
			}
			return financialYear;
		}
		
		public static Date[] getMonthDates() {			
			
			Date financialMonth[] = new Date[2];
			
			Calendar calDate = Calendar.getInstance();
			
			int year = calDate.get(Calendar.YEAR);  
			int month = calDate.get(Calendar.MONTH);
			
			String startDate = year +"-"+(month+1);
			String endDate = year +"-"+(month+2);
			
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.USDAEFORMAT);	
			
			 startDate = startDate+"-01";
				//year++;
			 endDate = endDate+"-01" ;
				
					try {
						
						financialMonth[0] = sdf.parse(startDate);
						financialMonth[1] = sdf.parse(endDate);
						
					} catch (ParseException e) {
						
						e.printStackTrace();
						
					}
			
			return financialMonth;
		}
		

		public static void SendMail(String userMailID, String ConfirmationLinkMessage, String title, String attachmentFilePath) {

/*		final String username = "bookmyledapp@gmail.com";
		final String password = "bookmyled@123";
*/
/*		final String username = "rahultest@consistentsystem.com";
		final String password = "bookmyled@123";
*/
		
		final String username = "admin@bookmyled.com";
		final String password = "showad";

		
		Properties props = new Properties();
		/*props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		*/
		
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.host", "smtpauth.net4india.com");
		//props.put("mail.smtp.host", "smtpauth.bookmyled.com");
		props.put("mail.smtp.port", "25");
		
		
		/*props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.port", "25");
		
		*/
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userMailID));
			message.setSubject(title);
			
		  // the parent or main part if you will
	    Multipart mainMultipart = new MimeMultipart("related");

	    // this will hold text and html and tells the client there are 2 versions of the message (html and text). presumably text
	    // being the alternative to html
	    Multipart htmlAndTextMultipart = new MimeMultipart("alternative");

	    // set text
	    MimeBodyPart textBodyPart = new MimeBodyPart();
	    textBodyPart.setText("Here will be text");
	    htmlAndTextMultipart.addBodyPart(textBodyPart);

	    // set html (set this last per rfc1341 which states last = best)
	    MimeBodyPart htmlBodyPart = new MimeBodyPart();
	    htmlBodyPart.setContent(ConfirmationLinkMessage, "text/html; charset=utf-8");
	    htmlAndTextMultipart.addBodyPart(htmlBodyPart);

	    // stuff the multipart into a bodypart and add the bodyPart to the mainMultipart
	    MimeBodyPart htmlAndTextBodyPart = new MimeBodyPart();
	    htmlAndTextBodyPart.setContent(htmlAndTextMultipart);
	    mainMultipart.addBodyPart(htmlAndTextBodyPart);

	    // attach file body parts directly to the mainMultipart
	    MimeBodyPart filePart = new MimeBodyPart();
	    FileDataSource fds = new FileDataSource(attachmentFilePath);
	    filePart.setDataHandler(new DataHandler(fds));
	    filePart.setFileName(fds.getName());
	    mainMultipart.addBodyPart(filePart);

	    // set message content
	    message.setContent(mainMultipart);

			System.out.println("sending mail---------------");
			Transport.send(message);

			System.out.println("mail sent to client..............");

		} catch (MessagingException e) {
			System.out.println(" MessagingException :: Exception occurred while sending email to client ..............");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		catch(Exception e){
			System.out.println(" MessagingException :: Exception occurred while sending email to client ..............");
			e.printStackTrace();
			
		}
	
			
		}
		
		
		public static Date getDateFromString(String dateString,String format){
			
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			
			Date parsedDate = null;			
			try {
				parsedDate = formatter.parse(dateString);								
			}catch (ParseException e) {
				
				e.printStackTrace();
				
			}
			
			return parsedDate;
			
		}

		public static void setWeekDates(StaffScheduleDto staffScheduleDto) {
			
			LocalDate today = LocalDate.now();

		    // Go backward to get Monday
		    LocalDate sunday = today;
		    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY)
		    {
		      sunday = sunday.minusDays(1);
		    }		    		    
	        Date date = getDateFromString(sunday.toString(),"yyyy-MM-dd");
	        date = getDateFromString(sunday.toString(),"yyyy-MM-dd");
			staffScheduleDto.setSunday(dateAsString("EEE, dd MMM",date));
			
			LocalDate nextDay = sunday.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setMonday(dateAsString("EEE, dd MMM",date));
		    
			nextDay = nextDay.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setTuesday(dateAsString("EEE, dd MMM",date));
			
			nextDay = nextDay.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setWednesday(dateAsString("EEE, dd MMM",date));
			
			nextDay = nextDay.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setThrusday(dateAsString("EEE, dd MMM",date));
			
			nextDay = nextDay.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setFriday(dateAsString("EEE, dd MMM",date));
			
			nextDay = nextDay.plusDays(1);
	        date = getDateFromString(nextDay.toString(),"yyyy-MM-dd");
			staffScheduleDto.setSaturday(dateAsString("EEE, dd MMM",date));
			
		}
	}
