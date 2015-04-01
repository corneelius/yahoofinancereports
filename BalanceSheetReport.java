import java.util.ArrayList;
import java.util.Map;




public class BalanceSheetReport implements ReportLoader.Report{
	private Ticker ticker;
	private ArrayList<Year> years = new ArrayList<Year>();

	public BalanceSheetReport(ArrayList<Year> years)
	{
		this.years=years;
	}
	public Ticker getTicker()
	{
		return ticker;
	}
	public Year[] getYears()
	{
		return years.toArray(new Year[years.size()]);
	}
	
	@Override
	public String getId() {
		return "balance_sheet";
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
	public String toString() {
		String s= "BalanceSheetReport("+ticker+")"+"\n";
		
		for(Year y: years)
		{
			s +=y;
		}
		return s;
	}

}
