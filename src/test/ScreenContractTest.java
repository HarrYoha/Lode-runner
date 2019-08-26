package test;

import org.junit.Before;
import org.junit.Test;

import contracts.ScreenContract;
import exceptions.PreconditionException;
import impl.ScreenImpl;
import interfaces.Screen;

public class ScreenContractTest {

	Screen screen;

	@Before
	public void initialize() {
		this.screen = new ScreenContract(new ScreenImpl());
	}

	@Test
	public void testInitNormal() {
		this.screen.init(4, 5);
	}

	@Test(expected = PreconditionException.class)
	public void testInitFail1() {
		this.screen.init(0, 5);
	}

	@Test(expected = PreconditionException.class)
	public void testInitFail2() {
		this.screen.init(4, 0);
	}

	@Test(expected = PreconditionException.class)
	public void testInitFail3() {
		this.screen.init(-4, 5);
	}

	@Test(expected = PreconditionException.class)
	public void testInitFail4() {
		this.screen.init(4, -5);
	}

}
