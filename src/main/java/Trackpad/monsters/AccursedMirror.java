package Trackpad.monsters;

import Trackpad.Trackpad;
import Trackpad.powers.ReflectionPower;
import Trackpad.powers.ShatterPower;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class AccursedMirror extends AbstractMonster {

    public static final String ID = Trackpad.makeID("AccursedMirror");
    public static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    public static final String IMG = Trackpad.makePath(Trackpad.ACCURSED_MIRROR);

    private int turnCount = 0;

    private int BlockGain;

    public AccursedMirror() {
        super(NAME, ID, 80, 0.0F, 0.0F, 205f, 250f, IMG);
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
        Reflection.count = 0;
        turnCount = 0;
    }
    @Override
    public void usePreBattleAction() {
        GameActionManager manager = AbstractDungeon.actionManager;
        manager.addToBottom(new ApplyPowerAction(this, this, new ShatterPower(this, 1), 1));
        manager.addToBottom(new ApplyPowerAction(this, this, new ReflectionPower(this, 1), 1));
    }

    @Override
    public void takeTurn() {
        turnCount++;
        switch(this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(
                        new com.megacrit.cardcrawl.actions.common.GainBlockAction(this, this, this.BlockGain));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Pain(), 1, true, true));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if(turnCount%3 == 0 && turnCount != 0)
        {
            this.setMove(MOVES[1], (byte) 2, Intent.STRONG_DEBUFF);
        }
        else
        {
            this.setMove(MOVES[0], (byte) 1, Intent.DEFEND);
        }
    }

}
