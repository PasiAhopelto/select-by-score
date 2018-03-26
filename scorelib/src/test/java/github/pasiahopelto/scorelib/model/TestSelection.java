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
public class TestSelection {

	@Test
	public void testBean() {
		new BeanTester().testBean(Selection.class);
	}

	@Test
	public void testEqualsMethod() {
		new EqualsMethodTester().testEqualsMethod(Selection.class);
	}

	@Test
	public void hasHashCodeMethod() {
		new HashCodeMethodTester().testHashCodeMethod(Selection.class);
	}

	@Test
	public void isSerializable() {
		assertTrue(new Selection() instanceof Serializable);
	}
}
