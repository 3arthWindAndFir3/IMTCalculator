package startsev;

import static org.junit.Assert.*;
import org.junit.Test;
import startsev.Calculate;

public class TestKhairullin extends Calculate{

	@Test
	public void test() {
		Calculate w = new Calculate();
	      w.timeThread.start();
		first=49;
		second=169;
		double num=first/(second*second)*10000;
		if (first==0) {fail ();}
		if (second==0) {fail ();}
		if (num<=0) {fail ();}
		if (num!=17.15626203564301) {fail ();}
	} 
}
