package emsTest;

import java.io.ByteArrayInputStream;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ems.EMS;

public class EMSTest {
	
	@Test
	public void UITest() {
		String[] inputs = {"2", "1"};
		class EMSStub extends EMS {
			private int inputCount = 0;
			@Override
			public String readInput() {
				String result = inputs[inputCount];
				System.out.println(result);
				inputCount++;
				return result;
			}
		}
		EMSStub emsStub = new EMSStub();
	}
	@Test
	public void testReadInput()
	{
		EMS testingEMS = new EMS();
		String input = "This is an input.";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		String result = testingEMS.readInput();
		assertEquals(input, result);
	}

}
