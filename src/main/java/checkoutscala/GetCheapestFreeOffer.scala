package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

case class GetCheapestFreeOffer(payFor: Int, getFree: Int) extends Offer {

    override def calculate(items: Seq[Item]): Price = {
        val prices: Seq[Price] = items.map(item => priceOf(item)).sorted.reverse

        prices.foldLeft(new Result)((result, nextPrice) => result.update(nextPrice)).totalPrice
    }

    private class Result {

        var paidCount = 0
        var gotFreeCount = 0
        var totalPrice = 0

        def update(price: Price): Result = {
            if (paidCount == payFor) {
                // get current item (price) for free
                gotFreeCount += 1
                if (gotFreeCount == getFree) {
                    paidCount = 0
                    gotFreeCount = 0
                }
            } else {
                totalPrice += price
                paidCount += 1
            }
            this
        }

    }

}

