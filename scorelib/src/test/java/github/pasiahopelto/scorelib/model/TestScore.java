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
public class TestScore {

	@Test
	public void testBean() {
		new BeanTester().testBean(Score.class);
	}

	@Test
	public void testEqualsMethod() {
		new EqualsMethodTester().testEqualsMethod(Score.class);
	}

	@Test
	public void hasHashCodeMethod() {
		new HashCodeMethodTester().testHashCodeMethod(Score.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Score() instanceof Serializable);
	}

	@Test
	public void percentageIsCalculatedFromMatchingAndTotalVotes() {
		Score score = new Score();
		score.setTotalVotes(10);
		score.setMatchingVotes(5);
		assertEquals(0.5, score.getPercentage(), 0f);
	}
}
