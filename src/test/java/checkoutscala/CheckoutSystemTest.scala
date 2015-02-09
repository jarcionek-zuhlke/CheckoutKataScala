package checkoutscala

import org.scalatest._

class CheckoutSystemTest extends FlatSpec with Matchers {

    val APPLE_PRICE: Int = 60
    val ORANGE_PRICE: Int = 25
    val BANANA_PRICE: Int = 20
    val PINEAPPLE_PRICE: Int = 100

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

    "apple and banana" should "share the offer \"buy one, get cheapest free\"" in {
        checkoutSystem.checkout("Banana", "Apple") should be (APPLE_PRICE)
    }

    "multiple items with different prices in the same offer" should "give the cheapest possible price" in {
        checkoutSystem.checkout("Apple", "Apple", "Apple", "Banana", "Banana", "Banana") should be (2 * APPLE_PRICE + BANANA_PRICE)
    }

    "3 pineapples without offers" should "cost £3.00" in {
        checkoutSystem.checkout("Pineapple", "Pineapple", "Pineapple") should be (3 * PINEAPPLE_PRICE)
    }

    "multiple different items in offer \"buy 3, get 2 free\"" should "work" in {
        val a = "Expensive Screwdriver"
        val b = "Moderate Screwdriver"
        val c = "Cheap Screwdriver"

        val ap = 1000
        val bp = 500
        val cp = 100

        checkoutSystem.checkout(a, b, c) should be (ap + bp + cp)
        checkoutSystem.checkout(a, a, a, b, c) should be (3 * ap)
        checkoutSystem.checkout(a, a, b, b, c, c) should be (ap + ap + bp + cp)
        checkoutSystem.checkout(a, a, a, a, b, b, b, b, c, c, c, c) should be (3 * ap + 3 * bp + 2 * cp)
    }

    "single mango" should "cost 37p" in {
        checkoutSystem.checkout("Mango") should be (37)
    }

}
