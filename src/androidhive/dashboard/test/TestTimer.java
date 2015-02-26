package androidhive.dashboard.test;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.tests.TestPage;
import com.aptitudeguru.dashboard.tests.TestPage.MyCountDownTimer;
import com.aptitudeguru.dashboard.*;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.widget.TextView;
import androidhive.dashboard.R;

public class TestTimer extends ActivityInstrumentationTestCase2<TimerActivity> {
	
	
	private TimerActivity activity;
	private TextView timer;
	
	public TestTimer() {
		super(TimerActivity.class);
	}
	
	@Override
	public void setUp() throws Exception{
		super.setUp();
		Intent i = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(),TimerActivity.class);
		setActivityIntent(i);
		activity = getActivity();
	}
	
	public void tearDown() {
		
	}
	
	public void testCreateTimer() {
		timer = (TextView) activity.findViewById(R.id.timer);
		String resultText = (String) timer.getText();
		int time = Integer.parseInt(resultText);
		assertEquals(time, 120);		
	}

}
