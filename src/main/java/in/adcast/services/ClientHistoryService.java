package in.adcast.services;

import java.util.List;

import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientHistoryDto;

public interface ClientHistoryService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	
		public List<ClientHistoryDto> searchClientHistoryByNameMobile(ClientDto clientDto);

		public List<ClientHistoryDto> searchClientHistory(ClientDto clientDto);

}
