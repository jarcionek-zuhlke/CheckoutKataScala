package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}

trait Offer {

    def calculate(items: Seq[Item]): Price

}
