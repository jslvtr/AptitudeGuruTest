package androidhive.dashboard.test;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.tests.TestPage;
import com.aptitudeguru.dashboard.tests.TestPage.MyCountDownTimer;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.widget.TextView;
import androidhive.dashboard.R;

public class TestTimer extends ActivityInstrumentationTestCase2<TestPage> {
	
	TestPage activity;
	TextView timer;
	
	public TestTimer() {
		super(TestPage.class);
	}
	
	public void setUp() {
		this.setActivityInitialTouchMode(false);
		Intent i = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(), TestPage.class);
		i.putExtra("cat", "q3");

		activity = this.getActivity();
		timer = (TextView) activity.findViewById(R.id.timer);
	}
	
	public void tearDown() {
		
	}
	
	public void testCreateTimer() {
		
		
		
	}

}
