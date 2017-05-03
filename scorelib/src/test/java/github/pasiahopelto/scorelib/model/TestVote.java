package github.pasiahopelto.scorelib.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestVote {

	@Test
	public void testBean() {
		new BeanTester().testBean(Vote.class);
		new EqualsMethodTester().testEqualsMethod(Vote.class);
		new HashCodeMethodTester().testHashCodeMethod(Vote.class);
	}
}
