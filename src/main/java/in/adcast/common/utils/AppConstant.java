package in.adcast.common.utils;

public class AppConstant {
	
	
   public boolean NEW_PROGRAM_AVAILABLE = true;
   public boolean NEW_PROGRAM_NOT_AVAILABLE = false;
   
   public static String ERROR = "{\"status\":\"error\"}";
   public static String SUCCESS = "{\"status\":\"Ok\"}";
   public final static String SERVER_URL="http://localhost:8080";
   public final static String UI_SERVER_URL="http://localhost:9000";

	public enum Account{
		
		DEACTIVE(0),
		ACTIVE(1),
		NEW(2);
		
		private final int value;

		Account(final int newValue) {
			
			value = newValue;
			
		}

		public int getValue() { 
			
			return value; 
			
		}
		
	}

	public enum NotificationSubscriptionType{
		
		SMS(0),
		EMAIL(1),
		BOTH(2),
		DND(3);
		
		private final int value;

		NotificationSubscriptionType(final int newValue) {
			
			value = newValue;
		}

		public int getValue() { 
			
			return value; 
			
		}
		
	}

	public enum OrderStatus{
		
		BOOKED(0),
		CONFIRMED(1),
		REJECT(2),
		NOSHOW(3),
		COMPLETE(4),
		CHECKOUT(5);
		
		private final int value;

		OrderStatus(final int newValue) {
			
			value = newValue;
			
		}

		public int getValue() { 
			
			return value; 
			
		}
		
	}

	public enum AccountRole{
		
		ADMIN_SME(0),
		SHOP_OWNER(1),
		ADMIN_SHOP(2),
		EMPLOYEE_SME(3),
		EMPLOYEE_SHOP(4);
		
		private final int value;
		
		private AccountRole(final int newValue) {
			
			value = newValue;
			
		}
		
		public int getValue() { 
			
			return value; 
		
		}
		
	}
	
	public enum WeekDay{
		
		SUNDAY(0),
		MONDAY(1),
		TUESDAY(2),
		WEDNESDAY(3),
		THURSDAY(4),
		FRIDAY(5),
		SATURDAY(6);
		
		private final int value;
		
		private WeekDay(final int newValue) {
			
			value = newValue;
			
		}
		
		public int getValue() { 
			
			return value;
			
		}
		
	}
	
	public enum FileType{
		
		PDF(0),
		IMAGE(1);
		
		private final int value;
		
		private FileType(final int newValue) {
			
			value = newValue;
			
		}
		
		public int getValue() { 
			
			return value; 
		
		}
		
	}

	public enum DocumentType{
		
		Address(0),
		ID(1),
		PAN(2),
		TAX(3);
		
		private final int value;
		
		private DocumentType(final int newValue) {
			
			value = newValue;
			
		}
		
		public int getValue() { 
			
			return value; 
		
		}
		
	}

	public enum GenderType{
		
		FEMALE(0),
		MALE(1),
		UNISEX(2);
		
		private final int value;
		
		private GenderType(final int newValue) {
			
			value = newValue;
			
		}
		
		public int getValue() { 
			
			return value; 
		
		}
		
	}	
	
	public enum Experience{
		
		EXCELLENT(0),
		GOOD(1),
		FAIR(2),
		POOR(3),
		VERYPOOR(4);
		
		private final int value;

		Experience(final int newValue) {
			
			value = newValue;
			
		}

		public int getValue() { 
			
			return value; 
			
		}
		
	}

}
