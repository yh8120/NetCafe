package domain;

public class TimeDisplay {
	public static String timeDisplay(Long millisecond) {
		Integer intHour = (int) (millisecond / 3600000);
		Integer intMinute = (int) ((millisecond % 3600000) / 60000);
		Integer intSeccod = (int) (((millisecond % 3600000) % 60000) / 1000);

		String timeDisplay = String.format("%02d:%02d:%02d", intHour, intMinute, intSeccod);
		return timeDisplay;
	}

}
