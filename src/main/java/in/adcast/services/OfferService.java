package in.adcast.services;

import java.util.List;

import in.adcast.dto.OfferDto;

public interface OfferService {
	
	/**
	 * This will create a user account. 
	 * @param LoginDto loginDto.
	 * @return 	UserDto.
	 */
	
	public OfferDto createNewOffer(OfferDto offerDto);
	
	public OfferDto changeOffer(OfferDto offerDto);
	
	public List<OfferDto> listActiveOffers();
	
	public boolean checkOfferValidity(OfferDto offerDto);
	
	
	
}
