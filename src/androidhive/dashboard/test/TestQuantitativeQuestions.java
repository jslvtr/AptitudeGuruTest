package androidhive.dashboard.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.test.InstrumentationTestCase;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.MultipleAnswerTable;

public class TestQuantitativeQuestions extends InstrumentationTestCase {
	
	public TestQuantitativeQuestions() {
		super();
	}
	
	private DatabaseHandler db;

	protected void setUp() {
		db = new DatabaseHandler(this.getInstrumentation().getTargetContext().getApplicationContext());
	}
	
	public void testRandomQuestions() {
		
	}
	
	public void testTwentyQuestions() {
		
	}
	
	public void testDuplicateQuestions() {
		List<MultipleAnswerTable> questionWithCategory = db.getQuantitativeTestQuestionsWithCategory("q1");
		Set<MultipleAnswerTable> questionSet = new HashSet<MultipleAnswerTable>(questionWithCategory);
		assertEquals(questionWithCategory.size(), questionSet.size());
	}
	
	public void testQuestionCategoryMatchesParameter() {
		List<MultipleAnswerTable> quants = db.getQuantitativeTestQuestionsWithCategory("q1");
		Iterator<MultipleAnswerTable> itr = quants.iterator();
		while(itr.hasNext()) {
			MultipleAnswerTable quantitativeQuestion = itr.next();
			assertTrue(quantitativeQuestion.getCategory().equals("q1"));
		}
	}
}
