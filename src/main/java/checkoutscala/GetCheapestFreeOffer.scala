package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

case class GetCheapestFreeOffer(payFor: Int, getFree: Int) extends Offer {

    override def calculate(items: Seq[Item]): Price = {
        var totalPrice = 0

        val prices: Seq[Price] = items.map(item => priceOf(item)).sorted.reverse

        var paidCount = 0
        var gotFreeCount = 0

        for (price <- prices) {
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
        }

        totalPrice
    }

}

