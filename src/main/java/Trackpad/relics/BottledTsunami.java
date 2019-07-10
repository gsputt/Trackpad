package Trackpad.relics;

import Trackpad.patches.BottledTsunamiField;
import Trackpad.trackpad;
import basemod.BaseMod;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.function.Predicate;

public class BottledTsunami extends CustomRelic implements CustomBottleRelic, CustomSavable<Integer> {
    // ID, images, text.
    public static final String ID = trackpad.makeID("BottledTsunami");
    public static final String IMG = trackpad.makePath(trackpad.BOTTLED_TSUNAMI);
    public static final String OUTLINE = trackpad.makePath(trackpad.BOTTLED_TSUNAMI_OUTLINE);

    private boolean cardSelected = true;
    private AbstractCard card = null;


    public BottledTsunami() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction useCardAction) {
        if(BottledTsunamiField.inBottledTsunami.get(c))
        {
            this.flash();
            for(int i = 0; i < 2; i++)
            {
                AbstractCard card = c.makeStatEquivalentCopy();
                BottledTsunamiField.inBottledTsunami.set(card, false);
                if (card.exhaust == false) {
                    card.exhaust = true;
                    card.rawDescription += " NL Exhaust.";
                }
                if (card.isEthereal == false) {
                    card.isEthereal = true;
                    card.rawDescription += " NL Ethereal.";
                }
                card.initializeDescription();
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card));
                }
                else
                {
                    AbstractDungeon.player.createHandIsFullDialog();
                }
            }
        }
    }

    @Override
    public Predicate<AbstractCard> isOnCard()
    {
        return BottledTsunamiField.inBottledTsunami::get;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    public AbstractCard getCard()
    {
        return card.makeCopy();
    }

    @Override
    public Integer onSave()
    {
        return AbstractDungeon.player.masterDeck.group.indexOf(card);
    }

    @Override
    public void onLoad(Integer cardIndex)
    {
        if (cardIndex == null) {
            return;
        }
        if (cardIndex >= 0 && cardIndex < AbstractDungeon.player.masterDeck.group.size()) {
            card = AbstractDungeon.player.masterDeck.group.get(cardIndex);
            if (card != null) {
                BottledTsunamiField.inBottledTsunami.set(card, true);
                setDescriptionAfterLoading();
            }
        }
    }

    @Override
    public void onEquip()
    {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck),
                1, DESCRIPTIONS[1] + name + ".",
                false, false, false, false);
    }

    @Override
    public void onUnequip()
    {
        if (card != null) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(card);
            if (cardInDeck != null) {
                BottledTsunamiField.inBottledTsunami.set(cardInDeck, false);
            }
        }
    }

    @Override
    public void update()
    {
        super.update();

        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            BottledTsunamiField.inBottledTsunami.set(card, true);
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }

    private void setDescriptionAfterLoading()
    {
        description = FontHelper.colorString(card.name, "y") + DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new BottledTsunami();
    }

}
