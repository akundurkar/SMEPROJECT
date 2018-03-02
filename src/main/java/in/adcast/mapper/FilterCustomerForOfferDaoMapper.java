package in.adcast.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.adcast.model.FilterCustomerForoffer;

@Component
public class FilterCustomerForOfferDaoMapper {


	public List<FilterCustomerForoffer> prepareDto(List<String> customerList) {

		List<FilterCustomerForoffer> filterCustomerForofferList=new ArrayList<>();

		for(String customer:customerList)
		{
			
			FilterCustomerForoffer filterCustomerForoffer=new FilterCustomerForoffer();
			
				filterCustomerForoffer.setFilterCustomerType(customer);
				
			filterCustomerForofferList.add(filterCustomerForoffer);

		}

		return filterCustomerForofferList;
	}
}
