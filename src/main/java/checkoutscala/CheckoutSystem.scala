package checkoutscala

import checkoutscala.OfferProvider.offerFor

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        val offerMap: Map[Offer, Seq[Item]] = products groupBy (item => offerFor(item))

        offerMap map { case (offer, items) => offer.calculate(items)} sum
    }

}
