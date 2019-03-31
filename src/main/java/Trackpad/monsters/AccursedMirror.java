package Trackpad.monsters;

import Trackpad.Trackpad;
import Trackpad.powers.ReflectionPower;
import Trackpad.powers.ShatterPower;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AccursedMirror extends AbstractMonster {

    public static final String ID = Trackpad.makeID("AccursedMirror");
    public static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int BlockGain;

    public AccursedMirror() {
        super(NAME, ID, 80, -0.0F, -10.0F, 160f, 300f, null);
        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(100);
        }
        else
        {
            this.setHp(80);
        }
        if (AbstractDungeon.ascensionLevel >= 3) {
            this.BlockGain = 8;
        } else {
            this.BlockGain = 6;
        }
    }
    @Override
    public void usePreBattleAction() {
        GameActionManager manager = AbstractDungeon.actionManager;
        manager.addToBottom(new ApplyPowerAction(this, this, new ShatterPower(this, 1), 1));
        manager.addToBottom(new ApplyPowerAction(this, this, new ReflectionPower(this, 1), 1));
    }

    @Override
    public void takeTurn() {
        switch(this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(
                        new com.megacrit.cardcrawl.actions.common.GainBlockAction(this, this, this.BlockGain));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        this.setMove(MOVES[0], (byte) 1, Intent.DEFEND);
    }

}
