package in.adcast.mapper;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import in.adcast.dto.ClientNameDto;
import in.adcast.model.Client;

@Component
public class ClientNameMapper
{

	public List<ClientNameDto> prepareDto(List<Client> clientlist) {
		
		List<ClientNameDto> clientNameDtoList = new ArrayList<>();
		
		for(Client client : clientlist){
			
			ClientNameDto clientNameDto = new ClientNameDto();
			
				clientNameDto.setName(client.getFirstName()+" "+client.getLastName());
				clientNameDto.setMobile(client.getMobile());
				
			clientNameDtoList.add(clientNameDto);
		}
		
		return clientNameDtoList;
	}

}
