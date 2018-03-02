package in.adcast.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.adcast.dao.BranchDao;
import in.adcast.dao.ClosedDatesdao;
import in.adcast.dao.OrderMasterDatadao;

import in.adcast.dao.ReferralMasterdatadao;
import in.adcast.dao.ServiceMasterDataDao;
import in.adcast.dao.StaffScheduleDao;
import in.adcast.dao.TaxDetailsMasterdatadao;
import in.adcast.dao.UserDao;

import in.adcast.dto.BranchScheduleDto;
import in.adcast.dto.CancelOrderDto;
import in.adcast.dto.ClosedDatesDto;
import in.adcast.dto.ReferralSourceDto;
import in.adcast.dto.StaffDto;
import in.adcast.dto.StaffScheduleDto;
import in.adcast.dto.TaxDto;
import in.adcast.exception.CustomRuntimeException;

import in.adcast.mapper.BranchScheduleMapper;
import in.adcast.mapper.CancelOrderMapper;
import in.adcast.mapper.ClosedDatesMapper;
import in.adcast.mapper.ReferralSourceMapper;
import in.adcast.mapper.StaffScheduleMapper;
import in.adcast.mapper.TaxDetailsMapper;
import in.adcast.mapper.UserResourceMapper;

import in.adcast.model.ApplicationUser;
import in.adcast.model.Branch;
import in.adcast.model.BranchSchedule;
import in.adcast.model.ClosedDates;
import in.adcast.model.OrderMasterdata;
import in.adcast.model.ReferralMasterdata;
import in.adcast.model.StaffSchedule;
import in.adcast.model.TaxMasterdata;

import in.adcast.services.MasterDataService;


@Service
@Transactional
public class MasterDataServiceImpl implements MasterDataService{
	
	private static final Logger LOGGER = Logger.getLogger(MasterDataServiceImpl.class);

	@Autowired
	private CancelOrderMapper cancelOrderMapper;
	
	@Autowired
	private ReferralSourceMapper referralSourceMapper;
	
	@Autowired
	private TaxDetailsMapper taxDetailsMapper;
	
	@Autowired
	private BranchScheduleMapper branchScheduleMapper;
	
	@Autowired
	private OrderMasterDatadao orderMasterDatadao;
	
	@Autowired
	private ReferralMasterdatadao referralMasterdatadao;
	
	@Autowired
	private TaxDetailsMasterdatadao taxDetailsMasterdatadao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ServiceMasterDataDao serviceMasterDataDao;
	
	@Autowired
	private UserResourceMapper userResourceMapper;
	
	@Autowired 
	private StaffScheduleMapper staffScheduleMapper;
	
	@Autowired
	private StaffScheduleDao staffScheduleDao;
	
	@Autowired
	private ClosedDatesMapper closedDatesMapper;
	
	@Autowired
	private ClosedDatesdao closedDatesdao;
	
	@Autowired
	private BranchDao branchDao;
	
	
	@Override
	public boolean saveCancelOrderReason(CancelOrderDto cancelOrderDto) 
	{
		LOGGER.info("saveCancelOrderReason(CancelOrderDto cancelOrderDto) ---Start");
		
		boolean reasonStatus=false;
		
		try
		{
			if(null!=cancelOrderDto.getCancel_order_reason())
			{
				ApplicationUser applicationUser=userDao.findByUUID(cancelOrderDto.getUserId());
				OrderMasterdata orderMasterdata=cancelOrderMapper.prpareEntity(cancelOrderDto);
				orderMasterdata.setOrganisation(applicationUser.getOrganisation());
				orderMasterDatadao.create(orderMasterdata);
				reasonStatus= true;
			}else
			{
				reasonStatus=false;
			}
			
		}catch(HibernateException e)
		{   
			e.printStackTrace();
			LOGGER.error("saveCancelOrderReason(CancelOrderDto cancelOrderDto)"+e);			
		}
		LOGGER.info("saveCancelOrderReason(CancelOrderDto cancelOrderDto) ---End");
		return reasonStatus;
	}

	@Override
	public boolean saveReferralSource(ReferralSourceDto referralSourceDto) {
		LOGGER.info("saveReferralSource(ReferralSourceDto referralSourceDto) ---Start");
		
		boolean referralSourceStatus=false;
		
		try
		{
			
			if(null!=referralSourceDto.getReferralName())
			{
				ApplicationUser applicationUser=userDao.findByUUID(referralSourceDto.getUserId());
				ReferralMasterdata referralMasterdata=referralSourceMapper.prpareEntity(referralSourceDto);
				referralMasterdata.setOrganisation(applicationUser.getOrganisation());
				referralMasterdatadao.create(referralMasterdata);
				referralSourceStatus= true;
			}else
			{
				referralSourceStatus=false;
			}
			
		}catch(HibernateException e)
		{
			LOGGER.error("saveReferralSource(ReferralSourceDto referralSourceDto)"+e);
			e.printStackTrace();
		}
		
		LOGGER.info("saveReferralSource(ReferralSourceDto referralSourceDto) ---End");
		
		return referralSourceStatus;
	}

	@Override
	public boolean saveTaxDetails(TaxDto taxDto) {
		LOGGER.info("saveTaxDetails(TaxDto taxDto) ---Start");
		
		boolean taxDetailsStatus = false;
		
		try
		{
			if(null!=taxDto.getTaxName())
			{
				ApplicationUser applicationUser=userDao.findByUUID(taxDto.getUserId());
				TaxMasterdata taxMasterdata=taxDetailsMapper.prpareEntity(taxDto);
				taxMasterdata.setOrganisation(applicationUser.getOrganisation());
				taxDetailsMasterdatadao.create(taxMasterdata);
				taxDetailsStatus = true;
			}
		}catch(HibernateException e)
		{
		LOGGER.error("saveTaxDetails(TaxDto taxDto)"+e);
		e.printStackTrace();
		}
		LOGGER.info("saveTaxDetails(TaxDto taxDto) ---End");
		return taxDetailsStatus;
	}
	
	
	@Override
	public List<TaxDto> findAllTaxDetails(String userId) {

		LOGGER.info("findAllTaxDetails(String userId) ---- Start");
		
		List<TaxDto> taxDtos = null;
		
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			List<TaxMasterdata> taxMasterdataList = taxDetailsMasterdatadao.findAllTaxDetails(applicationUser.getOrganisation().getId());

			taxDtos = new ArrayList<TaxDto>();

			for(TaxMasterdata taxMasterdata : taxMasterdataList){
				taxDtos.add(taxDetailsMapper.prepareDto(taxMasterdata));
			}
		}catch(HibernateException e){
			LOGGER.error("findAllTaxDetails("+userId+")"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("findAllTaxDetails(String userId) ---- End");
		return taxDtos;
	}
	
	
	@Override
	public Boolean deleteTaxDetails(Integer taxId) {
		
		LOGGER.info("deleteTaxDetails(Integer taxId)-------Start");
		
		boolean success = false;
		System.out.println("in service implementation "+taxId);
		TaxMasterdata taxMasterdata = taxDetailsMasterdatadao.findById(taxId);
		
		try{
			
			taxDetailsMasterdatadao.delete(taxMasterdata);
			
			success = true;
			
		}catch(HibernateException e){
			
	      LOGGER.error("deleteTaxDetails(Integer id)"+e);
	      
	      throw new CustomRuntimeException("Hibernate Exeception", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	      
		}
		
		LOGGER.info("deleteTaxDetails(Integer id)-------End");
		
		return success;
	}

	
	@Override
	public List<CancelOrderDto> findAllCancellationReason(String userId) {

		LOGGER.info("findAllCancellationReason(String userId) ---- Start");
		
		List<CancelOrderDto> cancellationReasonDtos = null;
		
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			
			List<OrderMasterdata> orderMasterdataList = orderMasterDatadao.findAllCancellationReason(applicationUser.getOrganisation().getId());

			cancellationReasonDtos = new ArrayList<CancelOrderDto>();

			for(OrderMasterdata orderMasterdata : orderMasterdataList){
				cancellationReasonDtos.add(cancelOrderMapper.prepareDto(orderMasterdata));
			}
		}catch(HibernateException e){
			LOGGER.error("findAllCancellationReason("+userId+")"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("findAllCancellationReason(String userId) ---- End");
		return cancellationReasonDtos;
	}

	@Override
	public BranchScheduleDto getBranchScheduleDetails(Integer branchId) {
          
		LOGGER.info("getAllBranchScheduleDetails(Integer branchId) ---- Start");
          
		BranchScheduleDto branchScheduleDto=null;
		
		try{
			
			List<BranchSchedule> branchSchedulesList =serviceMasterDataDao.getBranchScheduleDetails(branchId);
	
			branchScheduleDto =	branchScheduleMapper.prepareDto(branchSchedulesList);
			 	
		}catch(HibernateException e){
			LOGGER.error("getAllBranchScheduleDetails"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getAllBranchScheduleDetails(Integer branchId) ---- End");
		return branchScheduleDto;
	}

	
	@Override
	public List<ReferralSourceDto> findAllReferralSourceDetails(String userId) {
		LOGGER.info("findAllReferralSourceDetails(String userId) ---- Start");
		
		List<ReferralSourceDto> referralSourceDtos=null;
		
		try{
			ApplicationUser applicationUser=userDao.findByUUID(userId);
			List<ReferralMasterdata> referralMasterdataList = referralMasterdatadao.findAllReferralSourceDetails(applicationUser.getOrganisation().getId());
			
			referralSourceDtos = new ArrayList<ReferralSourceDto>();
			
			for(ReferralMasterdata referralMasterdata : referralMasterdataList){
				referralSourceDtos.add(referralSourceMapper.prepareDto(referralMasterdata));
			}
		}catch(HibernateException e){
			LOGGER.error("findAllReferralSourceDetails("+userId+")"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("findAllReferralSourceDetails(String userId) ---- End");
		return referralSourceDtos;
	}

	@Override
	public boolean saveStaffDetails(StaffDto staffDto) {
		
		LOGGER.info("saveStaffDetails(StaffDto staffDto) ---Start");
		
		boolean staffDetailsStatus=false;
		
		try
		{
			
			if(null!=staffDto.getfName())
			{
				
				ApplicationUser shopOwner = userDao.findByUUID(staffDto.getUserId());
				
				ApplicationUser staffUser = userResourceMapper.prepareEntity(staffDto);
				staffUser.setOrganisation(shopOwner.getOrganisation());				
				userDao.create(staffUser);
				staffDetailsStatus= true;
			}else
			{
				staffDetailsStatus=false;
			}
			
		}catch(HibernateException e)
		{
			LOGGER.error("saveStaffDetails(StaffDto staffDto)"+e);
			e.printStackTrace();
		}
		LOGGER.info("saveStaffDetails(StaffDto staffDto) ---End");
		return staffDetailsStatus;
	}

	@Override
	public StaffDto getStaffEditDetails(Integer staffId) {
		LOGGER.info("getStaffEditDetails(Integer staffId)----------Start");
		
		StaffDto staffDto =null;
		
		try{
			
			ApplicationUser staff =userDao.findById(staffId);
			
			staffDto =userResourceMapper.prepareStaffDto(staff);
		}catch(HibernateException e){
			
			LOGGER.error("getStaffEditDetails(Integer staffId)"+e);
			e.printStackTrace();
		}
		
		LOGGER.info("getStaffEditDetails(Integer staffId)----------End");
		
		return staffDto;
	}

	@Override
	public StaffDto updateStaffEditDetails(StaffDto staffDtoReq) {
		LOGGER.info("updateStaffEditDetails(StaffDto staffDtoReq) --------Start");
		
		StaffDto staffDto = null;
		
		try{
			ApplicationUser user = userDao.findById(Integer.parseInt(staffDtoReq.getStaffId()));
			
			ApplicationUser userUpdate =userResourceMapper.prepareEntity(staffDtoReq);
			
			if(null!=userUpdate.getFirstName())
				user.setFirstName(userUpdate.getFirstName());
			if(null!=userUpdate.getLastName())
				user.setLastName(userUpdate.getLastName());
			if(null!=userUpdate.getMobile())
				user.setMobile(userUpdate.getMobile());
			if(null!=userUpdate.getEmailId())
				user.setEmailId(userUpdate.getEmailId());
			if(null!=userUpdate.getAddress1())
				user.setAddress1(userUpdate.getAddress1());
			if(null!=userUpdate.getStartDate())
				user.setStartDate(userUpdate.getStartDate());
			if(null!=userUpdate.getCity())
				user.setCity(userUpdate.getCity());
			if(null!=userUpdate.getState())
				user.setState(userUpdate.getState());
			if(null!=userUpdate.getPincode())
				user.setPincode(userUpdate.getPincode());
			if(null!=userUpdate.getUserPermission())
				user.setUserPermission(userUpdate.getUserPermission());
			
			
			userUpdate =userDao.update(user);
			
			staffDto = userResourceMapper.prepareStaffDto(userUpdate);
			
		}catch(HibernateException e){
			
			LOGGER.error("updateStaffEditDetails(StaffDto staffDtoReq)"+e);
			e.printStackTrace();
			
		}
		
		LOGGER.info("updateStaffEditDetails(StaffDto staffDtoReq) --------End");
		return staffDto;
		
	}

	@Override
	public StaffScheduleDto getStaffScheduleDetails(Integer staffId) {
		LOGGER.info("getStaffScheduleDetails(Integer staffId) ---------Start");
		
		StaffScheduleDto staffScheduleDto = null;
		
		try{
			List<StaffSchedule> staffSchedulesList = serviceMasterDataDao.getStaffScheduleDetails(staffId);
			
			staffScheduleDto =staffScheduleMapper.prepareDto(staffSchedulesList);
			
		}catch(HibernateException e){
			LOGGER.error("getStaffScheduleDetails"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("getStaffScheduleDetails(Integer staffId) ---------End");
		return staffScheduleDto;
	}

	@Override
	public boolean setStaffSchedule(StaffScheduleDto staffScheduleDto) {
		LOGGER.info("setStaffSchedule(StaffScheduleDto staffScheduleDto)---------Start");
		
		boolean staffScheduleDetailsStatus=false;
		
		try
		{
			List<StaffSchedule> staffSchedulesList =staffScheduleMapper.prepareEntity(staffScheduleDto);
			
			ApplicationUser applicationUser = userDao.findById(staffScheduleDto.getStaffId());
			
			List<StaffSchedule> getStaffScheduleList =serviceMasterDataDao.getStaffScheduleDetails(staffScheduleDto.getStaffId());
			
			if(getStaffScheduleList.size() > 0){
				
				Map<Integer, StaffSchedule> checkWeekDay = new HashMap<Integer, StaffSchedule>();
				
				for(StaffSchedule staffScheduleDB : getStaffScheduleList)
				{
					checkWeekDay.put(staffScheduleDB.getWeekDay(), staffScheduleDB);
					
				}
				
				for (StaffSchedule staffSchedule : staffSchedulesList)
				{
					staffSchedule.setApplicationUser(applicationUser);
					
					StaffSchedule obj = checkWeekDay.get(staffSchedule.getWeekDay());
					
					if(obj !=null){
						
						//if update days time of week for existing staff in staff_schedule table 
						
						obj.setDutyStartTime(staffSchedule.getDutyStartTime());
						obj.setDutyEndTime(staffSchedule.getDutyEndTime());
						
						staffScheduleDao.update(obj);
						
					}else{
						
						//if create remaining days time of week for existing in staff_schedule table
						
						staffScheduleDao.create(staffSchedule);
					}
				}
			
			}else{
				
				//if new staff week schedule save to staff_schedule table
				
				for (StaffSchedule staffSchedule : staffSchedulesList)
				{
					staffSchedule.setApplicationUser(applicationUser);
					
					staffScheduleDao.create(staffSchedule);
				}
			}
				
			staffScheduleDetailsStatus = true;	
		
		}catch(HibernateException e)
		{
			LOGGER.error("setStaffSchedule(StaffScheduleDto staffScheduleDto)"+e);	
			e.printStackTrace();
		}
		
		
		LOGGER.info("setStaffSchedule(StaffScheduleDto staffScheduleDto)---------End");
		return staffScheduleDetailsStatus;
	}

	@Override
	public boolean setClosedDates(ClosedDatesDto closedDatesDto) {
		// TODO Auto-generated method stub
	LOGGER.info("setClosedDates(ClosedDatesDto ClosedDatesDto) ---Start");
		
		boolean closedDatesStatus = false;
		
		try
		{
				/*ApplicationUser applicationUser=userDao.findByUUID(taxDto.getUserId());
				TaxMasterdata taxMasterdata=taxDetailsMapper.prpareEntity(taxDto);
				taxMasterdata.setOrganisation(applicationUser.getOrganisation());
				taxDetailsMasterdatadao.create(taxMasterdata);
				closedDatesStatus = true;*/
			     
			    Branch branch =branchDao.findById(closedDatesDto.getBranchId());
				ClosedDates closedDates=closedDatesMapper.prpareEntity(closedDatesDto);
				closedDates.setBranch(branch);
				closedDatesdao.create(closedDates);
				closedDatesStatus = true;
				
		}catch(HibernateException e)
		{
		LOGGER.error("setClosedDates(ClosedDatesDto ClosedDatesDto)"+e);
		e.printStackTrace();
		}
		LOGGER.info("setClosedDates(ClosedDatesDto ClosedDatesDto) ---End");
		return closedDatesStatus;
		
	}
	
	
	@Override
	public List<ClosedDatesDto> getAllClosedDates(Integer branchId) {
		// TODO Auto-generated method stub
		LOGGER.info("getAllClosedDates(Integer branchId) ---- Start");
		
		List<ClosedDatesDto> closedDatesDto= new ArrayList<ClosedDatesDto>();;
		
		try{
			
			/*Branch branch  =branchDao.findById(branchId);*/
			List<ClosedDates> closedDatesList = closedDatesdao.getAllClosedDates(branchId);
			for(ClosedDates closedDates : closedDatesList){
				closedDatesDto.add(closedDatesMapper.prepareDto(closedDates));
			}
		
	}
		catch(HibernateException e){
			LOGGER.error("getAllClosedDates"+e);
			e.printStackTrace();
			throw new CustomRuntimeException("HibernateException EXCEPTION", "Empty record Generated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("getAllClosedDates(Integer branchId) ---- End");
		return closedDatesDto;
}
}
