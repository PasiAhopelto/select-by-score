package github.pasiahopelto.scorelib.reader;

public interface LineVisitor {

	void parseEntity(ParsedLine parsedLine) throws ParseException;
}
