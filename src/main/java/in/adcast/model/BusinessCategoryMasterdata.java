package in.adcast.model;
// Generated 3 Feb, 2018 4:50:11 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BusinessCategoryMasterdata generated by hbm2java
 */
@Entity
@Table(name = "business_category_masterdata", catalog = "sme_development")
public class BusinessCategoryMasterdata implements java.io.Serializable {

	private int id;
	private String businessCategoryType;
	private Set<BusinessTypeMasterdata> businessTypeMasterdatas = new HashSet<BusinessTypeMasterdata>(0);
	private Set<BusinessTypeMasterdata> businessTypeMasterdatas_1 = new HashSet<BusinessTypeMasterdata>(0);

	public BusinessCategoryMasterdata() {
	}

	public BusinessCategoryMasterdata(int id) {
		this.id = id;
	}

	public BusinessCategoryMasterdata(int id, String businessCategoryType,
			Set<BusinessTypeMasterdata> businessTypeMasterdatas,
			Set<BusinessTypeMasterdata> businessTypeMasterdatas_1) {
		this.id = id;
		this.businessCategoryType = businessCategoryType;
		this.businessTypeMasterdatas = businessTypeMasterdatas;
		this.businessTypeMasterdatas_1 = businessTypeMasterdatas_1;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "business_category_type", length = 256)
	public String getBusinessCategoryType() {
		return this.businessCategoryType;
	}

	public void setBusinessCategoryType(String businessCategoryType) {
		this.businessCategoryType = businessCategoryType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessCategoryMasterdata")
	public Set<BusinessTypeMasterdata> getBusinessTypeMasterdatas() {
		return this.businessTypeMasterdatas;
	}

	public void setBusinessTypeMasterdatas(Set<BusinessTypeMasterdata> businessTypeMasterdatas) {
		this.businessTypeMasterdatas = businessTypeMasterdatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessCategoryMasterdata")
	public Set<BusinessTypeMasterdata> getBusinessTypeMasterdatas_1() {
		return this.businessTypeMasterdatas_1;
	}

	public void setBusinessTypeMasterdatas_1(Set<BusinessTypeMasterdata> businessTypeMasterdatas_1) {
		this.businessTypeMasterdatas_1 = businessTypeMasterdatas_1;
	}

}
