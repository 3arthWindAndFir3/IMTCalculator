import static org.junit.Assert.*;
import org.junit.Test;

public class TestVaraxin extends Calculate{

	@Test
	public void test() {
		Calculate w = new Calculate();
	    w.timeThread.start();
		first=118;
		second=187;
		double num=first/(second*second)*10000;
		if (first<=0) {fail ();}
		if (second<=0) {fail ();}
		if (num<=0) {fail ();}
		if (num !=33.74417341073522) {fail ();};
	} 
}
