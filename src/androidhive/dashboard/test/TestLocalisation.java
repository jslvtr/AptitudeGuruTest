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
		final String oldUnits = "Here you go, have 10 miles";
		final String newUnits =  LocalisationUtilities.changeDistanceUnitsToMetric(oldUnits);
		assertEquals(newUnits, "Here you go, have 10 km");
	}
	
	public void testChangeDistanceUnitsToImperial(){
		final String oldUnits = "Here you go, have 10 km";
		final String newUnits =  LocalisationUtilities.changeDistanceUnitsToImperial(oldUnits);
		assertEquals(newUnits, "Here you go, have 10 miles");
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
