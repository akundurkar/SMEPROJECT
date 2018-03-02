package in.adcast.services.impl;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import in.adcast.dto.ClientDto;
import in.adcast.dto.ClientHistoryDto;

import in.adcast.services.ClientHistoryService;

@Service
@Transactional
public class ClientHistoryServiceImpl implements ClientHistoryService {
	
	private static final Logger LOGGER = Logger.getLogger(ClientHistoryServiceImpl.class);

	@Override
	public List<ClientHistoryDto> searchClientHistoryByNameMobile(ClientDto clientDto) {
		LOGGER.info("searchClientHistoryByNameMobile(ClientDto clientDto) ---Start");
		
		LOGGER.info("searchClientHistoryByNameMobile(ClientDto clientDto) ---End");
		return null;
	}

	@Override
	public List<ClientHistoryDto> searchClientHistory(ClientDto clientDto) {
        LOGGER.info("searchClientHistory(ClientDto clientDto) ---Start");
		
		LOGGER.info("searchClientHistory(ClientDto clientDto) ---End");
		return null;
	}

}
