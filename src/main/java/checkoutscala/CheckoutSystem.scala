package checkoutscala

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        val offerMap: Map[Offer, List[Item]] = createMap(products)

        var totalPrice = 0

        for ((offer: Offer, items: List[Item]) <- offerMap) {
            val prices: List[Price] = items.map(item => priceOf(item))

            var paidCount = 0
            var gotFreeCount = 0

            for (price <- prices) {
                if (paidCount == offer.payFor && gotFreeCount < offer.getFree) {
                    gotFreeCount += 1
                    // get current item for free
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

    private def createMap(products: Seq[Item]): Map[Offer, List[Item]] = {
        var offers: Set[Offer] = Set()

        for (item <- products) {
            offers = offers.+(offerFor(item))
        }

        var offerMap: Map[Offer,List[Item]] = Map()

        for (offer <- offers) {
            var items: List[Item] = List()
            for (item <- products) {
                if (offerFor(item) == offer) {
                    items = items :+ item
                }
            }
            offerMap = offerMap.+((offer, items))
        }

        offerMap
    }
    
    private def priceOf(product: Item): Price = {
        product match {
            case "Apple" => 60
            case "Orange" => 25
        }
    }

    private case class Offer(payFor: Int, getFree: Int)

    private def offerFor(product: Item): Offer = {
        product match {
            case "Apple" => Offer(1, 1)
            case "Orange" => Offer(2, 1)
        }
    }

}
