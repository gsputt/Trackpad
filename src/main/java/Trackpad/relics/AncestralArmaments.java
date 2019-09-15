package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AncestralArmaments extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("AncestralArmaments");
    public static final String IMG = trackpad.makePath(trackpad.ANCESTRAL_ARMAMENTS);
    public static final String OUTLINE = trackpad.makePath(trackpad.ANCESTRAL_ARMAMENTS_OUTLINE);


    public AncestralArmaments() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.CLINK);
    }
    //Go look in AncestralArmamentsPatch for the actual code for this relic.

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new AncestralArmaments();
    }
}
