package checkoutscala

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        val offerMap: Map[Offer, Seq[Item]] = products groupBy(item => offerFor(item))

        var totalPrice = 0

        for ((offer: Offer, items: Seq[Item]) <- offerMap) {
            val prices: Seq[Price] = items.map(item => priceOf(item)).sorted.reverse

            var paidCount = 0
            var gotFreeCount = 0

            for (price <- prices) {
                if (paidCount == offer.payFor && gotFreeCount < offer.getFree) { // get current item (price) for free
                    gotFreeCount += 1
                    if (gotFreeCount == offer.getFree) {
                        paidCount = 0
                        gotFreeCount = 0
                    }
                } else {
                    totalPrice += price
                    paidCount += 1
                }
            }
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

    private case class Offer(payFor: Int, getFree: Int)

    private def offerFor(product: Item): Offer = {
        product match {
            case "Apple" => Offer(1, 1)
            case "Banana" => Offer(1, 1)
            case "Orange" => Offer(2, 1)
            case _ => Offer(1, 0)
        }
    }

}
