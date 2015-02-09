package checkoutscala

import checkoutscala.CheckoutSystem.Item

object OfferProvider {

    def offerFor(product: Item): Offer = {
        product match {
            case "Apple" => GetCheapestFreeOffer(1, 1)
            case "Banana" => GetCheapestFreeOffer(1, 1)
            case "Orange" => GetCheapestFreeOffer(2, 1)
            case "Expensive Screwdriver" => GetCheapestFreeOffer(3, 2)
            case "Moderate Screwdriver" => GetCheapestFreeOffer(3, 2)
            case "Cheap Screwdriver" => GetCheapestFreeOffer(3, 2)
            case _ => NoOffer
        }
    }

}
