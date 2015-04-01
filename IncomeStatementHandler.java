import java.util.ArrayList;




public class IncomeStatementHandler implements ReportLoader.TagHandler{
	private IncomeStatementReport report;
	int calls=0;
	boolean nextDates=false;
	private ArrayList<IncomeStatementReport.Year> years = new ArrayList<IncomeStatementReport.Year>();

	@Override
	public boolean acceptsTag(Tag tag) {
		if(tag.getName().equals("table"))
			return true;
		return false;
	}

	@Override
	public ReportLoader.Report getReport() {
		return new IncomeStatementReport(years);
	}

	@Override
	public void handleContent(String data, Tag[] innerTags, Tag[] enclosingTags) {
		calls++;
		data = Types.convertToPlainString(data);
		data = data.trim();
		
		if(calls>20)
		{
			for(int i=0; i<data.length(); i++)
			{
				if(data.charAt(i) == ',')
				{
					data = data.substring(0, i) + data.substring(i+1);
				}
			}
		}
		if(data.equals("-"))
			data = "0";
		else if(data.charAt(0) == '(' && data.charAt(data.length()-1) == ')')
		{
			data = "-" + data.substring(1, data.length()-1);
		}
		
		switch(calls)
		{
		case 18:
		{
			IncomeStatementReport.Year year = new IncomeStatementReport.Year(data);
			years.add(year);
		}break;
		case 19:
		{
			IncomeStatementReport.Year year = new IncomeStatementReport.Year(data);
			years.add(year);
		}break;
		case 20:
		{
			IncomeStatementReport.Year year = new IncomeStatementReport.Year(data);
			years.add(year);
		}break;
		case 22:
		{
			years.get(0).put("Total Revenue", Long.valueOf(data));
		}break;
		case 23:
		{
			years.get(1).put("Total Revenue", Long.valueOf(data));
		}break;
		case 24:
		{
			years.get(2).put("Total Revenue", Long.valueOf(data));
		}break;
		case 26:
		{
			years.get(0).put("Cost Of Revenue", Long.valueOf(data));
		}break;
		case 27:
		{
			years.get(1).put("Cost Of Revenue", Long.valueOf(data));
		}break;
		case 28:
		{
			years.get(2).put("Cost Of Revenue", Long.valueOf(data));
		}break;
		case 30:
		{
			years.get(0).put("Gross Profit", Long.valueOf(data));
		}break;
		case 31:
		{
			years.get(1).put("Gross Profit", Long.valueOf(data));
		}break;
		case 32:
		{
			years.get(2).put("Gross Profit", Long.valueOf(data));
		}break;
		case 35:
		{
			years.get(0).put("Research & Development", Long.valueOf(data));
		}break;
		case 36:
		{
			years.get(1).put("Research & Development", Long.valueOf(data));
		}break;
		case 37:
		{
			years.get(2).put("Research & Development", Long.valueOf(data));
		}break;
		case 39:
		{
			years.get(0).put("Sales & Administrative", Long.valueOf(data));
		}break;
		case 40:
		{
			years.get(1).put("Sales & Administrative", Long.valueOf(data));
		}break;
		case 41:
		{
			years.get(2).put("Sales & Administrative", Long.valueOf(data));
		}break;
		case 43:
		{
			years.get(0).put("Non Recurring", Long.valueOf(data));
		}break;
		case 44:
		{
			years.get(1).put("Non Recurring", Long.valueOf(data));
		}break;
		case 45:
		{
			years.get(2).put("Non Recurring", Long.valueOf(data));
		}break;
		case 47:
		{
			years.get(0).put("Other Expenses", Long.valueOf(data));
		}break;
		case 48:
		{
			years.get(1).put("Other Expenses", Long.valueOf(data));
		}break;
		case 49:
		{
			years.get(2).put("Other Expenses", Long.valueOf(data));
		}break;
		case 51:
		{
			years.get(0).put("Total Operating Expenses", Long.valueOf(data));
		}break;
		case 52:
		{
			years.get(1).put("Total Operating Expenses", Long.valueOf(data));
		}break;
		case 53:
		{
			years.get(2).put("Total Operating Expenses", Long.valueOf(data));
		}break;
		case 55:
		{
			years.get(0).put("Operating Income/Loss", Long.valueOf(data));
		}break;
		case 56:
		{
			years.get(1).put("Operating Income/Loss", Long.valueOf(data));
		}break;
		case 57:
		{
			years.get(2).put("Operating Income/Loss", Long.valueOf(data));
		}break;
		case 60:
		{
			years.get(0).put("Total Other Income Expense", Long.valueOf(data));
		}break;
		case 61:
		{
			years.get(1).put("Total Other Income Expense", Long.valueOf(data));
		}break;
		case 62:
		{
			years.get(2).put("Total Other Income Expense", Long.valueOf(data));
		}break;
		case 64:
		{
			years.get(0).put("Earnings Before Interest/Taxes", Long.valueOf(data));
		}break;
		case 65:
		{
			years.get(1).put("Earnings Before Interest/Taxes", Long.valueOf(data));
		}break;
		case 66:
		{
			years.get(2).put("Earnings Before Interest/Taxes", Long.valueOf(data));
		}break;
		case 68:
		{
			years.get(0).put("Interest Expense", Long.valueOf(data));
		}break;
		case 69:
		{
			years.get(1).put("Interest Expense", Long.valueOf(data));
		}break;
		case 70:
		{
			years.get(2).put("Interest Expense", Long.valueOf(data));
		}break;
		case 72:
		{
			years.get(0).put("Income Before Tax", Long.valueOf(data));
		}break;
		case 73:
		{
			years.get(1).put("Income Before Tax", Long.valueOf(data));
		}break;
		case 74:
		{
			years.get(2).put("Income Before Tax", Long.valueOf(data));
		}break;
		case 76:
		{
			years.get(0).put("Income Tax Expense", Long.valueOf(data));
		}break;
		case 77:
		{
			years.get(1).put("Income Tax Expense", Long.valueOf(data));
		}break;
		case 78:
		{
			years.get(2).put("Income Tax Expense", Long.valueOf(data));
		}break;
		case 80:
		{
			years.get(0).put("Minority Interest", Long.valueOf(data));
		}break;
		case 81:
		{
			years.get(1).put("Minority Interest", Long.valueOf(data));
		}break;
		case 82:
		{
			years.get(2).put("Minority Interest", Long.valueOf(data));
		}break;
		case 84:
		{
			years.get(0).put("Net Income From Continuing Ops", Long.valueOf(data));
		}break;
		case 85:
		{
			years.get(1).put("Net Income From Continuing Ops", Long.valueOf(data));
		}break;
		case 86:
		{
			years.get(2).put("Net Income From Continuing Ops", Long.valueOf(data));
		}break;
		case 89:
		{
			years.get(0).put("Discontinued Ops", Long.valueOf(data));
		}break;
		case 90:
		{
			years.get(1).put("Discontinued Ops", Long.valueOf(data));
		}break;
		case 91:
		{
			years.get(2).put("Discontinued Ops", Long.valueOf(data));
		}break;
		case 93:
		{
			years.get(0).put("Extraordinary Items", Long.valueOf(data));
		}break;
		case 94:
		{
			years.get(1).put("Extraordinary Items", Long.valueOf(data));
		}break;
		case 95:
		{
			years.get(2).put("Extraordinary Items", Long.valueOf(data));
		}break;
		case 97:
		{
			years.get(0).put("Effect Of Accounting Changes", Long.valueOf(data));
		}break;
		case 98:
		{
			years.get(1).put("Effect Of Accounting Changes", Long.valueOf(data));
		}break;
		case 99:
		{
			years.get(2).put("Effect Of Accounting Changes", Long.valueOf(data));
		}break;
		case 101:
		{
			years.get(0).put("Other Items", Long.valueOf(data));
		}break;
		case 102:
		{
			years.get(1).put("Other Items", Long.valueOf(data));
		}break;
		case 103:
		{
			years.get(2).put("Other Items", Long.valueOf(data));
		}break;
		case 105:
		{
			years.get(0).put("Net Income", Long.valueOf(data));
		}break;
		case 106:
		{
			years.get(1).put("Net Income", Long.valueOf(data));
		}break;
		case 107:
		{
			years.get(2).put("Net Income", Long.valueOf(data));
		}break;
		case 113:
		{
			years.get(0).put("Net Income Applicable to Common Shares", Long.valueOf(data));
		}break;
		case 114:
		{
			years.get(1).put("Net Income Applicable to Common Shares", Long.valueOf(data));
		}break;
		case 115:
		{
			years.get(2).put("Net Income Applicable to Common Shares", Long.valueOf(data));
		}break;
		
		
		
		}
		
		
		
	}

}
