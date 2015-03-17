package androidhive.dashboard.test;

import java.util.Iterator;
import java.util.List;

import android.test.InstrumentationTestCase;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.PsychometricAnswer;

public class TestPsychometric extends InstrumentationTestCase {

	
	public TestPsychometric() {
		super();
	}
	
	private DatabaseHandler db;
	
	
	protected void setUp() {
		db = new DatabaseHandler(this.getInstrumentation().getTargetContext().getApplicationContext());
	}
	
	
	/*
	 * 	Checks for random questions
	 * 
	 */
	
	public void testQuestionCategoryMatchesParameter() {
		List<PsychometricAnswer> psychometric = db.getAllPsychometric("scenario1");
		Iterator<PsychometricAnswer> itr = psychometric.iterator();
		while(itr.hasNext()) {
			PsychometricAnswer psychometricQuestion = itr.next();
			assertTrue(psychometricQuestion.getCategory().equals("scenario1"));
		}
	}
	
	public void testMoreThanOneQuestion() {
		List<PsychometricAnswer> psychometric = db.getAllPsychometric("scenario1");
		assertTrue(psychometric.size() > 0);
	}
	
}