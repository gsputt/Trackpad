package Trackpad.actions;

import Trackpad.powers.DissipatingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SpawnDissipatingMonsterAction extends AbstractGameAction {
    private boolean used;
    private static final float DURATION = 0.1F;
    private AbstractMonster m;
    private boolean minion;
    private int targetSlot;
    private boolean dissipating;

    public SpawnDissipatingMonsterAction(AbstractMonster m, boolean isMinion, boolean isDissipating) {
        this(m, isMinion, isDissipating, -99);
    }

    public SpawnDissipatingMonsterAction(AbstractMonster m, boolean isMinion, boolean isDissipating, int slot) {
        this.used = false;
        this.actionType = ActionType.SPECIAL;
        this.duration = 0.1F;
        this.m = m;
        this.minion = isMinion;
        this.targetSlot = slot;
        this.dissipating = isDissipating;
        if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
            m.addPower(new StrengthPower(m, 1));
            AbstractDungeon.onModifyPower();
        }

    }

    public void update() {
        if (!this.used) {
            this.m.init();
            this.m.applyPowers();
            if (this.targetSlot < 0) {
                AbstractDungeon.getCurrRoom().monsters.addSpawnedMonster(this.m);
            } else {
                AbstractDungeon.getCurrRoom().monsters.addMonster(this.targetSlot, this.m);
            }

            this.m.showHealthBar();
            if (ModHelper.isModEnabled("Lethality")) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new StrengthPower(this.m, 3), 3));
            }

            if (ModHelper.isModEnabled("Time Dilation")) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new SlowPower(this.m, 0)));
            }

            if (this.minion) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
            }

            if(this.dissipating)
            {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.m, this.m, new DissipatingPower(this.m)));
            }
            this.used = true;
        }

        this.tickDuration();
    }
}
