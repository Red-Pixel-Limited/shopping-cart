package shopping.product

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import shopping.support.ProductTestData.{genBlankName, genNegativePrice, genProductName, genProductPrice}

class ProductTest extends AnyFlatSpec
  with ScalaCheckPropertyChecks
  with Matchers:

  "The product" should "not have blank name" in forAll(genBlankName, genProductPrice) { (blankName, price) =>
    Product(blankName, price) should be(Left(ProductError("Product name is required")))
  }

  it should "not have negative price" in forAll(genProductName, genNegativePrice) { (name, invalidPrice) =>
    Product(name, invalidPrice) should be(Left(ProductError("Price must be greater than 0")))
  }

  it should "be valid" in forAll(genProductName, genProductPrice) { (name, price) =>
    Product(name, price).isRight should be(true)
  }
