package Trackpad.relics;

import Trackpad.powers.TrackpadFlightPower;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MiracleGrow extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("MiracleGrow");
    public static final String IMG = trackpad.makePath(trackpad.MIRACLE_GROW);
    public static final String OUTLINE = trackpad.makePath(trackpad.MIRACLE_GROW_OUTLINE);


    public MiracleGrow() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayerEndTurn() {
        for(AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof Dark) {
                for (int i = 0; i < AbstractDungeon.player.orbs.indexOf(o); i++) {
                    o.onEndOfTurn();
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
        return new MiracleGrow();
    }
}
