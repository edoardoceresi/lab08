package it.unibo.deathnote.api;

public class DeathNoteImpl implements DeathNote{

    @Override
    public String getRule(int ruleNumber) {
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
       
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if("".equals(cause)){
            return false; 
        }
        throw new IllegalArgumentException("boby");
    }

    @Override
    public boolean writeDetails(String details) {
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        return null;
    }

    @Override
    public String getDeathDetails(String name) {
        return null;
    }

    @Override
    public boolean isNameWritten(String name) {
        return false;
    }

    private boolean controlRuleNumber(int ruleNumber){
        return false;
    }
    
}
