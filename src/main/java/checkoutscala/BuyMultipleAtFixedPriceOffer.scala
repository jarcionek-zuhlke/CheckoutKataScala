package checkoutscala

import checkoutscala.CheckoutSystem.{Price, Item}
import checkoutscala.PriceProvider.priceOf

case class BuyMultipleAtFixedPriceOffer(itemsCount: Int, price: Price) extends Offer {

    override def calculate(items: Seq[Item]): Price = {
        items.map(priceOf).sorted.reverse.grouped(itemsCount).map(priceOfGroup).sum
    }

    private def priceOfGroup: Seq[Price] => Int = {
        prices => {
            if (prices.size == itemsCount) {
                price
            } else {
                prices.sum
            }
        }
    }
    
}
