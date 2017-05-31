package github.pasiahopelto.scorelib.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestElection {

	@Test
	public void testBean() {
		new BeanTester().testBean(Election.class);
		new EqualsMethodTester().testEqualsMethod(Election.class);
		new HashCodeMethodTester().testHashCodeMethod(Election.class);
	}
	
}
