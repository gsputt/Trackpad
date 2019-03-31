package Trackpad.monsters;

import Trackpad.Trackpad;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;

public class Reflection extends AbstractMonster {

    public static final String ID = Trackpad.makeID("Reflection");
    public static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    public static final String IMG = Trackpad.makePath(Trackpad.REFLECTION);
    public static int count = 0;


    public Reflection(int damageTaken) {
        super(NAME, ID, 5, 0.0F, 0.0F, 145f, 145f, IMG, count * -200F, 400F);
        this.setHp(5);
        this.damage.add(new DamageInfo(this, damageTaken));
        count++;
        this.setMove(MOVES[0], (byte) 1, Intent.ATTACK, this.damage.get(0).base);
        this.createIntent();
    }


    @Override
    public void takeTurn() {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    protected void getMove(int num) {
        this.setMove(MOVES[0], (byte) 1, Intent.ATTACK, this.damage.get(0).base);
    }

    @Override
    public void die() {
        this.die(true);
        count--;

        AbstractMonster targetMonster;
        int i = 0;
        while(i < AbstractDungeon.getCurrRoom().monsters.monsters.size())
        {
            targetMonster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if(targetMonster.id.equals(AccursedMirror.ID))

            {AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(targetMonster, targetMonster, new ThornsPower(targetMonster, 1), 1));
            }
            i++;
        }
    }
}
