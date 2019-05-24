package Trackpad.relics;

import Trackpad.patches.SpectralField;
import Trackpad.trackpad;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class BiPhaseBiplane extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("BiPhaseBiplane");
    public static final String IMG = trackpad.makePath(trackpad.BIPHASE_BIPLANE);
    public static final String OUTLINE = trackpad.makePath(trackpad.BIPHASE_BIPLANE_OUTLINE);


    public BiPhaseBiplane() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart()
    {
        AbstractCard c = BiPlaneAction(AbstractDungeon.cardRng);
        if(c != null)
        {
            AbstractCard remove = c;
            c = c.makeSameInstanceOf();
            if(!c.purgeOnUse)
            {
                c.purgeOnUse = true;
                c.rawDescription += " NL Purge.";
            }
            if(!SpectralField.spectral.get(c))
            {
                SpectralField.spectral.set(c, true);
                c.rawDescription += " NL trackpad:Spectral.";
            }
            c.initializeDescription();
            if(AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE)
            {
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            }
            else
            {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(c));
            }
            AbstractDungeon.player.exhaustPile.removeCard(remove);
            this.flash();
        }
    }

    private static AbstractCard BiPlaneAction(Random rng) {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator exhaustPileIterator = AbstractDungeon.player.exhaustPile.group.iterator();

        while(exhaustPileIterator.hasNext()) {
            AbstractCard c = (AbstractCard)exhaustPileIterator.next();
            if(c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS) {
                list.add(c);
            }
        }

        if(list.size() <= 0)
        {
            return null;
        }
        else {
            return (AbstractCard) list.get(rng.random(list.size() - 1));
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
        return new BiPhaseBiplane();
    }
}
