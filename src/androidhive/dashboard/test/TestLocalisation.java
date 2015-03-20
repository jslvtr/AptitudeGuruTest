package androidhive.dashboard.test;

import java.util.Locale;

import com.aptitudeguru.dashboard.LocalisationUtilities;

import android.test.InstrumentationTestCase;

public class TestLocalisation extends InstrumentationTestCase {

	public TestLocalisation() {
		super();
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	public void testChangeCurrencyOfString() {
		final String input = "Here you go, have Rs100.";
		final String changedCurrency = LocalisationUtilities.changeCurrency(input, "$");
		final String changedCurrencyFromDefaultLocale = LocalisationUtilities.localiseCurrencyStringForLocale(input, Locale.US);

		assertEquals("Here you go, have $100.", changedCurrency);
		assertFalse(changedCurrencyFromDefaultLocale.contains("Rs"));
	}
	
	public void testCurrencyFromSettings() {
		final Locale USLocale = Locale.US;
		final String currencyForUser = LocalisationUtilities.getCurrencyFromLocale(USLocale);
		assertEquals(currencyForUser, "$");
	}
	
	public void testStringContainsRupees() {
		final String RsString = "Here you go, have Rs100";
		final String rupeesString = "Here you go, have 100 rupees";
		final String RupeesString = "Here you go, have 100 Rupees";
		final String rupeeString = "Here you go, have 1 rupee";
		

		assertTrue(LocalisationUtilities.stringHasRupees(rupeesString));
		assertTrue(LocalisationUtilities.stringHasRupees(RsString));
		assertTrue(LocalisationUtilities.stringHasRupees(RupeesString));
		assertTrue(LocalisationUtilities.stringHasRupees(rupeeString));
		assertFalse(LocalisationUtilities.stringHasRupees("Here you go, have $100"));
	}
	
	public void testChangeDistanceUnitsToMetric(){
		final String miles = "Here you go, have 10 miles";
		final String mph = "Here you go, have 10 mph mphate";
		final String mileshr = "Here you go, have 10 miles/hr pal";
		final String milesh = "Here you go, have 10 miles/h son";
		final String milesFullStopHr = "Here you go, have 10 miles.hr son";
		
		final String milesNewUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(miles);
		final String mphNewUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(mph);
		final String mileshrNewUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(mileshr);
		final String mileshNewUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(milesh);
		final String milesFullStopHrNewUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(milesFullStopHr);
		
		assertEquals(milesNewUnits, "Here you go, have 10 km");
		assertEquals(mphNewUnits, "Here you go, have 10 km/hr mphate");
		assertEquals(mileshrNewUnits, "Here you go, have 10 km/hr pal");
		assertEquals(mileshNewUnits, "Here you go, have 10 km/hr son");
		assertEquals(milesFullStopHrNewUnits, "Here you go, have 10 km/hr son");
	}
	
	public void testChangeDistanceUnitsToImperial(){
		final String oldUnits = "Here you go, have 10 km";
		final String kmph = "Here you go, have 10 kmph kmphate";
		final String kmphr = "Here you go, have 10 km/hr pal";
		final String kmh = "Here you go, have 10 km/h son";
		final String kilometres = "Here you go, have 10 kilometres ok";
		final String kilometers = "Here you go, have 10 kilometers.";
		
		final String newUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(oldUnits);
		final String kmphNewUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(kmph);
		final String kmphrNewUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(kmphr);
		final String kmhNewUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(kmh);
		final String kilometresNewUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(kilometres);
		final String kilometersNewUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(kilometers);
		
		assertEquals(newUnits, "Here you go, have 10 miles");
		assertEquals(kmphNewUnits, "Here you go, have 10 miles/hr kmphate");
		assertEquals(kmphrNewUnits, "Here you go, have 10 miles/hr pal");
		assertEquals(kmhNewUnits, "Here you go, have 10 miles/hr son");
		assertEquals(kilometresNewUnits, "Here you go, have 10 miles ok");
		assertEquals(kilometersNewUnits, "Here you go, have 10 miles.");
	}
	
	public void testChangeDistanceUnitsForCountryCode(){
		final String oldImperialUnits = "Here you go, have 10 miles";
		final String newMetricUnits =  LocalisationUtilities.changeDistanceUnitsForCountryCode(oldImperialUnits, "USSR");
		
		final String oldMetricUnits = "Here you go, have 10 km";
		final String newImperialUnits =  LocalisationUtilities.changeDistanceUnitsForCountryCode(oldMetricUnits, "US");

		assertEquals(newMetricUnits, "Here you go, have 10 km");
		assertEquals(newImperialUnits, "Here you go, have 10 miles");
	}
	
	public void testStringNeedsDistanceLocalisation() {
		assertTrue(LocalisationUtilities.stringNeedsDistanceLocalisationForCountryCode("Here you go, have 10 miles", "USSR"));
		assertFalse(LocalisationUtilities.stringNeedsDistanceLocalisationForCountryCode("Here you go, have 10 km", "USSR"));
		assertTrue(LocalisationUtilities.stringNeedsDistanceLocalisationForCountryCode("Here you go, have 10 km", "US"));
		assertFalse(LocalisationUtilities.stringNeedsDistanceLocalisationForCountryCode("Here you go, have 10 miles", "US"));
	}
	
	public void testCountryUsesImperial(){
		assertTrue(LocalisationUtilities.countryUsesImperial("US"));
		assertFalse(LocalisationUtilities.countryUsesImperial("USSR"));	
	}
	
	public void testLocaliseDistanceAndCurrencyStringForCountryCode() {
		final String oldUnits = "Here you go km, have Rs100";
		assertEquals("Here you go miles, have $100", LocalisationUtilities.localiseString(oldUnits, Locale.US));
	}
}
