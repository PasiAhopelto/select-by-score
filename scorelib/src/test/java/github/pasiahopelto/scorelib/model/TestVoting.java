package github.pasiahopelto.scorelib.model;

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
	
}
