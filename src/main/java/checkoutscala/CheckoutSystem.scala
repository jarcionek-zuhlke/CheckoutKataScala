package checkoutscala

object CheckoutSystem {

    type Item = String
    type Price = Int

    def checkout(products: Item*) = {
        var totalPrice = 0
        for (item <- products) {
            totalPrice += priceOf(item)
        }
        totalPrice
    }

    private def priceOf(product: Item): Price = {
        product match {
            case "Apple" => 60
            case "Orange" => 25
        }
    }

}
