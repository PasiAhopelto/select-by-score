package github.pasiahopelto.scorelib.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingOption {

	@Test
	public void testBean() {
		new BeanTester().testBean(VotingOption.class);
		new EqualsMethodTester().testEqualsMethod(VotingOption.class);
		new HashCodeMethodTester().testHashCodeMethod(VotingOption.class);
	}
	
}
