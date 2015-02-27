package androidhive.dashboard.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.test.InstrumentationTestCase;

import com.aptitudeguru.dashboard.DatabaseHandler;
import com.aptitudeguru.dashboard.MultipleAnswerTable;

public class TestVerbalAndLogic extends InstrumentationTestCase {

	
	public TestVerbalAndLogic() {
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
	
	public void testRandomQuestions() {
		List<MultipleAnswerTable> questions = db.getAllVL("v1");
		List<MultipleAnswerTable> questionsCompare = db.getAllVL("v1");
		int equalElements = 0;
		for (int questionIndex=0; questionIndex < questions.size(); questionIndex++)
		{
			if (questions.get(questionIndex).getQuestion().equals(questionsCompare.get(questionIndex).getQuestion()))
				equalElements++;
		}
		assertFalse(equalElements == questions.size());

	}
	
	public void testTwentyQuestions() {
		for(int category = 1; category <= 7; category++) {
			assertEquals(db.getAllVL("v" + category).size(), 20);
		}
	}
	
	public void testDuplicateQuestions() {
		for(int b = 1; b <= 14; b++) {
			for(int index = 1; index <= 14; index++) {
				List<MultipleAnswerTable> questionWithCategory = db.getAllVL("v" + index);
				Set<String> questionSet = new HashSet<String>();
				for(int questionIndex = 0; questionIndex < questionWithCategory.size(); questionIndex++) {
					MultipleAnswerTable question = questionWithCategory.get(questionIndex);
					questionSet.add(question.getQuestion() + question.getOption1() + question.getOption2() + question.getOption3() + question.getOption4() + question.getSolution());
				}
				assertEquals(questionWithCategory.size(), questionSet.size());
			}
		}
	}
	
	public void testQuestionCategoryMatchesParameter() {
		List<MultipleAnswerTable> quants = db.getAllVL("v1");
		Iterator<MultipleAnswerTable> itr = quants.iterator();
		while(itr.hasNext()) {
			MultipleAnswerTable quantitativeQuestion = itr.next();
			assertTrue(quantitativeQuestion.getCategory().equals("v1"));
		}
	}
	
}