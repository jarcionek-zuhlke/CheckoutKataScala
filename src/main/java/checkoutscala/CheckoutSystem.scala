package checkoutscala

import checkoutscala.OfferProvider.offerFor

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        products.groupBy(offerFor).map({ case (offer, items) => offer.calculate(items)}).sum
    }

}
