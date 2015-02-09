package checkoutscala

import org.scalatest._

class CheckoutSystemTest extends FlatSpec with Matchers {

    val checkoutSystem = CheckoutSystem

    "single apple" should "cost 60p" in {
        checkoutSystem.checkout("Apple") should be (60)
    }

    "single orange" should "cost 25p" in {
        checkoutSystem.checkout("Orange") should be (25)
    }

}
