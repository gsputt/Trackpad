package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WeightlessShivs extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("WeightlessShivs");
    public static final String IMG = trackpad.makePath(trackpad.WEIGHTLESS_SHIVS);
    public static final String OUTLINE = trackpad.makePath(trackpad.WEIGHTLESS_SHIVS_OUTLINE);


    public WeightlessShivs() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayerEndTurn() {
        for(AbstractCard c: AbstractDungeon.player.hand.group)
        {
            if(c.cardID.equals(Shiv.ID))
            {
                if(!c.retain) {
                    c.retain = true;
                    c.rawDescription += " NL Retain.";
                    c.initializeDescription();
                }
            }
        }
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new WeightlessShivs();
    }
}
