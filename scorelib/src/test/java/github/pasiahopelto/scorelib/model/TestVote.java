package github.pasiahopelto.scorelib.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.io.Serializable;

@RunWith(MockitoJUnitRunner.class)
public class TestVote {

	@Test
	public void testBean() {
		new BeanTester().testBean(Vote.class);
		new EqualsMethodTester().testEqualsMethod(Vote.class);
		new HashCodeMethodTester().testHashCodeMethod(Vote.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Vote() instanceof Serializable);
	}
}
