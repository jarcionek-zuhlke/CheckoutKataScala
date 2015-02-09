package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

object NoOffer extends Offer {

    override def calculate(items: Seq[Item]): Price = items map (item => priceOf(item)) sum

}