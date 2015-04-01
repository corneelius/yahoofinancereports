import java.util.ArrayList;



public class CashFlowStatementHandler implements ReportLoader.TagHandler{
	private IncomeStatementReport report;
	int calls=0;
	boolean nextDates=false;
	private ArrayList<CashFlowReport.Year> years = new ArrayList<CashFlowReport.Year>();

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
			CashFlowReport.Year year = new CashFlowReport.Year(data);
			years.add(year);
		}break;
		case 19:
		{
			CashFlowReport.Year year = new CashFlowReport.Year(data);
			years.add(year);
		}break;
		case 20:
		{
			CashFlowReport.Year year = new CashFlowReport.Year(data);
			years.add(year);
		}break;
		case 22:
		{
			years.get(0).put("Net Income", Long.valueOf(data));
		}break;
		case 23:
		{
			years.get(1).put("Net Income", Long.valueOf(data));
		}break;
		case 24:
		{
			years.get(2).put("Net Income", Long.valueOf(data));
		}break;
		case 27:
		{
			years.get(0).put("Depreciation", Long.valueOf(data));
		}break;
		case 28:
		{
			years.get(1).put("Depreciation", Long.valueOf(data));
		}break;
		case 29:
		{
			years.get(2).put("Depreciation", Long.valueOf(data));
		}break;
		case 31:
		{
			years.get(0).put("Adjustments To Net Income", Long.valueOf(data));
		}break;
		case 32:
		{
			years.get(1).put("Adjustments To Net Income", Long.valueOf(data));
		}break;
		case 33:
		{
			years.get(2).put("Adjustments To Net Income", Long.valueOf(data));
		}break;
		case 35:
		{
			years.get(0).put("Changes In Accounts Receivables", Long.valueOf(data));
		}break;
		case 36:
		{
			years.get(1).put("Changes In Accounts Receivables", Long.valueOf(data));
		}break;
		case 37:
		{
			years.get(2).put("Changes In Accounts Receivables", Long.valueOf(data));
		}break;
		case 39:
		{
			years.get(0).put("Changes In Liabilities", Long.valueOf(data));
		}break;
		case 40:
		{
			years.get(1).put("Changes In Liabilities", Long.valueOf(data));
		}break;
		case 41:
		{
			years.get(2).put("Changes In Liabilities", Long.valueOf(data));
		}break;
		case 43:
		{
			years.get(0).put("Changes In Inventory", Long.valueOf(data));
		}break;
		case 44:
		{
			years.get(1).put("Changes In Inventory", Long.valueOf(data));
		}break;
		case 45:
		{
			years.get(2).put("Changes In Inventory", Long.valueOf(data));
		}break;
		case 47:
		{
			years.get(0).put("Changes In Other Operating Activities", Long.valueOf(data));
		}break;
		case 48:
		{
			years.get(1).put("Changes In Other Operating Activities", Long.valueOf(data));
		}break;
		case 49:
		{
			years.get(2).put("Changes In Other Operating Activities", Long.valueOf(data));
		}break;
		case 51:
		{
			years.get(0).put("Total Cash Flow From Operating Activities", Long.valueOf(data));
		}break;
		case 52:
		{
			years.get(1).put("Total Cash Flow From Operating Activities", Long.valueOf(data));
		}break;
		case 53:
		{
			years.get(2).put("Total Cash Flow From Operating Activities", Long.valueOf(data));
		}break;
		case 56:
		{
			years.get(0).put("Capital Expenditures", Long.valueOf(data));
		}break;
		case 57:
		{
			years.get(1).put("Capital Expenditures", Long.valueOf(data));
		}break;
		case 58:
		{
			years.get(2).put("Capital Expenditures", Long.valueOf(data));
		}break;
		case 60:
		{
			years.get(0).put("Investments", Long.valueOf(data));
		}break;
		case 61:
		{
			years.get(1).put("Investments", Long.valueOf(data));
		}break;
		case 62:
		{
			years.get(2).put("Investments", Long.valueOf(data));
		}break;
		case 64:
		{
			years.get(0).put("Other Cash Flow From Investing Activities", Long.valueOf(data));
		}break;
		case 65:
		{
			years.get(1).put("Other Cash Flow From Investing Activities", Long.valueOf(data));
		}break;
		case 66:
		{
			years.get(2).put("Other Cash Flow From Investing Activities", Long.valueOf(data));
		}break;
		case 68:
		{
			years.get(0).put("Total Cash Flows From Investing Activities", Long.valueOf(data));
		}break;
		case 69:
		{
			years.get(1).put("Total Cash Flows From Investing Activities", Long.valueOf(data));
		}break;
		case 70:
		{
			years.get(2).put("Total Cash Flows From Investing Activities", Long.valueOf(data));
		}break;
		case 73:
		{
			years.get(0).put("Dividends Paid", Long.valueOf(data));
		}break;
		case 74:
		{
			years.get(1).put("Dividends Paid", Long.valueOf(data));
		}break;
		case 75:
		{
			years.get(2).put("Dividends Paid", Long.valueOf(data));
		}break;
		case 77:
		{
			years.get(0).put("Sale Purchase Of Stock", Long.valueOf(data));
		}break;
		case 78:
		{
			years.get(1).put("Sale Purchase Of Stock", Long.valueOf(data));
		}break;
		case 79:
		{
			years.get(2).put("Sale Purchase Of Stock", Long.valueOf(data));
		}break;
		case 81:
		{
			years.get(0).put("Net Borrowing", Long.valueOf(data));
		}break;
		case 82:
		{
			years.get(1).put("Net Borrowing", Long.valueOf(data));
		}break;
		case 83:
		{
			years.get(2).put("Net Borrowing", Long.valueOf(data));
		}break;
		
		case 85:
		{
			years.get(0).put("Other Cash Flows From Financing Activities", Long.valueOf(data));
		}break;
		case 86:
		{
			years.get(1).put("Other Cash Flows From Financing Activities", Long.valueOf(data));
		}break;
		case 87:
		{
			years.get(2).put("Other Cash Flows From Financing Activities", Long.valueOf(data));
		}break;
		case 89:
		{
			years.get(0).put("Total Cash Flow From Financing Activity", Long.valueOf(data));
		}break;
		case 90:
		{
			years.get(1).put("Total Cash Flow From Financing Activity", Long.valueOf(data));
		}break;
		case 91:
		{
			years.get(2).put("Total Cash Flow From Financing Activity", Long.valueOf(data));
		}break;
		case 93:
		{
			years.get(0).put("Effect Of Exchange Rate Changes", Long.valueOf(data));
		}break;
		case 94:
		{
			years.get(1).put("Effect Of Exchange Rate Changes", Long.valueOf(data));
		}break;
		case 95:
		{
			years.get(2).put("Effect Of Exchange Rate Changes", Long.valueOf(data));
		}break;
		case 97:
		{
			years.get(0).put("Change In Cash and Cash Equivalents", Long.valueOf(data));
		}break;
		case 98:
		{
			years.get(1).put("Change In Cash and Cash Equivalents", Long.valueOf(data));
		}break;
		case 99:
		{
			years.get(2).put("Change In Cash and Cash Equivalents", Long.valueOf(data));
		}break;
		
		
		
		
		}
		
	}

	@Override
	public boolean acceptsTag(Tag tag) {
		if(tag.getName().equals("table"))
			return true;
		return false;
	}

	@Override
	public ReportLoader.Report getReport() {
		return new CashFlowReport(years);
	}

}
