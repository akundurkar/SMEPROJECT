package in.adcast.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.adcast.common.utils.AppConstant;
import in.adcast.dao.FeedbackMasterdataDao;
import in.adcast.dto.FeedBackDto;
import in.adcast.model.FeedbackMasterdata;
import in.adcast.model.FeedbackSubdata;


@Component
public class FeedbackSubdataMapper 
{
	
	@Autowired
	private FeedbackMasterdataDao feedbackMasterdataDao;
	

	public List<FeedbackSubdata> prepareEntityList(FeedBackDto feedBackDto) {
		
		List<FeedbackSubdata> feedbackSubdatasList = new ArrayList<>();
		
		
			if(null !=feedBackDto.getServiceExperience())
			{
			
				FeedbackSubdata feedbackSubdata = new FeedbackSubdata();
				
				FeedbackMasterdata feedbackMasterdata = feedbackMasterdataDao.findById(feedBackDto.getServiceExperienceQue());
				feedbackSubdata.setFeedbackMasterdata(feedbackMasterdata);
				
				switch(feedBackDto.getServiceExperience())
				{
				case "EXCELLENT":
					
					feedbackSubdata.setAnswer(Integer.toString(AppConstant.Experience.EXCELLENT.getValue()));
					
					break;
					
				case "GOOD":
					
					feedbackSubdata.setAnswer(Integer.toString(AppConstant.Experience.GOOD.getValue()));
					
					break;
				
				case "FAIR":
					
					feedbackSubdata.setAnswer(Integer.toString(AppConstant.Experience.FAIR.getValue()));
					
					break;
					
				case "POOR":
					
					feedbackSubdata.setAnswer(Integer.toString(AppConstant.Experience.POOR.getValue()));
					
					break;
				
				case "VERYPOOR":
					
					feedbackSubdata.setAnswer(Integer.toString(AppConstant.Experience.VERYPOOR.getValue()));
					
					break;
					
				}
				
				feedbackSubdatasList.add(feedbackSubdata);
				
			}
		
			if(null !=feedBackDto.getOfferExperience())
			{
				
				FeedbackSubdata feedbackSubdata1 = new FeedbackSubdata();
				
				FeedbackMasterdata feedbackMasterdata = feedbackMasterdataDao.findById(feedBackDto.getOfferExperienceQue());
				feedbackSubdata1.setFeedbackMasterdata(feedbackMasterdata);
				
				switch(feedBackDto.getOfferExperience())
				{
				case "EXCELLENT":
					
					feedbackSubdata1.setAnswer(Integer.toString(AppConstant.Experience.EXCELLENT.getValue()));
					
					break;
					
				case "GOOD":
					
					feedbackSubdata1.setAnswer(Integer.toString(AppConstant.Experience.GOOD.getValue()));
					
					break;
				
				case "FAIR":
					
					feedbackSubdata1.setAnswer(Integer.toString(AppConstant.Experience.FAIR.getValue()));
					
					break;
					
				case "POOR":
					
					feedbackSubdata1.setAnswer(Integer.toString(AppConstant.Experience.POOR.getValue()));
					
					break;
				
				case "VERYPOOR":
					
					feedbackSubdata1.setAnswer(Integer.toString(AppConstant.Experience.VERYPOOR.getValue()));
					
					break;
					
				}
				
				feedbackSubdatasList.add(feedbackSubdata1);
				
			}
		
			if(null !=feedBackDto.getComments()){
				
				FeedbackSubdata feedbackSubdata2 = new FeedbackSubdata();
				
				FeedbackMasterdata feedbackMasterdata = feedbackMasterdataDao.findById(feedBackDto.getCommentsQue());
				
				feedbackSubdata2.setFeedbackMasterdata(feedbackMasterdata);
				
				feedbackSubdata2.setAnswer(feedBackDto.getComments());
				
				feedbackSubdatasList.add(feedbackSubdata2);
			}
			
		return feedbackSubdatasList;
	}


	public FeedBackDto prepareDto(FeedbackSubdata feedbackSubdata) {
		
		FeedBackDto feedBackDto = new FeedBackDto();
		
		if(null != feedbackSubdata.getId())
			feedBackDto.setFeedbackId(feedbackSubdata.getId());
		
		if(null != feedbackSubdata.getClient())
			feedBackDto.setFirstName(feedbackSubdata.getClient().getFirstName());
		if(null != feedbackSubdata.getClient())
			feedBackDto.setLastName(feedbackSubdata.getClient().getLastName());
		
		/*if((String)feedbackSubdata.getAnswer()!= null)
		{
			switch(AppConstant.GenderType.values()[feedbackSubdata.getAnswer()].toString())
			{
			
			case "FEMALE":
				branchDto.setGender(AppConstant.GenderType.FEMALE.toString());
				break;
				
			case "MALE":
				branchDto.setGender(AppConstant.GenderType.MALE.toString());
				break;
				
			case "UNISEX":
				branchDto.setGender(AppConstant.GenderType.UNISEX.toString());
				break;
			
			}
		}
		*/
		
		return null;
	}

	
	
	
}
