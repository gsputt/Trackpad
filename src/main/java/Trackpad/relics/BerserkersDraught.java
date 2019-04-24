package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BerserkersDraught extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("BerserkersDraught");
    public static final String IMG = trackpad.makePath(trackpad.BERSERKERS_DRAUGHT);
    public static final String OUTLINE = trackpad.makePath(trackpad.BERSERKERS_DRAUGHT_OUTLINE);


    public BerserkersDraught() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new StrengthPower(AbstractDungeon.player, this.counter), this.counter));

    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(c.type == AbstractCard.CardType.ATTACK)
        {
            this.counter++;
        }
        else if(c.type == AbstractCard.CardType.SKILL)
        {
            this.counter = 0;
        }
    }

    @Override
    public void onEquip()
    {
        this.counter = 0;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new BerserkersDraught();
    }
}
