package androidhive.dashboard.test;

import java.util.Iterator;
import java.util.List;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.PsychometricAnswer;
import com.aptitudeguru.dashboard.tests.TestPsychometric;

public class TestPsychometricUnit extends InstrumentationTestCase {

	
	public TestPsychometricUnit() {
		super();
	}
	
	private DatabaseHandler db;
	private TestPsychometric psycho;
	
	
	protected void setUp() {
		db = new DatabaseHandler(this.getInstrumentation().getTargetContext().getApplicationContext());
		psycho = new TestPsychometric();
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
	
	/*
	 * 
	 * 	Checks if there is questions available
	 * 
	 */
	
	public void testMoreThanOneQuestion() {
		List<PsychometricAnswer> psychometric = db.getAllPsychometric("scenario1");
		assertTrue(psychometric.size() > 0);
	}
	
	public void testCheckIfInputIsCorrect()	{
		assertTrue(psycho.isValidInput("A"));
	}
	
	public void testCheckIfInputIsIncorrect()	{
		assertFalse(psycho.isValidInput("Z"));
	}
	
	public void testCheckIfInpuSpacetIsIncorrect()	{
		assertFalse(psycho.isValidInput(" "));
	}
	
	public void testCheckIfInputCharacterIsIncorrect()	{
		assertFalse(psycho.isValidInput(";"));
	}
	
	public void testCheckIfDuplicateEntries()	{
		assertTrue(psycho.areAnswersDuplicate("A", "A"));
	}
	
	public void testCheckIfDuplicateSymbolEntries()	{
		assertTrue(psycho.areAnswersDuplicate("@", "@"));
	}
	
	
	
	public void testCheckIfNotDuplicateEntries()	{
		assertFalse(psycho.areAnswersDuplicate("A", "B"));
	}

}