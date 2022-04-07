package shopping

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import shopping.basket.TaxRate._
import shopping.basket.{ShoppingCart, TaxRate}
import shopping.product.Product.{AxeDeo, DoveSoap}

class ShoppingSpec extends AnyFeatureSpec with Matchers with GivenWhenThen:

  Feature("Shopping Cart") {

    info("As a user")
    info("I wish to add products to a shopping cart")
    info("So that I can buy some products")

    Scenario("Add products to the shopping cart") {
      Given("an empty shopping cart")
      val emptyShoppingCart = ShoppingCart.empty

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = DoveSoap(39.99)

      When("the user adds 5 Dove Soaps to the shopping cart")
      val filledShoppingCart = emptyShoppingCart.add(doveSoap, 5)

      Then("the shopping cart should contain Dove Soaps each with a unit price of 39.99")
      filledShoppingCart.get(doveSoap) should be(5)

      And("the shopping cart’s total price should equal 199.95")
      filledShoppingCart.total should be(199.95)
    }

    Scenario("Add additional products of the same type to the shopping cart") {
      Given("an empty shopping cart")
      val emptyShoppingCart = ShoppingCart.empty

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = DoveSoap(39.99)

      When("the user adds 5 Dove Soaps to the shopping cart")
      val shoppingCartWithFiveAddedDoveSoaps = emptyShoppingCart.add(doveSoap, 5)

      And("then adds another 3 Dove Soaps to the shopping cart")
      val shoppingCartWithThreeOtherDoveSoaps = shoppingCartWithFiveAddedDoveSoaps.add(doveSoap, 3)

      Then("the shopping cart should contain 8 Dove Soaps each with a unit price of 39.99")
      shoppingCartWithThreeOtherDoveSoaps.get(doveSoap) should be(8)

      And("the shopping cart’s total price should equal 319.92")
      shoppingCartWithThreeOtherDoveSoaps.total should be(319.92)
    }

    Scenario("Calculate the tax rate of the shopping cart with multiple items") {
      Given("an empty shopping cart")
      val emptyShoppingCart = ShoppingCart.empty

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = DoveSoap(39.99)

      And("another product, Axe Deo with a unit price of 99.99")
      val axeDeo = AxeDeo(99.99)

      And("a tax rate of 12.5%")
      val taxRate: TaxRate = 12.5.percentage

      When("the user adds 2 Dove Soaps to the shopping cart")
      val shoppingCartWithDoveSoaps = emptyShoppingCart.add(doveSoap, 2)

      And("then adds 2 Axe Deo’s to the shopping cart")
      val finalShoppingCart = shoppingCartWithDoveSoaps.add(axeDeo, 2)

      Then("the shopping cart should contain 2 Dove Soaps each with a unit price of 39.99")
      finalShoppingCart.get(doveSoap) should be(2)

      And("the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99")
      finalShoppingCart.get(axeDeo) should be(2)

      And("the total tax amount should equal 35.00")
      taxRate.on(finalShoppingCart) should be(35.0)

      And("the shopping cart’s total price should equal 314.96")
      taxRate.on(finalShoppingCart) + finalShoppingCart.total should be(314.96)
    }
  }
