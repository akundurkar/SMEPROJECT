package in.adcast.model;
// Generated 3 Feb, 2018 4:50:11 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "branch", catalog = "sme_development")
public class Branch implements java.io.Serializable {

	private Integer id;
	private Organisation organisation;
	private String locationName;
	private String contactNo;
	private String country;
	private String state;
	private String city;
	private String area;
	private String phone;
	private String latitude;
	private int gender;
	private Boolean acAvailable;
	private String langitude;
	private Boolean onlineBookingStatus;
	private Boolean wifiAvailable;
	private String pincode;
	private Set<BranchSchedule> branchSchedules = new HashSet<BranchSchedule>(0);
	private Set<BranchSchedule> branchSchedules_1 = new HashSet<BranchSchedule>(0);
	private Set<ServiceOffered> serviceOffereds = new HashSet<ServiceOffered>(0);
	private Set<Offers> offerses = new HashSet<Offers>(0);
	private Set<FeedbackSubdata> feedbackSubdatas = new HashSet<FeedbackSubdata>(0);
	private Set<ClosedDates> closedDateses = new HashSet<ClosedDates>(0);
	private Set<ClosedDates> closedDateses_1 = new HashSet<ClosedDates>(0);
	private Set<ClientOrder> clientOrders = new HashSet<ClientOrder>(0);
	private Set<FeedbackSubdata> feedbackSubdatas_1 = new HashSet<FeedbackSubdata>(0);
	private Set<Offers> offerses_1 = new HashSet<Offers>(0);
	private Set<ServiceOffered> serviceOffereds_1 = new HashSet<ServiceOffered>(0);
	private Set<ClientOrder> clientOrders_1 = new HashSet<ClientOrder>(0);

	public Branch() {
	}

	public Branch(Organisation organisation, int gender) {
		this.organisation = organisation;
		this.gender = gender;
	}

	public Branch(Organisation organisation, String locationName, String contactNo, String country, String state,
			String city, String area, String phone, String latitude, int gender, Boolean acAvailable, String langitude,
			Boolean onlineBookingStatus, Boolean wifiAvailable, String pincode, Set<BranchSchedule> branchSchedules,
			Set<BranchSchedule> branchSchedules_1, Set<ServiceOffered> serviceOffereds, Set<Offers> offerses,
			Set<FeedbackSubdata> feedbackSubdatas, Set<ClosedDates> closedDateses, Set<ClosedDates> closedDateses_1,
			Set<ClientOrder> clientOrders, Set<FeedbackSubdata> feedbackSubdatas_1, Set<Offers> offerses_1,
			Set<ServiceOffered> serviceOffereds_1, Set<ClientOrder> clientOrders_1) {
		this.organisation = organisation;
		this.locationName = locationName;
		this.contactNo = contactNo;
		this.country = country;
		this.state = state;
		this.city = city;
		this.area = area;
		this.phone = phone;
		this.latitude = latitude;
		this.gender = gender;
		this.acAvailable = acAvailable;
		this.langitude = langitude;
		this.onlineBookingStatus = onlineBookingStatus;
		this.wifiAvailable = wifiAvailable;
		this.pincode = pincode;
		this.branchSchedules = branchSchedules;
		this.branchSchedules_1 = branchSchedules_1;
		this.serviceOffereds = serviceOffereds;
		this.offerses = offerses;
		this.feedbackSubdatas = feedbackSubdatas;
		this.closedDateses = closedDateses;
		this.closedDateses_1 = closedDateses_1;
		this.clientOrders = clientOrders;
		this.feedbackSubdatas_1 = feedbackSubdatas_1;
		this.offerses_1 = offerses_1;
		this.serviceOffereds_1 = serviceOffereds_1;
		this.clientOrders_1 = clientOrders_1;
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
	@JoinColumn(name = "organisation_id", nullable = false)
	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Column(name = "location_name", length = 30)
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Column(name = "contact_no", length = 10)
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Column(name = "country", length = 30)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 45)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "area", length = 45)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "latitude", length = 45)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "gender", nullable = false)
	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "ac_available")
	public Boolean getAcAvailable() {
		return this.acAvailable;
	}

	public void setAcAvailable(Boolean acAvailable) {
		this.acAvailable = acAvailable;
	}

	@Column(name = "langitude", length = 45)
	public String getLangitude() {
		return this.langitude;
	}

	public void setLangitude(String langitude) {
		this.langitude = langitude;
	}

	@Column(name = "online_booking_status")
	public Boolean getOnlineBookingStatus() {
		return this.onlineBookingStatus;
	}

	public void setOnlineBookingStatus(Boolean onlineBookingStatus) {
		this.onlineBookingStatus = onlineBookingStatus;
	}

	@Column(name = "wifi_available")
	public Boolean getWifiAvailable() {
		return this.wifiAvailable;
	}

	public void setWifiAvailable(Boolean wifiAvailable) {
		this.wifiAvailable = wifiAvailable;
	}

	@Column(name = "pincode", length = 6)
	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<BranchSchedule> getBranchSchedules() {
		return this.branchSchedules;
	}

	public void setBranchSchedules(Set<BranchSchedule> branchSchedules) {
		this.branchSchedules = branchSchedules;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<BranchSchedule> getBranchSchedules_1() {
		return this.branchSchedules_1;
	}

	public void setBranchSchedules_1(Set<BranchSchedule> branchSchedules_1) {
		this.branchSchedules_1 = branchSchedules_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ServiceOffered> getServiceOffereds() {
		return this.serviceOffereds;
	}

	public void setServiceOffereds(Set<ServiceOffered> serviceOffereds) {
		this.serviceOffereds = serviceOffereds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<Offers> getOfferses() {
		return this.offerses;
	}

	public void setOfferses(Set<Offers> offerses) {
		this.offerses = offerses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<FeedbackSubdata> getFeedbackSubdatas() {
		return this.feedbackSubdatas;
	}

	public void setFeedbackSubdatas(Set<FeedbackSubdata> feedbackSubdatas) {
		this.feedbackSubdatas = feedbackSubdatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ClosedDates> getClosedDateses() {
		return this.closedDateses;
	}

	public void setClosedDateses(Set<ClosedDates> closedDateses) {
		this.closedDateses = closedDateses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ClosedDates> getClosedDateses_1() {
		return this.closedDateses_1;
	}

	public void setClosedDateses_1(Set<ClosedDates> closedDateses_1) {
		this.closedDateses_1 = closedDateses_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ClientOrder> getClientOrders() {
		return this.clientOrders;
	}

	public void setClientOrders(Set<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<FeedbackSubdata> getFeedbackSubdatas_1() {
		return this.feedbackSubdatas_1;
	}

	public void setFeedbackSubdatas_1(Set<FeedbackSubdata> feedbackSubdatas_1) {
		this.feedbackSubdatas_1 = feedbackSubdatas_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<Offers> getOfferses_1() {
		return this.offerses_1;
	}

	public void setOfferses_1(Set<Offers> offerses_1) {
		this.offerses_1 = offerses_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ServiceOffered> getServiceOffereds_1() {
		return this.serviceOffereds_1;
	}

	public void setServiceOffereds_1(Set<ServiceOffered> serviceOffereds_1) {
		this.serviceOffereds_1 = serviceOffereds_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<ClientOrder> getClientOrders_1() {
		return this.clientOrders_1;
	}

	public void setClientOrders_1(Set<ClientOrder> clientOrders_1) {
		this.clientOrders_1 = clientOrders_1;
	}

}
