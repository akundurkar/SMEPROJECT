package in.adcast.services;

import java.util.List;

import in.adcast.dto.AppointmentHistoryDto;
import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientNameDto;
import in.adcast.dto.ClientProfileDto;
import in.adcast.dto.ClientReviewDto;
import in.adcast.dto.ContactDto;
import in.adcast.dto.OrderCountDto;
import in.adcast.dto.UpcomingAppointmentDto;

public interface ClientService 
{
	
	public Integer addNewClient(ClientDto clientDto);
	
	public boolean saveClientAddress(ClientDto clientDto);

	public List<ClientDto> findAll(String userId);

	public List<ClientNameDto> searchClientByMobileORName(Integer organizationId,String queri);

	public ClientProfileDto findClientProfile(Integer clientId);
	
	public OrderCountDto getOrderCountforClient(Integer clientId);
	
	public List<UpcomingAppointmentDto> getUpcomingAppointmentForClient(Integer clientId);
	
	public List<AppointmentHistoryDto> getAppointmentHistoryForClient(Integer clientId);

	public ClientDto getClientEditDetails(Integer clientId);

	public ClientDto updateClientEditDetails(ClientDto clientEditDtoReq);

	public boolean saveClientReview(ClientReviewDto clientReviewDto);

	public boolean saveContact(ContactDto contactDto);

	

	

}
