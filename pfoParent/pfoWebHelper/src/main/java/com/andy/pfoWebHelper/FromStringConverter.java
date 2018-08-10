package com.andy.pfoWebHelper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Logger;

public class FromStringConverter {
	private static Logger logger = Logger.getLogger("com.andy.portfolioWebHelper.FromStringConverter");

	
	public FromStringConverter() {}
	
	public Double toDouble(String string) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		Double d;
		try {
			d = nf.parse(string).doubleValue();
		} catch (ParseException ex) {
			logger.info("PARSEEXCEPTION");
			return new Double(0.0);
		}
		return d;
	}
	
	public Integer toInteger(String string) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		Integer i;
		try {
			i = nf.parse(string).intValue();
		} catch (ParseException ex) {
			logger.info("PARSEEXCEPTION");
			return new Integer(0);
		}
		return i;
	}

}
