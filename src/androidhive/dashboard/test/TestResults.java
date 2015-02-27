package androidhive.dashboard.test;

import com.aptitudeguru.dashboard.scores.showscorevl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import androidhive.dashboard.R;
import android.test.ActivityInstrumentationTestCase2;

public class TestResults extends ActivityInstrumentationTestCase2<showscorevl> {

	showscorevl activity;
	int allid[] = new int[20];
	int yourans[] = new int[20];
	int givenans[] = new int[20];

	public TestResults() {
		super(showscorevl.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent i;

		for (int p = 0; p < (givenans.length - 2); p++) {
			givenans[p] = 1;
			yourans[p] = 1;
			allid[p] = 1;
		}

		givenans[18] = 0;
		yourans[18] = 3;
		allid[18] = 2;

		givenans[19] = 1;
		yourans[19] = 3;
		allid[19] = 2;

		i = new Intent(getInstrumentation().getTargetContext(),
				showscorevl.class);

		i.putExtra("allid", allid);
		i.putExtra("givenans", yourans);
		i.putExtra("score", givenans);
		setActivityIntent(i);
		activity = getActivity();
		activity.startActivity(i);

	}

	public void testtwentyQuestions() {
		activity = getActivity();
		Button[] buttons = new Button[20];
		buttons[0] = (Button) activity.findViewById(R.id.score1);
		buttons[1] = (Button) activity.findViewById(R.id.score2);
		buttons[2] = (Button) activity.findViewById(R.id.score3);
		buttons[3] = (Button) activity.findViewById(R.id.score4);
		buttons[4] = (Button) activity.findViewById(R.id.score5);
		buttons[5] = (Button) activity.findViewById(R.id.score6);
		buttons[6] = (Button) activity.findViewById(R.id.score7);
		buttons[7] = (Button) activity.findViewById(R.id.score8);
		buttons[8] = (Button) activity.findViewById(R.id.score9);
		buttons[9] = (Button) activity.findViewById(R.id.score10);
		buttons[10] = (Button) activity.findViewById(R.id.score11);
		buttons[11] = (Button) activity.findViewById(R.id.score12);
		buttons[12] = (Button) activity.findViewById(R.id.score13);
		buttons[13] = (Button) activity.findViewById(R.id.score14);
		buttons[14] = (Button) activity.findViewById(R.id.score15);
		buttons[15] = (Button) activity.findViewById(R.id.score16);
		buttons[16] = (Button) activity.findViewById(R.id.score17);
		buttons[17] = (Button) activity.findViewById(R.id.score18);
		buttons[18] = (Button) activity.findViewById(R.id.score19);
		buttons[19] = (Button) activity.findViewById(R.id.score20);

		for (int i = 0; i < (buttons.length - 2); i++) {
			ColorDrawable background = (ColorDrawable) buttons[i]
					.getBackground();
			assertEquals(Color.GREEN, background.getColor());
		}

		ColorDrawable background = (ColorDrawable) buttons[18].getBackground();
		assertEquals(Color.BLUE, background.getColor());

		background = (ColorDrawable) buttons[19].getBackground();
		assertEquals(Color.RED, background.getColor());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
