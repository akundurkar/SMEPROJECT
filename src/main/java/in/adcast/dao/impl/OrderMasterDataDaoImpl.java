package in.adcast.dao.impl;

import java.util.List;

import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import in.adcast.common.GenericDAOImpl;

import in.adcast.dao.OrderMasterDatadao;

import in.adcast.dto.CancelOrderDto;

import in.adcast.model.OrderMasterdata;


@SuppressWarnings("serial")
@Repository
public class OrderMasterDataDaoImpl extends GenericDAOImpl<OrderMasterdata,Integer> implements OrderMasterDatadao  
{
	

	@Override
	public boolean savecancelOrderReason(CancelOrderDto cancelOrderDto) 
	{
	
		return false;
	}

	@Override
	public List<OrderMasterdata> findAllCancellationReason(Integer organizationId) {
		Query query = entityManager.createQuery("SELECT o FROM OrderMasterdata o where o.organisation.id = " + organizationId);
	    return (List<OrderMasterdata>) query.getResultList();
		}

}
