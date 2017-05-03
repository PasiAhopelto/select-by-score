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
public class TestCandidate {

	@Test
	public void testBean() {
		new BeanTester().testBean(Candidate.class);
		new EqualsMethodTester().testEqualsMethod(Candidate.class);
		new HashCodeMethodTester().testHashCodeMethod(Candidate.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Candidate() instanceof Serializable);
	}
}
