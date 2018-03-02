package in.adcast.services;

import java.util.List;

import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.CancelOrderDto;
import in.adcast.dto.ClosedDatesDto;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.dto.TaxDto;




public interface MasterDataService 
{

	public boolean saveCancelOrderReason(CancelOrderDto cancelOrder);

	public boolean saveReferralSource(ReferralSourceDto referralSourceDto);

	
	public boolean saveTaxDetails(TaxDto taxDto);
	
	public List<TaxDto> findAllTaxDetails(String userId);
	
	public Boolean deleteTaxDetails(Integer taxId);
	
	
	public boolean saveStaffDetails(StaffDto staffDto);
	
	public List<CancelOrderDto> findAllCancellationReason(String userId);

	public List<ReferralSourceDto> findAllReferralSourceDetails(String userId);

	public BranchScheduleDto getBranchScheduleDetails(Integer branchId);

	public StaffDto getStaffEditDetails(Integer staffId);

	public StaffDto updateStaffEditDetails(StaffDto staffDtoReq);

	public StaffScheduleDto getStaffScheduleDetails(Integer staffId);

	public boolean setStaffSchedule(StaffScheduleDto staffScheduleDto);

	public boolean setClosedDates(ClosedDatesDto closedDatesDto);

	public List<ClosedDatesDto> getAllClosedDates(Integer branchId);

}
