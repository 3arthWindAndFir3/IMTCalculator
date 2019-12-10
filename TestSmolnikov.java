import static org.junit.Assert.*;
import org.junit.Test;

public class TestSmolnikov extends Calculate{

	@Test
	public void test() {
		Calculate w = new Calculate();
	      w.timeThread.start();
		first=57;
		second=171;
		double num=first/(second*second)*10000;
		if (first<=0) {fail ();}
		if (second<=0) {fail ();}
		if (num<=0) {fail ();}
		if (num!=19.49317738791423) {fail ();}
	} 
}
