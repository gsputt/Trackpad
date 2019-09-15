package Trackpad.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import Trackpad.trackpad;

public class ByrdBGone extends CustomRelic {
    // ID, images, text.
    public static final String ID = trackpad.makeID("ByrdBGone");
    public static final String IMG = trackpad.makePath(trackpad.BYRD_B_GONE);
    public static final String OUTLINE = trackpad.makePath(trackpad.BYRD_B_GONE_OUTLINE);

    public static final int THORNS_AMOUNT = 1;

    public ByrdBGone() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if(info.type == DamageInfo.DamageType.NORMAL)
        {
            if(damageAmount <= 0 || AbstractDungeon.player.hasPower(BufferPower.POWER_ID))
            {
                this.flash();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, THORNS_AMOUNT), THORNS_AMOUNT));
            }
        }
        return damageAmount;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new ByrdBGone();
    }
}
