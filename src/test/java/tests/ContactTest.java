package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;

@RunWith(Karate.class)
@KarateOptions(features = { "classpath:tests/Contacts.feature" })
public class ContactTest {
  public static void main(String[] args) {
    JUnitCore.main(ContactTest.class.getCanonicalName());
  }
}
