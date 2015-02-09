package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

case class GetCheapestFreeOffer(payFor: Int, getFree: Int) extends Offer {

    override def calculate(items: Seq[Item]): Price = {
        items.map(priceOf).sorted.reverse.grouped(payFor + getFree).map(_.take(payFor).sum).sum
    }

}

