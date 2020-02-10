package Trackpad.relics;

import Trackpad.actions.StolenKidneysDrawAction;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class StolenKidneys extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("StolenKidneys");
    public static final String IMG = trackpad.makePath(trackpad.STOLEN_KIDNEYS);
    public static final String OUTLINE = trackpad.makePath(trackpad.STOLEN_KIDNEYS_OUTLINE);


    public StolenKidneys() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public void atTurnStartPostDraw()
    {
        AbstractDungeon.actionManager.addToBottom(new StolenKidneysDrawAction(AbstractDungeon.player));
        this.flash();
    }

    @Override
    public void onEquip()
    {
        ArrayList<AbstractCard> removeList = new ArrayList<>();
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group)
        {
            if(c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
            {
                removeList.add(c);
            }
        }
        for(AbstractCard card : removeList)
        {
            AbstractDungeon.player.masterDeck.removeCard(card);
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
        return new StolenKidneys();
    }
}
