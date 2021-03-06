package in.adcast.model;
// Generated 3 Feb, 2018 4:50:11 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReferralMasterdata generated by hbm2java
 */
@Entity
@Table(name = "referral_masterdata", catalog = "sme_development")
public class ReferralMasterdata implements java.io.Serializable {

	private Integer id;
	private Organisation organisation;
	private String referralType;
	private Integer referralCode;
	private Integer referralPoint;

	public ReferralMasterdata() {
	}

	public ReferralMasterdata(Organisation organisation, String referralType, Integer referralCode,
			Integer referralPoint) {
		this.organisation = organisation;
		this.referralType = referralType;
		this.referralCode = referralCode;
		this.referralPoint = referralPoint;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Column(name = "referral_type", length = 45)
	public String getReferralType() {
		return this.referralType;
	}

	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	@Column(name = "referral_code")
	public Integer getReferralCode() {
		return this.referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	@Column(name = "referral_point")
	public Integer getReferralPoint() {
		return this.referralPoint;
	}

	public void setReferralPoint(Integer referralPoint) {
		this.referralPoint = referralPoint;
	}

}
