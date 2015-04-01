import java.util.ArrayList;




public class IncomeStatementReport implements ReportLoader.Report{
	
	private Ticker ticker;
	private ArrayList<Year> years = new ArrayList<Year>();
	
	public IncomeStatementReport(ArrayList<Year> years)
	{
		this.years=years;
	}
	public Ticker getTicker()
	{
		return ticker;
	}
	public static class Year
	{
		private String id;
		public ArrayList<DataEntry> data= new ArrayList<DataEntry>();
		
		private class DataEntry
		{
			public DataEntry(String s, long l)
			{
				key=s;
				value=l;
			}
			public String key;
			public long value;
		}
		public Year(String id)
		{
			this.id=id;
		}
		public void put(String s, long l)
		{
			data.add(new DataEntry(s, l));
		}
		
		@Override
		public String toString() {
			String s=id+"\n";
			
			for (DataEntry e: data)
			{
				s += e.key+"="+e.value+", ";
			}
			s+="\n";
			return s;
		}
		
		
		
	}

	@Override
	public String getId() {
		return "income_statement";
	}
	public void addYear(Year year)
	{
		years.add(year);
	}
	public Year[] getYears()
	{
		return years.toArray(new Year[years.size()]);
	}
	@Override
	public String toString() {
		String s= "IncomeStatementReport("+ticker+")"+"\n";
		
		for(Year y: years)
		{
			s +=y;
		}
		return s;
	}

}
