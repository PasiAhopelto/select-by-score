package github.pasiahopelto.scorelib.reader;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineSplitter {

	private static final Pattern PATTERN = Pattern.compile("(votes|voting)\\s(.+)");

	public ParsedLine splitLine(String string) throws ParseException {
		ParsedLine result = null;
		if(string == null) {
			throw new ParseException("input is null");
		}
		Matcher matcher = PATTERN.matcher(string);
		if(matcher.matches()) {
			result = makeResult(matcher.group(1), matcher.group(2).split("\\|"));
		}
		else {
			throw new ParseException("input is missing values");
		}
		return result;
	}

	private ParsedLine makeResult(String type, String[] parts) {
		ParsedLine result = new ParsedLine();
		result.setType(type);
		result.setValues(Arrays.asList(parts));
		return result;
	}
}
