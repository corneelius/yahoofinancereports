import java.util.ArrayList;



public class BalanceSheetHandler implements ReportLoader.TagHandler{
	private IncomeStatementReport report;
	int calls=0;
	boolean nextDates=false;
	private ArrayList<BalanceSheetReport.Year> years = new ArrayList<BalanceSheetReport.Year>();

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
			BalanceSheetReport.Year year = new BalanceSheetReport.Year(data);
			years.add(year);
		}break;
		case 19:
		{
			BalanceSheetReport.Year year = new BalanceSheetReport.Year(data);
			years.add(year);
		}break;
		case 20:
		{
			BalanceSheetReport.Year year = new BalanceSheetReport.Year(data);
			years.add(year);
		}break;
		case 24:
		{
			years.get(0).put("Cash", Long.valueOf(data));
		}break;
		case 25:
		{
			years.get(1).put("Cash", Long.valueOf(data));
		}break;
		case 26:
		{
			years.get(2).put("Cash", Long.valueOf(data));
		}break;
		case 28:
		{
			years.get(0).put("Short Term Investments", Long.valueOf(data));
		}break;
		case 29:
		{
			years.get(1).put("Short Term Investments", Long.valueOf(data));
		}break;
		case 30:
		{
			years.get(2).put("Short Term Investments", Long.valueOf(data));
		}break;
		case 32:
		{
			years.get(0).put("Net Receivables", Long.valueOf(data));
		}break;
		case 33:
		{
			years.get(1).put("Net Receivables", Long.valueOf(data));
		}break;
		case 34:
		{
			years.get(2).put("Net Receivables", Long.valueOf(data));
		}break;
		case 36:
		{
			years.get(0).put("Inventory", Long.valueOf(data));
		}break;
		case 37:
		{
			years.get(1).put("Inventory", Long.valueOf(data));
		}break;
		case 38:
		{
			years.get(2).put("Inventory", Long.valueOf(data));
		}break;
		case 40:
		{
			years.get(0).put("Other Current Assets", Long.valueOf(data));
		}break;
		case 41:
		{
			years.get(1).put("Other Current Assets", Long.valueOf(data));
		}break;
		case 42:
		{
			years.get(2).put("Other Current Assets", Long.valueOf(data));
		}break;
		case 44:
		{
			years.get(0).put("Total Current Assets", Long.valueOf(data));
		}break;
		case 45:
		{
			years.get(1).put("Total Current Assets", Long.valueOf(data));
		}break;
		case 46:
		{
			years.get(2).put("Total Current Assets", Long.valueOf(data));
		}break;
		case 48:
		{
			years.get(0).put("long_term_investments", Long.valueOf(data));
		}break;
		case 49:
		{
			years.get(1).put("long_term_investments", Long.valueOf(data));
		}break;
		case 50:
		{
			years.get(2).put("long_term_investments", Long.valueOf(data));
		}break;
		case 52:
		{
			years.get(0).put("Property Plant Equipment", Long.valueOf(data));
		}break;
		case 53:
		{
			years.get(1).put("Property Plant Equipment", Long.valueOf(data));
		}break;
		case 54:
		{
			years.get(2).put("Property Plant Equipment", Long.valueOf(data));
		}break;
		case 56:
		{
			years.get(0).put("Goodwill", Long.valueOf(data));
		}break;
		case 57:
		{
			years.get(1).put("Goodwill", Long.valueOf(data));
		}break;
		case 58:
		{
			years.get(2).put("Goodwill", Long.valueOf(data));
		}break;
		case 60:
		{
			years.get(0).put("Intangible Assets", Long.valueOf(data));
		}break;
		case 61:
		{
			years.get(1).put("Intangible Assets", Long.valueOf(data));
		}break;
		case 62:
		{
			years.get(2).put("Intangible Assets", Long.valueOf(data));
		}break;
		case 64:
		{
			years.get(0).put("Accumulated Amortization", Long.valueOf(data));
		}break;
		case 65:
		{
			years.get(1).put("Accumulated Amortization", Long.valueOf(data));
		}break;
		case 66:
		{
			years.get(2).put("Accumulated Amortization", Long.valueOf(data));
		}break;
		case 68:
		{
			years.get(0).put("Other Assets", Long.valueOf(data));
		}break;
		case 69:
		{
			years.get(1).put("Other Assets", Long.valueOf(data));
		}break;
		case 70:
		{
			years.get(2).put("Other Assets", Long.valueOf(data));
		}break;
		case 72:
		{
			years.get(0).put("Deferred Long Term Asset Charges", Long.valueOf(data));
		}break;
		case 73:
		{
			years.get(1).put("dDeferred Long Term Asset Charges", Long.valueOf(data));
		}break;
		case 74:
		{
			years.get(2).put("Deferred Long Term Asset Charges", Long.valueOf(data));
		}break;
		case 76:
		{
			years.get(0).put("Total Assets", Long.valueOf(data));
		}break;
		case 77:
		{
			years.get(1).put("Total Assets", Long.valueOf(data));
		}break;
		case 78:
		{
			years.get(2).put("Total Assets", Long.valueOf(data));
		}break;
		case 82:
		{
			years.get(0).put("Accounts Payable", Long.valueOf(data));
		}break;
		case 83:
		{
			years.get(1).put("Accounts Payable", Long.valueOf(data));
		}break;
		case 84:
		{
			years.get(2).put("Accounts Payable", Long.valueOf(data));
		}break;
		case 86:
		{
			years.get(0).put("Short Current Long Term Debt", Long.valueOf(data));
		}break;
		case 87:
		{
			years.get(1).put("Short Current Long Term Debt", Long.valueOf(data));
		}break;
		case 88:
		{
			years.get(2).put("Short Current Long Term Debt", Long.valueOf(data));
		}break;
		case 90:
		{
			years.get(0).put("Other Current Liabilities", Long.valueOf(data));
		}break;
		case 91:
		{
			years.get(1).put("Other Current Liabilities", Long.valueOf(data));
		}break;
		case 92:
		{
			years.get(2).put("Other Current Liabilities", Long.valueOf(data));
		}break;
		case 94:
		{
			years.get(0).put("Total Current Liabilities", Long.valueOf(data));
		}break;
		case 95:
		{
			years.get(1).put("Total Current Liabilities", Long.valueOf(data));
		}break;
		case 96:
		{
			years.get(2).put("Total Current Liabilities", Long.valueOf(data));
		}break;
		case 98:
		{
			years.get(0).put("Long Term Debt", Long.valueOf(data));
		}break;
		case 99:
		{
			years.get(1).put("Long Term Debt", Long.valueOf(data));
		}break;
		case 100:
		{
			years.get(2).put("Long Term Debt", Long.valueOf(data));
		}break;
		case 102:
		{
			years.get(0).put("Other Liabilities", Long.valueOf(data));
		}break;
		case 103:
		{
			years.get(1).put("Other Liabilities", Long.valueOf(data));
		}break;
		case 104:
		{
			years.get(2).put("Other Liabilities", Long.valueOf(data));
		}break;
		case 106:
		{
			years.get(0).put("Deferred Long Term Liability Charges", Long.valueOf(data));
		}break;
		case 107:
		{
			years.get(1).put("Deferred Long Term Liability Charges", Long.valueOf(data));
		}break;
		case 108:
		{
			years.get(2).put("Deferred Long Term Liability Charges", Long.valueOf(data));
		}break;
		case 110:
		{
			years.get(0).put("Minority Interest", Long.valueOf(data));
		}break;
		case 111:
		{
			years.get(1).put("Minority Interest", Long.valueOf(data));
		}break;
		case 112:
		{
			years.get(2).put("Minority Interest", Long.valueOf(data));
		}break;
		case 114:
		{
			years.get(0).put("Negative Goodwill", Long.valueOf(data));
		}break;
		case 115:
		{
			years.get(1).put("Negative Goodwill", Long.valueOf(data));
		}break;
		case 116:
		{
			years.get(2).put("Negative Goodwill", Long.valueOf(data));
		}break;
		case 118:
		{
			years.get(0).put("Total Liabilities", Long.valueOf(data));
		}break;
		case 119:
		{
			years.get(1).put("Total Liabilities", Long.valueOf(data));
		}break;
		case 120:
		{
			years.get(2).put("Total Liabilities", Long.valueOf(data));
		}break;
		case 123:
		{
			years.get(0).put("Misc Stocks Options Warrants", Long.valueOf(data));
		}break;
		case 124:
		{
			years.get(1).put("Misc Stocks Options Warrants", Long.valueOf(data));
		}break;
		case 125:
		{
			years.get(2).put("Misc Stocks Options Warrants", Long.valueOf(data));
		}break;
		case 127:
		{
			years.get(0).put("Redeemable Preferred Stock", Long.valueOf(data));
		}break;
		case 128:
		{
			years.get(1).put("Redeemable Preferred Stock", Long.valueOf(data));
		}break;
		case 129:
		{
			years.get(2).put("Redeemable Preferred Stock", Long.valueOf(data));
		}break;
		case 131:
		{
			years.get(0).put("Preferred Stock", Long.valueOf(data));
		}break;
		case 132:
		{
			years.get(1).put("Preferred Stock", Long.valueOf(data));
		}break;
		case 133:
		{
			years.get(2).put("Preferred Stock", Long.valueOf(data));
		}break;
		case 135:
		{
			years.get(0).put("Common Stock", Long.valueOf(data));
		}break;
		case 136:
		{
			years.get(1).put("Common Stock", Long.valueOf(data));
		}break;
		case 137:
		{
			years.get(2).put("Common Stock", Long.valueOf(data));
		}break;
		case 139:
		{
			years.get(0).put("Retained Earnings", Long.valueOf(data));
		}break;
		case 140:
		{
			years.get(1).put("Retained Earnings", Long.valueOf(data));
		}break;
		case 141:
		{
			years.get(2).put("Retained Earnings", Long.valueOf(data));
		}break;
		case 143:
		{
			years.get(0).put("Treasury Stock", Long.valueOf(data));
		}break;
		case 144:
		{
			years.get(1).put("Treasury Stock", Long.valueOf(data));
		}break;
		case 145:
		{
			years.get(2).put("Treasury Stock", Long.valueOf(data));
		}break;
		case 147:
		{
			years.get(0).put("Capital Surplus", Long.valueOf(data));
		}break;
		case 148:
		{
			years.get(1).put("Capital Surplus", Long.valueOf(data));
		}break;
		case 149:
		{
			years.get(2).put("Capital Surplus", Long.valueOf(data));
		}break;
		case 151:
		{
			years.get(0).put("Other Stockholder Equity", Long.valueOf(data));
		}break;
		case 152:
		{
			years.get(1).put("Other Stockholder Equity", Long.valueOf(data));
		}break;
		case 153:
		{
			years.get(2).put("Other Stockholder Equity", Long.valueOf(data));
		}break;
		case 155:
		{
			years.get(0).put("Total Stockholder Equity", Long.valueOf(data));
		}break;
		case 156:
		{
			years.get(1).put("Total Stockholder Equity", Long.valueOf(data));
		}break;
		case 157:
		{
			years.get(2).put("Total Stockholder Equity", Long.valueOf(data));
		}break;
		case 159:
		{
			years.get(0).put("net_tangible_assets", Long.valueOf(data));
		}break;
		case 160:
		{
			years.get(1).put("net_tangible_assets", Long.valueOf(data));
		}break;
		case 161:
		{
			years.get(2).put("net_tangible_assets", Long.valueOf(data));
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
		return new BalanceSheetReport(years);
	}

}
