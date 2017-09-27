package github.pasiahopelto.scorelib.reader;

import github.pasiahopelto.scorelib.model.Voting;

public interface LineVisitor {

	Voting parseEntity(ParsedLine parsedLine) throws ParseException;
}
