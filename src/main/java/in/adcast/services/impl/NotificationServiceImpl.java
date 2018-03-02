package in.adcast.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.common.utils.Mail;
import in.adcast.dao.ClientDao;
import in.adcast.dao.CustomerHasOfferDao;
import in.adcast.dao.OfferDao;

import in.adcast.model.Client;
import in.adcast.model.CustomerHasOffer;
import in.adcast.model.FilterCustomerForoffer;
import in.adcast.model.OfferTemplate;
import in.adcast.model.Offers;
import in.adcast.model.Organisation;
import in.adcast.services.MailService;
import in.adcast.services.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService 
{
	private static final Logger LOGGER = Logger.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private MailService mailService = new MailServiceImpl();
	
	@Autowired
	OfferDao offerDao;

	@Autowired
	ClientDao clientDao;

	@Autowired
	CustomerHasOfferDao customerHasOfferDao;
	
	@Override
	public void offerEmail() {
		LOGGER.info("offerEmail() --- Start");
		
		List<Offers> offerList =  offerDao.findActiveOffers();
		
		for(Offers offer:offerList){
			
			sendOfferEmail(offer);
			offer.setOfferSent(true);			
			offerDao.update(offer);
		}
		
		LOGGER.info("offerEmail() --- End");
	}
	
	public void sendOfferEmail(Offers offer) {
		LOGGER.info("sendOfferEmail(ClientDto clientDto) --- Start");
		try
		{			
			Map<String, Object> freeMarkerModel = new HashMap<String,Object>();
			//${} ${}
			Set<CustomerHasOffer> customerHasOfferSet = new HashSet<CustomerHasOffer>(0);
			Organisation organisation = offer.getBranch().getOrganisation(); 
			Integer organizationId = organisation.getId();
			
			freeMarkerModel.put("branchLocation", organisation.getOrganisationName());			
			freeMarkerModel.put("offerName", offer.getOfferType().getOfferType());
			freeMarkerModel.put("offerValue", offer.getOfferDiscountPer());
			freeMarkerModel.put("branchMobile", organisation.getPhone());				
			List<OfferTemplate> offerTemplates = new LinkedList<>();
			offerTemplates.addAll(offer.getOfferTemplates());
			OfferTemplate offerTemplate = offerTemplates.get(0);
			
			Set<Client> clientSetForOffer = new HashSet<>();
			for(FilterCustomerForoffer filterCustomerForoffer:offer.getFilterCustomerForoffers()){
				//LOGGER.info("offer.getFilterCustomerForoffers() --- "+offer.getFilterCustomerForoffers());
				switch(filterCustomerForoffer.getFilterCustomerType())
				{
					case "All Customer":
						List<Client> allClientList = clientDao.findAll(organizationId);
						clientSetForOffer.addAll(allClientList);
						break;
					case "MALE":
						List<Client> maleClientList = clientDao.getClientByGender(organizationId,'M');
						clientSetForOffer.addAll(maleClientList);
						break;
					case "Only for Female":
						List<Client> femaleClientList = clientDao.getClientByGender(organizationId,'F');
						clientSetForOffer.addAll(femaleClientList);
						break;
					case "NOSHOW":
						List<Client> noShowClient = clientDao.getClientNoShow(organizationId);
						clientSetForOffer.addAll(noShowClient);
						break;
				}
				
			}
			
			for(Client client :clientSetForOffer){
				CustomerHasOffer customerHasOffer = new CustomerHasOffer();
				
				customerHasOffer.setClient(client);
				customerHasOffer.setOffers(offer);				
				customerHasOfferDao.create(customerHasOffer);
				
				freeMarkerModel.put("customerName", client.getFirstName()+" "+client.getLastName());							
				if("email".equals(offer.getSendBy()))
				{
					sendMail("offerTemplate.html",client.getEmailId(),freeMarkerModel,offerTemplate.getEmailSubject());
				}				
			}		
			
		} catch (Exception e) {
    	
			e.printStackTrace();
    	
			e.getMessage();
        
		}
		LOGGER.info("sendOfferEmail(ClientDto clientDto) --- End");
	}
	

	@Override
	public void sendTestMail() {
    	Mail mail = new Mail();
    	System.out.println("Hellu 23");
    	mail.setMailFrom("admin@bookmyled.com");
    	mail.setMailTo("akundurkar@gmail.com");
    	mail.setMailSubject("Test");
    	System.out.println("Hellu 24");
    	Map<String, Object> freeMarkerModel = new HashMap<String,Object>();
    	
    	freeMarkerModel.put("firstName", "Yashwant");
    	freeMarkerModel.put("lastName", "Chavan");
    	freeMarkerModel.put("location", "Pune");
    	freeMarkerModel.put("signature", "www.technicalkeeda.com");
    	mail.setModel(freeMarkerModel);
    
        mailService.sendEmail(mail,"endCampaign.html");
        
    }

	@Override
	public void sendMail(String templateFile,String mailTo,Map<String, Object> freeMarkerModel,String subject) {
    	Mail mail = new Mail();
    	mail.setMailFrom("admin@bookmyled.com");
    	mail.setMailTo(mailTo);
    	mail.setMailSubject(subject);    	
    	mail.setModel(freeMarkerModel);        
        mailService.sendEmail(mail,templateFile);        
    }

	@Override
	public void birthdayOfferEmail() {
		LOGGER.info("offerEmail() --- Start");
		
		List<Offers> birthdayOfferList =  offerDao.findBirthdayOffers();
		
		for(Offers birthdayOffer:birthdayOfferList){
			
			sendOfferEmail(birthdayOffer);			
		}
		
		LOGGER.info("offerEmail() --- End");
	}


}
