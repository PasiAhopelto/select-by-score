package github.pasiahopelto.scorelib.model;

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
}
