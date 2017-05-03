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
public class TestVoting {

	@Test
	public void testBean() {
		new BeanTester().testBean(Voting.class);
		new EqualsMethodTester().testEqualsMethod(Voting.class);
		new HashCodeMethodTester().testHashCodeMethod(Voting.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Voting() instanceof Serializable);
	}

}
