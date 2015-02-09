package checkoutscala

object CheckoutSystem {

    type Item = String

    def checkout(product: Item) = {
        product match {
            case "Apple" => 60
            case "Orange" => 25
        }
    }

}
