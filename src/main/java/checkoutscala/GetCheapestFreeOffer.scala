package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

case class GetCheapestFreeOffer(payFor: Int, getFree: Int) extends Offer {

    override def calculate(items: Seq[Item]): Price = {
        val prices: Seq[Price] = items.map(item => priceOf(item)).sorted.reverse

        val initialResult: Result = Paying(0, payFor)

        prices.foldLeft(initialResult)((result, nextPrice) => result.update(nextPrice)).totalPrice
    }

    private trait Result {
        val totalPrice: Price
        def update(price: Price): Result
    }

    private case class Paying(totalPrice: Price, remaining: Int) extends Result {
        override def update(price: Price): Result = {
            if (remaining == 1) {
                GettingFree(totalPrice + price, getFree)
            } else {
                Paying(totalPrice + price, remaining - 1)
            }
        }
    }

    private case class GettingFree(totalPrice: Price, remaining: Int) extends Result {
        override def update(price: Price): Result = {
            if (remaining == 1) {
                Paying(totalPrice, payFor)
            } else {
                GettingFree(totalPrice, remaining - 1)
            }
        }
    }

}

