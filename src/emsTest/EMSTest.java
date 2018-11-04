package emsTest;

import java.util.Scanner;

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

}
