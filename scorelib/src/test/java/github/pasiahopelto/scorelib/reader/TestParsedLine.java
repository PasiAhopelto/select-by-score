package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.io.Serializable;

@RunWith(MockitoJUnitRunner.class)
public class TestParsedLine {

	@Test
	public void testBean() {
		new BeanTester().testBean(ParsedLine.class);
		new EqualsMethodTester().testEqualsMethod(ParsedLine.class);
		new HashCodeMethodTester().testHashCodeMethod(ParsedLine.class);
		assertTrue(new ParsedLine() instanceof Serializable);
	}
}
