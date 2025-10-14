package tutorial;

public class Calendar {
	
	private Day today;

	public void setDay(Day day) {
		this.today = day;
	}

	public boolean askTodayIs(Day day) {
		return today.equals(day);
	}
	
	

}
