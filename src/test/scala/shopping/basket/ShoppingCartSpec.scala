package shopping.basket

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import shopping.product.Product
import shopping.support.ProductTestData.given

class ShoppingCartSpec extends AnyWordSpec
  with ScalaCheckPropertyChecks
  with Matchers:

  "The shopping cart" should {
    "return 0" when {
      "do not contain requested item" in forAll { (product: Product) =>
        ShoppingCart.empty.get(product) should be(0)
      }
    }
  }

  "return count" when {
    "requested product found" in forAll { (product: Product, n: Int) =>
      ShoppingCart.empty
        .add(product, n)
        .get(product) should be(n)
    }
  }
