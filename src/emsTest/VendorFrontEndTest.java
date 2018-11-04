package emsTest;

import ems.Vendor;
import ems.VendorFrontEnd;

public class VendorFrontEndTest {
	
	class VendorFrontEndStub extends VendorFrontEnd {
		VendorFrontEndStub(Vendor vendor) {
			super(vendor);
		}
		
		@Override
		public void vendorOperations() {
			System.out.println("This is an Operation");
		}
		
		
	}
	

}
