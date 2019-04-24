package Trackpad.relics;

import Trackpad.relicInterfaces.removeOrEvokeOrbRelicHook;
import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IciclePrecepts extends CustomRelic implements OnChannelRelic, removeOrEvokeOrbRelicHook {

    // ID, images, text.
    public static final String ID = trackpad.makeID("IciclePrecepts");
    public static final String IMG = trackpad.makePath(trackpad.ICICLE_PRECEPTS);
    public static final String OUTLINE = trackpad.makePath(trackpad.ICICLE_PRECEPTS_OUTLINE);


    public IciclePrecepts() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onChannel(AbstractOrb orb){
        if(orb instanceof Frost)
        {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new ThornsPower(AbstractDungeon.player, 1), 1));
        }
    }

    @Override
    public void onRemoveOrEvokeOrb(AbstractOrb orb)
    {
        if(orb instanceof Frost)
        {
            if(AbstractDungeon.player.hasPower(ThornsPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, ThornsPower.POWER_ID, 1));
            }
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
        return new IciclePrecepts();
    }
}
