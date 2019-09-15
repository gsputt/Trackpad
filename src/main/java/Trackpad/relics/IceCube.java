package Trackpad.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import Trackpad.trackpad;

public class IceCube extends CustomRelic {
    // ID, images, text.
    public static final String ID = trackpad.makeID("IceCube");
    public static final String IMG = trackpad.makePath(trackpad.ICE_CUBE);
    public static final String OUTLINE = trackpad.makePath(trackpad.ICE_CUBE_OUTLINE);

    public static final int DEXTERITY_AMOUNT = 3;

    public IceCube() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, DEXTERITY_AMOUNT), DEXTERITY_AMOUNT));
        this.counter = 3;
    }

    @Override
    public void atTurnStart()
    {
        if(this.counter > 0)
        {
            this.counter--;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseDexterityPower(AbstractDungeon.player, 1)));
        }
    }

    @Override
    public void onVictory()
    {
        this.counter = -1;
    }

    @Override
    public void onEquip()
    {
        this.counter = -1;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new IceCube();
    }
}
