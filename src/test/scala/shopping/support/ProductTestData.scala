package shopping.support

import org.scalacheck.{Arbitrary, Gen}
import shopping.product.Product
import shopping.product.Product.{AxeDeo, DoveSoap}
import shopping.support.ProductTestData.genProductPrice

object ProductTestData:

  lazy val genProductPrice: Gen[BigDecimal] =
    Gen.choose(1.0, 1000.0).map(BigDecimal(_))

  lazy val genNegativePrice: Gen[BigDecimal] =
    Gen.negNum[Double].map(BigDecimal(_))

  lazy val genBlankName: Gen[String] =
    Gen.listOf(Gen.oneOf("", " ")).map(_.mkString(""))

  lazy val genProductName: Gen[String] =
    Gen.nonEmptyListOf(Gen.alphaChar).map(_.mkString(""))

  given Arbitrary[Product] = Arbitrary(
    genProductPrice.flatMap { price =>
      Gen.oneOf(DoveSoap(price), AxeDeo(price))
    })

