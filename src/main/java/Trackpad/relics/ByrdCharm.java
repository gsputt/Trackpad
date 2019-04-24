package Trackpad.relics;

import Trackpad.powers.TrackpadFlightPower;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ByrdCharm extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("ByrdCharm");
    public static final String IMG = trackpad.makePath(trackpad.BYRD_CHARM);
    public static final String OUTLINE = trackpad.makePath(trackpad.BYRD_CHARM_OUTLINE);


    public ByrdCharm() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new TrackpadFlightPower(AbstractDungeon.player, 1), 1));
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new ByrdCharm();
    }
}
