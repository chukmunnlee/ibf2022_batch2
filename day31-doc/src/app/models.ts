export interface Card {
  imageIdx: number
  suit: string
  name: string
}

const SUIT = [
  "clover", "diamond", "heart", "spade"
]

const NAME = [
  "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
  "Eight", "Nine", "Ten", "Jack", "Queen", "King"
]

export class DeckOfCards {

  deck: Card[] = []

  constructor() {

    for (let s = 0; s < SUIT.length; s++) {
      for (let i = 0; i < NAME.length; i++)
        this.deck.push({
          imageIdx: i + 1,
          suit: SUIT[s],
          name: NAME[i]
        })
    }
  }

  shuffle() {
    for (let i = 0; i < this.deck.length; i++) {
      let curr = this.deck[i]
      let toSwap = this.randNum(this.deck.length)
      this.deck[i] = this.deck[toSwap]
      this.deck[toSwap] = curr
    }
  }

  take(): Card | undefined {
    //return this.deck.splice(0, 1)[0]
    return this.deck.pop()
  }

  private randNum(size = 52): number {
    return Math.floor((Math.random() * size) + 1)
  }
}
