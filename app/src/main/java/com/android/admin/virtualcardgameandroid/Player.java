package com.android.admin.virtualcardgameandroid;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by admin1 on 03-10-2017.
 */

public class Player {

    ArrayList cards;
    int numOfPlayers,numOfCards;

    public Player(int numOfPlayers, int numOfCards) {
        this.numOfPlayers = numOfPlayers;
        this.numOfCards = numOfCards;
        cards = new ArrayList();
    }

    void setList()
    {
        for(int i=0;i<51;i++)
        {
            cards.add(i);
        }
        Collections.shuffle(cards);
    }

    void getCards(ArrayList cards)
    {
        this.cards = cards;
    }

    ArrayList distributeCards()
    {
        ArrayList temp = new ArrayList();
        for(int i=0;i<numOfCards;i++)
        {
            temp.add(this.cards.get(i));
            this.cards.remove(i);
        }
        return temp;
    }

    
}
