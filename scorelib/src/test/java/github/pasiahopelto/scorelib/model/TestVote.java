package github.pasiahopelto.scorelib.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.factories.FactoryCollection;
import org.meanbean.lang.Factory;
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
		BeanTester beanTester = new BeanTester();
		addFactories(beanTester.getFactoryCollection());
		beanTester.testBean(Vote.class);
	}

	@Test
	public void testEqualsMethod() {
		EqualsMethodTester equalsMethodTester = new EqualsMethodTester();
		addFactories(equalsMethodTester.getFactoryCollection());
		equalsMethodTester.testEqualsMethod(Vote.class);
	}

	@Test
	public void testHashCodeMethod() {
		HashCodeMethodTester hashCodeMethodTester = new HashCodeMethodTester();
		addFactories(hashCodeMethodTester.getFactoryCollection());
		hashCodeMethodTester.testHashCodeMethod(Vote.class);
	}

	private void addFactories(FactoryCollection factoryCollection) {
		factoryCollection.addFactory(Voting.class, new Factory<Voting>() {
			public Voting create() {
				return new Voting();
			}
		});
	}

	@Test
	public void isSerializable() {
		assertTrue(new Vote() instanceof Serializable);
	}
}
