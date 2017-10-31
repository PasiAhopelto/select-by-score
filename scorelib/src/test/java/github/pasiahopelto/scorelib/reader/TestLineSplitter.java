package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestLineSplitter {

	private LineSplitter splitter = new LineSplitter();
	
	@Test(expected=ParseException.class)
	public void throwsExceptionOnNullLine() throws ParseException {
		splitter.splitLine(null);
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionOnEmptyLine() throws ParseException {
		splitter.splitLine("");
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionOnLineWithoutPipe() throws ParseException {
		splitter.splitLine("type");
	}
	
	@Test
	public void splitsLineToTypeAndValues() throws ParseException {
		ParsedLine parsedLine = splitter.splitLine("votes partyName|yes|4");
		assertEquals("votes", parsedLine.getType());
		assertEquals(Lists.newArrayList("partyName", "yes", "4"), parsedLine.getValues());
	}
}
