package test;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import exceptions.PreconditionException;
import impl.EditableScreenImpl;
import interfaces.EditableScreen;
import screen.Cell;

public class EditableScreenTest {

	private EditableScreen screen;

	@Before
	public void initialize() {
		this.screen = new EditableScreenContract(new EditableScreenImpl());
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

	@Test
	public void testInitCellContent() {
		this.screen.init(4, 5);
		for (int i = 0; i < this.screen.getWidth(); ++i) {
			for (int j = 0; j < this.screen.getHeight(); j++) {
				assert (this.screen.getCellNature(i, j) == Cell.EMP);
			}
		}
	}

	@Test
	public void testSetNatureNormal1() {
		this.screen.init(4, 5);
		this.screen.setNature(0, 0, Cell.MTL);
	}

	@Test
	public void testSetNatureNormal2() {
		this.screen.init(4, 5);
		this.screen.setNature(3, 4, Cell.HDR);
	}

	@Test(expected = PreconditionException.class)
	public void testSetNatureFail1() {
		this.screen.init(4, 5);
		this.screen.setNature(-1, 4, Cell.MTL);
	}

	@Test(expected = PreconditionException.class)
	public void testSetNatureFail2() {
		this.screen.init(4, 5);
		this.screen.setNature(4, -1, Cell.MTL);
	}

	@Test(expected = PreconditionException.class)
	public void testSetNatureFail3() {
		this.screen.init(4, 5);
		this.screen.setNature(4, 4, Cell.MTL);
	}

	@Test(expected = PreconditionException.class)
	public void testSetNatureFail4() {
		this.screen.init(4, 5);
		this.screen.setNature(3, 5, Cell.MTL);
	}

	@Test
	public void testDigNormal() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);

		this.screen.dig(1, 1);
	}

	@Test(expected = PreconditionException.class)
	public void testDigFail1() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);

		this.screen.dig(-1, 1);
	}

	@Test(expected = PreconditionException.class)
	public void testDigFail2() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);

		this.screen.dig(1, -1);
	}

	@Test(expected = PreconditionException.class)
	public void testDigFail3() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);

		this.screen.dig(4, 1);
	}

	@Test(expected = PreconditionException.class)
	public void testDigFail4() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);

		this.screen.dig(1, 5);
	}

	@Test(expected = PreconditionException.class)
	public void testDigFail5() {
		this.screen.init(4, 5);

		this.screen.dig(1, 1);
	}

	public void testFillNormal() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);
		this.screen.dig(1, 1);

		this.screen.fill(1, 1);

	}

	@Test(expected = PreconditionException.class)
	public void testFillFail1() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);
		this.screen.dig(1, 1);

		this.screen.fill(-1, 1);
	}

	@Test(expected = PreconditionException.class)
	public void testFillFail2() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);
		this.screen.dig(1, 1);

		this.screen.fill(1, -1);
	}

	@Test(expected = PreconditionException.class)
	public void testFillFail3() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);
		this.screen.dig(1, 1);

		this.screen.fill(4, 1);
	}

	@Test(expected = PreconditionException.class)
	public void testFillFail4() {
		this.screen.init(4, 5);
		this.screen.setNature(1, 1, Cell.PLT);
		this.screen.dig(1, 1);

		this.screen.fill(1, 5);
	}

	@Test(expected = PreconditionException.class)
	public void testFillFail5() {
		this.screen.init(4, 5);

		this.screen.fill(1, 1);
	}

	@Test
	public void testPlayableNormal() {
		this.screen.init(4, 5);
		for (int i = 0; i < 4; ++i) {
			this.screen.setNature(i, 0, Cell.MTL);
		}

		this.screen.setNature(3, 3, Cell.PLT);

		assert (this.screen.playable());
	}

	@Test
	public void testPlayableFail1() {
		this.screen.init(4, 5);
		// Pas de HOL mais pas de MTL au sol

		this.screen.setNature(3, 3, Cell.PLT);

		assert (this.screen.playable() == false);
	}

	@Test
	public void testPlayableFail2() {
		this.screen.init(4, 5);
		for (int i = 0; i < 4; ++i) {
			this.screen.setNature(i, 0, Cell.MTL);
		}
		// presence d'uen case HOL
		this.screen.setNature(3, 3, Cell.HOL);

		assert (this.screen.playable() == false);
	}

}
