package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GuardianSpirit extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("GuardianSpirit");
    public static final String IMG = trackpad.makePath(trackpad.GUARDIAN_SPIRIT);
    public static final String OUTLINE = trackpad.makePath(trackpad.GUARDIAN_SPIRIT_OUTLINE);


    public GuardianSpirit() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip()
    {
        if(AbstractDungeon.player.hasRelic("Sozu")) {
            AbstractDungeon.player.getRelic("Sozu").flash();
        }
        else
        {
            for (AbstractPotion p : AbstractDungeon.player.potions) {
                if (p instanceof PotionSlot) {
                    AbstractDungeon.player.obtainPotion(PotionHelper.getPotion("FairyPotion"));
                }
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
        return new GuardianSpirit();
    }
}
