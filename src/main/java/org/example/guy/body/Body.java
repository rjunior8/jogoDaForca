package org.example.guy.body;

import org.example.constants.Gibbet;

public enum Body{
    HEAD(Gibbet.HEAD),
    HEAD_AND_RIGHT_ARM(Gibbet.HEAD_AND_RIGHT_ARM),
    HEAD_RIGHT_ARM_AND_LEFT_ARM(Gibbet.HEAD_RIGHT_ARM_AND_LEFT_ARM),
    HEAD_RIGHT_ARM_LEFT_ARM_AND_STEM(Gibbet.HEAD_RIGHT_ARM_LEFT_ARM_AND_STEM),
    HEAD_RIGHT_ARM_LEFT_ARM_STEM_AND_RIGHT_LEG(Gibbet.HEAD_RIGHT_ARM_LEFT_ARM_STEM_AND_RIGHT_LEG),
    HEAD_RIGHT_ARM_LEFT_ARM_STEM_RIGHT_LEG_AND_LEFT_LEG(Gibbet.HEAD_RIGHT_ARM_LEFT_ARM_STEM_RIGHT_LEG_AND_LEFT_LEG);

    private final String phase;
    Body(String phase){
        this.phase = phase;
    }

    public String getPhase(){
        return this.phase;
    }
}
