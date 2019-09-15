package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.FireBreathing;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FieryHotPepper extends CustomRelic {
    // ID, images, text.
    public static final String ID = trackpad.makeID("FieryHotPepper");
    public static final String IMG = trackpad.makePath(trackpad.FIERY_HOT_PEPPER);
    public static final String OUTLINE = trackpad.makePath(trackpad.FIERY_HOT_PEPPER_OUTLINE);

    public FieryHotPepper() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart()
    {
        AbstractCard FireBreathing = new FireBreathing();
        FireBreathing.applyPowers();
        FireBreathing.freeToPlayOnce = true;
        FireBreathing.purgeOnUse = true;
        AbstractDungeon.actionManager.addToBottom(new QueueCardAction(FireBreathing, AbstractDungeon.getRandomMonster()));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new FieryHotPepper();
    }
}
