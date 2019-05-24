package Trackpad.relics;

import Trackpad.powers.LuckyPower;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RiggedDice extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("RiggedDice");
    public static final String IMG = trackpad.makePath(trackpad.RIGGED_DICE);
    public static final String OUTLINE = trackpad.makePath(trackpad.RIGGED_DICE_OUTLINE);


    public RiggedDice() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onManualDiscard()
    {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LuckyPower(AbstractDungeon.player, 1), 1, true));
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new RiggedDice();
    }
}
