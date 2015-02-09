package checkoutscala

import scala.language.postfixOps

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        val offerMap: Map[Offer, Seq[Item]] = products groupBy(item => offerFor(item))

        var totalPrice = 0

        for ((offer: Offer, items: Seq[Item]) <- offerMap) {
            totalPrice += offer.calculate(items)
        }

        totalPrice
    }
    
    private def priceOf(product: Item): Price = {
        product match {
            case "Apple" => 60
            case "Orange" => 25
            case "Banana" => 20
            case "Pineapple" => 100
        }
    }

    private trait Offer {
        def calculate(items: Seq[Item]): Price
    }

    private case class GetCheapestFreeOffer(payFor: Int, getFree: Int) extends Offer {
        override def calculate(items: Seq[Item]): Price = {
            var totalPrice = 0

            val prices: Seq[Price] = items.map(item => priceOf(item)).sorted.reverse

            var paidCount = 0
            var gotFreeCount = 0

            for (price <- prices) {
                if (paidCount == payFor && gotFreeCount < getFree) { // get current item (price) for free
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

    private object NoOffer extends Offer {
        override def calculate(items: Seq[Item]): Price = items map(item => priceOf(item)) sum
    }

    private def offerFor(product: Item): Offer = {
        product match {
            case "Apple" => GetCheapestFreeOffer(1, 1)
            case "Banana" => GetCheapestFreeOffer(1, 1)
            case "Orange" => GetCheapestFreeOffer(2, 1)
            case _ => NoOffer
        }
    }

}
