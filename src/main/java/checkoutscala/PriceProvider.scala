package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}

object PriceProvider {

    def priceOf(product: Item): Price = {
        product match {
            case "Apple" => 60
            case "Orange" => 25
            case "Banana" => 20
            case "Pineapple" => 100
        }
    }

}
