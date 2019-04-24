package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Mousetrap extends CustomRelic implements ClickableRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("Mousetrap");
    public static final String IMG = trackpad.makePath(trackpad.MOUSETRAP);
    public static final String OUTLINE = trackpad.makePath(trackpad.MOUSETRAP_OUTLINE);
    private boolean resetThisCombat = false;


    public Mousetrap() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.owner != AbstractDungeon.player && info.owner != null)
        {
            if(this.counter == -15){
                this.counter = 0;
                this.flash();
                this.stopPulse();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(info.owner, this));
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(info.owner,
                        new DamageInfo(AbstractDungeon.player, 15, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                this.resetThisCombat = true;
            }
        }
        return damageAmount;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        this.resetThisCombat = false;
    }

    @Override
    public void onEquip()
    {
        this.counter = 0;
    }


    @Override
    public void onRightClick()
    {
        if(AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if(!this.resetThisCombat)
            {
                this.flash();
                this.counter -= 1;
                if (this.counter == -15) {
                    this.beginLongPulse();
                } else if (this.counter <= -16) {
                    this.counter = 0;
                    this.flash();
                    this.stopPulse();
                    this.resetThisCombat = true;
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
        return new Mousetrap();
    }
}
