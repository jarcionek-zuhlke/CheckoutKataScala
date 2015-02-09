package checkoutscala

import org.scalatest._

class CheckoutSystemTest extends FlatSpec with Matchers {

    val checkoutSystem = CheckoutSystem

    "single apple" should "cost 60p" in {
        checkoutSystem.checkout("Apple") should be (60)
    }

}
