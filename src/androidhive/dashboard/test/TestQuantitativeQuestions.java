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
		List<MultipleAnswerTable> questions = db.getRandomizedQuantitativeQuestionsWithCategory("q1");
		List<MultipleAnswerTable> questionsCompare = db.getRandomizedQuantitativeQuestionsWithCategory("q1");
		int equalElements = 0;
		for (int questionIndex=0; questionIndex < questions.size(); questionIndex++)
		{
			if (questions.get(questionIndex).getQuestion().equals(questionsCompare.get(questionIndex).getQuestion()))
				equalElements++;
		}
		assertFalse(equalElements == questions.size());
	}
	
	public void testTwentyQuestions() {
		
	}
	
	public void testDuplicateQuestions() {
		for(int index = 1; index <= 14; index++) {
			List<MultipleAnswerTable> questionWithCategory = db.getQuantitativeTestQuestionsWithCategory("q" + index);
			Set<String> questionSet = new HashSet<String>();
			for(int questionIndex = 0; questionIndex < questionWithCategory.size(); questionIndex++) {
				questionSet.add(questionWithCategory.get(questionIndex).getQuestion());
			}
			assertEquals(questionWithCategory.size(), questionSet.size());
		}
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
