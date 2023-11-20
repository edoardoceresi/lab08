package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNoteImpl;

class TestDeathNote {

    private DeathNoteImpl testClass = new DeathNoteImpl();

    @BeforeEach
    public void setUp() {
       
    }

    @Test
    public void testNegativesRules(){
        int negativeRule = -1;
        try{
            String ruleNegative = testClass.getRule(negativeRule);
            fail();
        } catch ( IllegalArgumentException e){
            assertEquals("Illegal argument must >0", e.getMessage());
        }
    }

    @Test
    public void testRulesEmpty(){
        String notExpectedValue = "";
        for( String rules : testClass.RULES){
            assertNotNull(rules);
            assertNotEquals( notExpectedValue, rules );
        }
    }

    @Test
    public void testWriteHuman(){
        String testingValue = "Mario";
        assertFalse(testClass.isNameWritten(testingValue));
        testClass.writeName(testingValue);
        assertTrue(testClass.isNameWritten(testingValue));
        String testingValue2 = "Giorgio";
        assertFalse(testClass.isNameWritten(testingValue2));
        assertFalse(testClass.isNameWritten(""));
    }

    @Test
    public void testWriteDeathCause(){
        try {
            testClass.writeDeathCause("infarto");
            
        } catch (IllegalArgumentException e) {
            assertEquals("You're writing a cause of death before writing a name", e.getMessage());
        }
    }


}