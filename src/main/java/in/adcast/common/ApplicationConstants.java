package in.adcast.common;

public enum ApplicationConstants {

	OAUTH_URL("OAUTH_URL"),
	SECURED_REST_SERVICE_FILTERS("SECURED_REST_SERVICE_FILTERS"),
	ISRESTSECURITYREQ("ISRESTSECURITYREQ");
	private String propKey;
	
	private ApplicationConstants(final String propKey) {
		this.propKey = propKey;
    }

	/**
	 * @return the propKey
	 */
	public String getPropKey() {
		return propKey;
	}

	/**
	 * @param propKey the propKey to set
	 */
	public void setPropKey(final String propKey) {
		this.propKey = propKey;
	}
}