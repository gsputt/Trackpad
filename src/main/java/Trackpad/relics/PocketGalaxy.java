package Trackpad.relics;

import Trackpad.relicInterfaces.triggerOnAnyDiscardRelicInterface;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PocketGalaxy extends CustomRelic implements triggerOnAnyDiscardRelicInterface {

    // ID, images, text.
    public static final String ID = trackpad.makeID("PocketGalaxy");
    public static final String IMG = trackpad.makePath(trackpad.POCKET_GALAXY);
    public static final String OUTLINE = trackpad.makePath(trackpad.POCKET_GALAXY_OUTLINE);


    public PocketGalaxy() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onDiscard(AbstractCard c)
    {
        AbstractCard randomCard = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        randomCard.modifyCostForCombat(-1);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(randomCard, 1, true, true));
        this.flash();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new PocketGalaxy();
    }
}
