package github.pasiahopelto.scorelib.reader;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class LineSplitter {

	public ParsedLine splitLine(String string) throws ParseException {
		if(string == null) {
			throw new ParseException("input is null");
		}
		String[] parts = string.split("\\|");
		if(parts.length < 2) {
			throw new ParseException("input is missing values");
		}
		return makeResult(parts);
	}

	private ParsedLine makeResult(String[] parts) {
		ParsedLine result = new ParsedLine();
		result.setType(parts[0]);
		result.setValues(Arrays.asList(ArrayUtils.subarray(parts, 1, parts.length)));
		return result;
	}
}
