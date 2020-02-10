package Trackpad.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.srcRareCardPool;

public class CompendiumAction extends AbstractGameAction {


    public CompendiumAction()
    {}
    @Override
    public void update()
    {
        isDone = true;
        ArrayList<AbstractCard> list = new ArrayList<>();
        for(AbstractCard c : srcRareCardPool.group)
        {
            if (!c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        AbstractCard card = (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
        card.modifyCostForCombat(-1);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card, 1, true, true));
    }
}
