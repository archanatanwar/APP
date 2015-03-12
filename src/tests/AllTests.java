package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGameEngine.class, TestPlayer.class, TestPlayerCards.class,
		TestRegion.class, TestNewGame.class, TestCityAreaCards.class })
public class AllTests {

}
