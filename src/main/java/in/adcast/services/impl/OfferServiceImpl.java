package in.adcast.services.impl;

import java.util.List;

import org.apache.log4j.Logger; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.dto.OfferDto;

import in.adcast.services.OfferService;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    
	private static final Logger LOGGER =  Logger.getLogger(OfferServiceImpl.class);

	@Override
	public OfferDto createNewOffer(OfferDto offerDto) {
		LOGGER.info("createNewOffer(OfferDto offerDto) ---Start");
		
		LOGGER.info("createNewOffer(OfferDto offerDto) ---End");
		return null;
	}

	@Override
	public OfferDto changeOffer(OfferDto offerDto) {
		LOGGER.info("changeOffer(OfferDto offerDto) ---Start");
		
		LOGGER.info("changeOffer(OfferDto offerDto) ---End");
		return null;
	}

	@Override
	public List<OfferDto> listActiveOffers() {
        LOGGER.info("listActiveOffers() ---Start");
		
		LOGGER.info("listActiveOffers() ---End");
		return null;
	}

	@Override
	public boolean checkOfferValidity(OfferDto offerDto) {
		 LOGGER.info("checkOfferValidity(OfferDto offerDto) ---Start");
			
		 LOGGER.info("checkOfferValidity(OfferDto offerDto) ---End");
		return false;
	}

}
