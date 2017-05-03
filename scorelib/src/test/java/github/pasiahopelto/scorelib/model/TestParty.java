package github.pasiahopelto.scorelib.model;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestParty {

	@Test
	public void testBean() {
		new BeanTester().testBean(Party.class);
		new EqualsMethodTester().testEqualsMethod(Party.class);
		new HashCodeMethodTester().testHashCodeMethod(Party.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Party() instanceof Serializable);
	}

}
