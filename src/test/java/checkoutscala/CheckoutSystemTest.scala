package checkoutscala

import org.scalatest._

class CheckoutSystemTest extends FlatSpec with Matchers {

    val APPLE_PRICE: Int = 60
    val ORANGE_PRICE: Int = 25
    val BANANA_PRICE: Int = 20

    val checkoutSystem = CheckoutSystem

    "single apple" should "cost 60p" in {
        checkoutSystem.checkout("Apple") should be (APPLE_PRICE)
    }

    "single orange" should "cost 25p" in {
        checkoutSystem.checkout("Orange") should be (ORANGE_PRICE)
    }

    "multiple items without offers" should "have total cost equal to sum of their individual prices" in {
        checkoutSystem.checkout("Apple", "Orange", "Orange") should be (APPLE_PRICE + 2 * ORANGE_PRICE)
    }

    "apple" should "be in offer \"buy one, get one free\"" in {
        checkoutSystem.checkout("Apple", "Apple") should be (APPLE_PRICE)
    }

    "orange" should "be in offer \"buy two, get one free\"" in {
        checkoutSystem.checkout("Orange", "Orange", "Orange") should be (2 * ORANGE_PRICE)
    }

    "single banana" should "cost 20p" in {
        checkoutSystem.checkout("Banana") should be (BANANA_PRICE)
    }

}
