

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


//class with static methods that can convert between different primitive types and format text
public class Types {
	private static final String[] codes = {
			"&#33;", "&#34;", "&#35;", "&#36;", "&#37;", "&#38;", "&#39;", "&#40;",
			"&#41;", "&#42;", "&#43;", "&#44;", "&#45;", "&#46;", "&#47;", "&#58;",
			"&#59;", "&#60;", "&#61;", "&#62;", "&#63;", "&#64;", "&nbsp;", "&amp;", "&lt;", "&gt;"
	};
	
	private static final char[] answers = {
		'!', '"', '#', '$', '%', '&', (char)39, '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', 
		'<', '=', '>', '?', '@', ' ', '&', '<', '>'
	};
	
	public static String convertCodes(String code)
	{
		int index = Arrays.binarySearch(codes, code);
		return (index < 0 ? code : ""+answers[index]);
	}
	public static String convertToPlainString(String string)
	{
		int ampersand = string.indexOf('&');
		int semicolon = string.indexOf(';');
		while(true)
		{
			if(ampersand != -1 && semicolon !=  -1)
			{
				string = (string.substring(0, ampersand) + convertCodes(string.substring(ampersand, semicolon+1)) + string.substring(semicolon+1));
			}
			else
			{
				return string;
			}
			ampersand = string.indexOf('&', ampersand+1);
			semicolon = string.indexOf(';', ampersand+1); //because code is replaced with char
		}
	}
	public static void sortBigDecimalArray(BigDecimal[] array)
	{
		
		for(int i=1; i < array.length; i++)
		{
			for(int x = i; x > 0; x--)
			{
				if(array[x].compareTo(array[x-1]) < 0)
				{
					BigDecimal temp = array[i-1];
					array[x-1] = array[x];
					array[x] = temp;
				}
			}
		}
	}
	public static void checkStringArrayListForDups(ArrayList<String> tickers, int start)
	{
		outer : for(int i=start; i<tickers.size(); i++)
		{
			String s = tickers.get(i);
			for(int x=i+1; x<tickers.size(); x++)
			{
				if(s.equals(tickers.get(x)))
				{
					tickers.remove(x);
					i--;
					continue outer;
				}
			}
		}
	}
	public static double[] copyOfRangeDouble(double[] array, int start, int end)
	{
		double[] d = new double[end-start];
		for(int i=Math.max(0, start); i<end && i<array.length; i++)
		{
			d[i-start] = array[i];
		}
		return d;
	}
	public static Date[] copyOfRangeDate(Date[] array, int start, int end)
	{
		Date[] d = new Date[end-start];
		for(int i=Math.max(0, start); i<end && i<array.length; i++)
		{
			d[i-start] = array[i];
		}
		return d;
	}
	
	
	public static double toPositive(double d)
	{
		if(d > 0)  //already positive
			return d;
		
		return d + (2*(-d));
	}
	public static BigDecimal roundToTenths(double d)
	{
		String finder = ""+d;
		int point = finder.indexOf('.');
		int significantDigits = 0;
		if(point != -1)
		{
			significantDigits = point+1+1;
		}
		else
		{
			return new BigDecimal(d);
		}
		
		BigDecimal decimal = new BigDecimal(d);
		BigDecimal returnD = decimal.round(new MathContext(significantDigits, RoundingMode.HALF_UP));
		return returnD;
	}
	private final static BigDecimal hundred = new BigDecimal(100);
	public static String toPercentString(BigDecimal bg)
	{
		String s = bg.multiply(hundred).toPlainString();
		if(s.charAt(0)!='-')
			s = "+"+s;
		return s;
	}
	public static BigDecimal roundToHundredths(double d)
	{

		String finder = ""+d;
		int point = finder.indexOf('.');
		int significantDigits = 0;
		if(point != -1)
		{
			significantDigits = point+1+2;
		}
		else
		{
			return new BigDecimal(d);
		}
		
		BigDecimal decimal = new BigDecimal(d);
		BigDecimal returnD = decimal.round(new MathContext(significantDigits, RoundingMode.HALF_UP));
		return returnD;
	}
	
	
	public static double[] trimDoubleArray(double[] array)
	{
		int arraySize = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] == 0)
			{
				arraySize = i;
				break;
			}
		}
		double[] returnArray = new double[arraySize];
		
		for(int i=0;i<returnArray.length;i++)
		{
			returnArray[i] = array[i];
		}
		
		return returnArray;
	}
	
	public static String[] trimStringArray(String[] array)
	{
		int arraySize = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] == null)
			{
				arraySize = i;
				break;
			}
		}
		String[] returnArray = new String[arraySize];
		for(int i=0;i<returnArray.length;i++)
		{
			returnArray[i] = array[i];
		}
		
		return returnArray;
	}
	public static Date[] trimDateArray(Date[] array)
	{
		int arraySize = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] == null)
			{
				arraySize = i;
				break;
			}
		}
		Date[] returnArray = new Date[arraySize];
		for(int i=0;i<returnArray.length;i++)
		{
			returnArray[i] = array[i];
		}
		
		return returnArray;
	}
	
	public static String trauncateToStringWithZeros(double d)
	{
		DecimalFormat formatter;
		String r;
		try
		{
		formatter = new DecimalFormat("###########################.###########################");
		r = formatter.format(d);
		}
		catch(IllegalArgumentException e)
		{
			r = ""+d;
		}
		
		
		int dot = r.indexOf('.');
		
		if(dot != -1)
		{
		try{
		r = r.substring(0, dot+3);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			try{
				r = r.substring(0, dot+2)+"0";
				}
			catch(StringIndexOutOfBoundsException e2)
			{
					r = r.substring(0, dot+1)+"00";
			}
		}
		}

		DecimalFormat commaFormatter = new DecimalFormat("###,###,###,###,###,###,###,###,###.###########################");
		String temp = commaFormatter.format(Double.parseDouble(r));
		
		if(temp.indexOf(".")==-1)
			temp = temp+".00";
		if(temp.indexOf(".") ==temp.length()-2)
			temp = temp+"0";
		return temp;

		
	}
	public static Date[] toDateArray(Object[] array)
	{
		Date[] d = new Date[array.length];
		for(int i=0; i<array.length; i++)
		{
			d[i]=(Date)array[i];
		}
		return d;
	}
	public static double[] toDoubleArray(Object[] array)
	{
		double[] d = new double[array.length];
		for(int i=0; i<array.length; i++)
		{
			d[i]=(Double)array[i];
		}
		return d;
	}
	
	public static String trauncateToStringWithoutZeros(double d)
	{
		DecimalFormat formatter;
		String r;
		try
		{
		formatter = new DecimalFormat("###########################.###########################");
		r = formatter.format(d);
		}
		catch(IllegalArgumentException e)
		{
			r = ""+d;
		}
		
		
		int dot = r.indexOf('.');
		
		if(dot != -1)
		{
		try{
		r = r.substring(0, dot+3);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			try{
				r = r.substring(0, dot+2);
				}
			catch(StringIndexOutOfBoundsException e2)
			{
					r = r.substring(0, dot);
			}
		}
		
		//erase zeros
		if(r.charAt(r.length()-1) =='0' && r.charAt(r.length()-2) == '0' && r.charAt(r.length()-3) == '.')
			r = r.substring(0, r.length()-3);
		else if(r.charAt(r.length()-1) == '0' && r.charAt(r.length()-2) == '.')
			r = r.substring(0, r.length()-2);
		
		}
		
		DecimalFormat commaFormatter = new DecimalFormat("###,###,###,###,###,###,###,###,###.###########################");
		return commaFormatter.format(Double.parseDouble(r));

	}
	
	public static Date toDate(final String input)
	{
		//format in XML file: Tue, 26 Jun 2012 13:00:00 GMT
		int year;
		int month;
		int day;
		int hour;
		int min;
		
		String worker = input;
			worker.trim();
		int comma = worker.indexOf(',');
		worker = worker.substring(comma+2);
		
		int space = worker.indexOf(' ');
			day = Integer.parseInt(worker.substring(0, space)); //got day
		worker = worker.substring(space+1);

		space = worker.indexOf(' ');
		String mo = worker.substring(0, space);
		
		
		if(mo.contains("Jan"))
			month = 0;
		else if(mo.contains("Feb"))
			month = 1;
		else if(mo.contains("Mar"))
			month = 2;
		else if(mo.contains("Apr"))
			month = 3;
		else if(mo.contains("May"))
			month = 4;
		else if(mo.contains("Jun") || mo.contains("June"))
			month = 5;
		else if(mo.contains("Jul") || mo.contains("July"))
			month = 6;
		else if(mo.contains("Aug"))
			month = 7;
		else if(mo.contains("Sept"))
			month = 8;
		else if(mo.contains("Oct"))
			month = 9;
		else if(mo.contains("Nov"))
			month = 10;
		else if(mo.contains("Dec"))
			month = 11; //got month
		else
			month = -1;
		
		worker = worker.substring(space+1);

		space = worker.indexOf(' ');
		year = Integer.parseInt(worker.substring(0, space))-1900;  //got year
		
		worker = worker.substring(space+1);
		
		int colon = worker.indexOf(':');
		hour = Integer.parseInt(worker.substring(0, colon));  //got hour
		
		worker = worker.substring(colon+1);

		colon = worker.indexOf(':');
		min = Integer.parseInt(worker.substring(0, colon));  //got min
		
		Date date = new Date(year, month, day, hour, min);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.roll(Calendar.HOUR_OF_DAY, -6 );
		
		Date date2 = cal.getTime();
		
		return date2;
	}
	
	
	public static double parseDouble(String s)
	{
		boolean hitPoint = false;
		double result = 0.0;
		long decimalPlace=1;
		boolean isNegative = false;
		boolean isNegativeDisabled = false;
		boolean needsRounding = false;
		boolean inMillions = false;
		boolean inBillions = false;
		for(char c : s.toCharArray())
		{
			if(c == '-' && !isNegativeDisabled)
			{
				isNegative = true;
				continue;
			} 
			if(c == '+')
			{
				isNegative = false;
				isNegativeDisabled = true;
				continue;
			}
			if(c == '.')
			{
				isNegativeDisabled = true;
				hitPoint = true;
				decimalPlace = 10;
				continue;
			}
			if(Character.isDigit(c))
			{
				isNegativeDisabled = true;
				if(hitPoint)
				{
					result += (Double.parseDouble(Character.toString(c)))/decimalPlace;
					decimalPlace *= 10;
				}
				else
				{
					result *= 10;
					result += (Double.parseDouble(Character.toString(c)));
				}		
				
				if(decimalPlace == 10000) //has three decimal places
				{
					needsRounding = true;
				}
				continue;
			}
			if(c == 'M')
			{
				inMillions = true;
				continue;
			}
			if(c == 'B')
			{
				inBillions = true;
				continue;
			}
			
		}
		if(inMillions)
		{
			if(decimalPlace <= 100000000L || Math.abs(decimalPlace - 100000000L) < 1) 
				needsRounding = false;
			result *= 1000000;	
		}
		if(inBillions)
		{
			if(decimalPlace <= 100000000000L || Math.abs(decimalPlace - 100000000000L) < 1)
				needsRounding = false;
			result *= 1000000000;
				
		}
		if(needsRounding)
		{
		long resultAsLong = ((long) (result * (1000)));
		long last =  resultAsLong % 10;
		resultAsLong /= 10;
		
		result = resultAsLong;
		if(last >= 5 || Math.abs(last - 5) < .2) //round up
		{
			result += 1;
		}
		result /= 100;
		//eliminate roundoff error
		result *= 100;
		resultAsLong = (long) result;
		result = resultAsLong;
		result /= 100; 
		}
		
		
		if(isNegative) 
		{
			result = result - 2*result;
		}
		return(result);
	}
	

}
