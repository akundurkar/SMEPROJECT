package in.adcast.common;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyNotFoundException;

/**
 * EnvProperties.java to retrieve environment property value. 
 * @author dinesh_sonsale
 * 
 */
public class ApplicationProperties {

	private static final Log LOGGER = LogFactory.getLog(ApplicationProperties.class);
	private static Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(final Properties appProperties) {
		properties = appProperties;
		System.out.println("-----------------Show AD Services REST Properties--------------------------");
		System.out.println(appProperties.toString());
		System.out.println("-----------------------------------------------------");
	}

	public static String getProperty(final String key) throws PropertyNotFoundException {
		final String value = properties.getProperty(key);
		if (value == null) {
			final PropertyNotFoundException exception = new PropertyNotFoundException("Requested key (" + key + ") is not defined in the Poperties Database table");
			LOGGER.error(exception);
			throw exception;
		}
		return value;
	}
	
	public static String getProperty(final ApplicationConstants key) throws PropertyNotFoundException {
		final String value = properties.getProperty(key.getPropKey());
		if (value == null) {
			final PropertyNotFoundException exception = new PropertyNotFoundException("Requested key (" + key+ ") is not defined in the Poperties Database table");
			LOGGER.error(exception);
			throw exception;
		}
		return value;
	}
}