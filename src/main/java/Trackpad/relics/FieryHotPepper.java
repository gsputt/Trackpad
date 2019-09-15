package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FieryHotPepper extends CustomRelic {
    // ID, images, text.
    public static final String ID = trackpad.makeID("FieryHotPepper");
    public static final String IMG = trackpad.makePath(trackpad.FIERY_HOT_PEPPER);
    public static final String OUTLINE = trackpad.makePath(trackpad.FIERY_HOT_PEPPER_OUTLINE);

    public FieryHotPepper() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart()
    {
        AbstractCard burnCard = new Burn();
        AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(burnCard, 2, true, true));
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if(drawnCard.cardID.equals(Burn.ID))
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            AbstractDungeon.player.hand.moveToDiscardPile(drawnCard);
            this.flash();
        }
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
